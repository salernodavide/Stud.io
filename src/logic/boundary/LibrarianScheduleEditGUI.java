package logic.boundary;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.control.LibrarianScheduleController;

public class LibrarianScheduleEditGUI extends LibrarianGUI {
	static final String[] day = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
	protected TextField sundayField;
	protected TextField mondayField;
	protected TextField tuesdayField;
	protected TextField wednesdayField;
	protected TextField thursdayField;
	protected TextField fridayField;
	protected TextField saturdayField;
	protected Button applySchedEditBtnLibr;

	public VBox createLibrarianScheduleEditGUI(BorderPane root) {
		this.root = root;
		Label titleLibrSchedEdit = createLabel("Update timetable:", 24);
		Label sunday = new Label("Sunday:");
		sundayField = new TextField();
		Label monday = new Label("Monday:");
		mondayField = new TextField();
		Label tuesday = new Label("Tuesday:");
		tuesdayField = new TextField();
		Label wednesday = new Label("Wednesday:");
		wednesdayField = new TextField();
		Label thursday = new Label("Thursday:");
		thursdayField = new TextField();
		Label friday = new Label("Friday:");
		fridayField = new TextField();
		Label saturday = new Label("Saturday:");
		saturdayField = new TextField();

		applySchedEditBtnLibr = createBtn("Apply");

		VBox contentLibrSettings = createPanel(titleLibrSchedEdit, sunday, sundayField, monday, mondayField, tuesday,
				tuesdayField, wednesday, wednesdayField, thursday, thursdayField, friday, fridayField, saturday,
				saturdayField, applySchedEditBtnLibr);

		contentLibrSettings.setSpacing(4);
		contentLibrSettings.setPadding(new Insets(0, 0, 10, 0));

		
		applySchedEditBtnLibr.setOnAction((event -> {
			try {

				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(0).setDay("0");
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(1).setDay("1");
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(2).setDay("2");
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(3).setDay("3");
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(4).setDay("4");
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(5).setDay("5");
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(6).setDay("6");
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(0)
						.setHour(sundayField.getText());
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(1)
						.setHour(mondayField.getText());
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(2)
						.setHour(tuesdayField.getText());
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(3)
						.setHour(wednesdayField.getText());
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(4)
						.setHour(thursdayField.getText());
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(5)
						.setHour(fridayField.getText());
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleB().get(6)
						.setHour(saturdayField.getText());
				LibrarianScheduleController.getLibrarianScheduleController().updateOrario();
				LibrarianScheduleController.getLibrarianScheduleController().getScheduleFromDb();
				root.setCenter(new LibrarianScheduleGUI().createLibrarianScheduleGUI(root));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
		
		return contentLibrSettings;

	}
}
