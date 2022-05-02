package tpv.bros.common.mapper;

import tpv.bros.common.table.ReceivingOrder;
import tpv.core.annotation.Mapper;

@Mapper(ReceivingOrder.class)
public class ReceivingOrderMapper extends EntityMapper<ReceivingOrder> {
	public final static String COLUMN___BOX_ID = "BOX_ID";
	public final static String COLUMN___NUMBER_OF_BOX = "NUMBER_OF_BOX";
	public final static String COLUMN___PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
	public final static String COLUMN___SKU = "SKU";
	public final static String COLUMN___QUANTITY = "QUANTITY";
	public final static String COLUMN___WEIGHT_PER_ITEM = "WEIGHT_PER_ITEM";
	public final static String COLUMN___WEIGHT_UNIT = "WEIGHT_UNIT";
	public final static String COLUMN___INCH_LENGTH = "INCH_LENGTH";
	public final static String COLUMN___INCH_WIDTH = "INCH_WIDTH";
	public final static String COLUMN___INCH_HEIGHT = "INCH_HEIGHT";
	public final static String COLUMN___SHIPING_DATE = "SHIPING_DATE";
	public final static String COLUMN___TRACKING_NUMBER = "TRACKING_NUMBER";
	public final static String COLUMN___WAREHOUSE_ID = "WAREHOUSE_ID";
	public final static String COLUMN___NEW_SKU = "NEW_SKU";
	public final static String COLUMN___IS_FRAGILE_GOOD = "IS_FRAGILE_GOOD";
	public final static String COLUMN___BUYER_INFORMATION = "BUYER_INFORMATION";
	public final static String COLUMN___BUYER_ADDRESS = "BUYER_ADDRESS";
	public final static String COLUMN___DATA_TYPE = "DATA_TYPE";
	public final static String COLUMN___STATUS = "STATUS";

	@Override public ReceivingOrder newInstance() { return new ReceivingOrder(); }
}
