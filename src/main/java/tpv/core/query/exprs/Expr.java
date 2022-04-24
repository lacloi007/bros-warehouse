package tpv.core.query.exprs;

import tpv.core.query.Query;
import tpv.core.query.exprs.ComparableExpr.CompareType;

public abstract class Expr {
	public abstract String gen(Query query);
	
	public ComparableExpr equal(String text) {
		return new ComparableExpr(this, CompareType.equal, text);
	}
}
