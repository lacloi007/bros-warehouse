package tpv.core.define.enm;

public enum ColumnType {
	ID        // PRIMARY KEY
	, REFERENCE // Reference to another entity
	, MASTER    // Reference to master entity
	, TEXT       // TEXT
	, ENCRYPTION // ENCRYPTION
	, HTML    // HTML
	, URL     // URL
	, DATE      // DATE
	, DATE_TIME // DATE_TIME
	, UNDEFINED
}
