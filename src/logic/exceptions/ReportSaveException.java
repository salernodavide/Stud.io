package logic.exceptions;

import java.sql.SQLException;

public class ReportSaveException extends SQLException {
	
	private static final long serialVersionUID = 1L;

	public ReportSaveException() {
		super("Error while saving report");
	}

	public ReportSaveException(Throwable cause) {
		super(cause);
	}

	public ReportSaveException(String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}


}
