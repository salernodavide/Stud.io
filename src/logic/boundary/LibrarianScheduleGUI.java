package logic.boundary;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.control.LibrarianScheduleController;

public class LibrarianScheduleGUI extends LibrarianGUI {

	protected Button ediBtnLibr;

	public VBox createLibrarianScheduleGUI(BorderPane root) {
        this.root = root;
		Label titleLibrSettings = createLabel("Timetable:", 24);

		Label sundayLabel = createLabel(
				"Sunday: "
						+ LibrarianScheduleController.getLibrarianScheduleController().getSchedule().get(0).getOrario(),
				16);
		Label mondayLabel = createLabel(
				"Monday: "
						+ LibrarianScheduleController.getLibrarianScheduleController().getSchedule().get(1).getOrario(),
				16);
		Label tuesdayLabel = createLabel(
				"Tuesday: "
						+ LibrarianScheduleController.getLibrarianScheduleController().getSchedule().get(2).getOrario(),
				16);
		Label wednesdayLabel = createLabel(
				"Wednesday: "
						+ LibrarianScheduleController.getLibrarianScheduleController().getSchedule().get(3).getOrario(),
				16);
		Label thursdayLabel = createLabel(
				"Thursday: "
						+ LibrarianScheduleController.getLibrarianScheduleController().getSchedule().get(4).getOrario(),
				16);
		Label fridayLabel = createLabel(
				"Friday: "
						+ LibrarianScheduleController.getLibrarianScheduleController().getSchedule().get(5).getOrario(),
				16);
		Label saturdayLabel = createLabel(
				"Saturday: "
						+ LibrarianScheduleController.getLibrarianScheduleController().getSchedule().get(6).getOrario(),
				16);

		ediBtnLibr = createBtn("Edit");

		VBox contentLibrSchedule = createPanel(titleLibrSettings, sundayLabel, mondayLabel, tuesdayLabel,
				wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel, ediBtnLibr);

		contentLibrSchedule.setSpacing(4);
		contentLibrSchedule.setPadding(new Insets(0, 0, 10, 0));

		ediBtnLibr.setOnAction((event -> {
			try {

				root.setCenter(new LibrarianScheduleEditGUI().createLibrarianScheduleEditGUI(root));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
		
		return contentLibrSchedule;

	

	}
}
