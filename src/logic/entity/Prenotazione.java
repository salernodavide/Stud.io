package logic.entity;

public class Prenotazione {
	private String biblioteca;
	private String utente;
	private long numero;
	private String orarioPrenotazione;
	private String usernameStud;

	public Prenotazione() {
	}

	public Prenotazione(String biblioteca, String utente, long numero, String orarioDiPrenotazione, String username) {
		this.biblioteca = biblioteca;
		this.utente = utente;
		this.numero = numero;
		this.orarioPrenotazione = orarioDiPrenotazione;
		this.usernameStud = username;
	}

	public String getBiblioteca() {
		return biblioteca;
	}

	public String getUser() {
		return utente;
	}

	public long getNumero() {
		return numero;
	}

	public String getOrarioPrenotazione() {
		return orarioPrenotazione;
	}

	public void setOrarioPrenotazione(String orarioPrenotazione) {
		this.orarioPrenotazione = orarioPrenotazione;
	}

	public String getUsernameStud() {
		return usernameStud;
	}

	public void setUsernameStud(String usernameStud) {
		this.usernameStud = usernameStud;
	}

}
