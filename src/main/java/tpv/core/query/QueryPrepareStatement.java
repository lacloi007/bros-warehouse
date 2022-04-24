package tpv.core.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;

import lombok.Getter;

public class QueryPrepareStatement implements PreparedStatementSetter {
	@Getter List<Object> parameters = new ArrayList<>();
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		if (parameters.isEmpty())
			return;

		int idx = 1;
		for (Object object : parameters) {
			if (object instanceof String)
				ps.setString(idx, (String) object);
			if (object instanceof Integer)
				ps.setInt(idx, (Integer) object);
			if (object instanceof LocalDate)
				ps.setDate(idx, java.sql.Date.valueOf((LocalDate) object));
			else
				ps.setObject(idx, object);
		}
	}

	public void add(Object param) { parameters.add(param); }
	public boolean isEmpty() { return parameters.isEmpty(); }
}
