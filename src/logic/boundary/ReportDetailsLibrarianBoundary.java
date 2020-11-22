package logic.boundary;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import logic.constants.FxmlConstants;
import logic.control.LibrarianSuperviseController;
import logic.control.ReportIssueController;
import logic.control.SuperviseController;
import logic.exceptions.ReportUpdateException;

public class ReportDetailsLibrarianBoundary extends ReportDetailsBoundary {
	
	@FXML private Button btnSolve;
	@FXML private Button btnReportUser;
	private IssueListLibraryBoundary issueListLibraryBoundary;
	private LibrarianSuperviseController superviseController;
	
	public ReportDetailsLibrarianBoundary(ReportIssueController reportIssueController, IssueListLibraryBoundary issueListLibraryBoundary) {
		this.reportIssueController=reportIssueController;
		this.issueListLibraryBoundary=issueListLibraryBoundary;
		this.reportBean=reportIssueController.getReportBean();
		superviseController = new SuperviseController();
	}
	
	@FXML
	public void solveClicked(ActionEvent event) {
		try {
			this.reportIssueController.solveIssue();
			this.backClicked(event);
		} catch (ReportUpdateException e) {
			this.lbStatus.setText("Report solving failed");
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void reportUserClicked(ActionEvent event) {
		try {
			// alert confirm student report
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Confirmation report");
			alert.setHeaderText("Warning!");
			alert.setContentText("Are you sure you want to report account?");
			if (alert.showAndWait().get() == ButtonType.OK) {
				superviseController.getStudent(this.reportIssueController.getReportBean().getStudentId());
				superviseController.increaseReportingCounter(this.reportIssueController.getSessionUser().getMail(), "feedback");
				this.reportIssueController.deleteReport(this.reportIssueController.getReportBean().getReportId());
			}
			this.backClicked(event);
		} catch (NoSuchElementException e) {
			// if this exception is caught, no need to do anything
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
	
	@Override
	public void backClicked(ActionEvent event) {
		guiLoader(FxmlConstants.ISSUE_LIST_LIBRARY_GUI, this.issueListLibraryBoundary, event);	
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.lbUser.setText(this.reportIssueController.getSessionUser().getName());
		this.tvTitle.setText(this.reportBean.getTitle());
		this.tvDescription.setText(this.reportBean.getDescription());
		
	}

	
}