package exceptions;

public class NoRowFoundException extends Exception {

	private static final long serialVersionUID = 2529030748503902800L;

	public NoRowFoundException(String errMessage) {
		super(errMessage);
		
	}
	

}
