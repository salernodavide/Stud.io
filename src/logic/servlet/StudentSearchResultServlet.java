package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.SearchBean;
import logic.control.SearchController;
import logic.entity.Library;

public class StudentSearchResultServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentSearchResultServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		SearchController searchController=(SearchController)request.getSession().getAttribute("searchController");
		SearchBean searchBean=searchController.getSearchBean();
		for(int i=0;i<searchBean.getResultLibraries().size();i++) {
			if(request.getParameter("lib".concat(searchBean.getResultLibraries().get(i).getMail()))!=null) {
				Library selectedLibrary=searchBean.getResultLibraries().get(i);
				request.setAttribute("selectedLibrary", selectedLibrary);
				try {
					request.getRequestDispatcher("studentSearchLibrPage.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
