package tpv.core.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tpv.core.annotation.Column;
import tpv.core.annotation.Table;
import tpv.core.define.enm.ColumnType;
import tpv.core.define.enm.EncryptionType;
import tpv.core.query.exprs.ColExpr;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "COMMON__USER", prefix = "C00")
public class User extends Entity {
	final static String COLUMN___USERNAME = "USERNAME";
	public final static ColExpr USERNAME = new ColExpr(COLUMN___USERNAME);
	@Column(name = COLUMN___USERNAME, type = ColumnType.TEXT, mandatory = true)
	private String username;

	final static String COLUMN___PASSWORD = "PASSWORD";
	public final static ColExpr PASSWORD = new ColExpr(COLUMN___PASSWORD);
	@Column(name = COLUMN___PASSWORD, type = ColumnType.ENCRYPTION, mandatory = true, encryptType = EncryptionType.MD5)
	private String password;

	final static String COLUMN___FIRST_NAME = "FIRST_NAME";
	public final static ColExpr FIRST_NAME = new ColExpr(COLUMN___FIRST_NAME);
	@Column(name = COLUMN___FIRST_NAME, type = ColumnType.TEXT, mandatory = true)
	private String firstName;

	final static String COLUMN___LAST_NAME = "LAST_NAME";
	public final static ColExpr LAST_NAME = new ColExpr(COLUMN___LAST_NAME);
	@Column(name = COLUMN___LAST_NAME, type = ColumnType.TEXT)
	private String lastName;

	final static String COLUMN___SUR_NAME = "SUR_NAME";
	public final static ColExpr SUR_NAME = new ColExpr(COLUMN___SUR_NAME);
	@Column(name = COLUMN___SUR_NAME, type = ColumnType.TEXT)
	private String surName;
}
