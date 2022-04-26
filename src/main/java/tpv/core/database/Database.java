package tpv.core.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import lombok.Setter;
import tpv.bros.common.mapper.EntityMapper;
import tpv.bros.common.security.CurrentUserInfo;
import tpv.bros.common.table.Entity;
import tpv.core.Entities;
import tpv.core.Entities.FieldInfo;
import tpv.core.Entities.TableInfo;
import tpv.core.define.enm.ColumnType;
import tpv.core.query.QueryPrepareStatement;

public class Database {
	@Setter static JdbcTemplate template;

	/**
	 * @param <T>
	 * @param record
	 */
	public static <T extends Entity> void insert(T record) {
		if (record.getId() != null && record.getId().isEmpty() == false)
			throw new DatabaseException();

		AtomicLong current = new AtomicLong(new Date().getTime());
		TableInfo table = Entities.tblInfo(record.getClass());
		String recordId = generateId(table.getPrefix(), current);
		Operator preparation = new Operator(OperatorType.insert, record, recordId, table);
		template.update(preparation.sql(), preparation.params());
		record.setId(recordId);
	}

	/**
	 * @param <T>
	 * @param record
	 */
	public static <T extends Entity> void update(T record) {
		if (record.getId() == null || record.getId().isEmpty())
			throw new DatabaseException();
		TableInfo table = Entities.tblInfo(record.getClass());
		String recordId = record.getId();
		Operator preparation = new Operator(OperatorType.update, record, recordId, table);
		template.update(preparation.sql(), preparation.params());
	}

	/**
	 * @param <T>
	 * @param record
	 */
	public static <T extends Entity> void delete(T record) {
		if (record.getId() == null || record.getId().isEmpty())
			throw new DatabaseException();
		TableInfo table = Entities.tblInfo(record.getClass());
		String recordId = record.getId();
		Operator preparation = new Operator(OperatorType.delete, record, recordId, table);
		template.update(preparation.sql(), preparation.params());
	}

	public static <T extends Entity> List<T> query2List(String sql, RowMapper<T> rowMapper) {
		return template.query(sql, rowMapper);
	}

	public static <T extends Entity> List<T> query2List(String sql, QueryPrepareStatement ps, RowMapper<T> rowMapper) {
		return template.query(sql, ps, rowMapper);
	}

	public static <T extends Entity> Stream<T> query2Stream(String sql, Map<String, Object> parameters) {
		return Stream.of();
	}

	public static <T extends Entity> Map<String, T> query2Map(String sql, Map<String, Object> parameters) {
		return Map.of();
	}

	public static Savepoint makeSavePoint() {
		try {
			return connection().setSavepoint();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public static void rollback() {
		rollback(null);
	}

	public static void rollback(Savepoint savepoint) {
		try {
			if (savepoint == null)
				connection().rollback();
			else
				connection().rollback(savepoint);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public static Connection connection() throws SQLException {
		return template.getDataSource().getConnection();
	}

	/**
	 * @param prefix
	 * @param current
	 * @return
	 */
	static String generateId(String prefix, AtomicLong current) {
		String pad = "000000000000000";
		String groupTime = pad + current.getAndIncrement();
		groupTime = groupTime.substring(groupTime.length() - pad.length());
		return prefix 
				+ "B1" 
				+ RandomStringUtils.randomAlphabetic(10)
				+ groupTime
		;
	}

	private static class Operator {
		OperatorType sqlType;
		String tableName;
		List<String> columns = new ArrayList<String>();
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();

		@SuppressWarnings("unchecked")
		public <T extends Entity> Operator(OperatorType uType, T record, String recordId, TableInfo table) {
			this.sqlType = uType;
			this.tableName = table.getName();
			String actor = CurrentUserInfo.getCreatedBy();
			if (sqlType == OperatorType.insert) {
				columns.add(EntityMapper.COLUMN___ID);             params.add("?");     values.add(recordId);
				columns.add(EntityMapper.COLUMN___CREATED_BY);     params.add("?");     values.add(actor);
				columns.add(EntityMapper.COLUMN___CREATED_DATE);   params.add("NOW()");
			}

			if (sqlType == OperatorType.insert || sqlType == OperatorType.update) {
				columns.add(EntityMapper.COLUMN___UPDATED_BY);     params.add("?");     values.add(actor);
				columns.add(EntityMapper.COLUMN___UPDATED_DATE);   params.add("NOW()");

				PropertyAccessor accessor = PropertyAccessorFactory.forBeanPropertyAccess(record);
				for (FieldInfo field: table.databaseFields.values()) {
					ColumnType type = field.getColumn().type();
					String databaseField = field.getDatabaseField();
					String declaredField = field.getDeclaredField();
					if (record.updatedFields.contains(declaredField)) {
						columns.add(databaseField);
						params.add("?");

						Object fieldValue = accessor.getPropertyValue(declaredField);
						if (fieldValue != null) {

							if (type == ColumnType.DATE || type == ColumnType.DATE_TIME) {
								LocalDate date = (LocalDate) fieldValue;
								fieldValue = java.sql.Date.valueOf(date);
							}

							if (type == ColumnType.SET) {
								Set<String> set = (Set<String>) fieldValue;
								fieldValue = String.join(EntityMapper.SPLITTER, set);
							}

							values.add(fieldValue);
						} else {
							values.add(null);
						}
					}
				}
			}

			if (sqlType == OperatorType.update || sqlType == OperatorType.delete)
				values.add(recordId);
		}

		String sql() {
			if (sqlType == OperatorType.insert) {
				return String.format("INSERT INTO %s(%s) VALUES (%s)"
						, this.tableName
						, String.join(", ", this.columns)
						, String.join(", ", this.params)
				);
			}

			if (sqlType == OperatorType.update) {
				List<String> list = new ArrayList<String>();
				for (int x = 0; x < columns.size(); x++)
					list.add(String.format("%s = %s", columns.get(x), params.get(x)));
				return String.format("UPDATE %s SET %s WHERE ID = ?"
						, tableName
						, String.join(", ", list)
				);
			}

			return String.format("DELETE FROM %s WHERE ID = ?", tableName);
		}

		Object[] params() { return this.values.toArray(); }
	}

	enum OperatorType { insert, update, delete }
}
