package logic.exceptions;

import java.sql.SQLException;

public class ReportUpdateException extends SQLException{
	private static final long serialVersionUID = 1L;

	public ReportUpdateException() {
		super("Error while updating report");
	}

	public ReportUpdateException(Throwable cause) {
		super(cause);
	}

	public ReportUpdateException(String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
