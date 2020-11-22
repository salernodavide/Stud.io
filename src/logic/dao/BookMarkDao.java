package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import logic.entity.BookMark;

public class BookMarkDao extends GenericDao {

	static Logger myLogger = Logger.getLogger("logger");
	private Connection con;
	private PreparedStatement ps;
	
	public BookMarkDao() {
		con=Db.getInstance().getConnection();
	}

// INSERT
	public int insert(String mailB, String mailS) throws SQLException {
		int status = 0;
		try {
			// a sonarlint non piace come bug
			ps = con.prepareStatement("INSERT INTO BookMarks(mailBiblioteca,mailStudente) VALUES(?,?)");
			ps.setString(1, mailB);
			ps.setString(2, mailS);
			status = ps.executeUpdate();
			
		} catch (Exception e) {

			myLogger.info("Salvataggio bookmark fallito");// definire un eccezione apposita con logger serio

		} finally {
			ps.close();
		}
		return status;
	}

	public List<BookMark> select(String mailStud) throws SQLException {
		ResultSet rs = null;
		List<BookMark> bookList = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT * FROM BookMarks WHERE mailStudente = ?");
			fillSelectStatement(ps, mailStud, null);
			rs = ps.executeQuery();
			while (rs.next()) {
				bookList.add(new BookMark(rs.getString(MAILB), rs.getString(MAIL), rs.getInt(NUMERO)));
			}
			
			return bookList;

		}

		catch (SQLException e) {
			myLogger.info("Select bookmark fallito");
		} finally {
			if(rs!=null) {
				rs.close();
			}
			ps.close();
		}
		return bookList;
	}

}
