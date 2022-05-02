package tpv.bros.common.mapper;

import tpv.bros.common.table.Warehouse;
import tpv.core.annotation.Mapper;

@Mapper(Warehouse.class)
public class WarehouseMapper extends EntityMapper<Warehouse> {
	public final static String COLUMN___PHONE_NUMBER = "PHONE_NUMBER";
	public final static String COLUMN___ADDRESS = "ADDRESS";
	public final static String COLUMN___NOTE = "NOTE";

	@Override public Warehouse newInstance() { return new Warehouse(); }
}
