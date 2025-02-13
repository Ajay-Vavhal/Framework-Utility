package exceptions;

public class SheetNotFoundException extends Exception {
	public SheetNotFoundException(String errMessage) {
		super(errMessage);
	}
}
