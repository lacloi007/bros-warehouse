package tpv.bros.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import tpv.bros.common.table.User;
import tpv.core.utils.MapperUtilities;

public class UserMapper implements RowMapper<User> {
	final Set<String> columnNames = new LinkedHashSet<>();
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		// prepare base data from Entity.class
		MapperUtilities.entity(user, columnNames, rs);
		return user;
	}
}
