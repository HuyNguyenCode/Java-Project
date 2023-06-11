package sceneBuilder;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import database.ControllDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Invoice;
import model.InvoiceDetail;
import model.Tool;

public class InvoiceController implements Initializable {

    @FXML
    private Label userNameInScene;
    
    @FXML
    private HBox btnAddInvoice;

    @FXML
    private HBox btnBooks;

    @FXML
    private HBox btnDeleteInvoice;

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
    private HBox btnBookEntry;
    
    @FXML
    private HBox btnUpdateInvoice;

    @FXML
    private TableColumn<Invoice, String> colDate;

    @FXML
    private TableColumn<Invoice, Integer> colIDInvoice;

    @FXML
    private TableColumn<Invoice, String> colStaffName;

    @FXML
    private TableColumn<Invoice, Integer> colStaffID;

    @FXML
    private TableColumn<Invoice, Double> colTotal;

    @FXML
    private TableColumn<Invoice, String> colDetail;

    @FXML
    private TableView<Invoice> invoiceTableView;

    @FXML
    private BorderPane pnInvoice;

    @FXML
    private TextField searchInput;

    private Class<InvoiceController> invoiceClass = InvoiceController.class;

    ObservableList<Invoice> invoices = FXCollections.observableArrayList();   

    public void initialize(URL location, ResourceBundle resources){

        // String userName = SigninController.user.getFullName();
        // this.userNameInScene.setText(userName);

        try {
            invoices = ControllDB.getListFromInvoices();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addInvoieToTable(invoices);    
    }

    public void addInvoieToTable(ObservableList<Invoice> invoices) {
        colIDInvoice.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceID"));
        colDate.setCellValueFactory(new PropertyValueFactory<Invoice, String>("invoiceDate"));
        colStaffName.setCellValueFactory(new PropertyValueFactory<Invoice, String>("Staff"));
        colStaffID.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("staffID"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("Total"));
        Callback<TableColumn<Invoice, String>, TableCell<Invoice, String>> cellFactory = new Callback<TableColumn<Invoice, String>, TableCell<Invoice, String>>() {
            @Override
            public TableCell<Invoice, String> call(final TableColumn<Invoice, String> param) {
                final TableCell<Invoice, String> cell = new TableCell<Invoice, String>() {
                    final Button btnDetail = new Button("Show Detail");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnDetail.setOnMouseClicked((MouseEvent event) -> {
                                Invoice invoiceClicked = getTableView().getItems().get(getIndex());                                                                                   
                                FXMLLoader fxmlLoader = Tool.getFxml(invoiceClass, "InvoiceDetailTable");
                                DialogPane invoiceDetailDialogPane;
                                try {                            
                                                                                        
                                    invoiceDetailDialogPane = fxmlLoader.load();
                                    InvoiceDetailTableController invoiceDetailTable = fxmlLoader.getController();
                                    invoiceDetailTable.invoicesDetailList = ControllDB.getDetailsFromDB(invoiceClicked.getInvoiceID());
                                    invoiceDetailTable.setInvoiceID(invoiceClicked.getInvoiceID());
                                    invoiceDetailTable.invoiceID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("invoiceID"));
                                    invoiceDetailTable.bookID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("bookID"));
                                    invoiceDetailTable.bookTitle_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("bookTitle"));
                                    invoiceDetailTable.unitPrice_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Double>("unitPrice"));
                                    invoiceDetailTable.quantity_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("quantity"));
                                    invoiceDetailTable.setInvoiceDate_detail(invoiceClicked.getInvoiceDate());
                                    invoiceDetailTable.setTotal_detail(String.valueOf(invoiceClicked.getTotal()));
                                    invoiceDetailTable.setInvoiceNo_detail(String.format("%03d", getIndex() + 1));
                                    invoiceDetailTable.tableviewDetail.setItems(invoiceDetailTable.invoicesDetailList);
                                    Optional<ButtonType> res = Tool.showDialogPaneOptional("Invoice Detail", invoiceDetailDialogPane);
                                    if (res.get() == ButtonType.CLOSE) {
                                        Tool.loadScene(invoiceClass, "Invoice", event);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            setGraphic(btnDetail);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        colDetail.setCellFactory(cellFactory);
        invoiceTableView.setItems(invoices);
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException, SQLException {
        //Handle event on addbtn
        if (event.getSource() == btnAddInvoice) {

            //Show dialog to add a new invoice
            FXMLLoader fxmlLoader = Tool.getFxml(invoiceClass, "AddInvoice");            
            DialogPane addInvoiceDialogPane = fxmlLoader.load();
            AddInvoiceController addInvoice = fxmlLoader.getController();  
            Optional<ButtonType> clickedButton = Tool.showDialogPaneOptional("Add new invoice", addInvoiceDialogPane);
            if (clickedButton.get() == ButtonType.OK) { 
                //Add invoice to tableview
                boolean isUpdate = ControllDB.insertValuesIntoInvoices(addInvoice.getDatePickerDates(), addInvoice.getComboboxStaffID());
                if(isUpdate == false) return;
                invoices.add(ControllDB.getLastestInvoice());
                addInvoieToTable(invoices);
                
            }
        } else if (event.getSource() == btnExit) {
            Optional<ButtonType> result = Tool.showConfirmAlert(
                "Confirm to exit program !", 
                "Do you want to exit ?");
            if (result.get() == ButtonType.OK) { 
                javafx.application.Platform.exit();
            }
        } else if (event.getSource() == btnBooks) {
            Tool.loadScene(invoiceClass, "MainScene", event);
        } else if (event.getSource() == btnSuppliers) {
            Tool.loadScene(invoiceClass, "Suppliers", event);
        } else if (event.getSource() == btnStaffs) {
            Tool.loadScene(invoiceClass, "Staffs", event);
        } else if (event.getSource() == btnInvoiceDashboard) {
            Tool.loadScene(invoiceClass, "Dashboard", event);
        } else if (event.getSource() == btnBookEntry) {
            Tool.loadScene(invoiceClass, "BookEntry", event);
        }
    }

    @FXML
    void handleUpdate(MouseEvent event) throws IOException { 
        FXMLLoader fxmlLoader = Tool.getFxml(invoiceClass, "UpdateInvoice");
        DialogPane updateInvoiceDialogPane = fxmlLoader.load();
        UpdateInvoiceController updateInvoice = fxmlLoader.getController();
        
        Invoice clickedInvoice = invoiceTableView.getSelectionModel().getSelectedItem();
        
        if (event.getSource() == btnUpdateInvoice) {
            if(invoices.isEmpty()) {
                Tool.showAlert(Alert.AlertType.ERROR,
                "Empty board error!",
                "Unable to update the information in the table because the table is empty !");
            } else { 
                updateInvoice.setInvoiceID(clickedInvoice.getInvoiceID());
                updateInvoice.setDatePickerDates(clickedInvoice.getInvoiceDate());
                updateInvoice.setComboboxStaffID(clickedInvoice.getStaffID());
                updateInvoice.setTextfiledStaff(clickedInvoice.getStaff());
                Optional<ButtonType> clickedButton = Tool.showDialogPaneOptional("Update invoice", updateInvoiceDialogPane);
                if(clickedButton.get() == ButtonType.APPLY) { 
                    ObservableList<Invoice> currentTableData = invoiceTableView.getItems();
                    int currentID = updateInvoice.getInvoiceID();
                    for (Invoice invoice : currentTableData) {
                        if(invoice.getInvoiceID() == currentID) {
                            invoice.setStaffID(updateInvoice.getComboboxStaffID());
                            invoice.setInvoiceDate(updateInvoice.getDatePickerDates().getValue().format(DateTimeFormatter.ofPattern("yyy-MM-dd")));
                            invoice.setStaff(updateInvoice.getTextfiledStaff());
                            ControllDB.updateInvoice(invoice);
                            invoiceTableView.setItems(currentTableData);
                            invoiceTableView.refresh();
                            break;
                        }
                    }
                }
            }
        } else if (event.getSource() == btnDeleteInvoice) { 
            if(invoices.isEmpty()) {
                Tool.showAlert(Alert.AlertType.ERROR,
            "Empty board error!", 
            "Unable to delete the information in the table because the table is empty !"
                );
            } else  {
                Optional<ButtonType> result = Tool.showConfirmAlert("Confirm to delete a invoice !", "Do you want to delete a invoice ?");
                if (result.get() == ButtonType.OK) { 
                    if(ControllDB.deleteFromInvoices(clickedInvoice) == true){
                        invoiceTableView.getItems().removeAll(clickedInvoice);
                    }
                }
            }
        }    
    }

    @FXML
    void searchInvoice() {
        FilteredList<Invoice> filterData = new FilteredList<>(invoices, e->true);
        searchInput.setOnKeyReleased(e -> {
            searchInput.textProperty().addListener(((observable, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super Invoice>) cust -> {
                    if (newValue == null) {
                        return true;
                    } 
                    String toLowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(cust.getInvoiceID()).contains(newValue)) {
                        return true;
                    } else if (cust.getStaff().toLowerCase().contains(toLowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(cust.getStaffID()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(cust.getTotal()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(cust.getInvoiceDate()).contains(newValue)) {
                        return true;
                    }
                return false;
                });
            }));
            final SortedList<Invoice> invoicesSortedList = new SortedList<>(filterData);
            invoicesSortedList.comparatorProperty().bind(invoiceTableView.comparatorProperty());
            invoiceTableView.setItems(invoicesSortedList);
        });
    }
}
