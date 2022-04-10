package tpv.core.query;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import tpv.core.query.exprs.Expr;
import tpv.core.table.Entity;

public class Query {
	/*********************
	 * MAIN VARIABLE
	 *********************/
	List<Expr> selectExprs, whereExprs, orderByExprs, groupByExprs;
	Class<? extends Entity> fromEntity;
	String fromAlias;
	Long limit, offset;

	private RuntimeStorage rs;

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
	public static Query select(Expr... exprs) { Query instance = new Query(); return instance.addExprs(instance.selectExprs, exprs); }
	public Query where(Expr... exprs) { return addExprs(whereExprs, exprs); }
	public Query orderBy(Expr... exprs) { return addExprs(orderByExprs, exprs); }
	public Query groupBy(Expr... exprs) { return addExprs(groupByExprs, exprs); }
	public Query limit(Long limit) { this.limit = limit; return this; }
	public Query offset(Long offset) { this.offset = offset; return this; }
	public Query from(Class<? extends Entity> entityClass) { this.fromEntity = entityClass; return this; }

	/******************************************
	 * PUBLIC SUPPORTER METHODS for OUTPUT
	 ******************************************/

	/******************************************
	 * PRIVATE METHODS
	 ******************************************/
	private Query addExprs(List<Expr> list, Expr... exprs) { if (exprs != null && exprs.length > 0) list.addAll(List.of(exprs)); return this; }

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
		rs = new RuntimeStorage(this);

		String where = createSqlBlock(BlockType.where);
		String groupBy = createSqlBlock(BlockType.groupBy);
		String orderBy = createSqlBlock(BlockType.orderBy);
		String select = createSqlBlock(BlockType.select);
		return "";
	}

	private String createSqlBlock(BlockType type) {
		rs.currentSqlBlock = type;
		List<Expr> exprs = expression(type);
		if (exprs == null || exprs.isEmpty())
			return "";

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

	private String createSqlBlock4From() {
		StringBuilder sb = new StringBuilder();
		//sb.append(String.format("FROM %s", null))
		return "";
	}

	private List<Expr> expression(BlockType type) {
		switch (type) {
		case select: return rs.select;
		case where: return rs.where;
		case orderBy: return rs.orderBy;
		case groupBy: return rs.groupBy;
		default:
			return null;
		}
	}

	enum BlockType { select, from, where, orderBy, groupBy, undefined }

	static class RuntimeStorage {
		BlockType currentSqlBlock = BlockType.undefined;
		List<Expr> select, where, orderBy, groupBy;

		public RuntimeStorage(Query query) {
			currentSqlBlock = BlockType.undefined;

			select = new ArrayList<>();
			select.addAll(query.selectExprs);
			if (query.selectDefaultColumn)
				select.add(0, Entity.ID);

			where = new ArrayList<>();
			where.addAll(query.whereExprs);

			orderBy = new ArrayList<>();
			orderBy.addAll(query.orderByExprs);

			groupBy = new ArrayList<>();
			groupBy.addAll(query.groupByExprs);
		}
	}
}
