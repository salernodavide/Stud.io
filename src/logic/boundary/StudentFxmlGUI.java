package logic.boundary;


import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import logic.application.Main;
import logic.constants.FxmlConstants;
import logic.control.SearchController;
import logic.entity.Student;

public class StudentFxmlGUI extends FxmlGUI{
	
	@FXML private Button btnSearchSeat;
	@FXML private Button btnMessages;
	@FXML private Button btnLogout;
	@FXML protected Button btnUserPic;
	@FXML protected ImageView ivBtnUserPic;
	private Student student;
	private SearchController searchController;
	protected Main main;
	//Dichiarazione messagesController
	
	public StudentFxmlGUI() {
	}
	
	public StudentFxmlGUI(Student student,Main main) {
		this.student = student;
		this.main=main;
	}
	
	/*
	 * Metodo associato al click sull'immagine utente nel pannello
	 */
	@FXML
	public void profileUserInfoButtonClicked() {
		// passa a vista profilo
	}
	
	/*
	 * Metodo associato al click su "Search Seat" nel pannello
	 */
	@FXML
	public void btnSearchSeatClicked(ActionEvent event) throws IOException {
		// passa a vista search
		searchController=new SearchController();
		guiLoader(FxmlConstants.STUDENT_SEARCH_GUI, new StudentSearchFxmlGUI(student, searchController,main), event);

	}
	
	/*
	 * Metodo associato al click su "Messages" nel pannello
	 */
	@FXML
	public void messagesButtonClicked() {
		// passa a vista messaggi
	}
	
	/*
	 * Metodo associato al click su "Logout" nel pannello
	 */
	@FXML
	public void logoutButtonClicked() {
		try {
			// alert conferma log out
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Log out");
			alert.setHeaderText("Warning!");
			alert.setContentText("Are you sure you want to log out?");
			if (alert.showAndWait().get() == ButtonType.OK) {
				main.setNewStage("StartGUI");
			}
		}
		catch (NoSuchElementException exc) {
			//Nothing to do
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SearchController getSearchController() {
		return searchController;
	}

	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// default
	}

	

}
