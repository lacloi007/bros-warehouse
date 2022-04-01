package tpv.bros.common.object;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tpv.core.annotation.Table;
import tpv.core.define.Entity;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "ACCOUNT", prefix = "g00")
public class Account extends Entity {
}
