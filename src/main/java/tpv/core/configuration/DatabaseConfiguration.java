package tpv.core.configuration;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import tpv.core.database.Database;

@Configuration
public class DatabaseConfiguration {
	/**
	 * jdbc:h2:localhost:/tool/git-source/bros-warehouse/data/brosWarehouse
	 * jdbc:h2:tcp://localhost:9092/brosWarehouse
	 * jdbc:h2:tcp://localhost:9002/~/brosWarehouse
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2WebServer() throws SQLException, IOException {
		return Server.createWebServer("-trace"
				, "-web", "-webAllowOthers", "-webPort", "8889"
		);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2TcpServer() throws SQLException, IOException {
		File file = new File("data");
		return Server.createTcpServer("-trace"
				, "-tcp", "-tcpAllowOthers", "-tcpPort", "9092"
				, "-baseDir", file.getAbsolutePath()
		);
	}

	@Autowired
	private void setJdbcTemplate(JdbcTemplate template) {
		Database.setTemplate(template);
	}
}
