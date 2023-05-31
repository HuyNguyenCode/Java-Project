package sceneBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Staff;

public class StaffsController {

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
    private TableColumn<Staff, String> colEmail;

    @FXML
    private TableColumn<Staff, Integer> colIDStaff;

    @FXML
    private TableColumn<Staff, String> colName;

    @FXML
    private TableColumn<Staff, Integer> colPhone;

    @FXML
    private BorderPane pnStaff;

    @FXML
    private TextField searchInput;

    @FXML
    private TableView<Staff> staffsTableView;

    ObservableList<Staff> staffs = FXCollections.observableArrayList();   

    private Stage primaryStage;

    // @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            // staffs = ControllDB.getListFromBooks();
            colIDStaff.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("id"));
            colEmail.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));
            colName.setCellValueFactory(new PropertyValueFactory<Staff, String>("staffName"));
            colPhone.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("phone"));
            staffsTableView.setItems(staffs);

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
