package logic.entity;

import java.util.List;
import logic.pattern.AbstractObservable;

public class User extends AbstractObservable{
	protected String name;
	protected String userName;
	protected String mail;
	protected String password;
	protected String phone;
	protected List<Report> reports;

	public String getName() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void fillUser(String username, String mail, String password, String name, String phone) {
		this.name = name;
		this.userName = username;
		this.mail = mail;
		this.password = password;
		this.phone = phone;
	}
	
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
		notifyObservers();
	}
	
	public void addReport(Report report) {
		this.reports.add(report);
		notifyObservers();
	}
	
	public void removeReport(Report report) {
		this.reports.remove(report);
		notifyObservers();
	}
	
	public void updateReportStatus(int index,String status) {
		this.reports.get(index).setStatus(status);
		notifyObservers();
	}

}
