package exceptions;

public class FileDoesNotExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public FileDoesNotExistsException(String errMessage) {
		super(errMessage);
	}

}
