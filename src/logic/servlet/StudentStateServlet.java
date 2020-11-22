package logic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.MessageBean;
import logic.bean.StudentBean;
import logic.control.StudentSuperviseController;
import logic.control.SuperviseController;

/**
 * Servlet implementation class StudentStateServlet
 */
public class StudentStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentStateServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentBean studentBean = (StudentBean) request.getSession().getAttribute("studentBean");
		StudentSuperviseController superviseController = new SuperviseController();
		List<MessageBean> messages = superviseController.getMessages(studentBean.getMail());
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("NotifiedStudent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentSuperviseController superviseController = (StudentSuperviseController) request.getSession().getAttribute("superviseController");
		superviseController.deleteAllUploadedMessages();
        StudentBean studentBean = (StudentBean) request.getSession().getAttribute("studentBean");
		superviseController.sendMessageInteraction(studentBean.getMail());
		request.getRequestDispatcher("login.html").forward(request, response);
	}

}
