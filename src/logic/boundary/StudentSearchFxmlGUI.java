package logic.boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import logic.application.Main;
import logic.bean.SearchBean;
import logic.constants.FxmlConstants;
import logic.control.SearchController;
import logic.entity.Library;
import logic.entity.Student;

public class StudentSearchFxmlGUI extends FxmlGUI {
	
	@FXML private TextField tfSearchSeat;
	@FXML private Button btnSearchSeat;
	@FXML private Button btnBack;
	@FXML private Button btnNext;
	@FXML private ListView<String> lvSearchSeatResults;
	private Student student;
	private SearchController searchController;
	private SearchBean searchBean;
	private List<Library> librariesResult;
	private Integer selectListViewIndex;
	private StudentSearchResultFxmlGUI studentSearchResultFxmlGUI;
	private Main main;
	
	
	public StudentSearchFxmlGUI() {
		this.searchBean = searchController.getSearchBean();
		this.lvSearchSeatResults = new ListView<>();
		this.librariesResult = new ArrayList<>();
	}
	
	public StudentSearchFxmlGUI(Student student, SearchController searchController, Main main) {
		this.student = student;
		this.searchController = searchController;
		this.searchBean = searchController.getSearchBean();
		this.lvSearchSeatResults = new ListView<>();
		this.librariesResult = new ArrayList<>();
		this.main=main;
	}

	/*
	 * Metodo associato al click su "Search"
	 */
	@FXML
	public void searchSeat() {
		searchLibrariesFromCity(tfSearchSeat.getText().toString());	//chiamo metodo controller
		librariesResult = searchBean.getResultLibraries();
		updateListView();
	}

	/*
	 * Metodo per aggiornare la listView
	 */
	private void updateListView() {
		for(int i=0; i<librariesResult.size(); i++) {
			lvSearchSeatResults.getItems().add(librariesResult.get(i).getName() + " - Posti liberi: " + (librariesResult.get(i).getCapacity() - librariesResult.get(i).getPostiOccupati()));
		}
	}
	
	/*
	 * Metodo per la selezione della biblioteca nella listView
	 */
	@FXML
	public void selectLibraryFromResults() {
		selectListViewIndex = lvSearchSeatResults.getSelectionModel().getSelectedIndex();
	}
	
	/*
	 * Metodo per tornare indietro
	 */
	
	
	@FXML
	public void back(ActionEvent event) throws IOException {
		// passa a vista student
		
		guiLoader(FxmlConstants.STUDENT_GUI, new StudentFxmlGUI(student,main), event);

	}
	
	/*
	 * Metodo associato al click su "Next" per aprire la pagina sulle informazioni della biblioteca selezionata
	 */
	@FXML
	public void next(ActionEvent event) throws IOException {
		// passa a vista risultato
		guiLoader(FxmlConstants.STUDENT_SEARCH_RESULT_GUI,new StudentSearchResultFxmlGUI(student, librariesResult.get(selectListViewIndex), this), event);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.selectListViewIndex = -1;
		this.tfSearchSeat.setText("");
	}
	
	/////////////////////
	//	TO CONTROLLER  //
	/////////////////////
	
	/*
	 * Metodo per controller
	 */
	private void searchLibrariesFromCity(String city) {
		searchController.searchLibraryFromCity(city);
	}

	public StudentSearchResultFxmlGUI getStudentSearchResultFxmlGUI() {
		return studentSearchResultFxmlGUI;
	}

	public void setStudentSearchResultFxmlGUI(StudentSearchResultFxmlGUI studentSearchResultFxmlGUI) {
		this.studentSearchResultFxmlGUI = studentSearchResultFxmlGUI;
	}

}
