package tpv.core.query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import tpv.bros.common.configuration.CommonConfiguration;
import tpv.core.configuration.DatabaseConfiguration;
import tpv.core.database.Database;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = { DatabaseConfiguration.class, CommonConfiguration.class })
@ComponentScan
@TestInstance(Lifecycle.PER_CLASS)
public class DefaultQueryTest {
	@BeforeAll
	protected void beforeAll() {
	}

	@AfterAll
	protected void afterAll() {
		Database.rollback();
	}
}
