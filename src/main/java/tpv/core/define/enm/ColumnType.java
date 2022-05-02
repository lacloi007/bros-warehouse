package tpv.core.define.enm;

public enum ColumnType {
	UNDEFINED
	, ID             // PRIMARY KEY
	, REFERENCE    // Reference to another entity
	, MASTER       // Reference to master entity
	, TEXT         // TEXT
	, EMAIL        // EMAIL
	, ENCRYPTION   // ENCRYPTION
	, HTML         // HTML
	, URL          // URL
	, DATE         // DATE
	, DATE_TIME    // DATE_TIME
	, CHECKBOX    // DATE_TIME

	, SET          // SET (split by "|")
	, ENUMERATE    // ENUM
	, INTEGER, LONG, DECIMAL
}
