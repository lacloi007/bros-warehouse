package tpv.bros.common.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class EntityErrors {
	@Getter final List<EntityError> errors = new ArrayList<>();
	public EntityErrors() {}
	public void addError(String field, String message) { this.errors.add(new EntityError(field, message)); }
	public void addError(String message)               { this.errors.add(new EntityError(message)); }
	public boolean hasError()                          { return errors.isEmpty() == false; }
}
