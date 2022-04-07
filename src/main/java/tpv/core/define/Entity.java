package tpv.core.define;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import tpv.core.annotation.Column;
import tpv.core.define.enm.ColumnType;
import tpv.core.query.exprs.ColExpr;

public class Entity {
	/********************************
	 * Column ID
	 ********************************/
	final static String COLUMN___ID = "ID";
	public final static ColExpr ID = new ColExpr(COLUMN___ID);
	@Column(name = COLUMN___ID, type = ColumnType.ID)
	@Getter @Setter private String id;

	/********************************
	 * Column CREATED_BY
	 ********************************/
	final static String COLUMN___CREATED_BY = "CREATED_BY";
	public final static ColExpr CREATED_BY = new ColExpr(COLUMN___CREATED_BY);
	@Column(name = COLUMN___CREATED_BY, type = ColumnType.REFERENCE, referTo = UserEntity.class)
	@Getter @Setter private String createdBy;

	/********************************
	 * Column CREATED_DATE
	 ********************************/
	final static String COLUMN___CREATED_DATE = "CREATED_DATE";
	public final static ColExpr CREATED_DATE = new ColExpr(COLUMN___CREATED_DATE);
	@Column(name = COLUMN___CREATED_DATE, type = ColumnType.DATE_TIME)
	@Getter @Setter private LocalDate createdDate;

	/********************************
	 * Column UPDATED_BY
	 ********************************/
	final static String COLUMN___UPDATED_BY = "UPDATED_BY";
	public final static ColExpr UPDATED_BY = new ColExpr(COLUMN___UPDATED_BY);
	@Column(name = COLUMN___UPDATED_BY, type = ColumnType.REFERENCE, referTo = UserEntity.class)
	@Getter @Setter private String updatedBy;

	/********************************
	 * Column UPDATED_DATE
	 ********************************/
	final static String COLUMN___UPDATED_DATE = "UPDATED_DATE";
	public final static ColExpr UPDATED_DATE = new ColExpr(COLUMN___UPDATED_DATE);
	@Column(name = COLUMN___UPDATED_DATE, type = ColumnType.DATE_TIME)
	@Getter @Setter private String updatedDate;
}
