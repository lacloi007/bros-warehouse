package tpv.core.query.exprs;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tpv.core.query.Query;
import tpv.core.query.QueryException;

public class ComparableExpr extends Expr {
	ColExpr column;
	CompareType type;
	Object value;
	Class<?> valueClass;

	public ComparableExpr(Expr column, CompareType type, String value) {
		if (column instanceof ColExpr == false)
			throw new QueryException("ComparableExpr support ColExpr only");
		this.column = (ColExpr) column;
		this.type = type;
		this.value = value;
		this.valueClass = String.class;
	}

	@Override
	public String gen(Query query) {
		List<String> list = new ArrayList<>();
		String sqlColumn = column.gen(query);
		if (value == null) {
			list.add(sqlColumn);
			list.add(type.getOperatorForNull());
			list.add("NULL");
		}

		else if (valueClass == String.class) {
			list.add(sqlColumn);
			list.add(type.getOperator());
			list.add("?");
			query.runtime().getParameters().add(value);
		}

		return String.join(" ", list);
	}

	@Getter @AllArgsConstructor protected enum CompareType {
		equal("=", "IS");
		public final String operator, operatorForNull;
	}
}
