package sceneBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import database.ControllDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BarChartData;
import model.LineChartData;
import model.Tool;

public class DashboardController implements Initializable{

    @FXML
    private HBox btnLogout;

    @FXML
    private HBox btnBooks;

    @FXML
    private HBox btnDashboard;

    @FXML
    private HBox btnBookEntry;

    @FXML
    private HBox btnReport;

    @FXML
    private Button btnExit;

    @FXML
    private HBox btnInvoices;

    @FXML
    private HBox btnStaffs;

    @FXML
    private HBox btnSuppliers;

     @FXML
    private HBox btnSettings;

    @FXML
    private Button btnbookManage;

    @FXML
    private PieChart categoryChart;

    @FXML
    private BorderPane pnBooks;

    @FXML
    private VBox pnDashboard;

    @FXML
    private Label userNameInScene;

    @FXML
    private Label totalBooks;

    @FXML
    private Label totalInvoices;

    @FXML
    private Label totalStaffs;

    @FXML
    private LineChart<String, Double> booksSoldChart;

    @FXML
    private BarChart<String, Integer> salesChart;

    @FXML
    private TextField searchInput;

    private Class<DashboardController> dashboarClass = DashboardController.class;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            String userName = SigninController.user.getFullName();
            String totalBooks = Integer.toString(ControllDB.countBooksFromDB());   
            String totalInvoices = Integer.toString(ControllDB.countInvoicesFromDB());    
            String totalStaffs = Integer.toString(ControllDB.countStaffsFromDB());  
            
            this.userNameInScene.setText(userName);
            this.totalBooks.setText(totalBooks);
            this.totalInvoices.setText(totalInvoices);
            this.totalStaffs.setText(totalStaffs);  

            // BarChart - salesChart
            
            ObservableList<BarChartData> barChartDataList = ControllDB.getBarChartDataFromDB();

            XYChart.Series<String, Integer> barChart = new XYChart.Series<>();
            barChart.setName("Number of books by genre");
            for(BarChartData barChartData : barChartDataList){
                barChart.getData().add(new XYChart.Data<String, Integer>(barChartData.getX(), barChartData.getY()));
            }
            salesChart.getData().add(barChart);

            // LineChart - Double
            
            ObservableList<LineChartData> lineChartDataList = ControllDB.getLineChartDataFromDB();

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

    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == btnExit) {
            Optional<ButtonType> result = Tool.showConfirmAlert("Confirm to exit program !", "Do you want to exit ?");
            if (result.get() == ButtonType.OK) { 
                javafx.application.Platform.exit();
            }
        } else if (event.getSource() == btnInvoices) {
            Tool.loadScene(dashboarClass, "Invoice", event);
        } else if (event.getSource() == btnStaffs) {
            Tool.loadScene(dashboarClass, "Staffs", event);
        } else if (event.getSource() == btnBooks) {
            Tool.loadScene(dashboarClass, "MainScene", event);
        } else if (event.getSource() == btnSuppliers) {
            Tool.loadScene(dashboarClass, "Suppliers", event);
        } else if (event.getSource() == btnBookEntry) {
            Tool.loadScene(dashboarClass, "BookEntry", event);
        } else if (event.getSource() == btnLogout) {
            Tool.loadScene(dashboarClass, "Signin", event);
        }

        if (event.getSource() == btnSettings) {

        }

        if (event.getSource() == btnReport) {
            
        }
    }
}
