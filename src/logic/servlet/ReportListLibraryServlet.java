package logic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.control.ReportIssueController;
import logic.exceptions.ReportDeleteException;
import logic.exceptions.ReportUpdateException;

public class ReportListLibraryServlet extends ReportListServlet {

	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		ReportIssueController reportIssueController=(ReportIssueController) request.getSession().getAttribute("reportIssueController");
		for (Integer i=0; i<reportIssueController.getSessionUser().getReports().size(); i++) {
			if (request.getParameter("btnOpen".concat((reportIssueController.getSessionUser().getReports().get(i).getReportId()).toString())) != null) {
				createBean(request,reportIssueController,i);
				try {
					reportIssueController.readIssue();
					request.getRequestDispatcher("LibraryReportDetails.jsp").forward(request, response);
				} catch (ServletException|IOException|ReportUpdateException e) {
					e.printStackTrace();
				} 
			}
			if (request.getParameter("btnDelete".concat((reportIssueController.getSessionUser().getReports().get(i).getReportId()).toString())) != null) {
				try {
					reportIssueController.deleteReport(reportIssueController.getSessionUser().getReports().get(i).getReportId());
					request.getRequestDispatcher("ReportListLibrary.jsp").forward(request, response);
				} catch (ReportDeleteException | ServletException | IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		
	}
	public ReportListLibraryServlet() {
		super();
	}
}
