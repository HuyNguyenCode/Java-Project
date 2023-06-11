package sceneBuilder;

import java.net.URL;
import java.util.ResourceBundle;

import database.ControllBooks;
import database.ControlInvoices;
import database.ControllStaffs;
import database.ControllChartValues;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.BarChartData;
import model.LineChartData;

public class PrintReportController implements Initializable{

    @FXML
    private LineChart<String, Double> booksSoldChart;

    @FXML
    private BarChart<String, Integer> salesChart;

    @FXML
    private VBox pnDashboard;


    @FXML
    private Label totalBooks;

    @FXML
    private Label totalInvoices;

    @FXML
    private Label totalStaffs;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            // String userName = SigninController.user.getFullName();
            // this.userNameInScene.setText(userName);
            
            String totalBooks = Integer.toString(ControllBooks.countBooksFromDB());   
            String totalInvoices = Integer.toString(ControlInvoices.countInvoicesFromDB());    
            String totalStaffs = Integer.toString(ControllStaffs.countStaffsFromDB());  
            
            this.totalBooks.setText(totalBooks);
            this.totalInvoices.setText(totalInvoices);
            this.totalStaffs.setText(totalStaffs);  

            // BarChart - salesChart
            
            ObservableList<BarChartData> barChartDataList = ControllChartValues.getBarChartDataFromDB();

            XYChart.Series<String, Integer> barChart = new XYChart.Series<>();
            barChart.setName("Number of books by genre");
            for(BarChartData barChartData : barChartDataList){
                barChart.getData().add(new XYChart.Data<String, Integer>(barChartData.getX(), barChartData.getY()));
            }
            salesChart.getData().add(barChart);

            // LineChart - Double
            
            ObservableList<LineChartData> lineChartDataList = ControllChartValues.getLineChartDataFromDB();

            XYChart.Series<String, Double> lineChart = new XYChart.Series<>();
            lineChart.setName("Revenue per year");
            for(LineChartData lineChartData : lineChartDataList){
                lineChart.getData().add(new XYChart.Data<String, Double>(lineChartData.getX(), lineChartData.getY()));
            }
            booksSoldChart.getData().add(lineChart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
