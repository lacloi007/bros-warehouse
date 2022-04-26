package tpv.core.define.enm;

public enum ColumnType {
	ID        // PRIMARY KEY
	, REFERENCE // Reference to another entity
	, MASTER    // Reference to master entity
	, TEXT       // TEXT
	, EMAIL       // EMAIL
	, ENCRYPTION // ENCRYPTION
	, HTML    // HTML
	, URL     // URL
	, DATE      // DATE
	, DATE_TIME // DATE_TIME
	, UNDEFINED

	, SET       // SET (split by "|")
}
