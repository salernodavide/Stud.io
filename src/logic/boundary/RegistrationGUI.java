package logic.boundary;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.application.Main;

public class RegistrationGUI extends GuiSUPER {

	public Scene createRegistration(Main main) {
		Label titleReg = createLabel("Registration:", 24);
		Button homeBtn = createBtn(PROJ_NAME);
		Label t1 = new Label("Select user:");
		Button studBtn = createBtn("Student");
		Button librarianBtn = createBtn("Librarian");
		HBox topPanel = createTopPanel(homeBtn, "Registration");
		VBox content = createPanel(titleReg, t1, studBtn, librarianBtn);
		
		homeBtn.setOnAction((event -> {
			try {
				main.setNewStage(START);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		librarianBtn.setOnAction((event -> {
			try {
				main.setNewStage(REG_LIBR_SETTINGS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		studBtn.setOnAction((event -> {
			try {
				main.setNewStage(REG_STUD_SETTINGS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		// ALLINEAMENTI
		BorderPane root = new BorderPane(content, topPanel, null, null, null);
		return (new Scene(root, 800, 600));

	}
}