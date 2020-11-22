package logic.boundary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import logic.application.Main;
import logic.constants.FxmlConstants;
import logic.control.ReportIssueController;
import logic.entity.Library;
import logic.exceptions.ReportUpdateException;
import logic.pattern.Observer;

public class IssueListLibraryBoundary extends IssueListBoundary implements Observer {

	private Scene homeLibrarianGuiScene;
	private Main main;

	public IssueListLibraryBoundary(Library sessionLibrary, Scene homeLibrarianGuiScene, Main main) {
		this.reportIssueController = new ReportIssueController(sessionLibrary);
		this.homeLibrarianGuiScene = homeLibrarianGuiScene;
		this.main = main;
		sessionLibrary.attachObserver(this);
	}

	@FXML
	public void openClicked(ActionEvent event) {
		this.reportIssueController
				.fillBeanWithSelectedReport(parseReportId(this.lvReports.getSelectionModel().getSelectedItem()));
		try {
			this.reportIssueController.readIssue();
			guiLoader(FxmlConstants.REPORT_DETAILS_LIBRARIAN_GUI,
					new ReportDetailsLibrarianBoundary(this.reportIssueController, this), event);
		} catch (ReportUpdateException e) {
			this.lbStatus.setText("Open failed");
			e.printStackTrace();
		}

	}

	@FXML
	public void backClicked(ActionEvent event) {
		main.getStage().setScene(this.homeLibrarianGuiScene);
		main.getStage().show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.lbUser.setText(this.reportIssueController.getSessionUser().getName());
		this.reportIssueController.getLibraryReports();
	}

	@Override
	public void update() {
		for (int i = 0; i < this.lvReports.getItems().size(); i++) {
			for (int j = 0; j < this.reportIssueController.getSessionUser().getReports().size(); j++) {
				if ((parseReportId(lvReports.getItems().get(i)) == this.reportIssueController.getSessionUser()
						.getReports().get(j).getReportId())
						&& (!lvReports.getItems().get(i).equals(this.reportIssueController.getSessionUser().getReports()
								.get(j).getMainInfoForLibrarian()))) {
					lvReports.getItems().remove(i);
					lvReports.getItems().add(i,
							this.reportIssueController.getSessionUser().getReports().get(j).getMainInfoForLibrarian());
					return;
				}
			}

		}

		for (int i = 0; i < this.reportIssueController.getSessionUser().getReports().size(); i++) {
			if (!lvReports.getItems().contains(
					this.reportIssueController.getSessionUser().getReports().get(i).getMainInfoForLibrarian()))
				this.lvReports.getItems()
						.add(this.reportIssueController.getSessionUser().getReports().get(i).getMainInfoForLibrarian());
		}

		updateDelete();

	}

}