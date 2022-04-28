package tpv.bros.common.mapper;

import tpv.bros.common.table.User;
import tpv.core.annotation.Mapper;

@Mapper(User.class)
public class UserMapper extends EntityMapper<User> {
	public final static String COLUMN___EMAIL        = "EMAIL";
	public final static String COLUMN___PASSWORD     = "PASSWORD";
	public final static String COLUMN___FIRST_NAME   = "FIRST_NAME";
	public final static String COLUMN___MIDDLE_NAME  = "MIDDLE_NAME";
	public final static String COLUMN___LAST_NAME    = "LAST_NAME";
	public final static String COLUMN___PHONE_NUMBER = "PHONE_NUMBER";
	public final static String COLUMN___ZALO_NUMBER  = "ZALO_NUMBER";
	public final static String COLUMN___ROLES        = "ROLES";

	@Override public User newInstance() { return new User(); }
}
