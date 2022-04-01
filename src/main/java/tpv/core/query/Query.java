package tpv.core.query;

import java.util.ArrayList;
import java.util.List;

import tpv.core.define.Entity;
import tpv.core.query.exprs.Expr;

public class Query {

	/*********************
	 *       MAIN VARIABLE
	 *********************/
	List<Expr> selectExpr;
	Class<? extends Entity> fromEntity;

	/*********************
	 *         CONSTRUCTOR
	 *********************/
	private Query() {
		selectExpr = new ArrayList<>();
	}

	/**
	 * @param exprs
	 * @return
	 */
	public static Query select(Expr... exprs) {
		Query instance = new Query();
		if (exprs != null && exprs.length > 0)
			instance.selectExpr.addAll(List.of(exprs));
		return instance;
	}

	/**
	 * @param fromEntity
	 * @return
	 */
	public Query from(Class<? extends Entity> fromEntity) {
		this.fromEntity = fromEntity;
		return this;
	}

	/**************************************
	 *       feature SELECT DEFAULT COLUMNS
	 * Default column are:
	 * (1) ID
	 **************************************/
	boolean selectDefaultColumn = true;

	public Query includeDefaultColumns() {
		this.selectDefaultColumn = true;
		return this;
	}

	public Query excludeDefaultColumns() {
		this.selectDefaultColumn = false;
		return this;
	}
}
