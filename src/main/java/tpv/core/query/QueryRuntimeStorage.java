package tpv.core.query;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lombok.Getter;
import tpv.bros.common.table.Entity;
import tpv.core.Entities;
import tpv.core.Entities.TableInfo;
import tpv.core.query.Query.BlockType;
import tpv.core.query.exprs.Expr;

public class QueryRuntimeStorage {
	@Getter BlockType currentSqlBlock = BlockType.undefined;
	List<Expr> select, where, orderBy, groupBy;
	@Getter List<Consumer<PreparedStatement>> preparedStatementConsumers;
	@Getter QueryPrepareStatement parameters = new QueryPrepareStatement();
	@Getter String tableName;
	TableInfo tableInformation;

	public QueryRuntimeStorage(Query query) {
		currentSqlBlock = BlockType.undefined;

		select = new ArrayList<>();
		select.addAll(query.expression(BlockType.select));
		if (query.selectDefaultColumn)
			select.add(0, Entity.ID);

		tableInformation = Entities.tblInfoByClassName(query.fromEntity);
		tableName = tableInformation.getName();

		where = new ArrayList<>();
		where.addAll(query.expression(BlockType.where));

		orderBy = new ArrayList<>();
		orderBy.addAll(query.expression(BlockType.orderBy));

		groupBy = new ArrayList<>();
		groupBy.addAll(query.expression(BlockType.groupBy));

		preparedStatementConsumers = new ArrayList<>();
	}
}
