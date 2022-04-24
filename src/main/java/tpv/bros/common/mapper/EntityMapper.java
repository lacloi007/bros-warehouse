package tpv.bros.common.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import tpv.bros.common.table.Entity;

public abstract class EntityMapper<T extends Entity> implements RowMapper<T> {
	protected static final String SPLITTER = "|";
	public final static String COLUMN___ID           = "ID";
	public final static String COLUMN___CREATED_BY   = "CREATED_BY";
	public final static String COLUMN___CREATED_DATE = "CREATED_DATE";
	public final static String COLUMN___UPDATED_BY   = "UPDATED_BY";
	public final static String COLUMN___UPDATED_DATE = "UPDATED_DATE";

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
		return mapping(rs, columns, rowNum);
	}

	/**
	 * @param rs
	 * @param columnNames
	 * @param rowNum
	 * @return
	 * @throws SQLException
	 */
	public abstract T mapping(ResultSet rs, Set<String> columnNames, int rowNum) throws SQLException;

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
