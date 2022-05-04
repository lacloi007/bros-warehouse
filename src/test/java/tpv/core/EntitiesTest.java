package tpv.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tpv.bros.common.table.ReceivingOrder;
import tpv.core.Entities.TableInfo;

public class EntitiesTest {
	@Test
	public void test() {
		TableInfo info = new TableInfo(ReceivingOrder.class);
		Assertions.assertNotNull(info);
	}
}
