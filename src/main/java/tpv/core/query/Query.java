package tpv.core.query;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import tpv.core.query.exprs.Expr;
import tpv.core.table.Entity;

public class Query {
	static final String LineSeparator = System.lineSeparator();

	/*********************
	 * MAIN VARIABLE
	 *********************/
	List<Expr> selectExprs, whereExprs, orderByExprs, groupByExprs;
	Class<? extends Entity> fromEntity;
	Long limit, offset;

	QueryRuntimeStorage qrs;

	/******************************************
	 * CONSTRUCTOR
	 ******************************************/
	private Query() {
		selectExprs = new ArrayList<>();
		whereExprs = new ArrayList<>();
		orderByExprs = new ArrayList<>();
		groupByExprs = new ArrayList<>();
		selectDefaultColumn = true;
	}

	/******************************************
	 * PUBLIC SUPPORTER METHODS
	 ******************************************/
	public static Query select(Expr... exprs) { Query instance = new Query(); return instance.setter(instance.selectExprs, exprs); }
	public Query where(Expr... exprs) { return setter(whereExprs, exprs); }
	public Query orderBy(Expr... exprs) { return setter(orderByExprs, exprs); }
	public Query groupBy(Expr... exprs) { return setter(groupByExprs, exprs); }
	public Query limit(Long limit) { return setter(this.limit, limit); }
	public Query offset(Long offset) { return setter(this.offset, offset); }
	public Query from(Class<? extends Entity> entityClass) { return setter(fromEntity, entityClass); }

	/******************************************
	 * PUBLIC SUPPORTER METHODS for OUTPUT
	 ******************************************/
	public QueryRuntimeStorage runtime() { return this.qrs; }

	/******************************************
	 * PRIVATE METHODS FOR SUPPORT
	 ******************************************/
	private Query setter(List<Expr> list, Expr... exprs) { if (exprs != null && exprs.length > 0) list.addAll(List.of(exprs)); return this; }
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
	private List<Expr> expression(BlockType type) {
		switch (type) {
		case select: return qrs.select;
		case where: return qrs.where;
		case orderBy: return qrs.orderBy;
		case groupBy: return qrs.groupBy;
		default:
			return null;
		}
	}

	/******************************************
	 * ENUM for SQL BLOCK TYPE
	 ******************************************/
	public static enum BlockType { select, from, where, orderBy, groupBy, undefined }
}
