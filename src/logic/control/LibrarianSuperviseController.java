package logic.control;

import java.util.List;

import logic.bean.MessageBean;
import logic.bean.StudentBean;

/*
 * Dichiarazione metodi di interazione del Librarian nel caso d'uso SuperviseStudent
 */
public interface LibrarianSuperviseController {
	public List<String> fillSupervisePage(String biblioId);
	public StudentBean getInfoStudent(String username);
	public void increaseReportingCounter(String libraryId, String reason);
	public void sendMessage(MessageBean messageBean);
	public void getStudent(String studentId);
}
