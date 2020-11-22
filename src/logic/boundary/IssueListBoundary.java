package logic.boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import logic.control.ReportIssueController;
import logic.exceptions.ReportDeleteException;

public abstract class IssueListBoundary extends FxmlGUI {

	@FXML
	protected Button btnDelete;
	@FXML
	protected Button btnOpen;
	@FXML
	protected Button btnBack;
	@FXML
	protected Label lbStatus;
	@FXML
	protected Label lbUser;
	@FXML
	protected ListView<String> lvReports;
	protected ReportIssueController reportIssueController;

	@FXML
	public abstract void openClicked(ActionEvent event);

	@FXML
	public abstract void backClicked(ActionEvent event);

	@FXML
	public void deleteClicked(ActionEvent event) {
		try {
			this.reportIssueController
					.deleteReport(parseReportId(this.lvReports.getSelectionModel().getSelectedItem()));
		} catch (ReportDeleteException e) {
			this.lbStatus.setText("Report delete failed");
			e.printStackTrace();
		}
	}

	public long parseReportId(String selectedItem) {
		String[] splittedStrings = selectedItem.split("    ");
		return Long.parseLong(splittedStrings[0]);
	}

	public void updateDelete() {
		boolean itsIn;
		for (int i = 0; i < this.lvReports.getItems().size(); i++) {
			itsIn = false;
			for (int j = 0; j < this.reportIssueController.getSessionUser().getReports().size(); j++) {
				if (parseReportId(lvReports.getItems().get(i)) == this.reportIssueController.getSessionUser()
						.getReports().get(j).getReportId()) {
					itsIn = true; // Nella lista dell'utente e nella listview e' presente percio' non va eliminato
									// mi fermo e passo al prossimo elemento della listview
					break;
				}
			}
			if (!itsIn) { // Nella listview e' presente ma nella lista dell'utente no
				lvReports.getItems().remove(i);
				break;
			}

		}
	}

}
