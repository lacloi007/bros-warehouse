package tpv.bros.common.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tpv.core.annotation.Table;
import tpv.core.table.Entity;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "APP__ACCOUNT", prefix = "A00")
public class Account extends Entity {
}
