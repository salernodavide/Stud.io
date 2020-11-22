package logic.boundary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logic.constants.FxmlConstants;
import logic.control.ReportIssueController;

public class ReportDetailsStudentBoundary extends ReportDetailsBoundary {

	protected IssueListStudentBoundary issueListStudentBoundary;

	public ReportDetailsStudentBoundary(ReportIssueController reportIssueController,
			IssueListStudentBoundary issueListStudentBoundary) {
		this.reportIssueController = reportIssueController;
		this.issueListStudentBoundary = issueListStudentBoundary;
		this.reportBean=reportIssueController.getReportBean();
	}

	public ReportDetailsStudentBoundary(ReportIssueController reportIssueController) {
		this.reportIssueController = reportIssueController;
	}

	@FXML
	public void backClicked(ActionEvent event) {
		guiLoader(FxmlConstants.ISSUE_LIST_STUDENT_GUI, this.issueListStudentBoundary, event);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.lbUser.setText(this.reportIssueController.getSessionUser().getUsername());
		this.tvTitle.setText(this.reportBean.getTitle());
		this.tvDescription.setText(this.reportBean.getDescription());
	}

}