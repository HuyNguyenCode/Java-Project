package sceneBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
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

    private Stage primaryStage;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            String userName = SigninController.user.getFullName();
            String totalBooks = "100";   
            String totalInvoices = "100";    
            String totalStaffs = "100";  
            
            this.userNameInScene.setText(userName);
            this.totalBooks.setText(totalBooks);
            this.totalInvoices.setText(totalInvoices);
            this.totalStaffs.setText(totalStaffs);  

            // BarChart - salesChart
            XYChart.Series<String, Integer> barChart = new XYChart.Series<>();
            barChart.setName("Category");
            barChart.getData().add(new XYChart.Data<String, Integer>("2020", 20));
            barChart.getData().add(new XYChart.Data<String, Integer>("2021", 30));
            barChart.getData().add(new XYChart.Data<String, Integer>("2022", 15));
            barChart.getData().add(new XYChart.Data<String, Integer>("2023", 10));
            salesChart.getData().add(barChart);

            // LineChart - Double
            XYChart.Series<String, Double> lineChart = new XYChart.Series<>();
            lineChart.setName("Category");
            lineChart.getData().add(new XYChart.Data<String, Double>("Category1", 98.3));
            lineChart.getData().add(new XYChart.Data<String, Double>("Category2", 26.1));
            lineChart.getData().add(new XYChart.Data<String, Double>("Category3", 72.8));
            lineChart.getData().add(new XYChart.Data<String, Double>("Category4", 41.6));
            lineChart.getData().add(new XYChart.Data<String, Double>("Category5", 51.5));
            booksSoldChart.getData().add(lineChart);

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
