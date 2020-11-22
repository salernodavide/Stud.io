package logic.boundary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.bean.ReportBean;
import logic.control.ReportIssueController;

public abstract class ReportDetailsBoundary extends FxmlGUI {

	@FXML
	protected TextField tvTitle;
	@FXML
	protected TextArea tvDescription;
	@FXML
	protected Button btnBack;
	@FXML
	protected Label lbUser;
	@FXML
	protected Label lbStatus;
	protected ReportBean reportBean;
	protected ReportIssueController reportIssueController;

	@FXML
	public abstract void backClicked(ActionEvent event);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Default
	}

}