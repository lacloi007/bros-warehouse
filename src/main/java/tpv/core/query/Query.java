package tpv.core.query;

import java.util.ArrayList;
import java.util.List;

import tpv.core.define.Entity;
import tpv.core.query.exprs.Expr;

public class Query {
	/*********************
	 * MAIN VARIABLE
	 *********************/
	List<Expr> selectExprs, whereExprs, orderByExprs, groupByExprs;
	Class<? extends Entity> fromEntity;
	Long limit, offset;

	/******************************************
	 * CONSTRUCTOR
	 ******************************************/
	private Query() {
		selectExprs = new ArrayList<>();
		whereExprs = new ArrayList<>();
		orderByExprs = new ArrayList<>();
	}

	/******************************************
	 * PUBLIC SUPPORTER METHODS
	 ******************************************/
	public static Query select(Expr... exprs) { Query instance = new Query(); return instance.loadExprs(instance.selectExprs, exprs); }
	public Query from(Class<? extends Entity> entityClass) { this.fromEntity = entityClass; return this; }
	public Query where(Expr... exprs) { return loadExprs(whereExprs, exprs); }
	public Query orderBy(Expr... exprs) { return loadExprs(orderByExprs, exprs); }
	public Query groupBy(Expr... exprs) { return loadExprs(orderByExprs, exprs); }
	public Query limit(Long limit) { this.limit = limit; return this; }
	public Query offset(Long offset) { this.offset = offset; return this; }

	/******************************************
	 * PUBLIC SUPPORTER METHODS for OUTPUT
	 ******************************************/

	/******************************************
	 * PRIVATE METHODS
	 ******************************************/
	private Query loadExprs(List<Expr> list, Expr... exprs) { 
		if (exprs != null && exprs.length > 0)
			list.addAll(List.of(exprs));
		return this;
	}

	/******************************************
	 * feature SELECT DEFAULT COLUMNS <br>
	 * Default column are: <br>
	 * (1) ID
	 ******************************************/
	boolean selectDefaultColumn = true;
	public Query includeDefaultColumns() { this.selectDefaultColumn = true; return this; }
	public Query excludeDefaultColumns() { this.selectDefaultColumn = false; return this; }
}
