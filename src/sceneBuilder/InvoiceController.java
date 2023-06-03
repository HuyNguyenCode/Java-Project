package sceneBuilder;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
    private HBox btnUpdateInvoice;

    @FXML
    private TableColumn<Invoice, String> colDate;

    @FXML
    private TableColumn<Invoice, Integer> colIDInvoice;

    @FXML
    private TableColumn<Invoice, String> colStaffName;

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

    private Stage primaryStage;

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
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("InvoiceDetailTable.fxml"));

                                DialogPane invoiceDetailDialogPane;
                                try {                            
                                                                                        
                                    invoiceDetailDialogPane = fxmlLoader.load();
                                    InvoiceDetailTableController invoiceDetailTable = fxmlLoader.getController();
                                    invoiceDetailTable.invoicesDetailList = ControllDB.getDetailsFromDB(invoiceClicked.getInvoiceID());

                                    invoiceDetailTable.invoiceID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("invoiceID"));
                                    invoiceDetailTable.bookID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("bookID"));
                                    invoiceDetailTable.bookTitle_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("bookTitle"));
                                    invoiceDetailTable.unitPrice_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Double>("unitPrice"));
                                    invoiceDetailTable.quantity_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("quantity"));
                                    invoiceDetailTable.setInvoiceDate_detail(invoiceClicked.getInvoiceDate());
                                    invoiceDetailTable.setTotal_detail(String.valueOf(invoiceClicked.getTotal()));
                                    invoiceDetailTable.setInvoiceNo_detail(String.format("%03d", getIndex() + 1));
                                    invoiceDetailTable.tableviewDetail.setItems(invoiceDetailTable.invoicesDetailList);

                                    Dialog<ButtonType> dialog = new Dialog<>();
                                    dialog.setDialogPane(invoiceDetailDialogPane);
                                    dialog.setTitle("Invoice Detail");
                                    dialog.showAndWait();
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

    private void loadScene(String sceneName, MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(sceneName + ".fxml"));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(sceneName + " Management");
        primaryStage.show(); 
    }

    private Controller loadFxml(String fxmlName, Boolean Dialog) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxmlName + ".fxml"));
        // AddInvoiceController addInvoice = fxmlLoader.getController();  
        DialogPane addInvoiceDialogPane = fxmlLoader.load();
        if (Dialog) {
            return fxmlLoader.load();
        } else {
            return fxmlLoader.getController();
        }
    }

    private void showAlert(Alert.AlertType alertType, String alertTitle, String alertContentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertTitle);
        alert.setContentText(alertContentText);
        alert.showAndWait(); 
    }

    private Optional<ButtonType> showConfirmAlert(String alertTitle, String alertContentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(alertTitle);
        alert.setContentText(alertContentText);
        return alert.showAndWait();
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException, SQLException {
        //Handle event on addbtn
        if (event.getSource() == btnAddInvoice) {

            //Show dialog to add a new invoice
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddInvoice.fxml"));
            AddInvoiceController addInvoice = fxmlLoader.getController();  
            DialogPane addInvoiceDialogPane = fxmlLoader.load();
            
            System.out.println(addInvoice);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addInvoiceDialogPane);
            dialog.setTitle("Add new invoice");
            Optional<ButtonType> clickedButton = dialog.showAndWait();


            if (clickedButton.get() == ButtonType.OK) { 
                //Adding Confirmation 
                Optional<ButtonType> result = showConfirmAlert("Confirm to add a new invoice !", "Do you want to add a new invoice ?");
                if (result.get() == ButtonType.OK) {
                    //Add invoice to tableview
                    boolean isUpdate = ControllDB.insertValuesIntoInvoices(addInvoice.getDatePickerDates(), addInvoice.getComboboxStaffID());
                    if(isUpdate == false) return;
                    invoices.add(ControllDB.getLastestInvoice());
                    addInvoieToTable(invoices);
                }
            }
        }

        else if (event.getSource() == btnExit) {
            Optional<ButtonType> result = showConfirmAlert("Confirm to exit program !", "Do you want to exit ?");
            if (result.get() == ButtonType.OK) { 
                javafx.application.Platform.exit();
            }
        }

        else if (event.getSource() == btnBooks) {
            loadScene("MainScene", event);
        }

        else if (event.getSource() == btnSuppliers) {
            loadScene("Suppliers", event);
        }

        else if (event.getSource() == btnStaffs) {
            loadScene("Staffs", event);
        }

        else if (event.getSource() == btnInvoiceDashboard) {
            loadScene("Dashboard", event);
        }

    }

    @FXML
    void handleUpdate(MouseEvent event) throws IOException { 
    
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("UpdateInvoice.fxml"));
        DialogPane updateInvoiceDialogPane = fxmlLoader.load();
        UpdateInvoiceController updateInvoice = fxmlLoader.getController();
        
        Invoice clickedInvoice = invoiceTableView.getSelectionModel().getSelectedItem();
        
        if (event.getSource() == btnUpdateInvoice) {

            if(invoices.isEmpty()) {
                showAlert(Alert.AlertType.ERROR,
                 "Empty board error!",
                "Unable to update the information in the table because the table is empty !");
            } else { 
                updateInvoice.setTextfiledID(String.valueOf(clickedInvoice.getInvoiceID()));
                updateInvoice.setTextfiledTotal(String.valueOf(clickedInvoice.getTotal()));
                updateInvoice.setDatePickerDates(clickedInvoice.getInvoiceDate());
                updateInvoice.setTextfiledStaff(clickedInvoice.getStaff());
        
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(updateInvoiceDialogPane);
                dialog.setTitle("Update invoice");        
                Optional<ButtonType> clickedButton = dialog.showAndWait();
        
                if(clickedButton.get() == ButtonType.APPLY) { 
                    Optional<ButtonType> result = showConfirmAlert("Confirm invoice information update!", "Do you want to update invoice information ?");
                    if (result.get() == ButtonType.OK) { 
                        ObservableList<Invoice> currentTableData = invoiceTableView.getItems();
                        int currentID = Integer.parseInt(updateInvoice.getTextfiledID().getText());
                        for (Invoice invoice : currentTableData) {
                            if(invoice.getInvoiceID() == currentID) {
                                invoice.setStaff(updateInvoice.getTextfiledStaff().getText());
                                invoice.setTotal(Double.parseDouble(updateInvoice.getTextfiledTotal().getText()));
                                invoice.setInvoiceDate(updateInvoice.getDatePickerDates().getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyy")));
                                invoiceTableView.setItems(currentTableData);
                                invoiceTableView.refresh();
                                break;
                            }
                        }
                    }
                }
            }
        }

        else if (event.getSource() == btnDeleteInvoice) { 
            if(invoices.isEmpty()) {
                showAlert(Alert.AlertType.ERROR,
                "Empty board error!", 
                "Unable to delete the information in the table because the table is empty !"
                );
            } else  {
                Optional<ButtonType> result = showConfirmAlert("Confirm to delete a invoice !", "Do you want to delete a invoice ?");
                if (result.get() == ButtonType.OK) { 
                    invoiceTableView.getItems().removeAll(clickedInvoice);
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
                    // String toLowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(cust.getInvoiceID()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(cust.getStaff()).contains(newValue)) {
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
