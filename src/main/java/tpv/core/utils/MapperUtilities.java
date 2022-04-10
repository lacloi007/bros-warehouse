package tpv.core.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import tpv.core.table.Entity;

public class MapperUtilities {
	public static <T extends Entity> void entity(T intance, Set<String> setColumnNames, ResultSet rs)
			throws SQLException {
		if (setColumnNames.contains(Entity.COLUMN___ID))
			intance.setId(rs.getString(Entity.COLUMN___ID));
		if (setColumnNames.contains(Entity.COLUMN___CREATED_BY))
			intance.setCreatedBy(rs.getString(Entity.COLUMN___CREATED_BY));
		if (setColumnNames.contains(Entity.COLUMN___CREATED_DATE))
			intance.setCreatedDate(rs.getDate(Entity.COLUMN___CREATED_DATE).toLocalDate());
		if (setColumnNames.contains(Entity.COLUMN___UPDATED_BY))
			intance.setUpdatedBy(rs.getString(Entity.COLUMN___UPDATED_BY));
		if (setColumnNames.contains(Entity.COLUMN___UPDATED_DATE))
			intance.setUpdatedDate(rs.getDate(Entity.COLUMN___UPDATED_DATE).toLocalDate());
	}

	public static Set<String> columnNames(ResultSet rs) throws SQLException {
		Set<String> set = new LinkedHashSet<String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++)
			set.add(rsmd.getColumnName(x));
		return set;
	}
}
