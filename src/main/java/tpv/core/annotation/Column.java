package tpv.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tpv.bros.common.table.Entity;
import tpv.core.define.enm.ColumnType;
import tpv.core.define.enm.EncryptionType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name();
	ColumnType type() default ColumnType.UNDEFINED;
	boolean mandatory() default false;
	boolean unique() default false;
	int maxLength() default -1;
	String label() default "";

	// definition for (ENCRYPTION)
	EncryptionType encryptType() default EncryptionType.UNDEFINED;
	String[] encryptArgs() default {};

	// definition for (REFER, MASTER)
	Class<? extends Entity> referTo() default Entity.class;

	// definition for (NUMERIC)
	int precision() default -1;
	int scale() default -1;
}
