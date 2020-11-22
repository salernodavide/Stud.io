package logic.exceptions;

public class FeedbackCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FeedbackCreationException(String message) {
		super("Not expected int:" + message);
	}

	public FeedbackCreationException(Throwable cause) {
		super(cause);
	}

	public FeedbackCreationException(String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}

}
