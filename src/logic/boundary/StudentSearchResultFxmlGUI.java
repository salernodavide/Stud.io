package logic.boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import logic.bean.BookSeatBean;
import logic.constants.FxmlConstants;
import logic.control.BookSeatController;
import logic.entity.Library;
import logic.entity.Prenotazione;
import logic.entity.Student;

public class StudentSearchResultFxmlGUI extends FxmlGUI {
	
	@FXML private Button btnBookSeat;
	@FXML private Button btnReportIssue;
	@FXML private Button btnNoticeboard;
	@FXML private Button btnTimetable;
	@FXML private Button btnMap;
	@FXML private Button btnBack;
	@FXML private Text txNome;
	@FXML private Text txIndirizzo;
	@FXML private Text txTelefono;
	@FXML private Text txMail;
	@FXML private Text txPostiLiberi;
	private Student student;
	private Library library;
	private Prenotazione prenotazione;
	private BookSeatController bookSeatController;
	private BookSeatBean bookSeatBean;
	private StudentSearchFxmlGUI studentSearchFxmlGUI;
	
	public StudentSearchResultFxmlGUI(Student student, Library library, StudentSearchFxmlGUI studentSearchFxmlGUI) {
		this.student = student;
		this.library = library;
		this.studentSearchFxmlGUI = studentSearchFxmlGUI;
		this.bookSeatBean = new BookSeatBean();
		this.bookSeatController = new BookSeatController(library);
	}

	
	@FXML
	private void reportIssue(ActionEvent event) {
		guiLoader(FxmlConstants.ISSUE_LIST_STUDENT_GUI, new IssueListStudentBoundary(this.student,this.library,this), event);
	}
	
	@FXML
	private void bookSeat(ActionEvent event) {
		bookSeatController.bookSeat(student, library);
		prenotazione = bookSeatBean.getPrenotazione();
	}
	
	@FXML
	private void noticeboard(ActionEvent event) {
		//da fare
	}
	
	@FXML
	private void timetable(ActionEvent event) {
		//da fare
	}
	
	@FXML
	private void map(ActionEvent event) {
		//da fare
	}
	
	@FXML
	private void backPressed(ActionEvent event) throws IOException {
		// passa a vista search
		guiLoader(FxmlConstants.STUDENT_SEARCH_GUI, studentSearchFxmlGUI,event);
				
	}
	
	/*
	 * Metodo per aggiornare i dati nella vista
	 */
	private void updateInfoView() {
		txNome.setText(library.getName());
		txIndirizzo.setText(library.getIndirizzo());
		txTelefono.setText(library.getPhone());
		txMail.setText(library.getMail());
		txPostiLiberi.setText(String.valueOf(library.getCapacity() - library.getPostiOccupati()));
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}


	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//da fare
		updateInfoView();
	}

}
