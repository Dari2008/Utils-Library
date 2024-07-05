package javad.utils.JDTest.Exception;

public class JDTestException extends Exception {


	private static final long serialVersionUID = 1L;

	public JDTestException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

	
	
}
