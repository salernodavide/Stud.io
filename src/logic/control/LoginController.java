package logic.control;

import logic.bean.LibrBean;
import logic.bean.StudentBean;
import logic.dao.LibraryDao;
import logic.dao.StudentDao;
import logic.entity.Library;
import logic.entity.Student;

import java.util.logging.Logger;
import java.sql.SQLException;

public class LoginController implements GenericController {
	private LibrBean librBean;
	private StudentBean studentBean;
	private Library library;
	private LibraryDao loginL;
	private StudentDao loginS;
	private Student studInfo;
	static Logger myLogger = Logger.getLogger("logger");

	public LoginController() {
		this.librBean = new LibrBean();
		this.studentBean = new StudentBean();
		this.library = new Library();
		this.loginL = new LibraryDao();
		this.loginS = new StudentDao();
	}


	public char validateUser(String mail, String password) throws SQLException {

		studInfo = loginS.select(mail, password);
		if (studInfo != null) {
		    studentBean.fillUserBean(studInfo.getUsername(), studInfo.getMail(), studInfo.getPassword(),studInfo.getName(), studInfo.getPhone());
		    studentBean.fillStudBean(studInfo.getSurname(), studInfo.isBanned(), studInfo.getReportCounter());
		    return 's';
		}
		library = loginL.selectL(mail, password);
		if (library != null) {
		    librBean.fillUserBean(library.getUsername(), library.getMail(), library.getPassword(), library.getName(), library.getPhone());
		    librBean.fillLibrBean(library.getIndirizzo(), library.getCity(), library.getCapacity(), library.getPostiOccupati());
		    return 'l';
		}
		return '0';
	}

	/*
	 * String username,String mail,String password,String name,String phone,String
	 * address,String city,String capacity,String postiOccupati
	 * 
	 */
	@Override
	public void fillBean(String... arg) {
		librBean.fillUserBean(arg[0], arg[1], arg[2], arg[3], arg[4]);
		librBean.fillLibrBean(arg[5], arg[6], Integer.valueOf(arg[7]), Integer.valueOf(arg[8]));
	}

	@Override
	public void fillEntity(String... arg) {
		library.fillUser(arg[0], arg[1], arg[2], arg[3], arg[4]);
		library.fillLibrary(arg[5], arg[6], Integer.valueOf(arg[7]), Integer.valueOf(arg[8]));
	}

	public LibrBean getLibrBean() {
		return librBean;
	}

	public void setLibrBean(LibrBean librBean) {
		this.librBean = librBean;
	}

	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Student getStudent() {
		return studInfo;
	}

	public void setStudent(Student student) {
		this.studInfo = student;
	}
	
	

}
