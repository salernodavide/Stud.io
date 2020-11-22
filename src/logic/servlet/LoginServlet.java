package logic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.LibrBean;
import logic.bean.MessageBean;
import logic.bean.StudentBean;
import logic.control.LibraryMainPageController;
import logic.control.LoginController;
import logic.control.StudentSuperviseController;
import logic.control.SuperviseController;
import logic.entity.Library;
import logic.entity.Student;
import logic.pattern.NotifiedState;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private LoginController loginController; 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        loginController = new LoginController();
        
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	    	if (loginController.validateUser(request.getParameter("emailLogin"), request.getParameter("passwordLogin")) == 's') {
	    		Student student = loginController.getStudent();
	    		StudentBean studentBean = loginController.getStudentBean();
		        request.getSession().setAttribute("studentBean", studentBean);
		        request.getSession().setAttribute("sessionUser", student);
		        		        
		    	if (student.getStateMachine().getState() instanceof NotifiedState) {
		    		getMessages(student, request);
					request.getRequestDispatcher("NotifiedStudent.jsp").forward(request, response);
					return;
				}
		    	if (student.getStateMachine().getState().getState().equals("Banned")) {
		    		getMessages(student, request);
					request.getRequestDispatcher("BannedStudent.jsp").forward(request, response);
                    return;
		    	}
		    	request.getRequestDispatcher("studentHome.jsp").forward(request, response);
	    	}	
		    else if (loginController.validateUser(request.getParameter("emailLogin"), request.getParameter("passwordLogin")) == 'l') {
		    	LibraryMainPageController.getLibraryMainPageController().setLibrInfo(loginController.getLibrary());
		        LibraryMainPageController.getLibraryMainPageController().setLibrInfoB(loginController.getLibrBean());
		        Library library = loginController.getLibrary();
		        LibrBean libraryBean = loginController.getLibrBean();
		        request.getSession().setAttribute("libraryBean", libraryBean);
		        request.getSession().setAttribute("sessionUser", library);
		        LibraryMainPageController.getLibraryMainPageController().updateLibraryMainPage();
		        request.setAttribute("free", libraryBean.getCapacity()-libraryBean.getPostiOccupati()-LibraryMainPageController.getLibraryMainPageController().getBooks().size());
		        request.setAttribute("booked", LibraryMainPageController.getLibraryMainPageController().getBooksBean());   //Ritorna List<BookSeatBean>
		        request.getRequestDispatcher("librarianHome.jsp").forward(request, response);
	        }
		    else {
		    	response.sendRedirect("erroreLogin.html");
		    }
		}
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
	public void getMessages(Student student, HttpServletRequest request) {
		StudentSuperviseController superviseController = new SuperviseController(student);
		List<MessageBean> messages = superviseController.getMessages(student.getMail());
		request.getSession().setAttribute("studentBean", loginController.getStudentBean());
		request.getSession().setAttribute("superviseController", superviseController);
		request.setAttribute("messages", messages);
	}

}
