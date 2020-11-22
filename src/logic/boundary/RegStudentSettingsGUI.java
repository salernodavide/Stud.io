package logic.boundary;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.application.Main;
import logic.control.RegistrationController;

public class RegStudentSettingsGUI extends GuiSUPER {
	public static final String TITLE_S1 = "Registration 2/2";
	public static final String TITLE_S2 = "Please insert this information about you:";


	public Scene createRegStudentSettings(Main main) { // main serve a setOnAction, NON TOGLIERE!

		Button homeBtn = createBtn(PROJ_NAME);
		Button signUpBtn = createBtn("Sign up");
		Label t1 = createLabel("Registration student:", 24);
		Label username = new Label("Username:");
		Label email = new Label("Email:");
		Label password = new Label("Password:");
		Label name = new Label("Name:");
		Label surname = new Label("Surname:");
		Label phone = new Label("Phone:");
		TextField usernameField = new TextField();
		TextField emailField = new TextField();
		PasswordField passwordField = new PasswordField();
		TextField nameField = new TextField();
		TextField surnameField = new TextField();
		TextField phoneField = new TextField();

		HBox topPanel = createTopPanel(homeBtn, "Registration");

		homeBtn.setOnAction((event -> {
			try {
				main.setNewStage(START);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
		// mail,password,username,nome,cognome,telefono
		signUpBtn.setOnAction((event -> {
			try {
				// CHIAMATA A METODO CONTROLLER
				RegistrationController.getRegistrationController().registerUser("Studente", emailField.getText(),
						passwordField.getText(), usernameField.getText(), nameField.getText(), surnameField.getText(),
						phoneField.getText());
				
				main.setNewStage(START);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		VBox content = createPanel(t1, username, usernameField, email, emailField, password, passwordField, name,
				nameField, surname, surnameField, phone, phoneField, signUpBtn);

		BorderPane root = new BorderPane(content, topPanel, null, null, null);

		return (new Scene(root, 800, 600));
	}
}