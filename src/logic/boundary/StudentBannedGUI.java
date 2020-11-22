package logic.boundary;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.application.Main;
import logic.bean.MessageBean;
import logic.bean.StudentBean;
import logic.control.StudentSuperviseController;

public class StudentBannedGUI extends GuiSUPER {
	protected List<MessageBean> messageBeanList;
	protected StudentBean studentBean;
	protected Button logOutStud;
	protected HBox topPanel;
	protected VBox center;
	protected List<HBox> hBoxList;
	
	public StudentBannedGUI (StudentBean studentBean, StudentSuperviseController superviseController) {
		messageBeanList = superviseController.getMessages(studentBean.getMail());
		this.studentBean = studentBean;
		logOutStud = createBtn("Log out");
		hBoxList = new ArrayList<>();
	}
	
	String font = "-fx-font:";
    String arial = " arial;";
	
	public Scene createBannedGUI(Main main) {
		createBase(main);
        HBox bottom = new HBox(logOutStud);
        bottom.setPadding(new Insets(20));
        BorderPane root = new BorderPane(center, topPanel, null, bottom, null);
		return (new Scene(root, 800, 600));
	}
	
	public void createBase(Main main) {
		ImageView studentHomeImg = createImg("src/resources/logo_button2.png");
		topPanel = createTopPanel(studentHomeImg, studentBean.getUsername());

		logOutStud.setOnAction((event -> {
			try {
				// alert conferma log out
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Log out");
				alert.setHeaderText("Warning!");
				alert.setContentText("Are you sure you want to log out?"
						+ "\nReply your messages");
				if (alert.showAndWait().get() == ButtonType.OK) {
					main.setNewStage(START);
				}
			}
			catch (NoSuchElementException exc) {
				//Nothing to do
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}));
		

		center = new VBox();                             //Contiene un titolo e la lista di messaggi
		Label title = new Label("Reported account");
		title.setStyle(font + 30 + arial);
        title.setAlignment(Pos.CENTER);
        center.getChildren().add(title);
        center.setSpacing(20);
        center.setPadding(new Insets(20));
        for (int i=0; i<messageBeanList.size(); i++) {
        	HBox hBox = new HBox();                         //Contiene un messaggio per lo studente
        	Label messageForm = new Label();
        	messageForm.setText(messageBeanList.get(i).getTitle() +"\n"+ messageBeanList.get(i).getText());
        	messageForm.setStyle(font + 20 + arial);
        	Label info = new Label();
        	info.setText("For explanation contact:\n" + messageBeanList.get(i).getLibrarianId());
        	info.setStyle(font + 20 + arial);
        	info.setMinSize(220,50);
        	messageForm.setPrefWidth(450);
        	hBox.getChildren().addAll(messageForm, info);
        	hBox.setSpacing(25);
        	hBoxList.add(hBox);
        	center.getChildren().add(hBox);
        }
	}

}
