package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.control.BookSeatController;
import logic.entity.Library;
import logic.entity.Student;

public class StudentSearchLibrServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("bookSeat") != null) { 
			BookSeatController bookSeatController=new BookSeatController();
			bookSeatController.bookSeat((Student)request.getSession().getAttribute("sessionUser"), (Library)request.getSession().getAttribute("currentLibrary"));
			request.setAttribute("selectedLibrary", (Library)request.getSession().getAttribute("currentLibrary"));
			request.getRequestDispatcher("studentSearchLibrPage.jsp").forward(request, response);
		  }
	}

}
