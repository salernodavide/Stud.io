package logic.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import logic.application.Main;
import logic.bean.StudentBean;
import logic.control.StudentSuperviseController;

import java.util.ArrayList;
import java.util.List;

public class StudentNotifiedGUI extends StudentBannedGUI {
	private StudentSuperviseController superviseController;
	private List<Button> btnList;
	
	public StudentNotifiedGUI (StudentBean studentBean, StudentSuperviseController superviseController) {
		super(studentBean, superviseController);
		this.superviseController = superviseController;
		btnList = new ArrayList<>();
	}
	public Scene createStudentNotifiedGUI(Main main) {
		createBase(main);
        for (int i=0; i<messageBeanList.size(); i++) {
        	HBox hBox = hBoxList.get(i);
        	Button button = new Button("Ok");
            button.setAlignment(Pos.CENTER);
            btnList.add(button);
            Long id = messageBeanList.get(i).getId();
            btnList.get(i).setOnAction((event -> {
    			superviseController.deleteMessage(id);
    			superviseController.sendMessageInteraction(studentBean.getMail());
    			btnList.remove(button);
    			center.getChildren().remove(hBox);
    			if (btnList.isEmpty()) {
    				main.setNewStage(START);
    			}
            }));
    		
            hBox.getChildren().add(button);
            hBox.setSpacing(25);
        	
        }
        HBox bottom = new HBox(logOutStud);
        bottom.setPadding(new Insets(20));
        BorderPane root = new BorderPane(center, topPanel, null, bottom, null);
		return (new Scene(root, 800, 600));
	}

}
