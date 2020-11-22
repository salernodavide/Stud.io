package logic.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import logic.application.Main;
import logic.bean.LibrBean;
import logic.control.LibrarianSettingsController;
import logic.control.LibraryMainPageController;

public class LibrarianSettingsGUI extends LibrarianGUI {

	protected TextField nameLibrField;
	protected TextField addressLibrField;
	protected TextField cityLibrField;
	protected TextField emailLibrField;
	protected TextField passwordLibrField;
	protected TextField usernameLibrField;
	protected TextField phoneLibrField;
	protected TextField capacityLibrField;
	protected Button applyBtnLibr;

	public LibrarianSettingsGUI() {

		nameLibrField = createTextField(400);
		addressLibrField = createTextField(400);
		cityLibrField = createTextField(400);
		emailLibrField = createTextField(400);
		passwordLibrField = createTextField(400);
		usernameLibrField = createTextField(400);
		phoneLibrField = createTextField(400);
		capacityLibrField = createTextField(400);
		applyBtnLibr = createBtn("Apply");

	}
	
	public LibrarianSettingsGUI(LibrBean libraryBean) {
        super(libraryBean);
		nameLibrField = createTextField(400);
		addressLibrField = createTextField(400);
		cityLibrField = createTextField(400);
		emailLibrField = createTextField(400);
		passwordLibrField = createTextField(400);
		usernameLibrField = createTextField(400);
		phoneLibrField = createTextField(400);
		capacityLibrField = createTextField(400);
		applyBtnLibr = createBtn("Apply");

	}

	public VBox createLibrarianSettingsGUI(Main main) {
		Label titleLibrSettings = createLabel("Settings:", 24);

		ScrollPane spLibrSettings = new ScrollPane();

		Label nameLibr = new Label("Name Library:");
		Label addressLibr = new Label("Address:");
		Label cityLibr = new Label("City:");
		Label emailLibr = new Label("Email:");
		Label passwordLibr = new Label("Password:");
		Label usernameLibr = new Label("Username:");
		Label phoneLibr = new Label("Phone:");
		Label capacityLibr = new Label("Capacity (seats):");

		VBox fieldSettings = new VBox();
		fieldSettings.getChildren().addAll(nameLibr, nameLibrField, usernameLibr, usernameLibrField, emailLibr,
				emailLibrField, passwordLibr, passwordLibrField, addressLibr, addressLibrField, cityLibr, cityLibrField,
				phoneLibr, phoneLibrField, capacityLibr, capacityLibrField);

		fieldSettings.setSpacing(4);
		fieldSettings.setPadding(new Insets(10, 20, 10, 20));
		fieldSettings.setAlignment(Pos.CENTER);

		spLibrSettings.setContent(fieldSettings);
		spLibrSettings.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		spLibrSettings.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		spLibrSettings.setMaxSize(450, 300);

		VBox contentLibrSett = new VBox();
		contentLibrSett.getChildren().addAll(titleLibrSettings, spLibrSettings, applyBtnLibr);
		contentLibrSett.setAlignment(Pos.CENTER);
		contentLibrSett.setSpacing(10);
		contentLibrSett.setPadding(new Insets(0, 20, 20, 20));

		applyBtnLibr.setOnAction((event -> {
			try {
				LibrBean newLibraryBean = new LibrBean();
				newLibraryBean.setMail(emailLibrField.getText());
				newLibraryBean.setPassword(passwordLibrField.getText());
				newLibraryBean.setUsername(usernameLibrField.getText());
				newLibraryBean.setName(nameLibrField.getText());
				newLibraryBean.setAddress(addressLibrField.getText());
				newLibraryBean.setPhone(phoneLibrField.getText());
				newLibraryBean.setCity(cityLibrField.getText());
				if (capacityLibrField.getText().isEmpty())
					newLibraryBean.setCapacity(0);
				else
					newLibraryBean.setCapacity(Integer.valueOf(capacityLibrField.getText()));
				
				LibrarianSettingsController.getLibrarianSettingsController().setLibrInfoB(newLibraryBean);
				LibrarianSettingsController.getLibrarianSettingsController().updateLibraryInfo(libraryBean.getMail());
				LibraryMainPageController.getLibraryMainPageController().updateLibraryMainPage();
				createHomeLibrarian(main);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

		return contentLibrSett;
	}
}