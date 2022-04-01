package tpv.core.define;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tpv.core.annotation.Column;
import tpv.core.annotation.Table;
import tpv.core.define.enm.ColumnType;
import tpv.core.define.enm.EncryptionType;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "USER_ENTITY", prefix = "usr")
public class UserEntity extends Entity {
	@Column(name = "USER_NAME", type = ColumnType.TEXT, mandatory = true)
	private String userName;

	@Column(name = "PASSWORD", type = ColumnType.ENCRYPTION, mandatory = true, encryptType = EncryptionType.MD5)
	private String password;

	@Column(name = "FIRST_NAME", type = ColumnType.TEXT, mandatory = true)
	private String firstName;

	@Column(name = "LAST_NAME", type = ColumnType.TEXT)
	private String lastName;

	@Column(name = "SUR_NAME", type = ColumnType.TEXT)
	private String surName;
}
