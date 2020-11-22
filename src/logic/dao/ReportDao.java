package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import logic.constants.ReportConstants;
import logic.entity.Library;
import logic.entity.Report;
import logic.entity.User;
import logic.exceptions.ReportDeleteException;
import logic.exceptions.ReportSaveException;
import logic.exceptions.ReportUpdateException;

public class ReportDao {
	private PreparedStatement ps;
	private Connection conn;
	private static Logger myLogger = Logger.getLogger("logger");

	public ReportDao() {
		conn = Db.getInstance().getConnection();
	}

	public void closeStatement(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Report> getReportFromDbByLibrary(User user) throws SQLException {
		ResultSet rs = null;
		List<Report> reportList = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.report WHERE  mailBiblioteca = ? ORDER BY numero");
			ps.setString(1, user.getMail());
			rs = ps.executeQuery();
			while (rs.next()) {
				reportList.add(new Report(rs.getString(ReportConstants.REPORT_TITLE),
						rs.getString(ReportConstants.REPORT_DESCRIPTION), rs.getLong(ReportConstants.REPORT_ID),
						rs.getString(ReportConstants.REPORT_STUDENT_ID),
						rs.getString(ReportConstants.REPORT_LIBRARY_ID), rs.getString(ReportConstants.REPORT_STATUS)));
			}

		}

		catch (Exception e) {
			myLogger.info("Select report fallito");
		} finally {
			closeStatement(ps);
			rs.close();
		}
		return reportList;
	}

	public List<Report> getReportFromDbByStudent(User sessionUser, Library library) {
		ResultSet rs = null;
		List<Report> reportList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(
					"SELECT * FROM mydb.report WHERE  mailStudente = ? AND mailBiblioteca = ? ORDER BY numero");
			ps.setString(1, sessionUser.getMail());
			ps.setString(2, library.getMail());
			rs = ps.executeQuery();
			while (rs.next()) {
				reportList.add(new Report(rs.getString(ReportConstants.REPORT_TITLE),
						rs.getString(ReportConstants.REPORT_DESCRIPTION), rs.getLong(ReportConstants.REPORT_ID),
						rs.getString(ReportConstants.REPORT_STUDENT_ID),
						rs.getString(ReportConstants.REPORT_LIBRARY_ID), rs.getString(ReportConstants.REPORT_STATUS)));
			}

		}

		catch (Exception e) {
			myLogger.info("Select report fallito");

		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeStatement(ps);
		}
		return reportList;
	}

	public int deleteReportFromDb(Report report) throws ReportDeleteException {
		int status = 0;
		try {

			ps = conn.prepareStatement("DELETE FROM mydb.report WHERE numero=?");
			ps.setLong(1, report.getReportId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			throw new ReportDeleteException();
		} finally {
			closeStatement(ps);
		}
		return status;
	}

	public int saveReportOnDb(Report report) throws ReportSaveException {
		int status = 0;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO mydb.report(testo,mailStudente,mailBiblioteca,titolo,stato) VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, report.getDescription());
			ps.setString(2, report.getStudentId());
			ps.setString(3, report.getLibraryId());
			ps.setString(4, report.getTitle());
			ps.setString(5, report.getStatus());
			status = ps.executeUpdate();

			if (status == 0) {
				throw new ReportSaveException();
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					report.setReportId(generatedKeys.getLong(1));
				} else {
					throw new ReportSaveException();
				}
			}
		} catch (SQLException e) {
			throw new ReportSaveException();
		} finally {

			closeStatement(ps);

		}

		return status;
	}

	public int updateReport(Report report) throws ReportUpdateException {
		int status = 0;
		try {

			ps = conn.prepareStatement("UPDATE mydb.report SET titolo=?, testo= ?, stato=?  WHERE numero=?");
			ps.setString(1, report.getTitle());
			ps.setString(2, report.getDescription());
			ps.setString(3, report.getStatus());
			ps.setLong(4, report.getReportId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			throw new ReportUpdateException();
		} finally {

			closeStatement(ps);

		}
		return status;
	}

}
