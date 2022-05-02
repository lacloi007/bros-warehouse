package tpv.bros.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ReceivingOrderDataType implements Enumerate {
	fbm("FBM")
	, fba("FBA")
	, epack("E-Package")
	;

	@Getter final String label;
}
