package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.ReportIssueController;
import logic.exceptions.ReportUpdateException;

public class ReportDetailsLibraryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		ReportIssueController reportIssueController=(ReportIssueController)request.getSession().getAttribute("reportIssueController");
		if(request.getParameter("btnSolve")!=null) {
			try {
				reportIssueController.solveIssue();
				request.getRequestDispatcher("ReportListLibrary.jsp").forward(request, response);
			} catch (ReportUpdateException|ServletException|IOException e) {
				e.printStackTrace();
			}
		}
	}
}
