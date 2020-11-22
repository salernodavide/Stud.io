package logic.bean;

public class StudentBean extends UserBean {

	private boolean isBanned;
	private String surname;
	private byte reportCounter;

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public byte getReportCounter() {
		return reportCounter;
	}

	public void increaseReportingCounter() {
		reportCounter++;
	}
	
	public void decreaseReportingCounter() {
		reportCounter--;
	}

	public void fillStudBean(String surname, boolean isBanned, byte reportCounter) {
		this.surname = surname;
		this.isBanned = isBanned;
		this.reportCounter = reportCounter;
	}

}
