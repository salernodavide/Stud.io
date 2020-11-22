package logic.boundary;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.application.Main;

public class GuestGUI extends GuiSUPER {

	public Scene createGuestGUI(Main main) {

		Button searchBtn = createBtn("Search seat:");
		Button guestHomeBtn = createBtn(PROJ_NAME);
		ImageView image = createImg("src/resources/guest.png");

		HBox topPanel = createTopPanel(guestHomeBtn, "<Guest>");

		VBox leftGuest = createPanel(image, searchBtn);
		leftPadding(leftGuest, 20);

		guestHomeBtn.setOnAction((event -> {
			try {
				main.setNewStage(START);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		BorderPane root = new BorderPane(null, topPanel, null, null, leftGuest);
		return (new Scene(root, 800, 600));

	}

}