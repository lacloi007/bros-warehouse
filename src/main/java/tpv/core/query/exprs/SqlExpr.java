package tpv.core.query.exprs;

import lombok.AllArgsConstructor;
import tpv.core.query.Query;

@AllArgsConstructor
public class SqlExpr extends Expr {
	String sql;

	@Override
	public String build(Query query) {
		return sql;
	}
}
