package logic.bean;

public class MessageBean {
	private long id;
	private String text;
	private String title;
	private String librarianId;
	private String studentId;
	
	public MessageBean(long id, String title, String text, String librarianId, String studentId) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.librarianId = librarianId;
		this.studentId = studentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(String librarianId) {
		this.librarianId = librarianId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
