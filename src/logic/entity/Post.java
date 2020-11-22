package logic.entity;

public class Post {

	private String testo;
	private String title;
	private String biblioteca;
	private String data;

	public Post() {
		this.title = null;
		this.biblioteca = null;
		this.testo = null;
		this.data = null;
	}

	public Post(String testo, String mailB, String data, String title) {
		this.testo = testo;
		this.biblioteca = mailB;
		this.data = data;
		this.title = title;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(String biblioteca) {
		this.biblioteca = biblioteca;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
