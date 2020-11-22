package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.SearchController;

public class StudentHomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentHomeServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("searchBtn") != null) { 
		   SearchController searchController=new SearchController();
		   request.getSession().setAttribute("searchController", searchController); 
		   searchController.searchLibraryFromCity(request.getParameter("searchField"));
		   request.setAttribute("searchBean",searchController.getSearchBean());
		   try {
			request.getRequestDispatcher("studentSearchResult.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		  }
	}

}
