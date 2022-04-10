package tpv.core.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import lombok.Setter;
import tpv.bros.common.table.Entity;

public class Database {
	@Setter
	static JdbcTemplate template;

	public static <T extends Entity> void insert(T record) {
	}

	public static <T extends Entity> void update(T record) {
	}

	public static <T extends Entity> void delete(T record) {
	}

	public static <T extends Entity> List<T> query2List(String sql, RowMapper<T> rowMapper) {
		return template.query(sql, rowMapper);
	}

	public static <T extends Entity> Stream<T> query2Stream(String sql, Map<String, Object> parameters) {
		return Stream.of();
	}

	public static <T extends Entity> Map<String, T> query2Map(String sql, Map<String, Object> parameters) {
		return Map.of();
	}

	public static Savepoint makeSavePoint() {
		try {
			return connection().setSavepoint();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public static void rollback() {
		rollback(null);
	}

	public static void rollback(Savepoint savepoint) {
		try {
			if (savepoint == null)
				connection().rollback();
			else
				connection().rollback(savepoint);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	private static Connection connection() throws SQLException {
		return template.getDataSource().getConnection();
	}
}
