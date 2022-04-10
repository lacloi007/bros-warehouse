package tpv.core.query;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ContextConfiguration;

import tpv.bros.common.configuration.CommonConfiguration;
import tpv.core.configuration.DatabaseConfiguration;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = { DatabaseConfiguration.class, CommonConfiguration.class })
public class QueryTest {

}
