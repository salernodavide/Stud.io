package logic.boundary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.entity.Servizio;

public class LibrarianServicesGUI extends LibrarianGUI {

	TableView<Servizio> tableServ;
	TextField nuovoServ;
	TextField descrNuovoServ;
	Button add;
	Button del;

	public ObservableList<Servizio> getServizio() {
		ObservableList<Servizio> servizi = FXCollections.observableArrayList();
		servizi.add(new Servizio("Wi fi", "bib1", "Aree wi fi alta velocita'."));
		servizi.add(new Servizio("Bagno disabili", "bib2", "Bagno disabili per ogni struttura."));
		servizi.add(new Servizio("Workstation", "bib3", "23 workstations in sala lettura."));
		servizi.add(new Servizio("Bar", "bib4", "Servizio bar attivo 08:00-18:00"));
		return servizi;
	}

	@SuppressWarnings("unchecked")
	public VBox createLibrarianServicesGUI() {

		Label titleServ = createLabel("Services", 24);

		TableColumn<Servizio, String> nomeServ = new TableColumn<>("Nome servizio");
		nomeServ.setMinWidth(137);
		nomeServ.setCellValueFactory(new PropertyValueFactory<>("nomeServizio"));

		TableColumn<Servizio, String> descrServ = new TableColumn<>("Descrizione");
		descrServ.setMinWidth(500);
		descrServ.setCellValueFactory(new PropertyValueFactory<>("descrizioneServizio"));

		nuovoServ = new TextField();
		nuovoServ.setPromptText("Nuovo servizio");
		nuovoServ.setPrefWidth(100);

		descrNuovoServ = new TextField();
		descrNuovoServ.setPromptText("Contenuto");
		descrNuovoServ.setPrefWidth(400);

		add = new Button("Add");
		add.setOnAction(e -> addClicked());

		del = new Button("Del");
		del.setOnAction(e -> delClicked());

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(nuovoServ, descrNuovoServ, add, del);

		tableServ = new TableView<>();
		tableServ.setItems(getServizio());
		tableServ.getColumns().addAll(nomeServ, descrServ);

		VBox vboxServ = new VBox();
		vboxServ.getChildren().addAll(titleServ, tableServ, hbox);
		vboxServ.setAlignment(Pos.CENTER);
		vboxServ.setPadding(new Insets(20, 20, 20, 20));

		return vboxServ;

	}

	private void delClicked() {
		ObservableList<Servizio> servizioSelected;
		ObservableList<Servizio> allServizi;
		allServizi = tableServ.getItems();
		servizioSelected = tableServ.getSelectionModel().getSelectedItems();
		servizioSelected.forEach(allServizi::remove);
	}

	private void addClicked() {
		Servizio serv = new Servizio();
		serv.setNomeServizio(nuovoServ.getText());
		serv.setDescrizioneServizio(descrNuovoServ.getText());
		tableServ.getItems().add(serv);
		nuovoServ.clear();
		descrNuovoServ.clear();
	}

}