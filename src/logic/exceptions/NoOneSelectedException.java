package logic.exceptions;

import java.util.NoSuchElementException;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class NoOneSelectedException extends NullPointerException {

	static final Logger myLogger = Logger.getLogger("logger");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoOneSelectedException() {
		super("Select something");

	}

	public void createAlert() {
		myLogger.info(this.getMessage());
		try {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Remember to select something!");
			if (alert.showAndWait().get() == ButtonType.OK) {
				alert.close();
			}
		} catch (NoSuchElementException e2) {
			// nothing to do
		}

	}
}
