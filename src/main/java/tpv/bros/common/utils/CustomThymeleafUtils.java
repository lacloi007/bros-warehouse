package tpv.bros.common.utils;

import lombok.NonNull;
import tpv.bros.common.dto.EntityColumn;

public class CustomThymeleafUtils {
	public EntityColumn entityColumn(@NonNull String table, @NonNull String column) { return new EntityColumn(table, column, ""); }
	public EntityColumn entityColumn(@NonNull String table, @NonNull String column, String exCls) { return new EntityColumn(table, column, exCls); }
}
