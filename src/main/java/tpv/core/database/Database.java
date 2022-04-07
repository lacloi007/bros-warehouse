package tpv.core.database;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.jdbc.core.JdbcTemplate;

import lombok.Setter;
import tpv.core.define.Entity;

public class Database {
	@Setter static JdbcTemplate template;

	public static <T extends Entity> void insert(T record) {
	}

	public static <T extends Entity> void update(T record) {
	}

	public static <T extends Entity> void delete(T record) {
	}

	public static <T extends Entity> List<T> query2List(String sql, Map<String, Object> parameters) {
		return List.of();
	}

	public static <T extends Entity> Stream<T> query2Stream(String sql, Map<String, Object> parameters) {
		return Stream.of();
	}

	public static <T extends Entity> Map<String, T> query2Map(String sql, Map<String, Object> parameters) {
		return Map.of();
	}
}
