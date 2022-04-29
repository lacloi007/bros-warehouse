package tpv.bros.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import tpv.core.define.enm.EntityErrorCode;

@Data
@AllArgsConstructor
public class EntityError {
	@Getter private String field;
	@Getter private EntityErrorCode errorCode;
	@Getter private String message;
	public EntityError(String message) {
		this("global", EntityErrorCode.undefined, message);
	}
	
}
