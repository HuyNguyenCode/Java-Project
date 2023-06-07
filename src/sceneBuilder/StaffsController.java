package sceneBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import database.ControlStaffs;
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
import model.Staff;
import model.Tool;

public class StaffsController implements Initializable {

    @FXML
    private Label userNameInScene;
    
    @FXML
    private HBox btnBooks;

    @FXML
    private HBox btnDashboard;

    @FXML
    private HBox btnBookEntry;

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
    private Class<StaffsController> staffsClass = StaffsController.class;
    ObservableList<Staff> staffs = FXCollections.observableArrayList();   

    // @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userNameInScene.setText(Tool.getUserFullName());
        try {
            staffs = ControlStaffs.getListFromStaffs();
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
            Optional<ButtonType> result = Tool.showConfirmAlert("Confirm to exit program !", "Do you want to exit ?");
            if (result.get() == ButtonType.OK) { 
                javafx.application.Platform.exit();
            }
        } else if (event.getSource() == btnInvoices) {
            Tool.loadScene(staffsClass, "Invoice", event);
        } else if (event.getSource() == btnSuppliers) {
            Tool.loadScene(staffsClass, "Suppliers", event);
        } else if (event.getSource() == btnBooks) {
            Tool.loadScene(staffsClass, "MainScene", event);
        } else if (event.getSource() == btnDashboard) {
            Tool.loadScene(staffsClass, "Dashboard", event);
        }  else if (event.getSource() == btnBookEntry) {
            Tool.loadScene(staffsClass, "BookEntry", event);
        }
    }    
}
