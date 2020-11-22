package logic.control;

import java.util.logging.Logger;
import logic.bean.StudentBean;
import logic.dao.StudentDao;
import logic.entity.Student;

public class StudentSettingsController {
	private Student studInfo;
	private StudentBean studInfoB;
	private StudentDao studDao;
	static Logger myLogger = Logger.getLogger("logger");
	private static StudentSettingsController instance = null;

	protected StudentSettingsController() {
		this.studInfoB = new StudentBean();
		this.studDao = new StudentDao();
	}

	/*
	 * Metodo per richiedere l'istanza signleton di controller
	 */

	public static StudentSettingsController getStudentSettingsController() {
		if (StudentSettingsController.instance == null)
			StudentSettingsController.instance = new StudentSettingsController();
		return instance;
	}

	public void updateStudentInfo() {
		if (!studInfoB.getMail().isEmpty())
			studDao.update("mailStudente", studInfoB.getMail(), studInfo.getMail());
		if (!studInfoB.getPassword().isEmpty())
			studDao.update("password", studInfoB.getPassword(), studInfo.getMail());
		if (!studInfoB.getUsername().isEmpty())
			studDao.update("username", studInfoB.getUsername(), studInfo.getMail());
		if (!studInfoB.getName().isEmpty())
			studDao.update("nome", studInfoB.getName(), studInfo.getMail());
		if (!studInfoB.getSurname().isEmpty())
			studDao.update("cognome", studInfoB.getSurname(), studInfo.getMail());
		if (!studInfoB.getPhone().isEmpty())
			studDao.update("telefono", studInfoB.getPhone(), studInfo.getMail());
	}
	
	

	public Student getStudInfo() {
		return studInfo;
	}

	public void setStudInfo(Student studInfo) {
		this.studInfo = studInfo;
	}

	public StudentBean getStudInfoB() {
		return studInfoB;
	}

	public void setStudInfoB(StudentBean studInfoB) {
		this.studInfoB = studInfoB;
	}

	public StudentDao getStudDao() {
		return studDao;
	}

	public void setStudDao(StudentDao studDao) {
		this.studDao = studDao;
	}

}
