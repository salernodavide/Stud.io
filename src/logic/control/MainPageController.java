package logic.control;

import logic.bean.LibrBean;
import logic.dao.LibraryDao;
import logic.dao.PrenotazioneDao;
import logic.entity.Library;

public class MainPageController implements GenericController {
	protected Library libraryInfo;
	protected LibrBean libraryInfoB;
	protected LibraryDao librDao;
	protected PrenotazioneDao bookDao;

	/*
	 * Metodo per riempire la bean e la entity, l ordine dei parametri e: String
	 * username,String mail,String password,String name,String phone,String address,
	 * String city,String capacity,String postiOccupati
	 */
	@Override
	public void fillBean(String... arg) {
		libraryInfoB.fillUserBean(arg[0], arg[1], arg[2], arg[3], arg[4]);
		libraryInfoB.fillLibrBean(arg[5], arg[6], Integer.valueOf(arg[7]), Integer.valueOf(arg[8]));
	}

	@Override
	public void fillEntity(String... arg) {
		libraryInfo.fillUser(arg[0], arg[1], arg[2], arg[3], arg[4]);
		libraryInfo.fillLibrary(arg[5], arg[6], Integer.valueOf(arg[7]), Integer.valueOf(arg[8]));

	}

	public Library getLibrInfo() {
		return libraryInfo;
	}

	public void setLibrInfo(Library librInfo) {
		this.libraryInfo = librInfo;
	}

	public LibrBean getLibrInfoB() {
		return libraryInfoB;
	}

	public void setLibrInfoB(LibrBean librInfoB) {
		this.libraryInfoB = librInfoB;
	}

	public LibraryDao getLibrDao() {
		return librDao;
	}

	public void setLibrDao(LibraryDao librDao) {
		this.librDao = librDao;
	}

	public PrenotazioneDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(PrenotazioneDao bookDao) {
		this.bookDao = bookDao;
	}

}
