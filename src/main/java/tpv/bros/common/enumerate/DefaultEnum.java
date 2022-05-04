package tpv.bros.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DefaultEnum implements Enumerate {
	T("T")
	;

	@Getter final String label;
}
