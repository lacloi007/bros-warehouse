package tpv.core.query;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import tpv.bros.common.table.Entity;
import tpv.core.database.Database;
import tpv.core.query.exprs.Expr;

public class Query {
	static final String LineSeparator = System.lineSeparator();

	/*********************
	 * MAIN VARIABLE
	 *********************/
	Map<BlockType, List<Expr>> exprs;
	Class<? extends Entity> fromEntity;
	Long limit, offset;

	// RUNTIME VARIABLE
	QueryRuntimeStorage qrs;

	/******************************************
	 * CONSTRUCTOR
	 ******************************************/
	private Query() {
		exprs = new HashMap<>();
		selectDefaultColumn = true;
	}

	/******************************************
	 * PUBLIC SUPPORTER METHODS
	 ******************************************/
	public static Query select(Expr... exprs) { Query instance = new Query(); return instance.setter(BlockType.select, exprs); }
	public Query where(Expr... exprs) { return setter(BlockType.where, exprs); }
	public Query orderBy(Expr... exprs) { return setter(BlockType.orderBy, exprs); }
	public Query groupBy(Expr... exprs) { return setter(BlockType.groupBy, exprs); }
	public Query limit(Long limit) { return setter(this.limit, limit); }
	public Query offset(Long offset) { return setter(this.offset, offset); }
	public Query from(Class<? extends Entity> entityClass) { this.fromEntity = entityClass; return this; }

	/******************************************
	 * PUBLIC SUPPORTER METHODS for OUTPUT
	 ******************************************/
	public QueryRuntimeStorage runtime() { return this.qrs; }
	@SuppressWarnings("unchecked")
	public <T extends Entity> List<T> queryList() {
		String sql = this.build();
		RowMapper<T> mapper;
		try {
			mapper = (RowMapper<T>) qrs.tableInformation.getMapper();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new QueryException(e);
		}

		if (qrs.getPreparedStatementConsumers().isEmpty())
			return Database.query2List(sql, mapper);
		return null;
	}

	/******************************************
	 * PRIVATE METHODS FOR SUPPORT
	 ******************************************/
	private Query setter(BlockType type, Expr... exprs) { 
		if (exprs != null && exprs.length > 0) {
			this.exprs.putIfAbsent(type, new ArrayList<>());
			this.exprs.get(type).addAll(List.of(exprs));
		}
		return this;
	}
	private Query setter(Object src, Object dst) { src = dst; return this; }

	/******************************************
	 * feature SELECT DEFAULT COLUMNS <br>
	 * Default column are: <br>
	 * (1) ID
	 ******************************************/
	boolean selectDefaultColumn = true;
	public Query includeDefaultColumns() { this.selectDefaultColumn = true; return this; }
	public Query excludeDefaultColumns() { this.selectDefaultColumn = false; return this; }

	/******************************************
	 * BUILDING SQL METHODS
	 ******************************************/
	public String build() {
		qrs = new QueryRuntimeStorage(this);

		String where = createSqlBlock(BlockType.where);
		String groupBy = createSqlBlock(BlockType.groupBy);
		String orderBy = createSqlBlock(BlockType.orderBy);
		String select = createSqlBlock(BlockType.select);
		String from = createSqlBlockFrom();

		// make SQL
		StringBuilder sb = new StringBuilder();
		sb.append(select).append(LineSeparator).append(from);
		if (where.isEmpty() == false) sb.append(LineSeparator).append(where);
		if (groupBy.isEmpty() == false) sb.append(LineSeparator).append(groupBy);
		if (orderBy.isEmpty() == false) sb.append(LineSeparator).append(orderBy);
		if (offset != null) sb.append(LineSeparator).append(String.format("OFFSET %d ROWS", offset));
		if (limit != null) sb.append(LineSeparator).append(String.format("FETCH FIRST %d ROWS ONLY", limit));
		return sb.toString();
	}

	/******************************************
	 * PRIVATE METHOD FOR BUILDING
	 ******************************************/

	/**
	 * @param type
	 * @return
	 */
	private String createSqlBlock(BlockType type) {
		qrs.currentSqlBlock = type;
		List<Expr> exprs = expression(type);
		if (exprs == null || exprs.isEmpty())
			return "";

		// load content from expressions
		List<String> contents = new ArrayList<>();
		for (Expr expr : exprs)
			contents.add(expr.gen(this));

		switch (type) {
		case select:
			List<String> selectContents = new ArrayList<>(new LinkedHashSet<>(contents));
			return String.format("SELECT %s", String.join("\n  , ", selectContents));
		case groupBy:
			return String.format("GROUP BY %s", String.join("\n  , ", contents));
		case where:
			return String.format("WHERE %s", String.join(" ", contents));
		case orderBy:
			return String.format("ORDER BY %s", String.join(" ", contents));
		default:
			return "";
		}
	}

	/**
	 * @return
	 */
	private String createSqlBlockFrom() {
		return String.format("FROM %s", qrs.tableName);
	}

	/**
	 * @param type
	 * @return
	 */
	List<Expr> expression(BlockType type) {
		return this.exprs.getOrDefault(type, new ArrayList<>());
	}

	/******************************************
	 * ENUM for SQL BLOCK TYPE
	 ******************************************/
	public static enum BlockType { select, from, where, orderBy, groupBy, undefined }
}
