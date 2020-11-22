package logic.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.RegistrationController;

/**
 * Servlet implementation class AddStudentServlet
 */

public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!request.getParameter("emailRegistration").isEmpty() && !request.getParameter("passwordRegistration").isEmpty() && 
					!request.getParameter("username").isEmpty() && !request.getParameter("nameRegistration").isEmpty()) {
		    	RegistrationController.getRegistrationController().registerUser("Studente", request.getParameter("emailRegistration"),
					request.getParameter("passwordRegistration"), request.getParameter("username"), request.getParameter("nameRegistration"), request.getParameter("surnameRegistration"),
					request.getParameter("phoneRegistration"));
		    	RequestDispatcher view = request.getRequestDispatcher("login.html");
			    view.forward(request, response);
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("CreateStudent.html");
				view.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
