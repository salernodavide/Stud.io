package logic.control;

import java.sql.SQLException;
import java.util.logging.Logger;

import logic.bean.LibrBean;
import logic.dao.LibraryDao;

public class LibrarianSettingsController {
	private LibrBean librInfoB;
	private LibraryDao librDao;
	static Logger myLogger = Logger.getLogger("logger");
	private static LibrarianSettingsController instance = null;

	protected LibrarianSettingsController() {
		this.librInfoB = new LibrBean();
		this.librDao = new LibraryDao();
	}

	/*
	 * Metodo per richiedere l istanza signleton di controller
	 */

	public static LibrarianSettingsController getLibrarianSettingsController() {
		if (LibrarianSettingsController.instance == null)
			LibrarianSettingsController.instance = new LibrarianSettingsController();
		return instance;
	}

	public void updateLibraryInfo(String librarianId) throws SQLException {
		if (!librInfoB.getMail().isEmpty())
			librDao.update("mailBiblioteca", librInfoB.getMail(), librarianId);
		if (!librInfoB.getPassword().isEmpty())
			librDao.update("passwordBiblioteca", librInfoB.getPassword(), librarianId);
		if (!librInfoB.getUsername().isEmpty())
			librDao.update("username", librInfoB.getUsername(), librarianId);
		if (!librInfoB.getName().isEmpty())
			librDao.update("nomeBiblioteca", librInfoB.getName(), librarianId);
		if (!librInfoB.getPhone().isEmpty())
			librDao.update("telefonoBiblioteca", librInfoB.getPhone(), librarianId);
		if (librInfoB.getCapacity() != 0)
			librDao.updatePosti(librInfoB.getCapacity(), librarianId, "posti");
		if (!librInfoB.getCity().isEmpty())
			librDao.update("citta", librInfoB.getCity(), librarianId);
		if (!librInfoB.getAddress().isEmpty())
			librDao.update("indirizzo", librInfoB.getAddress(), librarianId);
	}


	public LibrBean getLibrInfoB() {
		return librInfoB;
	}

	public void setLibrInfoB(LibrBean librInfoB) {
		this.librInfoB = librInfoB;
	}

	public LibraryDao getLibrDao() {
		return librDao;
	}

	public void setLibrDao(LibraryDao librDao) {
		this.librDao = librDao;
	}

}
