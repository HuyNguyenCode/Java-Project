package sceneBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

// import database.ControllDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import model.Supplier;


public class SuppliersController implements Initializable {

    @FXML
    private Label userNameInScene;

    @FXML
    private HBox btnBooks;

    @FXML
    private HBox btnDashboard;

    @FXML
    private Button btnExit;

    @FXML
    private HBox btnInvoices;

    @FXML
    private HBox btnStaffs;

    @FXML
    private HBox btnSuppliers;

    @FXML
    private Button btnbookManage;

    @FXML
    private TableColumn<Supplier, String> colAddress;

    @FXML
    private TableColumn<Supplier, Integer> colIDSupplier;

    @FXML
    private TableColumn<Supplier, String> colName;

    @FXML
    private TableColumn<Supplier, Integer> colPhone;

    @FXML
    private BorderPane pnSuppliers;

    @FXML
    private TextField searchInput;

    @FXML
    private TableView<Supplier> suppliersTableView;

    ObservableList<Supplier> suppliers = FXCollections.observableArrayList();   

   // @Override
   public void initialize(URL location, ResourceBundle resources) {

        String userName = "";
        this.userNameInScene.setText(userName);
        
        try {
        // suppliers = ControllDB.getListFromSuppliers();
        colIDSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        colName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("phone"));
        suppliersTableView.setItems(suppliers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Stage primaryStage;
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

        if (event.getSource() == btnBooks) {
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene booksScene = new Scene(root);
            primaryStage.setScene(booksScene);
            primaryStage.setTitle("Books Management");
            primaryStage.show(); 
        }

        if (event.getSource() == btnDashboard) {
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene dashboardScene = new Scene(root);
            primaryStage.setScene(dashboardScene);
            primaryStage.setTitle("Dashboard");
            primaryStage.show(); 
        }
    }
}
