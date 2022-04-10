package tpv.core.query.exprs;

import lombok.AllArgsConstructor;
import tpv.core.query.Query;

@AllArgsConstructor
public class SqlExpr extends Expr {
	String sql;

	@Override
	public String gen(Query query) {
		return sql;
	}
}
