package tpv.core.query.exprs;

import lombok.AllArgsConstructor;
import tpv.core.query.Query;
import tpv.core.query.QueryRuntimeStorage;

@AllArgsConstructor
public class SelectAllExpr extends Expr {
	@Override
	public String gen(Query query) {
		QueryRuntimeStorage runtime = query.runtime();
		return String.format("%s.*", runtime.getTableName());
	}
}
