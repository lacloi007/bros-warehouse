package tpv.core.query.exprs;

import lombok.AllArgsConstructor;
import tpv.core.query.Query;
import tpv.core.query.QueryRuntimeStorage;

@AllArgsConstructor
public class ColExpr extends Expr {
	String column;

	@Override
	public String gen(Query query) {
		QueryRuntimeStorage runtime = query.runtime();
		switch (runtime.getCurrentSqlBlock()) {
		case select:
			runtime.getColumnNames().add(column);
			return String.format("%s.%s", runtime.getTableName(), column);
		default:
			return "";
		}
	}
}
