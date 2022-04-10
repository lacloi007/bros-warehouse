package tpv.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tpv.core.define.enm.ColumnType;
import tpv.core.define.enm.EncryptionType;
import tpv.core.table.Entity;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name();
	ColumnType type() default ColumnType.UNDEFINED;
	boolean mandatory() default false;

	// definition for (ENCRYPTION)
	EncryptionType encryptType() default EncryptionType.UNDEFINED;
	String[] encryptArgs() default {};

	// definition for (REFER, MASTER)
	Class<? extends Entity> referTo() default Entity.class;
}
