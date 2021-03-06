package tpv.bros.common.table;

import lombok.Getter;
import tpv.bros.common.mapper.ReceivingOrderMapper;
import tpv.core.annotation.Column;
import tpv.core.annotation.Table;
import tpv.core.define.enm.ColumnType;
import tpv.core.query.exprs.ColExpr;

@Table(name = "BROS_RECEIVING_ORDER", prefix = "E00")
public class ReceivingOrder extends Entity {

	public final static ColExpr BOX_ID = new ColExpr(ReceivingOrderMapper.COLUMN___BOX_ID);
	@Column(name = ReceivingOrderMapper.COLUMN___BOX_ID, label = "Box code", type = ColumnType.TEXT, mandatory = true)
	@Getter private String boxId;
	public void setBoxId(String boxId) {
		this.setter("boxId");
		this.boxId = boxId;
	}

	public final static ColExpr NUMBER_OF_BOX = new ColExpr(ReceivingOrderMapper.COLUMN___NUMBER_OF_BOX);
	@Column(name = ReceivingOrderMapper.COLUMN___NUMBER_OF_BOX, label = "Number of Box", type = ColumnType.NUMERIC, precision = 3)
	@Getter private Integer numberOfBox;
	public void setNumberOfBox(Integer numberOfBox) {
		this.setter("numberOfBox");
		this.numberOfBox = numberOfBox;
	}

	public final static ColExpr PRODUCT_DESCRIPTION = new ColExpr(ReceivingOrderMapper.COLUMN___PRODUCT_DESCRIPTION);
	@Column(name = ReceivingOrderMapper.COLUMN___PRODUCT_DESCRIPTION, label = "Product description", type = ColumnType.TEXTAREA)
	@Getter private String productDescription;
	public void setProductDescription(String productDescription) {
		this.setter("productDescription");
		this.productDescription = productDescription;
	}

	public final static ColExpr SKU = new ColExpr(ReceivingOrderMapper.COLUMN___SKU);
	@Column(name = ReceivingOrderMapper.COLUMN___SKU, label = "SKU", type = ColumnType.TEXT)
	@Getter private String sku;
	public void setSku(String sku) {
		this.setter("sku");
		this.sku = sku;
	}

	public final static ColExpr QUANTITY = new ColExpr(ReceivingOrderMapper.COLUMN___QUANTITY);
	@Column(name = ReceivingOrderMapper.COLUMN___QUANTITY, label = "Quantity", type = ColumnType.NUMERIC, precision = 20, scale = 2)
	@Getter private java.math.BigDecimal quantity;
	public void setQuantity(java.math.BigDecimal quantity) {
		this.setter("quantity");
		this.quantity = quantity;
	}

	public final static ColExpr WEIGHT_PER_ITEM = new ColExpr(ReceivingOrderMapper.COLUMN___WEIGHT_PER_ITEM);
	@Column(name = ReceivingOrderMapper.COLUMN___WEIGHT_PER_ITEM, label = "Weight per item", type = ColumnType.NUMERIC, precision = 20, scale = 2)
	@Getter private java.math.BigDecimal weightPerItem;
	public void setWeightPerItem(java.math.BigDecimal weightPerItem) {
		this.setter("weightPerItem");
		this.weightPerItem = weightPerItem;
	}

	public final static ColExpr WEIGHT_UNIT = new ColExpr(ReceivingOrderMapper.COLUMN___WEIGHT_UNIT);
	@Column(name = ReceivingOrderMapper.COLUMN___WEIGHT_UNIT, label = "Weight Unit", type = ColumnType.ENUMERATE)
	@Getter private tpv.bros.common.enumerate.WeightUnit weightUnit;
	public void setWeightUnit(tpv.bros.common.enumerate.WeightUnit weightUnit) {
		this.setter("weightUnit");
		this.weightUnit = weightUnit;
	}

	public final static ColExpr INCH_LENGTH = new ColExpr(ReceivingOrderMapper.COLUMN___INCH_LENGTH);
	@Column(name = ReceivingOrderMapper.COLUMN___INCH_LENGTH, label = "Length (inch)", type = ColumnType.NUMERIC, precision = 20, scale = 2)
	@Getter private java.math.BigDecimal inchLength;
	public void setInchLength(java.math.BigDecimal inchLength) {
		this.setter("inchLength");
		this.inchLength = inchLength;
	}

	public final static ColExpr INCH_WIDTH = new ColExpr(ReceivingOrderMapper.COLUMN___INCH_WIDTH);
	@Column(name = ReceivingOrderMapper.COLUMN___INCH_WIDTH, label = "Width (inch)", type = ColumnType.NUMERIC, precision = 20, scale = 2)
	@Getter private java.math.BigDecimal inchWidth;
	public void setInchWidth(java.math.BigDecimal inchWidth) {
		this.setter("inchWidth");
		this.inchWidth = inchWidth;
	}

