package logic.bean;

import logic.entity.Prenotazione;

public class BookSeatBean {
	private Prenotazione prenotazione;
	private String librarianId;
	private String studentId;
	private long numero;
	private String orarioPrenotazione;
	private String usernameStud;
	
	public BookSeatBean() {
		//Default
	}
	
	public BookSeatBean(long numero, String librarianId, String studentId,String orarioPrenotazione, String usernameStud) {
		this.numero = numero;
		this.librarianId = librarianId;
		this.studentId = studentId;
		this.orarioPrenotazione = orarioPrenotazione;
		this.usernameStud = usernameStud;
	}
	
	public BookSeatBean(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public String getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(String librarianId) {
		this.librarianId = librarianId;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
