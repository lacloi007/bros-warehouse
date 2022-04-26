package tpv.bros.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class EntityError {
	@Getter private String field, message;
	public EntityError(String message) {
		this("global", message);
	}
}
