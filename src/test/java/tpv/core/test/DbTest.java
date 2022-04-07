package tpv.core.test;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ContextConfiguration;

import tpv.core.configuration.DatabaseConfiguration;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration( classes = {
		DatabaseConfiguration.class
})
public class DbTest {

}
