package sceneBuilder;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Invoice;

public class InvoiceController {

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
    private TableColumn<Invoice, Integer> colAmount;

    @FXML
    private TableColumn<Invoice, String> colDate;

    @FXML
    private TableColumn<Invoice, Integer> colIDInvoice;

    @FXML
    private TableColumn<Invoice, String> colName;

    @FXML
    private TableColumn<Invoice, String> colStaff;

    @FXML
    private TableColumn<Invoice, Double> colTotal;

    @FXML
    private TableView<Invoice> invoiceTableView;

    @FXML
    private BorderPane pnInvoice;

    @FXML
    private TextField searchInput;

    private Stage primaryStage;

    ObservableList<Invoice> invoices = FXCollections.observableArrayList();                  

    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        //Handle event on addbtn
        if (event.getSource() == btnAddInvoice) {

            //Show dialog to add a new invoice
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddInvoice.fxml"));

            DialogPane addInvoiceDialogPane = fxmlLoader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addInvoiceDialogPane);
            dialog.setTitle("Add new invoice");
            Optional<ButtonType> clickedButton = dialog.showAndWait();

      
            AddInvoiceController addInvoice = fxmlLoader.getController();  


            if (clickedButton.get() == ButtonType.OK) { 
                //Adding Confirmation 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm to add a new invoice !");
                alert.setContentText("Do you want to add a new invoice ?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    //Add invoice to tableview

                    // Iterate through tableview to check duplicate id
                    boolean isDuplicate = false;
                    for (Invoice invoice : invoices) {
                        if(addInvoice.getTextfiledID() == invoice.getInvoiceID()) {
                            isDuplicate = true;
                            break; 
                        } 
                    }

                    if (isDuplicate) {
                        Alert alertError = new Alert(Alert.AlertType.ERROR);
                        alertError.setTitle("Can't add new invoice!");
                        alertError.setContentText("You have entered an existing ID");
                        alertError.showAndWait();
                    } else {
                        invoices.add(new Invoice(
                            addInvoice.getTextfiledID(),
                            addInvoice.getDatePickerDates(), 
                            addInvoice.getTextfiledStaff(), 
                            addInvoice.getTextfiledTotal(),
                            addInvoice.getTextfiledAmount(),
                            addInvoice.getTextfiledProductName()
                        ));

                        colIDInvoice.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceID"));
                        colName.setCellValueFactory(new PropertyValueFactory<Invoice, String>("productName"));
                        colDate.setCellValueFactory(new PropertyValueFactory<Invoice, String>("invoiceDate"));
                        colAmount.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("Amount"));
                        colStaff.setCellValueFactory(new PropertyValueFactory<Invoice, String>("Staff"));
                        colTotal.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("Total"));
                        invoiceTableView.setItems(invoices);
                    }
                }
            }
        }

        if (event.getSource() == btnExit) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm to exit program !");
            alert.setContentText("Do you want to exit ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) { 
                javafx.application.Platform.exit();
            }
        }

        if (event.getSource() == btnBooks) {
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene bookScene = new Scene(root);
            primaryStage.setScene(bookScene);
            primaryStage.setTitle("Books Management");
            primaryStage.show(); 
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty board error!");
                alert.setContentText("Unable to update the information in the table because the table is empty !");
                alert.showAndWait();
            } else { 
                updateInvoice.setTextfiledID(String.valueOf(clickedInvoice.getInvoiceID()));
                updateInvoice.setTextfiledAmount(String.valueOf(clickedInvoice.getAmount()));
                updateInvoice.setTextfiledTotal(String.valueOf(clickedInvoice.getTotal()));
                updateInvoice.setDatePickerDates(clickedInvoice.getInvoiceDate());
                updateInvoice.setTextfiledProductName(clickedInvoice.getProductName());
                updateInvoice.setTextfiledStaff(clickedInvoice.getStaff());
        
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(updateInvoiceDialogPane);
                dialog.setTitle("Update invoice");        
                Optional<ButtonType> clickedButton = dialog.showAndWait();
        
                if(clickedButton.get() == ButtonType.APPLY) { 
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm invoice information update!");
                    alert.setContentText("Do you want to update invoice information ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) { 
                        ObservableList<Invoice> currentTableData = invoiceTableView.getItems();

                        int currentID = Integer.parseInt(updateInvoice.getTextfiledID().getText());
                        for (Invoice invoice : currentTableData) {
                            if(invoice.getInvoiceID() == currentID) {
                                invoice.setProductName(updateInvoice.getTextfiledProductName().getText());
                                invoice.setStaff(updateInvoice.getTextfiledStaff().getText());
                                invoice.setAmount(Integer.parseInt(updateInvoice.getTextfiledAmount().getText()));
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

        if (event.getSource() == btnDeleteInvoice) { 
            if(invoices.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty board error!");
                alert.setContentText("Unable to update the information in the table because the table is empty !");
                alert.showAndWait(); 
            } else  {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm to delete a invoice !");
                alert.setContentText("Do you want to delete a invoice ?");
                Optional<ButtonType> result = alert.showAndWait();
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
                    String toLowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(cust.getInvoiceID()).contains(newValue)) {
                        return true;
                    } else if (cust.getProductName().toLowerCase().contains(toLowerCaseFilter)) {
                        return true;
                    } else if (cust.getStaff().toLowerCase().contains(toLowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(cust.getTotal()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(cust.getAmount()).contains(newValue)) {
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