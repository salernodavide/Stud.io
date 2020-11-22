package logic.boundary;

import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.application.Main;
import logic.control.LibraryMainPageController;
import logic.control.StudentSuperviseController;
import logic.control.SuperviseController;
import logic.pattern.BannedState;
import logic.pattern.NotifiedState;
import javafx.scene.control.PasswordField;

public class LoginGUI extends GuiSUPER {
	static Logger myLogger = Logger.getLogger("logger");

	public Scene createLogin(Main main) {
		Label titleLog = createLabel("Login:", 24);
		Button homeBtn = createBtn(PROJ_NAME);
		Button loginBtn = createBtn("Login");
		Label t1 = new Label("Please insert your credentials");
		Label email = new Label("Email:");
		Label password = new Label("Password:");
		TextField emailField = new TextField();
		PasswordField passwordField = new PasswordField();

		HBox topPanel = createTopPanel(homeBtn, "Login");

		VBox content = createPanel(titleLog, t1, email, emailField, password, passwordField, loginBtn);

		homeBtn.setOnAction((event -> {
			try {
				main.setNewStage(START);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		loginBtn.setOnAction((event -> {
			try {
				// CHIAMATA A METODO CONTROLLER (..., userCredentials) che dovra ritornare un
				// valore per fare aprire lo stage STUDENT o il LIBRARIAN
				if (loginController.validateUser(emailField.getText(),
						passwordField.getText()) == 's') {
					if (loginController.getStudent().getStateMachine().getState() instanceof NotifiedState) {
						StudentSuperviseController superviseController = new SuperviseController(loginController.getStudent());
						StudentNotifiedGUI studentNotifiedGUI = new StudentNotifiedGUI(loginController.getStudentBean(), superviseController);
						
						Scene scene = studentNotifiedGUI.createStudentNotifiedGUI(main);
						main.getStage().setScene(scene);
						main.getStage().show();
					}
						
					else if (loginController.getStudent().getStateMachine().getState() instanceof BannedState) {
						
					    StudentSuperviseController superviseController = new SuperviseController(loginController.getStudent());
					    StudentBannedGUI studentBannedGUI = new StudentBannedGUI(loginController.getStudentBean(), superviseController);
					
				    	Scene scene = studentBannedGUI.createBannedGUI(main);
				    	main.getStage().setScene(scene);
				    	main.getStage().show();
					}
					else {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/logic/fxml/StudentGUI.fxml"));
						StudentFxmlGUI studentFxmlGui = new StudentFxmlGUI(loginController.getStudent(),main);
						fxmlLoader.setController(studentFxmlGui);
						
						BorderPane nextParent = fxmlLoader.load();
				        Scene nextScene = new Scene(nextParent, 800, 600);
						
				        //This line gets the Stage information
				        Stage window = getNodeStage(getEventNode(event));
				        
				        window.setScene(nextScene);
				        window.show();
					}
				} else if (loginController.validateUser(emailField.getText(),
						passwordField.getText()) == 'l') {
					LibraryMainPageController.getLibraryMainPageController()
							.setLibrInfo(loginController.getLibrary());
					LibraryMainPageController.getLibraryMainPageController()
							.setLibrInfoB(loginController.getLibrBean());
					LibraryMainPageController.getLibraryMainPageController().updateLibraryMainPage();
					HomeLibrarianGUI homeLibrarianGUI = new HomeLibrarianGUI(loginController.getLibrBean());
					homeLibrarianGUI.createRootLibrarian(main);
					Scene scene = homeLibrarianGUI.createLibrarianGUI(main);
					main.getStage().setScene(scene);
					main.getStage().show();
				}

				t1.setTextFill(Color.web("#ff0000"));
				t1.setText("Credenziali errate");

			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}));

		// ALLINEAMENTI
		BorderPane root = new BorderPane(content, topPanel, null, null, null);
		return (new Scene(root, 800, 600));

	}
	
}
