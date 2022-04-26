package tpv.core.database;

import java.util.Set;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import tpv.bros.common.mapper.EntityMapper;
import tpv.bros.common.table.Entity;
import tpv.core.Entities;
import tpv.core.Entities.FieldInfo;
import tpv.core.Entities.TableInfo;
import tpv.core.annotation.Column;

public class EntityValidator {
	public static <T extends Entity> boolean validateBeforeInsert(T instance) {
		return validate(OperatorType.insert, false, instance);
	}

	public static <T extends Entity> boolean validateBeforeUpdate(T instance) {
		return validate(OperatorType.update, false, instance);
	}

	static <T extends Entity> boolean validate(OperatorType type, boolean returnFirstError
		, T instance
	) {
		boolean isUpdate = type == OperatorType.update;
		TableInfo table = Entities.tblInfo(instance.getClass());
		PropertyAccessor accessor = PropertyAccessorFactory.forBeanPropertyAccess(instance);
		for (FieldInfo field: table.databaseFields.values()) {
			Column fieldColumn = field.getColumn();
			String declaredField = field.getDeclaredField();
			Object fieldValue = accessor.getPropertyValue(declaredField);
			String fieldStringValue = textValue(fieldValue);
			if (isUpdate && instance.updatedFields.contains(declaredField) == false)
				continue;

			if (fieldColumn.mandatory() && fieldStringValue.isEmpty()) {
				instance.errors.addError(declaredField, "Field [" + declaredField + "] is mandatory");
				if (returnFirstError)
					return false;
			}

			if (fieldColumn.maxLength() > 0 && fieldStringValue.length() > fieldColumn.maxLength()) {
				instance.errors.addError(declaredField, "Field content of [" + declaredField + "] is exceed " + fieldColumn.maxLength());
				if (returnFirstError)
					return false;
			}
		}

		return instance.errors.hasError();
	}

	@SuppressWarnings("unchecked")
	static String textValue(Object fieldValue) {
		if (fieldValue == null)
			return "";
		if (fieldValue instanceof Set)
			return String.join(EntityMapper.SPLITTER, (Set<String>) fieldValue);
		return String.valueOf(fieldValue);
	}

	enum OperatorType { insert, update, delete }
}
