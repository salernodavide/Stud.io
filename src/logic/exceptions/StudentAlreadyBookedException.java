package logic.exceptions;

import java.sql.SQLException;

public class StudentAlreadyBookedException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentAlreadyBookedException(String message) {
		super("Problema posti:" + message);
	}

	public StudentAlreadyBookedException(Throwable cause) {
		super(cause);
	}

	public StudentAlreadyBookedException(String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}

}
