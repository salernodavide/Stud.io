package logic.entity;

public class Orario {
	private String giorno;
	String orari;
	private String idBiblioteca;

	public Orario(String giorno, String orario, String idBib) {
		this.giorno = giorno;
		this.orari = orario;
		this.idBiblioteca = idBib;
	}

	public String getOrario() {
		return orari;
	}

	public void setOrario(String orario) {
		this.orari = orario;
	}

	public String getGiorno() {
		return giorno;
	}

	public String getidBiblioteca() {
		return idBiblioteca;
	}

	public void fillOrario(String giorno, String orario, String mailB) {
		this.giorno = giorno;
		this.orari = orario;
		this.idBiblioteca = mailB;

	}
}
