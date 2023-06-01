package sceneBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DashboardController implements Initializable{

    @FXML
    private HBox btnAddBook;

    @FXML
    private HBox btnBooks;

    @FXML
    private HBox btnDashboard;

    @FXML
    private HBox btnDelete;

    @FXML
    private Button btnExit;

    @FXML
    private HBox btnInvoices;

    @FXML
    private HBox btnStaffs;

    @FXML
    private HBox btnSuppliers;

    @FXML
    private HBox btnUpdate;

    @FXML
    private Button btnbookManage;

    @FXML
    private PieChart categoryChart;

    @FXML
    private BorderPane pnBooks;

    @FXML
    private VBox pnDashboard;

    @FXML
    private BarChart<String, Double> priceChart;

    @FXML
    private TextField searchInput;

    @FXML
    private StackedAreaChart<Integer, Integer> stockChart;


    @FXML
    private Label titleStatic;

    private Stage primaryStage;

    @SuppressWarnings("unchecked")

    public void initialize(URL location, ResourceBundle resources) {
        try {
                    
            // BarChart - priceChart
            XYChart.Series<String, Double> chart = new XYChart.Series<>();
            chart.setName("Product sold");
            chart.getData().add(new XYChart.Data<String, Double>("PA", 2000.0));
            chart.getData().add(new XYChart.Data<String, Double>("PB", 3000.0));
            chart.getData().add(new XYChart.Data<String, Double>("PC", 1500.0));
            chart.getData().add(new XYChart.Data<String, Double>("PD", 1000.0));
            priceChart.getData().add(chart);
            

            // PieChart - categoryChart
            ObservableList <PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("PA", 40),
                new PieChart.Data("PB", 20),
                new PieChart.Data("PC", 18),
                new PieChart.Data("PD", 70),
                new PieChart.Data("PE", 10)
            );
            categoryChart.setClockwise(true);
            categoryChart.setLabelLineLength(50);
            categoryChart.setLabelsVisible(true);
            categoryChart.setStartAngle(180);
            categoryChart.getData().addAll(pieChartData);
            categoryChart.setLegendSide(Side.LEFT);
            
            // StackedChart - stockChart
            XYChart.Series<Integer, Integer> stackedChart1 = new XYChart.Series<>();
            stackedChart1.setName("2012");
            stackedChart1.getData().add(new XYChart.Data<>(12, 25));
            stackedChart1.getData().add(new XYChart.Data<>(15, 18));
            stackedChart1.getData().add(new XYChart.Data<>(18, 15));
            stackedChart1.getData().add(new XYChart.Data<>(24, 19));
            stackedChart1.getData().add(new XYChart.Data<>(22, 21));

            XYChart.Series<Integer, Integer> stackedChart2 = new XYChart.Series<>();
            stackedChart2.setName("2011");
            stackedChart2.getData().add(new XYChart.Data<>(16, 12));
            stackedChart2.getData().add(new XYChart.Data<>(17, 26));
            stackedChart2.getData().add(new XYChart.Data<>(11, 21));
            stackedChart2.getData().add(new XYChart.Data<>(12, 29));
            stackedChart2.getData().add(new XYChart.Data<>(15, 23));
            stockChart.getData().addAll(stackedChart1, stackedChart2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == btnExit) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm to exit program !");
            alert.setContentText("Do you want to exit ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) { 
                javafx.application.Platform.exit();
            }
        }

        if (event.getSource() == btnInvoices) {
            Parent root = FXMLLoader.load(getClass().getResource("Invoice.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene invoiceScene = new Scene(root);
            primaryStage.setScene(invoiceScene);
            primaryStage.setTitle("Invoices Management");
            primaryStage.show(); 
        }

        if (event.getSource() == btnStaffs) {
            Parent root = FXMLLoader.load(getClass().getResource("Staffs.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene staffScene = new Scene(root);
            primaryStage.setScene(staffScene);
            primaryStage.setTitle("Staffs Management");
            primaryStage.show(); 
        }

        if (event.getSource() == btnSuppliers) {
            Parent root = FXMLLoader.load(getClass().getResource("Suppliers.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene supplierScene = new Scene(root);
            primaryStage.setScene(supplierScene);
            primaryStage.setTitle("Suppliers Management");
            primaryStage.show(); 
        }

        if (event.getSource() == btnBooks) {
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene booksScene = new Scene(root);
            primaryStage.setScene(booksScene);
            primaryStage.setTitle("Books Management");
            primaryStage.show();
        }
    }
}
