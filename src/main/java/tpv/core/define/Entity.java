package tpv.core.define;

import java.time.LocalDate;

import lombok.Data;
import tpv.core.annotation.Column;
import tpv.core.define.enm.ColumnType;

@Data
public class Entity {
	@Column(name = "ID", type = ColumnType.ID)
	private String id;

	@Column(name = "CREATED_BY", type = ColumnType.REFERENCE, referTo = UserEntity.class)
	private String createdBy;

	@Column(name = "CREATED_DATE", type = ColumnType.DATE_TIME)
	private LocalDate createdDate;

	@Column(name = "UPDATED_BY", type = ColumnType.REFERENCE, referTo = UserEntity.class)
	private String updatedBy;

	@Column(name = "UPDATED_DATE", type = ColumnType.DATE_TIME)
	private String updatedDate;
}
