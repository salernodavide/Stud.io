package logic.boundary;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import logic.bean.LibrBean;
import logic.control.LibrarianSuperviseController;
import logic.control.SuperviseController;

public class SuperviseGUI extends LibrarianGUI {
	private List<Button> infoAccountBtnList;
	private LibrarianSuperviseController superviseController;
	private List<String> usernameList;
	private VBox gui;

	public SuperviseGUI(LibrBean libraryBean) {
		super(libraryBean);
		infoAccountBtnList = new ArrayList<>(); //lista contenente tanti bottoni quanti sono gli utenti
        superviseController = new SuperviseController();
        usernameList = new ArrayList<>();
        usernameList = superviseController.fillSupervisePage(libraryBean.getMail());
        
	}

	public Button createBtnSupervise(String nameBtn) {
		Button btnUser = new Button(nameBtn);
		btnUser.setMinWidth(475);
		btnUser.setMinHeight(40);
		return btnUser;
	}
	
	public VBox createSuperviseGUI() {
		Label titleSupervisePage = createLabel("Studenti con cui hai interagito recentemente", 24);
		titleSupervisePage.setPadding(new Insets(50, 0, 15, 0));
		ScrollPane spUsers = new ScrollPane();
	    for (int i=0; i<usernameList.size(); i++) {
	    	infoAccountBtnList.add(createBtnSupervise(usernameList.get(i)));
	    	String studentId = infoAccountBtnList.get(i).getText();
	    	infoAccountBtnList.get(i).setOnAction((event -> {
				try {
					InfoAccountSelectedGUI infoAccountSelectedGUI = new InfoAccountSelectedGUI(this, superviseController.getInfoStudent(studentId));
					root.setCenter(infoAccountSelectedGUI.createInfoAccountGUI());					                
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}));
	    }
	    
		VBox vBoxUser = new VBox();
		for (int i=0; i<infoAccountBtnList.size(); i++) {
			vBoxUser.getChildren().add(infoAccountBtnList.get(i));
		}

		vBoxUser.setMaxSize(500, 500);
		vBoxUser.setAlignment(Pos.CENTER);
		vBoxUser.setSpacing(0);

		spUsers.setContent(vBoxUser);
		spUsers.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		spUsers.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		VBox content;
		content = createPanel(titleSupervisePage, spUsers);
		content.setMaxWidth(475);
		content.setAlignment(Pos.TOP_CENTER);
		
		this.gui = content;
		return content;
	}
	
	public VBox getGui() {
		return this.gui;
	}
	
	public LibrarianSuperviseController getSuperviseController() {
		return superviseController;
	}
}
