package logic.bean;

public class OrarioBean {
	private String day;
	String hour;
	private String mailB;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMailB() {
		return mailB;
	}

	public void setMailB(String mailB) {
		this.mailB = mailB;
	}

	public void fillOrarioB(String day, String hour, String mailB) {
		this.day = day;
		this.hour = hour;
		this.mailB = mailB;
	}

}
