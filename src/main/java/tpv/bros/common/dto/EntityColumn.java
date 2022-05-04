package tpv.bros.common.dto;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import tpv.core.Entities;
import tpv.core.Entities.EnumInfo;
import tpv.core.Entities.FieldInfo;
import tpv.core.Entities.TableInfo;
import tpv.core.define.enm.ColumnType;

@Getter
public class EntityColumn {
	/** INPUT **/
	private String table, column, field, extendClass = "";

	/** RUNTIME **/
	private String html, label;

	public EntityColumn(String table, String column, String extendClass) {
		this.table = table;
		this.column = column;
		if (StringUtils.isNotBlank(extendClass))
			this.extendClass = extendClass;
		this.initialize();
	}

	void initialize() {
		TableInfo tblInfo = Entities.tblInfoByTableName(table);
		FieldInfo colInfo = tblInfo.databaseField(column);
		ColumnType colType = colInfo.getColumn().type();
		this.label = MessageFormat.format("<label th:for=\"{0}\">{1}</label>"
				, colInfo.getDeclaredField()
				, colInfo.getColumn().label());
		this.field = colInfo.getDeclaredField();
		String mainClass = "w3-input w3-border";

		// for TEXTAREA
		if (ColumnType.TEXTAREA == colType) {
			html = MessageFormat.format(
					"<{0}"
					+ " id=\"{2}\""
					+ " ng-model=\"form.{2}\""
					+ " ng-class=\"'{''w3-border-red'': error.hasError('''{2}''')'}\""
					+ " class=\"{6} {5}\" placeholder=\"Enter {3}\"" 
					+ "{4}></{0}>"
					, "textarea" // {0}
					, ""         // {1}
					, field      // {2}
					, colInfo.getColumn().label() // {3}
					, colInfo.getColumn().mandatory()? " required": "" // {4}
					, extendClass // {5}
					, mainClass // {6}
			);
		}

		// for ENUMERATE
		if (ColumnType.ENUMERATE == colType) {
			StringBuilder options = new StringBuilder();
			if (colInfo.getEnums().isEmpty() == false) {
				options.append("<option value=\"\">-- Select --</option>");
				for (EnumInfo ein : colInfo.getEnums()) {
					if (options.length() > 0)
						options.append(System.lineSeparator());
					options.append(String.format("<option value=\"%s\">%s</option>", ein.getName(), ein.getLabel()));
				}
			}

			html = MessageFormat.format(
					"<{0}"
					+ " id=\"{2}\""
					+ " ng-model=\"form.{2}\""
					+ " ng-class=\"'{''w3-border-red'': error.hasError('''{2}''')'}\""
					+ " class=\"{7} {6}\" placeholder=\"Enter {3}\"" 
					+ "{4}>{5}</{0}>"
					, "select"
					, ""
					, field
					, colInfo.getColumn().label()
					, colInfo.getColumn().mandatory()? " required": "" // {4}
					, options.toString() // {5}
					, extendClass // {6}
					, mainClass
			);
		}

		if (StringUtils.isBlank(html)) {
			String attrType = "text";
			if (ColumnType.ENCRYPTION == colType) attrType = "password";
			if (ColumnType.NUMERIC == colType)    attrType = "number";
			if (ColumnType.EMAIL == colType)      attrType = "email";
			if (ColumnType.DATE_TIME == colType)  attrType = "datetime-local";
			if (ColumnType.DATE == colType)       attrType = "date";
			if (ColumnType.CHECKBOX == colType) { attrType = "checkbox"; mainClass = "w3-check"; }

			html = MessageFormat.format(
					"<{0}"
					+ " type=\"{1}\""
					+ " id=\"{2}\""
					+ " ng-model=\"form.{2}\""
					+ " ng-class=\"'{''w3-border-red'': error.hasError('''{2}''')'}\""
					+ " class=\"{6} {5}\" placeholder=\"Enter {3}\"" 
					+ "{4}>"
					, "input"
					, attrType
					, field
					, colInfo.getColumn().label()
					, colInfo.getColumn().mandatory()? " required": "" // {4}
					, extendClass // {5}
					, mainClass // {6}
			);
		}
	}
}
