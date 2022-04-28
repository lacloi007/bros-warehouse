package tpv.bros.common.table;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import tpv.bros.common.dto.EntityErrors;
import tpv.bros.common.mapper.EntityMapper;
import tpv.core.annotation.Column;
import tpv.core.define.enm.ColumnType;
import tpv.core.query.exprs.ColExpr;

public class Entity {
	/********************************
	 *              ERRORS DEFINITION
	 ********************************/
	public final EntityErrors errors = new EntityErrors();

	/********************************
	 *                CORE DEFINITION
	 ********************************/
	@JsonIgnore
	public final Set<String> updatedFields = new LinkedHashSet<String>();
	public void setter(String field) {
		updatedFields.add(field);
	}

	/********************************
	 * Column ID
	 ********************************/
	public final static ColExpr ID = new ColExpr(EntityMapper.COLUMN___ID);
	@Column(name = EntityMapper.COLUMN___ID, type = ColumnType.ID)
	@Getter @Setter private String id;

	/********************************
	 * Column CREATED_BY
	 ********************************/
	public final static ColExpr CREATED_BY = new ColExpr(EntityMapper.COLUMN___CREATED_BY);
	@Column(name = EntityMapper.COLUMN___CREATED_BY, type = ColumnType.REFERENCE, referTo = User.class)
	@Getter @Setter private String createdBy;

	/********************************
	 * Column CREATED_DATE
	 ********************************/
	public final static ColExpr CREATED_DATE = new ColExpr(EntityMapper.COLUMN___CREATED_DATE);
	@Column(name = EntityMapper.COLUMN___CREATED_DATE, type = ColumnType.DATE_TIME)
	@Getter @Setter private LocalDate createdDate;

	/********************************
	 * Column UPDATED_BY
	 ********************************/
	public final static ColExpr UPDATED_BY = new ColExpr(EntityMapper.COLUMN___UPDATED_BY);
	@Column(name = EntityMapper.COLUMN___UPDATED_BY, type = ColumnType.REFERENCE, referTo = User.class)
	@Getter @Setter private String updatedBy;

	/********************************
	 * Column UPDATED_DATE
	 ********************************/
	public final static ColExpr UPDATED_DATE = new ColExpr(EntityMapper.COLUMN___UPDATED_DATE);
	@Column(name = EntityMapper.COLUMN___UPDATED_DATE, type = ColumnType.DATE_TIME)
	@Getter @Setter private LocalDate updatedDate;
}
