package tpv.bros.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import lombok.Getter;
import tpv.bros.common.table.Entity;

public abstract class EntityMapper<T extends Entity> implements RowMapper<T> {
	protected static final String SPLITTER = "|";
	public final static String COLUMN___ID           = "ID";
	public final static String COLUMN___CREATED_BY   = "CREATED_BY";
	public final static String COLUMN___CREATED_DATE = "CREATED_DATE";
	public final static String COLUMN___UPDATED_BY   = "UPDATED_BY";
	public final static String COLUMN___UPDATED_DATE = "UPDATED_DATE";

	@Getter final Set<String> columnNames = new LinkedHashSet<>();

	protected void baseMapper(T instance, ResultSet rs) throws SQLException {
		if (columnNames.contains(COLUMN___ID))
			instance.setId(rs.getString(COLUMN___ID));
		if (columnNames.contains(COLUMN___CREATED_BY))
			instance.setCreatedBy(rs.getString(COLUMN___CREATED_BY));
		if (columnNames.contains(COLUMN___CREATED_DATE))
			instance.setCreatedDate(rs.getDate(COLUMN___CREATED_DATE).toLocalDate());
		if (columnNames.contains(COLUMN___UPDATED_BY))
			instance.setUpdatedBy(rs.getString(COLUMN___UPDATED_BY));
		if (columnNames.contains(COLUMN___UPDATED_DATE))
			instance.setUpdatedDate(rs.getDate(COLUMN___UPDATED_DATE).toLocalDate());
	}
}