	public final static ColExpr INCH_HEIGHT = new ColExpr(ReceivingOrderMapper.COLUMN___INCH_HEIGHT);
	@Column(name = ReceivingOrderMapper.COLUMN___INCH_HEIGHT, label = "Height (inch)", type = ColumnType.NUMERIC, precision = 20, scale = 2)
	@Getter private java.math.BigDecimal inchHeight;
	public void setInchHeight(java.math.BigDecimal inchHeight) {
		this.setter("inchHeight");
		this.inchHeight = inchHeight;
	}

	public final static ColExpr SHIPPING_DATE = new ColExpr(ReceivingOrderMapper.COLUMN___SHIPPING_DATE);
	@Column(name = ReceivingOrderMapper.COLUMN___SHIPPING_DATE, label = "Shipping date", type = ColumnType.DATE_TIME)
	@Getter private String shippingDate;
	public void setShippingDate(String shippingDate) {
		this.setter("shippingDate");
		this.shippingDate = shippingDate;
	}

	public final static ColExpr TRACKING_NUMBER = new ColExpr(ReceivingOrderMapper.COLUMN___TRACKING_NUMBER);
	@Column(name = ReceivingOrderMapper.COLUMN___TRACKING_NUMBER, label = "Tracking Number", type = ColumnType.TEXT)
	@Getter private String trackingNumber;
	public void setTrackingNumber(String trackingNumber) {
		this.setter("trackingNumber");
		this.trackingNumber = trackingNumber;
	}

	public final static ColExpr WAREHOUSE_ID = new ColExpr(ReceivingOrderMapper.COLUMN___WAREHOUSE_ID);
	@Column(name = ReceivingOrderMapper.COLUMN___WAREHOUSE_ID, label = "Warehouse ID", type = ColumnType.REFERENCE)
	@Getter private String warehouseId;
	public void setWarehouseId(String warehouseId) {
		this.setter("warehouseId");
		this.warehouseId = warehouseId;
	}

	public final static ColExpr NEW_SKU = new ColExpr(ReceivingOrderMapper.COLUMN___NEW_SKU);
	@Column(name = ReceivingOrderMapper.COLUMN___NEW_SKU, label = "New SKU", type = ColumnType.TEXT)
	@Getter private String newSku;
	public void setNewSku(String newSku) {
		this.setter("newSku");
		this.newSku = newSku;
	}

	public final static ColExpr IS_FRAGILE_GOOD = new ColExpr(ReceivingOrderMapper.COLUMN___IS_FRAGILE_GOOD);
	@Column(name = ReceivingOrderMapper.COLUMN___IS_FRAGILE_GOOD, label = "Fragile good", type = ColumnType.CHECKBOX)
	@Getter private String isFragileGood;
	public void setIsFragileGood(String isFragileGood) {
		this.setter("isFragileGood");
		this.isFragileGood = isFragileGood;
	}

	public final static ColExpr BUYER_INFORMATION = new ColExpr(ReceivingOrderMapper.COLUMN___BUYER_INFORMATION);
	@Column(name = ReceivingOrderMapper.COLUMN___BUYER_INFORMATION, label = "Buyer Information", type = ColumnType.TEXT)
	@Getter private String buyerInformation;
	public void setBuyerInformation(String buyerInformation) {
		this.setter("buyerInformation");
		this.buyerInformation = buyerInformation;
	}

	public final static ColExpr BUYER_ADDRESS = new ColExpr(ReceivingOrderMapper.COLUMN___BUYER_ADDRESS);
	@Column(name = ReceivingOrderMapper.COLUMN___BUYER_ADDRESS, label = "Buyer Address", type = ColumnType.TEXT)
	@Getter private String buyerAddress;
	public void setBuyerAddress(String buyerAddress) {
		this.setter("buyerAddress");
		this.buyerAddress = buyerAddress;
	}

	public final static ColExpr DATA_TYPE = new ColExpr(ReceivingOrderMapper.COLUMN___DATA_TYPE);
	@Column(name = ReceivingOrderMapper.COLUMN___DATA_TYPE, label = "Type", type = ColumnType.ENUMERATE)
	@Getter private tpv.bros.common.enumerate.ReceivingOrderDataType dataType;
	public void setDataType(tpv.bros.common.enumerate.ReceivingOrderDataType dataType) {
		this.setter("dataType");
		this.dataType = dataType;
	}

	public final static ColExpr STATUS = new ColExpr(ReceivingOrderMapper.COLUMN___STATUS);
	@Column(name = ReceivingOrderMapper.COLUMN___STATUS, label = "Status", type = ColumnType.ENUMERATE)
	@Getter private tpv.bros.common.enumerate.ReceivingOrderStatus status;
	public void setStatus(tpv.bros.common.enumerate.ReceivingOrderStatus status) {
		this.setter("status");
		this.status = status;
	}

}
