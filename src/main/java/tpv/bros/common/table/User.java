package tpv.bros.common.table;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tpv.bros.common.mapper.UserMapper;
import tpv.core.annotation.Column;
import tpv.core.annotation.Table;
import tpv.core.define.enm.ColumnType;
import tpv.core.define.enm.EncryptionType;
import tpv.core.query.exprs.ColExpr;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "COMMON__USER", prefix = "C00")
public class User extends Entity {
	public final static ColExpr USERNAME = new ColExpr(UserMapper.COLUMN___USERNAME);
	@Column(name = UserMapper.COLUMN___USERNAME, type = ColumnType.TEXT, mandatory = true)
	private String username;

	public final static ColExpr PASSWORD = new ColExpr(UserMapper.COLUMN___PASSWORD);
	@Column(name = UserMapper.COLUMN___PASSWORD, type = ColumnType.ENCRYPTION, mandatory = true, encryptType = EncryptionType.MD5)
	private String password;

	public final static ColExpr FIRST_NAME = new ColExpr(UserMapper.COLUMN___FIRST_NAME);
	@Column(name = UserMapper.COLUMN___FIRST_NAME, type = ColumnType.TEXT, mandatory = true)
	private String firstName;

	public final static ColExpr LAST_NAME = new ColExpr(UserMapper.COLUMN___LAST_NAME);
	@Column(name = UserMapper.COLUMN___LAST_NAME, type = ColumnType.TEXT)
	private String lastName;

	public final static ColExpr SUR_NAME = new ColExpr(UserMapper.COLUMN___SUR_NAME);
	@Column(name = UserMapper.COLUMN___SUR_NAME, type = ColumnType.TEXT)
	private String surName;

	public final static ColExpr ROLES = new ColExpr(UserMapper.COLUMN___ROLES);
	@Column(name = UserMapper.COLUMN___ROLES, type = ColumnType.SET)
	private final Set<String> roles = new LinkedHashSet<>();
}
