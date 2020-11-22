package logic.exceptions;

import java.sql.SQLException;

public class ReportDeleteException extends SQLException {
	private static final long serialVersionUID = 1L;

	public ReportDeleteException() {
		super("Error while deleting report");
	}

	public ReportDeleteException(Throwable cause) {
		super(cause);
	}

	public ReportDeleteException(String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
