package logic.boundary;

import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class LibrarianStatisticsGUI extends LibrarianGUI {

	static final String SUNDAY = "Sunday";
	static final String MONDAY = "Monday";
	static final String TUESDAY = "Tuesday";
	static final String WEDNESDAY = "Wednesday";
	static final String THURSDAY = "Thursday";
	static final String FRIDAY = "Friday";
	static final String SATURDAY = "Saturday";

	public VBox createLibrarianStatisticsGUI() {

		Label titleLibrStatistics = createLabel("Statistics:", 24);

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Visits");

		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

		XYChart.Series<String, Number> dataWeek = new XYChart.Series<>();
		dataWeek.setName("Week: 12/05/2016 - 19/05/2016");

		dataWeek.getData().add(new XYChart.Data<String, Number>(SUNDAY, 56));
		dataWeek.getData().add(new XYChart.Data<String, Number>(MONDAY, 101));
		dataWeek.getData().add(new XYChart.Data<String, Number>(TUESDAY, 120));
		dataWeek.getData().add(new XYChart.Data<String, Number>(WEDNESDAY, 54));
		dataWeek.getData().add(new XYChart.Data<String, Number>(THURSDAY, 143));
		dataWeek.getData().add(new XYChart.Data<String, Number>(FRIDAY, 43));
		dataWeek.getData().add(new XYChart.Data<String, Number>(SATURDAY, 23));

		barChart.getData().add(dataWeek);

		VBox content = new VBox();
		content.getChildren().addAll(titleLibrStatistics, barChart);
		content.setAlignment(Pos.CENTER);

		return content;

	}

}