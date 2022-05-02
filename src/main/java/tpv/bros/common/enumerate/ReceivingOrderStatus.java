package tpv.bros.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ReceivingOrderStatus implements Enumerate {
	open("Open")
	, inProgress("In progress")
	, pending("Pending")
	, resolved("Resolved")
	, canceled("Canceled")
	;

	@Getter final String label;
}
