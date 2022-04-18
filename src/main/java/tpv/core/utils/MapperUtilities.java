package tpv.core.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class MapperUtilities {
	public static Set<String> columnNames(ResultSet rs) throws SQLException {
		Set<String> set = new LinkedHashSet<String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++)
			set.add(rsmd.getColumnName(x));
		return set;
	}
}
