package logic.boundary;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.application.Main;
import logic.control.RegistrationController;

public class RegLibrarianSettingsGUI extends GuiSUPER {
	public static final String TITLE_L1 = "Registration 2/2";
	public static final String TITLE_L2 = "Please insert this information about your library:";

	public Scene createRegLibrarianSettings(Main main) {
		Button homeBtn = createBtn(PROJ_NAME);
		Button signUpBtn = createBtn("Sign up");
		Label t1 = createLabel("Registration librarian:", 24);
		Label username = new Label("Username:");
		Label email = new Label("Email:");
		Label password = new Label("Password:");
		Label nameLibrary = new Label("Library name:");
		Label address = new Label("Address:");
		Label city = new Label("City:");
		Label phone = new Label("Phone:");
		Label capacity = new Label("Capacity:");
		TextField usernameField = new TextField();
		TextField emailField = new TextField();
		PasswordField passwordField = new PasswordField();
		TextField nameLibraryField = new TextField();
		TextField addressField = new TextField();
		TextField cityField = new TextField();
		TextField phoneField = new TextField();
		TextField capacityField = new TextField();
		HBox topPanel = createTopPanel(homeBtn, "Registration");
		VBox content = new VBox();
		content.getChildren().addAll(t1, username, usernameField, email, emailField, password, passwordField,
				nameLibrary, nameLibraryField, city, cityField, address, addressField, phone, phoneField, capacity,
				capacityField, signUpBtn);
		content.setAlignment(Pos.CENTER);
		content.setSpacing(2);
		content.setMaxWidth(250);
		homeBtn.setOnAction((event -> {
			try {
				main.setNewStage(START);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		signUpBtn.setOnAction((event -> {
			try {
				RegistrationController.getRegistrationController().registerUser("Biblioteca", emailField.getText(),
						passwordField.getText(), nameLibraryField.getText(), addressField.getText(),
						phoneField.getText(), usernameField.getText(), capacityField.getText(), cityField.getText());
				loginController.setLibrary(RegistrationController.getRegistrationController().getLibr());
				main.setNewStage(START);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		BorderPane root = new BorderPane(content, topPanel, null, null, null);
		return (new Scene(root, 800, 600));
	}
}