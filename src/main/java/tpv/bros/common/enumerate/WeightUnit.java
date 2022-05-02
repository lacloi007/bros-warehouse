package tpv.bros.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum WeightUnit implements Enumerate {
	kg("KG")
	;

	@Getter final String label;
}
