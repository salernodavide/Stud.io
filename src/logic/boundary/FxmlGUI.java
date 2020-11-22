package logic.boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxmlGUI implements Initializable {

	public void guiLoader(String path, Object guiController, ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
		fxmlLoader.setController(guiController);

		BorderPane nextParent = null;
		try {
			nextParent = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene nextScene = new Scene(nextParent, 800, 600);

		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(nextScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Default

	}
}
