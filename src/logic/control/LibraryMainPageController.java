package logic.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import logic.bean.BookSeatBean;
import logic.bean.LibrBean;
import logic.dao.LibraryDao;
import logic.dao.PrenotazioneDao;
import logic.entity.Library;
import logic.entity.Prenotazione;

public class LibraryMainPageController extends MainPageController {
	private List<Prenotazione> books;
	static final String POSTIO = "postiOccupati";
	static Logger myLogger = Logger.getLogger("logger");
	private static LibraryMainPageController instance = null;

	protected LibraryMainPageController() {
		this.libraryInfoB = new LibrBean();
		this.libraryInfo = new Library();
		this.bookDao = new PrenotazioneDao();
		this.librDao = new LibraryDao();
		this.books = new ArrayList<>();
	}

	/*
	 * Metodo per richiedere l istanza signleton di controller
	 */

	public static LibraryMainPageController getLibraryMainPageController() {
		if (LibraryMainPageController.instance == null)
			LibraryMainPageController.instance = new LibraryMainPageController();
		return instance;
	}

	public void updateLibraryMainPage() throws SQLException {
		/*
		 * Update delle info della bilioteca
		 */
		List<Library> rsL = librDao.select(libraryInfo.getMail(), libraryInfo.getPassword());
		for (byte i = 0; i < rsL.size(); i++) {

			libraryInfoB.fillUserBean(rsL.get(i).getUsername(), rsL.get(i).getMail(), rsL.get(i).getPassword(),
					rsL.get(i).getName(), rsL.get(i).getPhone());
			libraryInfoB.fillLibrBean(rsL.get(i).getIndirizzo(), rsL.get(i).getCity(), rsL.get(i).getCapacity(),
					rsL.get(i).getPostiOccupati());

			libraryInfo = rsL.get(i);
		}

		books = bookDao.select(libraryInfo.getMail(), "mainB");

	}

	public void confirmPrenotatione(String studentId, String bibId) throws SQLException {
		bookDao.delete("Prenotazione", studentId, null);
		librDao.updatePosti((libraryInfo.getPostiOccupati() + 1), bibId, POSTIO);
		this.updateLibraryMainPage();
	}

	public void deletePrenotatione(String studentId) throws SQLException {
		bookDao.delete("Prenotazione", studentId, null);
		this.updateLibraryMainPage();
	}

	public void updateSeats(String tipo) {
		
		if (tipo.equals("+") && libraryInfo.getPostiOccupati() < libraryInfo.getCapacity()) {
			
			libraryInfo.setPostiOccupati(libraryInfo.getPostiOccupati() + 1, "occupati");
			try {
				librDao.updatePosti((libraryInfo.getPostiOccupati()), libraryInfo.getMail(), POSTIO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (tipo.equals("-") && libraryInfo.getPostiOccupati() > 0) {
		 	libraryInfo.setPostiOccupati(libraryInfo.getPostiOccupati() - 1, "occupati");
			try {
				librDao.updatePosti((libraryInfo.getPostiOccupati()), libraryInfo.getMail(), POSTIO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

		}
	}

	public List<Prenotazione> getBooks() {
		return books;
	}
	
	public List<BookSeatBean> getBooksBean(){
		try {
			books = bookDao.select(libraryInfo.getMail(), "mainB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<BookSeatBean> list = new ArrayList<>();
		for (int i=0; i<books.size(); i++) {
			BookSeatBean bookSeatBean = new BookSeatBean(books.get(i).getNumero(), books.get(i).getBiblioteca(), books.get(i).getUser(), books.get(i).getOrarioPrenotazione(), books.get(i).getUsernameStud());
			list.add(bookSeatBean);
		}
		return list;
	}

	public void setBooks(List<Prenotazione> books) {
		this.books = books;
	}

}
