package tpv.bros.common.table;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;
import tpv.bros.common.mapper.UserMapper;
import tpv.core.annotation.Column;
import tpv.core.annotation.Table;
import tpv.core.define.enm.ColumnType;
import tpv.core.query.exprs.ColExpr;

@Table(name = "COMMON__USER", prefix = "C00")
public class User extends Entity {
	public final static ColExpr EMAIL = new ColExpr(UserMapper.COLUMN___EMAIL);
	@Column(name = UserMapper.COLUMN___EMAIL, label = "Email address", type = ColumnType.EMAIL, unique = true, mandatory = true, maxLength = 1000)
	@Getter private String email;
	public void setEmail(String email) {
		this.setter("email");
		this.email = email;
	}

	public final static ColExpr PASSWORD = new ColExpr(UserMapper.COLUMN___PASSWORD);
	@Column(name = UserMapper.COLUMN___PASSWORD, label = "Password", type = ColumnType.TEXT, mandatory = true, maxLength = 255)
	@Getter private String password;
	public void setPassword(String password) {
		this.setter("password");
		this.password = password;
	}

	public final static ColExpr FIRST_NAME = new ColExpr(UserMapper.COLUMN___FIRST_NAME);
	@Column(name = UserMapper.COLUMN___FIRST_NAME, label = "First name", type = ColumnType.TEXT, mandatory = true, maxLength = 255)
	@Getter private String firstName;
	public void setFirstName(String firstName) {
		this.setter("firstName");
		this.firstName = firstName;
	}

	public final static ColExpr LAST_NAME = new ColExpr(UserMapper.COLUMN___LAST_NAME);
	@Column(name = UserMapper.COLUMN___LAST_NAME, label = "Last name", type = ColumnType.TEXT, mandatory = true, maxLength = 255)
	@Getter private String lastName;
	public void setLastName(String lastName) {
		this.setter("lastName");
		this.lastName = lastName;
	}

	public final static ColExpr MIDDLE_NAME = new ColExpr(UserMapper.COLUMN___MIDDLE_NAME);
	@Column(name = UserMapper.COLUMN___MIDDLE_NAME, label = "Middle name", type = ColumnType.TEXT, maxLength = 255)
	@Getter private String middleName;
	public void setMiddleName(String middleName) {
		this.setter("middleName");
		this.middleName = middleName;
	}

	public final static ColExpr PHONE_NUMBER = new ColExpr(UserMapper.COLUMN___PHONE_NUMBER);
	@Column(name = UserMapper.COLUMN___PHONE_NUMBER, label = "Phone number", type = ColumnType.TEXT, mandatory = true, maxLength = 255)
	@Getter private String phoneNumber;
	public void setPhoneNumber(String phoneNumber) {
		this.setter("phoneNumber");
		this.phoneNumber = phoneNumber;
	}

	public final static ColExpr ZALO_NUMBER = new ColExpr(UserMapper.COLUMN___ZALO_NUMBER);
	@Column(name = UserMapper.COLUMN___ZALO_NUMBER, label = "Zalo number", type = ColumnType.TEXT, maxLength = 255)
	@Getter private String zaloNumber;
	public void setZaloNumber(String zaloNumber) {
		this.setter("zaloNumber");
		this.zaloNumber = zaloNumber;
	}

	public final static ColExpr ROLES = new ColExpr(UserMapper.COLUMN___ROLES);
	@Column(name = UserMapper.COLUMN___ROLES, label = "Roles", type = ColumnType.SET)
	private final Set<String> roles = new LinkedHashSet<>();
	public Set<String> getRoles() {
		this.setter("roles");
		return roles;
	}
}
