package tpv.bros.common.table;

import lombok.Getter;
import tpv.bros.common.mapper.WarehouseMapper;
import tpv.core.annotation.Column;
import tpv.core.annotation.Table;
import tpv.core.define.enm.ColumnType;
import tpv.core.query.exprs.ColExpr;

@Table(name = "BROS_WAREHOUSE", prefix = "E01")
public class Warehouse extends Entity {

	public final static ColExpr PHONE_NUMBER = new ColExpr(WarehouseMapper.COLUMN___PHONE_NUMBER);
	@Column(name = WarehouseMapper.COLUMN___PHONE_NUMBER, label = "Phone number", type = ColumnType.TEXT, mandatory = true)
	@Getter private String phoneNumber;
	public void setPhoneNumber(String phoneNumber) {
		this.setter("phoneNumber");
		this.phoneNumber = phoneNumber;
	}

	public final static ColExpr ADDRESS = new ColExpr(WarehouseMapper.COLUMN___ADDRESS);
	@Column(name = WarehouseMapper.COLUMN___ADDRESS, label = "Address", type = ColumnType.TEXT)
	@Getter private String address;
	public void setAddress(String address) {
		this.setter("address");
		this.address = address;
	}

	public final static ColExpr NOTE = new ColExpr(WarehouseMapper.COLUMN___NOTE);
	@Column(name = WarehouseMapper.COLUMN___NOTE, label = "Note", type = ColumnType.TEXT)
	@Getter private String note;
	public void setNote(String note) {
		this.setter("note");
		this.note = note;
	}

}
