package logic.exceptions;

import java.sql.SQLException;

public class EmptyTextFieldException extends SQLException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyTextFieldException() {
		super("This field can't be empty");
	}

	public EmptyTextFieldException(String message) {
		super("TextField problem:" + message);
	}

	public EmptyTextFieldException(Throwable cause) {
		super(cause);
	}

	public EmptyTextFieldException(String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}

}
