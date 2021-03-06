package tpv.core.query;

public class QueryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public QueryException() {
		super();
	}
	
	public QueryException(String message) {
		super(message);
	}
	
	public QueryException(Throwable throwable) {
		super(throwable);
	}
}
