package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import logic.entity.Prenotazione;
import logic.exceptions.NotAvalibleSeatsException;

public class PrenotazioneDao extends GenericDao {
	static Logger myLogger = Logger.getLogger("logger");
	Connection con;
	PreparedStatement ps;
	
	public PrenotazioneDao() {
		con=Db.getInstance().getConnection();
	}

	// INSERT
	public int insert(String mailB, String mailS, String username) throws SQLException {
		int status = 0;
		try {

			ps = con.prepareStatement(
					"INSERT INTO Prenotazione(mailBiblioteca,mailStudente,username,orarioPrenotazione) VALUES(?,?,?,now())");
			ps.setString(1, mailB);
			ps.setString(2, mailS);
			ps.setString(3, username);
			status = ps.executeUpdate();
			ps = con.prepareStatement("INSERT INTO recent_student(mailBiblioteca, mailStudent, time) VALUES(?,?,?)");
			ps.setString(1, mailB);
			ps.setString(2, mailS);
			ps.setString(3, LocalDateTime.now().toString());
			status = ps.executeUpdate();
		} catch (NotAvalibleSeatsException exc) {
			myLogger.info(exc.toString());
		} catch(MySQLIntegrityConstraintViolationException e){
			//Nothing to do
		}
		finally {
			
			ps.close();
		}
		return status;
	}

	public List<Prenotazione> select(String mail, String tipo) throws SQLException {
		ResultSet rs = null;
		List<Prenotazione> bookList = new ArrayList<>();
		try {

			ps = null;
			if (tipo.equals("mainS"))
				ps = con.prepareStatement("SELECT * FROM Prenotazione WHERE mailStudente = ?");
			if (tipo.equals("mainB"))
				ps = con.prepareStatement("SELECT * FROM Prenotazione WHERE mailBiblioteca = ?");
			fillSelectStatement(ps, mail, null);
			rs = ps.executeQuery();
			while (rs.next()) {
				bookList.add(new Prenotazione(rs.getString(MAILB), rs.getString(MAIL), rs.getLong("numeroPrenotazione"),
						rs.getTime("orarioPrenotazione").toString(), rs.getString("username")));
			}
			return bookList;

		}

		catch (Exception e) {
			myLogger.info("Select prenotazione fallito");// definire un eccezione apposita con logger serio
		} finally {
			if(rs!=null) {
				rs.close();
			}
			ps.close();
		}
		return bookList;
	}

}
