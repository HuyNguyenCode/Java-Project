package sceneBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import database.ControlBookEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.BookEntry;
import model.Tool;

public class BookEntryController implements Initializable {

    @FXML
    private HBox btnAddEntry;

    @FXML
    private HBox btnBooks;

    @FXML
    private HBox btnDeleteEntry;

    @FXML
    private Button btnExit;

    @FXML
    private HBox btnInvoiceDashboard;

    @FXML
    private HBox btnInvoices;

    @FXML
    private HBox btnStaffs;

    @FXML
    private HBox btnSuppliers;

    @FXML
    private HBox btnSuppliers1;

    @FXML
    private HBox btnUpdateEntry;

    @FXML
    private Button btnbookManage;

    @FXML
    private TableColumn<BookEntry, String> colDate;

    @FXML
    private TableColumn<BookEntry, String> colDetail;

    @FXML
    private TableColumn<BookEntry, Integer> colIDEntry;

    @FXML
    private TableColumn<BookEntry, Integer> colStaffID;

    @FXML
    private TableColumn<BookEntry, Integer> colSupplierID;

    @FXML
    private TableColumn<BookEntry, Double> colTotal;

    @FXML
    private TableView<BookEntry> entryBookTableView;

    @FXML
    private BorderPane pnInvoice;

    @FXML
    private TextField searchInput;

    @FXML
    private Label userNameInScene;

    private Class<BookEntryController> bookEntryClass = BookEntryController.class;

    ObservableList<BookEntry> bookEntries = FXCollections.observableArrayList();   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.userNameInScene.setText(Tool.getUserFullName());
         
        try {
            bookEntries = ControlBookEntry.getListFromBookEntry();
            colIDEntry.setCellValueFactory(new PropertyValueFactory<BookEntry, Integer>("entryID"));
            colSupplierID.setCellValueFactory(new PropertyValueFactory<BookEntry, Integer>("supplierID"));
            colStaffID.setCellValueFactory(new PropertyValueFactory<BookEntry, Integer>("staffID"));
            colDate.setCellValueFactory(new PropertyValueFactory<BookEntry, String>("entryDate"));
            colTotal.setCellValueFactory(new PropertyValueFactory<BookEntry, Double>("total"));
            Callback<TableColumn<BookEntry, String>, TableCell<BookEntry, String>> cellFactory = new Callback<TableColumn<BookEntry, String>, TableCell<BookEntry, String>>() {
                @Override
                public TableCell<BookEntry, String> call(final TableColumn<BookEntry, String> param) {
                    final TableCell<BookEntry, String> cell = new TableCell<BookEntry, String>() {
                        final Button btnDetail = new Button("Show Detail");
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                setGraphic(btnDetail);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };
            colDetail.setCellFactory(cellFactory);
            entryBookTableView.setItems(bookEntries);
    
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
            Tool.loadScene(bookEntryClass, "Invoice", event);
        } else if (event.getSource() == btnStaffs) {
            Tool.loadScene(bookEntryClass, "Staffs", event);
        } else if (event.getSource() == btnBooks) {
            Tool.loadScene(bookEntryClass, "MainScene", event);
        } else if (event.getSource() == btnSuppliers) {
            Tool.loadScene(bookEntryClass, "Suppliers", event);
        } else if (event.getSource() == btnInvoiceDashboard) {
            Tool.loadScene(bookEntryClass, "Dashboard", event);
        }
    }

    @FXML
    void handleUpdate(MouseEvent event) {

    }

    @FXML
    void searchInvoice(KeyEvent event) {

    }

    

}
