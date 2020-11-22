package logic.bean;

import logic.exceptions.EmptyTextFieldException;

public class ReportBean {

	private String title;
	private String description;
	private String studentId;
	private String status;
	private String libraryId;
	private long reportId;

	public ReportBean() {
		// default constructor
	}

	public ReportBean(String title, String description, String studentId, String libraryId, String status,
			long reportId) {
		this.title = title;
		this.description = description;
		this.studentId = studentId;
		this.libraryId = libraryId;
		this.status = status;
		this.reportId = reportId;
	}

	public ReportBean(String title, String description) throws EmptyTextFieldException {
		if (title.trim().equals(""))

			throw new EmptyTextFieldException();
		else
			this.title = title;

		if (description.trim().equals(""))

			throw new EmptyTextFieldException();
		else
			this.description = description;
	}

	public void setTitle(String title) throws EmptyTextFieldException {

		if (title.trim().equals(""))

			throw new EmptyTextFieldException();
		else
			this.title = title;

	}

	public String getTitle() {

		return title;

	}

	public void setDescription(String description) throws EmptyTextFieldException {

		if (description.trim().equals(""))

			throw new EmptyTextFieldException();
		else
			this.description = description;

	}

	public String getDescription() {

		return description;

	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String user) {
		this.studentId = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

}
