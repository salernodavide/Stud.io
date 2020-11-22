package logic.boundary;

import java.io.File;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.StudentBean;
import logic.control.LoginController;


public class GuiSUPER {
	static Logger myLogger = Logger.getLogger("logger");
	public static final String PROJ_NAME = "Stud.io";
	public static final String START = "StartGUI";
	public static final String LOGIN = "LoginGUI";
	public static final String REGISTRATION = "RegistrationGUI";
	public static final String STUDENT = "StudentGUI";
	public static final String NOTIFIEDSTUDENT = "StudentNotifiedGUI";
	public static final String BANNEDSTUDENT = "StudentBannedGUI";
	public static final String LIBRARIAN = "LibrarianGUI";
	public static final String GUEST = "GuestGUI";
	public static final String REG_STUD_SETTINGS = "RegStudentSettingsGUI";
	public static final String REG_LIBR_SETTINGS = "RegLibrarianSettingsGUI";
	protected LoginController loginController;
	protected StudentBean studentBean;

	public GuiSUPER() {
		loginController = new LoginController();  
		
	}
	
	public GuiSUPER(StudentBean studentBean) {
		loginController = new LoginController();  
		this.studentBean = studentBean;
	}
	

	public void leftPadding(VBox panel, int padding) {
		panel.setPadding(new Insets(0, 0, 0, padding));
	}

	public void leftVPadding(VBox vPanel, int padding) {
		vPanel.setPadding(new Insets(0, 0, 0, padding));
	}

	public void leftHPadding(HBox hPanel) {
		hPanel.setPadding(new Insets(0, 0, 0, 20));
	}

	public VBox createPanel(Node... nodes) {
		VBox panel = new VBox();
		for (Node next : nodes)
			panel.getChildren().add(next);
		panel.setAlignment(Pos.CENTER);
		panel.setMaxWidth(250);
		panel.setSpacing(10);
		return panel;
	}

	public HBox createTopPanel(Node btn, String title) {
		Label titleLabel = new Label(title);
		HBox topPanel = new HBox();
		topPanel.getChildren().addAll(btn, titleLabel);
		topPanel.setPrefWidth(760);
		topPanel.setPrefHeight(50);
		topPanel.setSpacing(20);
		topPanel.setAlignment(Pos.CENTER_LEFT);
		HBox.setMargin(btn, new Insets(0, 0, 0, 20));
		topPanel.setStyle("-fx-background-color: #52be8c;");
		return topPanel;
	}

	public Button createBtn(String nameBtn) {
		Button btn = new Button(nameBtn);
		btn.setPrefWidth(107);
		btn.setPrefHeight(20);
		return btn;
	}

	public ImageView createImg(String nameImg) {
		File file = new File(nameImg);
		ImageView imageW = new ImageView();
		Image image = new Image(file.toURI().toString());
		imageW.setImage(image);
		imageW.prefWidth(100);
		imageW.prefHeight(100);
		return imageW;
	}

	/*
	 * setta altezza button a 100
	 */
	public void setHeightBtn(Button nameBtn, int height) {
		nameBtn.setPrefHeight(height);
	}

	/*
	 * crea label della dimensione specificata in size, con padding bottom 20
	 */
	public Label createLabel(String title, int size) {
		Label lbl = new Label(title);
		lbl.setStyle("-fx-font: " + size + " arial;");
		lbl.setPadding(new Insets(0, 0, 20, 0));
		return lbl;
	}

	/*
	 * Metodo che crea textfield della larghezza specificata (inserendo una stringa
	 * come parametro in TextField, essa viene inizializzata con una stringa)
	 */
	public TextField createTextField(int width) {
		TextField txtFld = new TextField();
		txtFld.setAlignment(Pos.CENTER);
		txtFld.setPrefWidth(width);
		return txtFld;
	}

	/*
	 * Metodo per creare una riga composta da label e button
	 */
	public HBox createLabelWithBtn(String nomeLabel, String typeBtn) {
		HBox panel = new HBox();
		panel.setPrefWidth(300);
		Button btn = new Button();
		btn.setPrefSize(30, 40);
		Button lbl = new Button(nomeLabel);
		lbl.setPrefSize(300, 40);
		if (typeBtn.equals("del")) {
			btn.setGraphic(createImg("src/resources/delete.png"));
		}
		if (typeBtn.equals("reply")) {
			btn.setGraphic(createImg("src/resources/reply.png"));
		} else myLogger.info("Errore passaggio parametro typeBtn "
					+ "a metodo createLabelWithBtn(String nomeLabel, String typeBtn). Use 'del' or 'reply' ");
		panel.getChildren().addAll(lbl, btn);
		return panel;
	}



	public VBox loadPrefers() {
		Label title = createLabel("Prefers:", 24);

		ScrollPane scroll = new ScrollPane();

		VBox listFavourites = new VBox();
		listFavourites.setMinWidth(250);

		for (int j = 0; j < 10; j++) {
			HBox panel = new HBox();
			Button namePref = new Button("Favourite library " + j);
			namePref.setPrefSize(300, 40);
			Button delPref = new Button();
			delPref.setGraphic(createImg("src/resources/delete.png"));
			delPref.setPrefSize(30, 40);
			panel.getChildren().addAll(namePref, delPref);
			listFavourites.getChildren().add(panel);
		}

		scroll.setContent(listFavourites);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		VBox content = new VBox();
		content.getChildren().addAll(title, scroll);
		content.setAlignment(Pos.CENTER);
		content.setMinWidth(365);
		content.setMaxHeight(300);
		return content;

	}
	
	public Node getEventNode(ActionEvent event) {
		return (Node)event.getSource();
	}
	
	public Stage getNodeStage(Node node) {
		return (Stage) node.getScene().getWindow();
	}
}
