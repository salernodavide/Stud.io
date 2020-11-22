package logic.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import logic.bean.LibrBean;
import logic.dao.LibraryDao;
import logic.entity.Library;

public class StudentSearchInsertController implements GenericController {
	private List<Library> librInfo;
	private List<LibrBean> librInfoB;
	private LibraryDao librDao;

	static Logger myLogger = Logger.getLogger("logger");
	private static StudentSearchInsertController instance = null;

	protected StudentSearchInsertController() {
		this.librDao = new LibraryDao();
		this.librInfo = new ArrayList<>();
		this.librInfoB = new ArrayList<>();
	}

	public static StudentSearchInsertController getStudentSearchInsertController() {
		if (StudentSearchInsertController.instance == null)
			StudentSearchInsertController.instance = new StudentSearchInsertController();
		return instance;
	}

	@Override
	public void fillBean(String... arg) {
		librInfoB.get(Integer.valueOf(arg[9])).fillUserBean(arg[0], arg[1], arg[2], arg[3], arg[4]);
		librInfoB.get(Integer.valueOf(arg[9])).fillLibrBean(arg[5], arg[6], Integer.valueOf(arg[7]),
				Integer.valueOf(arg[8]));
	}

	@Override
	public void fillEntity(String... arg) {
		librInfo.get(Integer.valueOf(arg[9])).fillUser(arg[0], arg[1], arg[2], arg[3], arg[4]);
		librInfo.get(Integer.valueOf(arg[9])).fillLibrary(arg[5], arg[6], Integer.valueOf(arg[7]),
				Integer.valueOf(arg[8]));

	}

	/*
	 * String username, String mail, String password, String name, String phone
	 */
	public void searchLibrariesWithCity(String city) throws SQLException {
		librInfo = librDao.select(city.toLowerCase(), null);
		for (byte i = 0; i < librInfo.size(); i++) {
			librInfoB.add(new LibrBean());
			fillBean(librInfo.get(i).getUsername(), librInfo.get(i).getMail(), librInfo.get(i).getPassword(),
					librInfo.get(i).getName(), librInfo.get(i).getPhone(), librInfo.get(i).getIndirizzo(),
					librInfo.get(i).getCity(), Integer.toString(librInfo.get(i).getCapacity()),
					Integer.toString(librInfo.get(i).getPostiOccupati()), Byte.toString(i));
		}

	}

	public void clearLibrary() {
		this.librInfo.clear();
	}

	public void clearLibraryB() {
		this.librInfoB.clear();
	}

	public List<Library> getLibrInfo() {
		return librInfo;
	}

	public void setLibrInfo(List<Library> librInfo) {
		this.librInfo = librInfo;
	}

	public List<LibrBean> getLibrInfoB() {
		return librInfoB;
	}

	public void setLibrInfoB(List<LibrBean> librInfoB) {
		this.librInfoB = librInfoB;
	}

}