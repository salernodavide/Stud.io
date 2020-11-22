package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class GenericDao {

	protected static Logger myLogger = Logger.getLogger("logger");
	public static final String NUMERO = "numero";
	public static final String POST = "post";
	public static final String FEEDBACK = "feedback";
	public static final String DESCRIZIONE = "descrizione";
	public static final String MESSAGE = "message";
	public static final String TITOLOM = "titoloMessaggio";
	public static final String TESTOM = "testoMessaggio";
	public static final String TITOLO = "titolo";
	public static final String TESTO = "testo";
	public static final String CAPACITY = "posti";
	public static final String POSTIO = "postiOccupati";
	public static final String CITTA = "citta";
	public static final String USERNAMEB = "usernameBiblioteca";
	public static final String MAIL = "mailStudente";
	public static final String STUDENTE = "Studente";
	public static final String NOMES = "nome";
	public static final String COGNOMES = "cognome";
	public static final String USERNAME = "username";
	public static final String TELEFONOS = "telefono";
	public static final String PASSWORD = "password";
	public static final String BIBLIOTECA = "Biblioteca";
	public static final String MAILB = "mailBiblioteca";
	public static final String PASSWORDB = "passwordBiblioteca";
	public static final String NOMEB = "nomeBiblioteca";
	public static final String INDIRIZZO = "indirizzo";
	public static final String SERVIZIO = "Servizio";
	public static final String ORARIO = "Orario";
	public static final String PRENOTAZIONE = "Prenotazione";
	public static final String TELEFONOB = "telefonoBiblioteca";
	public static final String BOOKMARK = "bookmark";
	protected Connection con;
	protected PreparedStatement ps;
	
	public GenericDao() {
		con=Db.getInstance().getConnection();
	}

	// AUTOFILL UPDATE
	public void fillUpdateStatement(PreparedStatement ps, String newValue, String entityId) throws SQLException {
		try {
			ps.setString(1, newValue);
			ps.setString(2, entityId);
		} catch (SQLException e) {
			myLogger.info("Update fill fallito");
		}
	}

	// AUTOFILL DELETE
	public void fillDeleteStatement(PreparedStatement ps, String entityId, String id2) throws SQLException {
		try {
			if (id2 == null)
				ps.setString(1, entityId);
			if (id2 != null) {
				ps.setString(1, entityId);
				ps.setString(2, id2);
			}
		} catch (SQLException e) {
			myLogger.info("Delete fill fallito");
		}

	}

	// AUTOFILL SELECT
	public void fillSelectStatement(PreparedStatement ps, String id1, String id2) throws SQLException {
		try {
			if (id2 != null) {
				ps.setString(1, id1);
				ps.setString(2, id2);
			}
			if (id2 == null)
				ps.setString(1, id1);
		} catch (SQLException e) {
			myLogger.info("Delete fill fallito");
		}

	}

	public int delete(String entity, String bibId, String studId) {
		int status = 0;
		try {

			if (entity.equals(PRENOTAZIONE)) {
				ps = con.prepareStatement("DELETE FROM Prenotazione WHERE mailStudente=?");
				fillDeleteStatement(ps, bibId, null);
			}

			if (entity.equals(STUDENTE)) {
				ps = con.prepareStatement("DELETE FROM Studente WHERE mailStudente=?");
				fillDeleteStatement(ps, bibId, null);
			}

			if (entity.equals(BIBLIOTECA)) {
				ps = con.prepareStatement("DELETE FROM Biblioteca WHERE mailBiblioteca=?");
				fillDeleteStatement(ps, bibId, null);
			}
			if (entity.equals(SERVIZIO)) {
				ps = con.prepareStatement("DELETE FROM SERVIZIO WHERE mailBiblioteca=?");
				fillDeleteStatement(ps, bibId, null);
			}

			if (entity.equals(FEEDBACK)) {
				ps = con.prepareStatement("DELETE FROM Feedback WHERE mailBiblioteca=? AND mailStudente=?");
				fillDeleteStatement(ps, bibId, studId);

			}
			if (entity.equals(BOOKMARK)) {
				ps = con.prepareStatement("DELETE FROM BookMarks WHERE mailBiblioteca=? AND mailStudente=?");
				fillDeleteStatement(ps, bibId, studId);
			}
			if (entity.equals(POST)) {
				ps = con.prepareStatement("DELETE FROM Post WHERE mailBiblioteca=? ");
				fillDeleteStatement(ps, bibId, null);
			}

			status = ps.executeUpdate();
		}

		catch (SQLException e) {
			myLogger.info("Eliminazione fallita");// definire un eccezione apposita con logger serio
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

}
