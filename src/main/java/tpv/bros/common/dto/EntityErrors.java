package tpv.bros.common.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import tpv.core.define.enm.EntityErrorCode;

public class EntityErrors {
	@Getter final List<EntityError> errors = new ArrayList<>();
	public void addError(String field, EntityErrorCode code, String message) { this.errors.add(new EntityError(field, code, message)); }
	public void addError(String message)               { this.errors.add(new EntityError(message)); }
	public boolean hasError()                          { return errors.isEmpty() == false; }
}
