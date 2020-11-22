package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import logic.entity.Servizio;

public class ServizioDao extends GenericDao {
	private Connection con;
	private PreparedStatement ps;

	static Logger myLogger = Logger.getLogger("logger");
	
	public ServizioDao() {
		con=Db.getInstance().getConnection();
	}

	// INSERT SERVIZIO
	public int insert(String idBiblioteca, String servizio, String descrizione) throws SQLException {
		int status = 0;
		try {

			ps = con.prepareStatement("INSERT INTO Servizio(mailBiblioteca,nome,descrizione) VALUES(?,?,?)");
			ps.setString(1, idBiblioteca);
			ps.setString(2, servizio);
			ps.setString(3, descrizione);
			status = ps.executeUpdate();

		}

		catch (Exception e) {

			myLogger.info("Salvataggio servizio fallito");// definire un eccezione apposita con logger serio

		} finally {
			
			ps.close();
		}
		return status;
	}

	public List<Servizio> select(String id1) {
		ResultSet rs = null;
		List<Servizio> serviceList = new ArrayList<>();
		try {
			
			ps = con.prepareStatement("SELECT * FROM Servizio WHERE mailBiblioteca = ?");
			fillSelectStatement(ps, id1, null);
			rs = ps.executeQuery();
			while (rs.next()) {
				serviceList.add(new Servizio(rs.getString(NOMES), rs.getString(MAILB), rs.getString(DESCRIZIONE)));
			}
			
			return serviceList;

		}

		catch (Exception e) {
			myLogger.info("Select servizi fallito");// definire un eccezione apposita con logger serio
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return serviceList;
	}

	// UPDATE ORARIO
	public int updateServizio(String nome, String newDesc, String idBib) throws SQLException {
		int status = 0;
		try {

			ps = con.prepareStatement("UPDATE Servizio SET descrizione = ? WHERE nome = ? AND mailBiblioteca = ?");
			ps.setString(1, newDesc);
			ps.setString(2, nome);
			ps.setString(3, idBib);
			status = ps.executeUpdate();

		}

		catch (Exception e) {
			myLogger.info("Aggiornamento Servizio fallito");// definire un eccezione apposita con logger serio
		} finally {
			
			ps.close();
		}
		return status;
	}

}
