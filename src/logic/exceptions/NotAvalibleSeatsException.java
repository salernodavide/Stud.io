package logic.exceptions;

import java.sql.SQLException;

public class NotAvalibleSeatsException extends SQLException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAvalibleSeatsException(String message) {
		super("Problema posti:" + message);
	}

	public NotAvalibleSeatsException(Throwable cause) {
		super(cause);
	}

	public NotAvalibleSeatsException(String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}

}
