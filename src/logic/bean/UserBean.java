package logic.bean;

public class UserBean {

	protected String username;
	protected String mail;
	protected String password;
	protected String name;
	protected String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void fillUserBean(String username, String mail, String password, String name, String phone) {
		this.name = name;
		this.username = username;
		this.mail = mail;
		this.password = password;
		this.phone = phone;
	}

}
