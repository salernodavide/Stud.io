package logic.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import logic.bean.ReportBean;
import logic.control.ReportIssueController;
import logic.exceptions.EmptyTextFieldException;

public class ReportListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void createBean(HttpServletRequest request, ReportIssueController reportIssueController, int i) {
		ReportBean selectedReport=null;
		try {
			selectedReport = new ReportBean(reportIssueController.getSessionUser().getReports().get(i).getTitle(),reportIssueController.getSessionUser().getReports().get(i).getDescription());
		} catch (EmptyTextFieldException e) {
			e.printStackTrace();
		}
		reportIssueController.fillBeanWithSelectedReport(reportIssueController.getSessionUser().getReports().get(i).getReportId());
		request.getSession().setAttribute("selectedReport",selectedReport);
	}
}
