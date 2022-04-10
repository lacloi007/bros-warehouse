package tpv.core.query;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import tpv.core.Entities;
import tpv.core.query.Query.BlockType;
import tpv.core.query.exprs.Expr;
import tpv.core.table.Entity;

public class QueryRuntimeStorage {
	@Getter BlockType currentSqlBlock = BlockType.undefined;
	List<Expr> select, where, orderBy, groupBy;
	Set<String> columnNames;
	@Getter String tableName;

	public QueryRuntimeStorage(Query query) {
		currentSqlBlock = BlockType.undefined;

		select = new ArrayList<>();
		select.addAll(query.selectExprs);
		if (query.selectDefaultColumn)
			select.add(0, Entity.ID);

		tableName = Entities.tblInfo(query.fromEntity).getName();

		where = new ArrayList<>();
		where.addAll(query.whereExprs);

		orderBy = new ArrayList<>();
		orderBy.addAll(query.orderByExprs);

		groupBy = new ArrayList<>();
		groupBy.addAll(query.groupByExprs);

		columnNames = new LinkedHashSet<>();
	}
}
