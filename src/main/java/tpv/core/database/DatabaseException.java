package tpv.core.database;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super();
	}

	public DatabaseException(Throwable throwable) {
		super(throwable);
	}
}
