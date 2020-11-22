package logic.control;

import java.sql.SQLException;
import java.util.logging.Logger;

import logic.dao.PrenotazioneDao;
import logic.entity.Library;
import logic.entity.Prenotazione;
import logic.entity.Student;
import logic.exceptions.StudentAlreadyBookedException;

public class BookSeatController {
	private PrenotazioneDao prenotazioneDao;
	private Library library;
	private Prenotazione prenotazione;
	static Logger myLogger = Logger.getLogger("logger");
	
	public BookSeatController(Library library) {
		this.library = library;
		this.prenotazioneDao = new PrenotazioneDao();
	}
	
	public BookSeatController() {
		this.prenotazioneDao = new PrenotazioneDao();
	}
	
	/*
	 * Metodo per prenotare posto
	 */
	public void bookSeat(Student student, Library library) {
		try {
			if (prenotazioneDao.select(student.getMail(), "mainS").isEmpty()) {
				prenotazioneDao.insert(library.getMail(), student.getMail(), student.getUsername());
			} else {
				throw new StudentAlreadyBookedException("non puoi prenotarti due volte");
			}
		} catch (StudentAlreadyBookedException e) {
			myLogger.info(e.toString());
		}catch (SQLException exc) {
			exc.printStackTrace();
		}
		
	}
	
	
	/*
	 * Metodo per cancellare prenotazione
	 */
	public boolean deleteBook() {
		//se prenotazione esiste (sicuro) eliminala
		return true;
	}
	
	/*
	 * Metodo per validare prenotazione, true valida, false invalida
	 */
	public void validBook(Prenotazione prenotazione, Boolean confirm) {
		//Da fare
	}
	
	/*
	 * Timer book
	 */
	public void timerBook(Prenotazione prenotazione) {
		//mentre il timer < 15:00 
		//	do nothing
		//fine ciclo: valida prenotazione (prenotazione, false)
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}
}
