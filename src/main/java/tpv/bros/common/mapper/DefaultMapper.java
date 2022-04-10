package tpv.bros.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import lombok.Getter;
import tpv.bros.common.table.Entity;

public abstract class DefaultMapper<T extends Entity> implements RowMapper<T> {
	@Getter final Set<String> columnNames = new LinkedHashSet<>();

	protected void baseMapper(T instance, ResultSet rs) throws SQLException {
		if (columnNames.contains(Entity.COLUMN___ID))
			instance.setId(rs.getString(Entity.COLUMN___ID));
		if (columnNames.contains(Entity.COLUMN___CREATED_BY))
			instance.setCreatedBy(rs.getString(Entity.COLUMN___CREATED_BY));
		if (columnNames.contains(Entity.COLUMN___CREATED_DATE))
			instance.setCreatedDate(rs.getDate(Entity.COLUMN___CREATED_DATE).toLocalDate());
		if (columnNames.contains(Entity.COLUMN___UPDATED_BY))
			instance.setUpdatedBy(rs.getString(Entity.COLUMN___UPDATED_BY));
		if (columnNames.contains(Entity.COLUMN___UPDATED_DATE))
			instance.setUpdatedDate(rs.getDate(Entity.COLUMN___UPDATED_DATE).toLocalDate());
	}
}
