package tpv.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import tpv.core.database.Database;

@Configuration
public class DatabaseConfiguration {
	@Autowired
	private void setJdbcTemplate(JdbcTemplate template) {
		Database.setTemplate(template);
	}
}
