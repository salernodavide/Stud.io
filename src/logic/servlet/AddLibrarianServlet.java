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
 * Servlet implementation class AddLibrarianServlet
 */
public class AddLibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLibrarianServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!request.getParameter("email").isEmpty() && !request.getParameter("password").isEmpty() && 
					!request.getParameter("username").isEmpty() && !request.getParameter("libraryName").isEmpty()) {
		    	RegistrationController.getRegistrationController().registerUser("Biblioteca", request.getParameter("email"),
					request.getParameter("password"), request.getParameter("libraryName"), request.getParameter("address"), request.getParameter("phone"),
					request.getParameter("username"),request.getParameter("capacity"),request.getParameter("city"));
		    	
		    	
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
