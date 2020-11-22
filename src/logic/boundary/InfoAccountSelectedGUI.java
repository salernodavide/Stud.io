package logic.boundary;

import java.util.NoSuchElementException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.bean.StudentBean;

public class InfoAccountSelectedGUI {
	private SuperviseGUI superviseGUI;
	private StudentBean studentBean;
	private Button reportAccount;
	private Button back;	
		
	public InfoAccountSelectedGUI (SuperviseGUI superviseGUI, StudentBean studentBean) {
		this.superviseGUI = superviseGUI;
		this.studentBean = studentBean;
	    back = createButton("Back");
	    reportAccount = createButton("reportAccount");
	}
	

	public VBox createInfoAccountGUI() {
		Label nameField = new Label(studentBean.getName());
		Label surnameField = new Label(studentBean.getSurname());
		Label emailField = new Label(studentBean.getMail());
		Label usernameField = new Label(studentBean.getUsername());
		Label phoneField = new Label(studentBean.getPhone());
		Label banLabel = new Label();
		if(studentBean.isBanned()) {
			banLabel.setText("Utente bannato");
		}
		else {
			banLabel.setText("Utente attivo");
		}
		banLabel.setFont(new Font(22));
		HBox hBox = new HBox();
		hBox.getChildren().addAll(back, reportAccount);
		hBox.setSpacing(50);
		hBox.getOpacity();
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(15,0,0,0));
	
		VBox content = createPanel(createLabel("Name:"), nameField, createLabel("Surname:"), surnameField, createLabel("Phone:"), phoneField, createLabel("Username:"),
				usernameField, createLabel("Email:"), emailField, banLabel, hBox);
		back.setOnAction((event -> {
			try {
				superviseGUI.getRoot().setCenter(superviseGUI.getGui());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
		
		reportAccount.setOnAction((event -> {
			try {
				// alert confirm student report
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Confirmation report");
				alert.setHeaderText("Warning!");
				alert.setContentText("Are you sure you want to report account?");
				if (alert.showAndWait().get() == ButtonType.OK) {
					superviseGUI.getSuperviseController().increaseReportingCounter(superviseGUI.libraryBean.getMail(), "infoAccount");
					superviseGUI.getRoot().setCenter(superviseGUI.getGui());
				}
			} catch (NoSuchElementException e) {
				// if this exception is caught, no need to do anything
			} catch (Exception exc) {
				exc.printStackTrace();
			}

		}));
		
		return content;
	}
  
	
	public Label createLabel(String text) {
		Label name = new Label(text);
		name.setFont(new Font(22));
		return name;
	}
	public VBox createPanel(Node... nodes) {
		VBox panel = new VBox();
		for (Node next : nodes)
			panel.getChildren().add(next);
		panel.setAlignment(Pos.CENTER);
		panel.setSpacing(10);
		return panel;
	}
	
	public Button createButton(String nameBtn) {
		Button btn = new Button(nameBtn);
		btn.setPrefWidth(130);
		btn.setPrefHeight(20);
		return btn;
	}
}
