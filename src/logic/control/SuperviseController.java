package logic.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.MessageBean;
import logic.bean.StudentBean;
import logic.dao.MessageDao;
import logic.dao.StudentDao;
import logic.entity.Message;
import logic.entity.Student;
import logic.pattern.BannedState;

public class SuperviseController implements StudentSuperviseController, LibrarianSuperviseController{
	private List<Student> listStudents;
	private List<StudentBean> listStudentBean;
	private StudentDao studentDao;
	private MessageDao messageDao;
	private List<MessageBean> messageBeanList;
	private Student student;
	
	public SuperviseController() {
		this.studentDao = new StudentDao();
		this.messageDao = new MessageDao();
		this.listStudentBean = new ArrayList<>();
		messageBeanList = new ArrayList<>();
	}
	
	public SuperviseController(Student student) {
		this.studentDao = new StudentDao();
		this.messageDao = new MessageDao();
		this.listStudentBean = new ArrayList<>();
		this.student = student;
		messageBeanList = new ArrayList<>();
	}

	@Override
	public List<String> fillSupervisePage(String biblioId) {
		try {
			listStudents = studentDao.getStudentFromDb(biblioId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> list = new ArrayList<>();
		for (int i=0; i< listStudents.size(); i++) {
			 list.add(listStudents.get(i).getUsername());
		}
		return list;
	}
	
	
    /*
     * Ritorna la StudentBean relativa a uno studente di cui si conosce lo username
     */
	@Override
	public StudentBean getInfoStudent(String username) {
		int i;
	    for (i=0; i<listStudentBean.size(); i++) {
	    	if (listStudentBean.get(i).getUsername().equals(username)) {
	    		return listStudentBean.get(i);
	    	}
	    }
	    StudentBean studentBean = new StudentBean();
		for (i=0; i< listStudents.size(); i++) {
			if (listStudents.get(i).getUsername().equals(username)) {
				studentBean.setPhone(listStudents.get(i).getPhone());
				studentBean.fillUserBean(listStudents.get(i).getUsername(),listStudents.get(i).getMail(), "****", listStudents.get(i).getName(),listStudents.get(i).getPhone());
				studentBean.fillStudBean(listStudents.get(i).getSurname(), listStudents.get(i).isBanned(), listStudents.get(i).getReportCounter());
				student = listStudents.get(i);
				listStudentBean.add(studentBean);
			}
		}
		return studentBean;
			
	}

	@Override
	public void getStudent(String studentId) {
		student = studentDao.select(studentId, null);
	}
	

	@Override
	public void increaseReportingCounter(String librarianId, String reason) {
		Message message = null;
		
		if (student.getStateMachine().getState().getState().equals("Notified")) {
			message = student.notifyStudent(reason);
		}
		else {
			if (student.getReportCounter() > 2) {
				student.getStateMachine().setState(new BannedState());
				message = student.notifyStudent(reason);
				student.setBanned(true);
			}
			if (student.getReportCounter() < 3) {
				student.increaseReportCounter();
				message = student.notifyStudent(reason);
				student.startCountdown();
				student.getStateMachine().goNext();			
			}
		}
		message.setLibrarianId(librarianId);
		message.setStudentId(student.getMail());
	
	    updateBean(student.getMail());
		studentDao.updateStudent(student);
		message.setId(messageDao.insert(message));
	}
	
	public void updateBean(String studentId) {
		for (int i=0; i<listStudentBean.size(); i++) {
	    	if (listStudentBean.get(i).getMail().equals(studentId)) {
	    		listStudentBean.get(i).increaseReportingCounter();
	    		listStudentBean.get(i).setBanned(student.isBanned());
	    		break;
	    	}
	    }
	}
	
	
	@Override
	public List<MessageBean> getMessages(String idStud) {
		List<Message> listMessages = null;
		try {
			listMessages = messageDao.getMessagesFromDb(idStud);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0; i< listMessages.size(); i++) {
			MessageBean messageBean = new MessageBean(listMessages.get(i).getId(), listMessages.get(i).getTitle(), listMessages.get(i).getText(), listMessages.get(i).getLibrarianId(), listMessages.get(i).getStudentId());
			messageBeanList.add(messageBean);
		}
		return messageBeanList;
	}

	@Override
	public void sendMessageInteraction(String studentId) {
		student.stopCountdown();
		studentDao.updateStudent(student);
	}

	@Override
	public void sendMessage(MessageBean messageBean) {
	    Message message = new Message(messageBean.getTitle(), messageBean.getText(), messageBean.getLibrarianId(), messageBean.getStudentId());
	    message.setId(messageDao.insert(message));
	}

	@Override
	public void deleteMessage(long messageId) {
		messageDao.delete(messageId);
		
	}

	@Override
	public void deleteAllUploadedMessages() {
        for	(int i=0; i<messageBeanList.size(); i++) {
        	messageDao.delete(messageBeanList.get(i).getId());	
        }
	}


}
