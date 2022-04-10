package tpv.core.query;

import org.junit.jupiter.api.Test;

import tpv.bros.common.table.User;

public class QueryTest extends DefaultQueryTest {
	@Test
	protected void test() {
		Query query = Query.select(User.FIRST_NAME).from(User.class);
	}
}
