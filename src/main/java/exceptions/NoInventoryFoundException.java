package exceptions;

public class NoInventoryFoundException extends Exception {
	public NoInventoryFoundException(String errMessage) {
		super(errMessage);
	}

}
