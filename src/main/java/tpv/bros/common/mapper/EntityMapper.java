package tpv.bros.common.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.RowMapper;

import tpv.bros.common.table.Entity;
import tpv.bros.common.table.User;
import tpv.core.Entities;
import tpv.core.Entities.FieldInfo;
import tpv.core.Entities.TableInfo;
import tpv.core.define.enm.ColumnType;

public abstract class EntityMapper<T extends Entity> implements RowMapper<T> {
	public static final String SPLITTER = "[|]";
	public final static String COLUMN___ID           = "ID";
	public final static String COLUMN___CREATED_BY   = "CREATED_BY";
	public final static String COLUMN___CREATED_DATE = "CREATED_DATE";
	public final static String COLUMN___UPDATED_BY   = "UPDATED_BY";
	public final static String COLUMN___UPDATED_DATE = "UPDATED_DATE";

	public abstract T newInstance();

	/**
	 * @param instance
	 * @param rs
	 * @param columns
	 * @throws SQLException
	 */
	protected void baseMapper(T instance, ResultSet rs, Set<String> columns) throws SQLException {
		if (columns.contains(COLUMN___ID))
			instance.setId(rs.getString(COLUMN___ID));
		if (columns.contains(COLUMN___CREATED_BY))
			instance.setCreatedBy(rs.getString(COLUMN___CREATED_BY));
		if (columns.contains(COLUMN___CREATED_DATE))
			instance.setCreatedDate(rs.getDate(COLUMN___CREATED_DATE).toLocalDate());
		if (columns.contains(COLUMN___UPDATED_BY))
			instance.setUpdatedBy(rs.getString(COLUMN___UPDATED_BY));
		if (columns.contains(COLUMN___UPDATED_DATE))
			instance.setUpdatedDate(rs.getDate(COLUMN___UPDATED_DATE).toLocalDate());
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		Set<String> columns = prepareColumns(rs);
		T instance = newInstance();
		mapping(instance, rs, columns, rowNum);
		return instance;
	}

	/**
	 * @param rs
	 * @param columnNames
	 * @param rowNum
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	void mapping(T instance, ResultSet rs, Set<String> columnNames, int rowNum) throws SQLException {
		PropertyAccessor accessor = PropertyAccessorFactory.forBeanPropertyAccess(instance);

		baseMapper(instance, rs, columnNames);
		TableInfo table = Entities.tblInfoByClassName(User.class);
		for (FieldInfo field: table.databaseFields.values()) {
			ColumnType type = field.getColumn().type();
			String databaseField = field.getDatabaseField();
			String declaredField = field.getDeclaredField();
			if (columnNames.contains(field.getDatabaseField())) {
				if (type == ColumnType.DATE || type == ColumnType.DATE_TIME) {
					LocalDate localDate = rs.getDate(databaseField).toLocalDate();
					accessor.setPropertyValue(declaredField, localDate);
				}

				// other case
				else {
					String string = rs.getString(databaseField);
					if (string != null) {
						if (type == ColumnType.SET) {
							Set<String> current = (Set<String>) accessor.getPropertyValue(declaredField);
							current.addAll(List.of(string.split(SPLITTER)));
						}

						else {
							accessor.setPropertyValue(declaredField, string);
						}
					}
				}
			}
		}
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Set<String> prepareColumns(ResultSet rs) throws SQLException {
		Set<String> columns = new LinkedHashSet<String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		for (int i = 1; i <= columnCount; i++)
			columns.add(rsmd.getColumnLabel(i));
		return columns;
	}
}
