package logic.boundary;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.Main;
import logic.bean.LibrBean;
import logic.constants.FxmlConstants;
import logic.control.LibraryMainPageController;
import logic.entity.Library;
import logic.entity.Prenotazione;
import logic.exceptions.NoOneSelectedException;

public class HomeLibrarianGUI extends LibrarianGUI {
	static final String FONT = "-fx-font-weight: bold;";
	Button add;
	Button del;
	Button confirmButton;
	Button deleteButton;
	TableView<Prenotazione> table;
	ObservableList<Prenotazione> prenotazioni;
	static Logger myLogger = Logger.getLogger("logger");

	
	public HomeLibrarianGUI(LibrBean libraryBean) {
		super(libraryBean);
		add = createBtn("Add");
		del = createBtn("Del");
		prenotazioni = FXCollections.observableArrayList();
	}
	


	public ObservableList<Prenotazione> getPrenotazione() {
		for (int i = 0; i < LibraryMainPageController.getLibraryMainPageController().getBooks().size(); i++)
			prenotazioni.add(LibraryMainPageController.getLibraryMainPageController().getBooks().get(i));
		return prenotazioni;
	}

	@SuppressWarnings("unchecked")
	public Scene createLibrarianGUI(Main main) {

		Label graphTitle = new Label("Update seats:");
		graphTitle.setStyle("-fx-font: 24 arial;");

		HBox posti = new HBox();

		TableColumn<Prenotazione, String> usrnm = new TableColumn<>("Visitatore:");
		usrnm.setPrefWidth(500);
		usrnm.setCellValueFactory(new PropertyValueFactory<>("usernameStud"));

		TableColumn<Prenotazione, String> timestamp = new TableColumn<>("Orario:");
		timestamp.setMinWidth(137);
		timestamp.setCellValueFactory(new PropertyValueFactory<>("orarioPrenotazione"));

		table = new TableView<>();
		table.setItems(getPrenotazione());
		table.getColumns().addAll(usrnm, timestamp);
		table.setMinHeight(150);
		table.setPlaceholder(new Label("No books available"));

		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(
						new PieChart.Data("Busy", libraryBean.getPostiOccupati()),
						new PieChart.Data("Booked", prenotazioni.size()),
						new PieChart.Data("Free", libraryBean.getCapacity()-libraryBean.getPostiOccupati()));

		PieChart chart = new PieChart(pieChartData);
		chart.getData().forEach(data -> Tooltip.install(data.getNode(),
				new Tooltip(String.format("%.2f%%", (data.getPieValue() / 100)))));
		chart.setLegendVisible(false);

		HBox seatsStatus = new HBox();

		Label free = new Label("Free: " + (libraryBean.getCapacity() - libraryBean.getPostiOccupati() - prenotazioni.size()));
		free.setPrefSize(100, 20);
		free.setStyle("-fx-text-fill: #38761d;" + FONT);
		Label busy = new Label("Busy: " + libraryBean.getPostiOccupati());
		busy.setPrefSize(100, 20);
		busy.setStyle("-fx-text-fill: #cc4125;" + FONT);
		Label booked = new Label("Booked: " + prenotazioni.size());
		booked.setPrefSize(100, 20);
		booked.setStyle("-fx-text-fill: #e69138;" + FONT);

		seatsStatus.getChildren().addAll(free, busy, booked);
		seatsStatus.setAlignment(Pos.CENTER);
		seatsStatus.setPadding(new Insets(0, 0, 0, 70));

		VBox graphAndLabels = new VBox();
		graphAndLabels.getChildren().addAll(chart, seatsStatus);
		graphAndLabels.setPadding(new Insets(10, 10, 10, 10));

		add.setPrefSize(100, 100);

		del.setPrefSize(100, 100);

		VBox buttons = createPanel(add, del);
		buttons.setMinSize(200, 120);
		buttons.setSpacing(20);
		buttons.setAlignment(Pos.CENTER_RIGHT);

		posti.getChildren().addAll(graphAndLabels, buttons);
		

		confirmButton = new Button("", createImg("src/resources/icons8-segno-di-spunta-16.png"));
		confirmButton.setOnAction((e -> {
			try {
				if (table.getSelectionModel().isEmpty()) {
					throw new NoOneSelectedException();
				}
				LibraryMainPageController.getLibraryMainPageController().confirmPrenotatione(
						table.getSelectionModel().getSelectedItems().get(0).getUser(), libraryBean.getMail());
				delClicked();
				LibraryMainPageController.getLibraryMainPageController().updateLibraryMainPage();
				createHomeLibrarian(main);
			} catch (NoOneSelectedException exc) {
				exc.createAlert();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}));

		deleteButton = new Button("", createImg("src/resources/icons8-eliminare-16.png"));
		deleteButton.setOnAction((e -> {
			try {
				if (table.getSelectionModel().isEmpty()) {
					throw new NoOneSelectedException();
				}
				LibraryMainPageController.getLibraryMainPageController()
						.deletePrenotatione(table.getSelectionModel().getSelectedItems().get(0).getUser());
				delClicked();
				LibraryMainPageController.getLibraryMainPageController().updateLibraryMainPage();
				createHomeLibrarian(main);
			} catch (NoOneSelectedException exc) {
				exc.createAlert();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}));

		addOnAction(add, main);

		delOnAction(del, main);

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(confirmButton, deleteButton);
		hbox.setAlignment(Pos.CENTER_RIGHT);

		Label titleBookings = new Label("Bookings:");
		titleBookings.setStyle("-fx-font: 20 arial;");
		titleBookings.setPadding(new Insets(0, 0, 10, 0));

		VBox vboxBook = new VBox();
		vboxBook.getChildren().addAll(titleBookings, table, hbox);
		vboxBook.setAlignment(Pos.CENTER);

		VBox contentPanel = new VBox();
		contentPanel.getChildren().addAll(graphTitle, posti, vboxBook, titleBookings, table, hbox);
		contentPanel.setPadding(new Insets(10, 20, 10, 20));
		contentPanel.setPrefSize(405, 700);
		contentPanel.setAlignment(Pos.CENTER);

		root.setCenter(contentPanel);
		Scene scene = new Scene(root, 800, 600);
		
		reports.setOnAction((event -> {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FxmlConstants.ISSUE_LIST_LIBRARY_GUI));
			IssueListLibraryBoundary issueListLibraryBoundary = new IssueListLibraryBoundary(new Library(this.libraryBean.getUsername(),
					this.libraryBean.getName(),
					this.libraryBean.getMail(),
					this.libraryBean.getPassword(),
					this.libraryBean.getAddress(),
					this.libraryBean.getCapacity().toString(),
					this.libraryBean.getPhone(),
					this.libraryBean.getCity(),
					this.libraryBean.getPostiOccupati().toString()), 
					scene, 
					main);
			fxmlLoader.setController(issueListLibraryBoundary);
			
			BorderPane nextParent = null;
			try {
				nextParent = fxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        Scene nextScene = new Scene(nextParent, 800, 600);
			
	        //This line gets the Stage information
	        Stage window = getNodeStage(getEventNode(event));
	        
	        window.setScene(nextScene);
	        window.show();
			
			
		}));

		return (scene);

	}

	private void delClicked() {
		ObservableList<Prenotazione> bookSelected;
		ObservableList<Prenotazione> allBookings;
		allBookings = table.getItems();
		bookSelected = table.getSelectionModel().getSelectedItems();
		bookSelected.forEach(allBookings::remove);
	}

}