package logic.control;

import java.sql.SQLException;
import java.util.logging.Logger;

import logic.dao.LibraryDao;
import logic.dao.OrarioDao;
import logic.dao.StudentDao;
import logic.entity.Library;
import logic.entity.Student;

public class RegistrationController {
	static Logger myLogger = Logger.getLogger("logger");
	private static RegistrationController instance = null;
	private Library libr;
	private Student stud;

	protected RegistrationController() {
		this.libr = new Library();
		this.stud = new Student();
	}

	public static RegistrationController getRegistrationController() {
		if (RegistrationController.instance == null)
			RegistrationController.instance = new RegistrationController();
		return instance;
	}

	// mail,password,username,nome,cognome,telefono
	// mailBibliotecario,passwordBibliotecario,nomeBiblioteca,indirizzo,telefonoBiblioteca,username,posti,citta
	public void registerUser(String... arg) throws SQLException {
		if (arg[0].contentEquals("Biblioteca")) {
			LibraryDao regL = new LibraryDao();
			OrarioDao regO = new OrarioDao();
			regL.insert(arg[1], arg[2], arg[3], arg[4], arg[5], arg[6], arg[7], arg[8]);
			regO.insert("0", arg[1], "-");
			regO.insert("1", arg[1], "-");
			regO.insert("2", arg[1], "-");
			regO.insert("3", arg[1], "-");
			regO.insert("4", arg[1], "-");
			regO.insert("5", arg[1], "-");
			regO.insert("6", arg[1], "-");
			libr.fillUser(arg[6], arg[1], arg[2], arg[3], arg[5]);
			libr.fillLibrary(arg[4], arg[8], Integer.valueOf(arg[7]), 0);
			/*
			 * String username,String mail,String password,String name,String phone,String
			 * address,String city,String capacity,String postiOccupati
			 * 
			 */
			LibraryMainPageController.getLibraryMainPageController().fillBean(arg[6], arg[1], arg[2], arg[3], arg[5],
					arg[4], arg[8], arg[7], "0");

			LibraryMainPageController.getLibraryMainPageController().fillEntity(arg[6], arg[1], arg[2], arg[3], arg[5],
					arg[4], arg[8], arg[7], "0");

		} else if (arg[0].contentEquals("Studente")) {
			StudentDao regS = new StudentDao();
			regS.insert(arg[1], arg[2], arg[3], arg[4], arg[5], arg[6]);
			stud.fillUser(arg[3], arg[1], arg[2], arg[4], arg[6]);
			stud.fillStudent(arg[5], false, (byte) 0);

		}

	}

	public Student getStud() {
		return stud;
	}

	public void setStud(Student stud) {
		this.stud = stud;
	}

	public Library getLibr() {
		return libr;
	}

	public void setLibr(Library libr) {
		this.libr = libr;
	}

}
