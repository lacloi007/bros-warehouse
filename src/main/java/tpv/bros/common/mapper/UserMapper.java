package tpv.bros.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import tpv.bros.common.table.User;
import tpv.core.Entities;
import tpv.core.Entities.FieldInfo;
import tpv.core.Entities.TableInfo;
import tpv.core.annotation.Mapper;
import tpv.core.define.enm.ColumnType;

@Mapper(User.class)
public class UserMapper extends EntityMapper<User> {
	public final static String COLUMN___USERNAME = "USERNAME";
	public final static String COLUMN___PASSWORD = "PASSWORD";
	public final static String COLUMN___FIRST_NAME = "FIRST_NAME";
	public final static String COLUMN___LAST_NAME = "LAST_NAME";
	public final static String COLUMN___SUR_NAME = "SUR_NAME";
	public final static String COLUMN___ROLES = "ROLES";

	@SuppressWarnings("unchecked")
	@Override
	public User mapping(ResultSet rs, Set<String> columnNames, int rowNum) throws SQLException {
		User user = new User();
		PropertyAccessor accessor = PropertyAccessorFactory.forBeanPropertyAccess(user);

		baseMapper(user, rs, columnNames);
		TableInfo table = Entities.tblInfo(User.class);
		for (FieldInfo field: table.databaseFields.values()) {
			ColumnType type = field.getColumn().type();
			String databaseField = field.getDatabaseField();
			String declaredField = field.getDeclaredField();
			if (columnNames.contains(field.getDatabaseField())) {
				if (type == ColumnType.DATE || type == ColumnType.DATE_TIME) {
					LocalDate localDate = rs.getDate(databaseField).toLocalDate();
					accessor.setPropertyValue(declaredField, localDate);
				}

				// other case
				else {
					String string = rs.getString(databaseField);
					if (string != null) {
						if (type == ColumnType.SET) {
							Set<String> current = (Set<String>) accessor.getPropertyValue(declaredField);
							current.addAll(List.of(string.split(SPLITTER)));
						}

						else {
							accessor.setPropertyValue(declaredField, string);
						}
					}
				}
			}
		}

		/**
		if (columnNames.contains(COLUMN___USERNAME)) user.setUsername(rs.getString(COLUMN___USERNAME));
		if (columnNames.contains(COLUMN___FIRST_NAME)) user.setFirstName(rs.getString(COLUMN___FIRST_NAME));
		if (columnNames.contains(COLUMN___LAST_NAME)) user.setLastName(rs.getString(COLUMN___LAST_NAME));
		if (columnNames.contains(COLUMN___PASSWORD)) user.setPassword(rs.getString(COLUMN___PASSWORD));
		if (columnNames.contains(COLUMN___SUR_NAME)) user.setSurName(rs.getString(COLUMN___SUR_NAME));
		if (columnNames.contains(COLUMN___ROLES)) user.getRoles().addAll(List.of(rs.getString(COLUMN___ROLES).split(SPLITTER)));
		*/
		return user;
	}
}
