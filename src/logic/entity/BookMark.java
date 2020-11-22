package logic.entity;

public class BookMark {

	String bibId;
	String studId;
	int id;

	public BookMark(String bibId, String studId, int id) {
		this.bibId = bibId;
		this.studId = studId;
		this.id = id;
	}

	public String getBibId() {
		return bibId;
	}

	public void setBibId(String bibId) {
		this.bibId = bibId;
	}

	public String getStudId() {
		return studId;
	}

	public void setStudId(String studId) {
		this.studId = studId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
