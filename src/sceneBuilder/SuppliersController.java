package sceneBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import model.Tool;

import database.ControllDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private Class<SuppliersController> suppliersClass = SuppliersController.class;

    ObservableList<Supplier> suppliers = FXCollections.observableArrayList();   

   // @Override
   public void initialize(URL location, ResourceBundle resources) {

        // String userName = SigninController.user.getFullName();
        // this.userNameInScene.setText(userName);
        
        try {
        suppliers = ControllDB.getListFromSuppliers();
        colIDSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        colName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("phone"));
        suppliersTableView.setItems(suppliers);

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
            Tool.loadScene(suppliersClass, "Invoice", event);
        } else if (event.getSource() == btnStaffs) {
            Tool.loadScene(suppliersClass, "Staffs", event);
        } else if (event.getSource() == btnBooks) {
            Tool.loadScene(suppliersClass, "MainScene", event);
        } else if (event.getSource() == btnDashboard) {
            Tool.loadScene(suppliersClass, "Dashboard", event);
        }
    }
}
