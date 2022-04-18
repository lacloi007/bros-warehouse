package tpv.bros.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import tpv.bros.common.table.User;
import tpv.core.annotation.Mapper;

@Mapper(User.class)
public class UserMapper extends EntityMapper<User> {
	public final static String COLUMN___USERNAME = "USERNAME";
	public final static String COLUMN___PASSWORD = "PASSWORD";
	public final static String COLUMN___FIRST_NAME = "FIRST_NAME";
	public final static String COLUMN___LAST_NAME = "LAST_NAME";
	public final static String COLUMN___SUR_NAME = "SUR_NAME";
	public final static String COLUMN___ROLES = "ROLES";

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		baseMapper(user, rs);
		if (columnNames.contains(COLUMN___USERNAME)) user.setUsername(rs.getString(COLUMN___USERNAME));
		if (columnNames.contains(COLUMN___FIRST_NAME)) user.setFirstName(rs.getString(COLUMN___FIRST_NAME));
		if (columnNames.contains(COLUMN___LAST_NAME)) user.setLastName(rs.getString(COLUMN___LAST_NAME));
		if (columnNames.contains(COLUMN___PASSWORD)) user.setPassword(rs.getString(COLUMN___PASSWORD));
		if (columnNames.contains(COLUMN___SUR_NAME)) user.setSurName(rs.getString(COLUMN___SUR_NAME));
		if (columnNames.contains(COLUMN___ROLES)) user.getRoles().addAll(List.of(rs.getString(COLUMN___ROLES).split(SPLITTER)));
		return user;
	}
}
