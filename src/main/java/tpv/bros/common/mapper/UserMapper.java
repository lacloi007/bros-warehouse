package tpv.bros.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tpv.bros.common.table.User;
import tpv.core.annotation.Mapper;

@Mapper(User.class)
public class UserMapper extends DefaultMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		baseMapper(user, rs);
		return user;
	}
}
