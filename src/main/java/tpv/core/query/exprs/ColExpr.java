package tpv.core.query.exprs;

import lombok.AllArgsConstructor;
import tpv.core.query.Query;
import tpv.core.query.QueryRuntimeStorage;

@AllArgsConstructor
public class ColExpr extends Expr {
	String column, alias;

	public ColExpr(String column) {
		this(column, null);
	}

	@Override
	public String gen(Query query) {
		QueryRuntimeStorage runtime = query.runtime();
		switch (runtime.getCurrentSqlBlock()) {
		case select:
			if (alias == null)
				return String.format("%s.%s", runtime.getTableName(), column);
			else
				return String.format("%s.%s AS %s", runtime.getTableName(), column, alias);
		default:
			return "";
		}
	}
}
