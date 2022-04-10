package tpv.core.query.exprs;

import lombok.AllArgsConstructor;
import tpv.core.query.Query;

@AllArgsConstructor
public class ColExpr extends Expr {
	String column, alias;
	public ColExpr(String column) {
		this(column, null);
	}

	@Override
	public String gen(Query query) {
		return null;
	}
}
