package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.ReportBean;
import logic.control.ReportIssueController;
import logic.exceptions.EmptyTextFieldException;
import logic.exceptions.ReportSaveException;

public class ReportFormServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("btnSendReport")!=null) {
			ReportIssueController reportIssueController=(ReportIssueController)request.getSession().getAttribute("reportIssueController");
			try {
				reportIssueController.sendReport(new ReportBean(request.getParameter("reportFormTitle"),request.getParameter("reportFormDescription")));
				request.getRequestDispatcher("ReportListStudent.jsp").forward(request, response);
			} catch (ReportSaveException|EmptyTextFieldException|ServletException|IOException e) {
				e.printStackTrace();
			} 
		}
	}

}
