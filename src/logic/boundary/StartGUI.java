package logic.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.application.Main;

public class StartGUI extends GuiSUPER {

	public Scene createStart(Main main) {

		Label titleStart = createLabel("Welcome:", 24);
		Button homeBtn = createBtn(PROJ_NAME);
		Button regUser = createBtn("User");
		setHeightBtn(regUser, 100);
		Button gueUser = createBtn("Guest");
		setHeightBtn(gueUser, 100);
		HBox users = new HBox();
		users.getChildren().addAll(regUser, gueUser);
		users.setSpacing(20);
		users.setAlignment(Pos.CENTER);
		Label t2 = new Label("If you are not registered:");
		Button signBtn = new Button("Sign up");

		HBox topPanel = createTopPanel(homeBtn, "Welcome");

		HBox logo = new HBox();
		logo.getChildren().add(createImg("src/resources/logo200.png"));
		logo.setAlignment(Pos.CENTER);
		logo.setPadding(new Insets(30, 10, 0, 0));

		VBox content = createPanel(logo, titleStart, users, t2, signBtn);

		homeBtn.setOnAction((event -> {
			try {
				main.setNewStage(START);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		regUser.setOnAction((event -> {
			try {
				main.setNewStage(LOGIN);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		signBtn.setOnAction((event -> {
			try {
				main.setNewStage(REGISTRATION);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		gueUser.setOnAction((event -> {
			try {
				main.setNewStage(GUEST);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		BorderPane root = new BorderPane(content, topPanel, null, null, null);

		return (new Scene(root, 800, 600));

	}
}