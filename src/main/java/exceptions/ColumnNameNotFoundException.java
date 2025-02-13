package exceptions;

public class ColumnNameNotFoundException extends Exception {
	public ColumnNameNotFoundException(String errMessage) {
		super(errMessage);
	}

}
