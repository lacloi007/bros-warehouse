package tpv.core.query.exprs;

import tpv.core.query.Query;

public abstract class Expr {
	public abstract String build(Query query);
}
