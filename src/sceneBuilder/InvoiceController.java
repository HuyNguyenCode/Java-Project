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
import model.Staff;

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
    private TableColumn<Invoice, Integer> colStaff;

    @FXML
    private TableColumn<Staff, String> colStaffName;

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

        // try {
        //     invoices = ControllDB.getListFromInvoices();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        // colIDInvoice.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceID"));
        // colDate.setCellValueFactory(new PropertyValueFactory<Invoice, String>("invoiceDate"));
        // colStaff.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("Staff"));
        // colStaffName.setCellValueFactory(new PropertyValueFactory<Staff, String>("Staff Name"));
        // colTotal.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("Total"));
        // Callback<TableColumn<Invoice, String>, TableCell<Invoice, String>> cellFactory = new Callback<TableColumn<Invoice, String>, TableCell<Invoice, String>>() {
        //     @Override
        //     public TableCell<Invoice, String> call(final TableColumn<Invoice, String> param) {
        //         final TableCell<Invoice, String> cell = new TableCell<Invoice, String>() {
        //             final Button btnDetail = new Button("Show Detail");
        //             @Override
        //             public void updateItem(String item, boolean empty) {
        //                 super.updateItem(item, empty);
        //                 if (empty) {
        //                     setGraphic(null);
        //                     setText(null);
        //                 } else {
        //                     btnDetail.setOnMouseClicked((MouseEvent event) -> {
        //                         Invoice invoiceClicked = getTableView().getItems().get(getIndex());                                                                                   
        //                         FXMLLoader fxmlLoader = new FXMLLoader();
        //                         fxmlLoader.setLocation(getClass().getResource("InvoiceDetailTable.fxml"));

        //                         DialogPane invoiceDetailDialogPane;
        //                         try {                            
                                                                                        
        //                             invoiceDetailDialogPane = fxmlLoader.load();
        //                             InvoiceDetailTableController invoiceDetailTable = fxmlLoader.getController();

        //                             // invoiceDetail.setBookID_detail(null);

        //                             // invoiceDetailTable.invoicesDetailList.add(new InvoiceDetail(123, 456, "Book_1", 56.000, 10, 5000));
        //                             // invoiceDetailTable.invoicesDetailList.add(new InvoiceDetail(672, 182, "Book_2", 45.000, 12, 5200));
        //                             // invoiceDetailTable.invoicesDetailList.add(new InvoiceDetail(736, 456, "Book_3", 12.000, 18, 9800));

        //                             // invoiceDetailTable.invoicesDetailList.add(new InvoiceDetail(
        //                             //     invoiceClicked.getInvoiceID(),
        //                             //     123,
        //                             //     "Book_1",
        //                             //     23.000,
        //                             //     10,
        //                             //     1000
        //                             // ));

        //                             invoiceDetailTable.invoicesDetailList = ControllDB.getDetailsFromDB(invoiceClicked.getInvoiceID());

        //                             invoiceDetailTable.invoiceID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("invoiceID"));
        //                             invoiceDetailTable.bookID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("bookID"));
        //                             invoiceDetailTable.bookTitle_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("bookTitle"));
        //                             invoiceDetailTable.unitPrice_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Double>("unitPrice"));
        //                             invoiceDetailTable.quantity_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("quantity"));
        //                             // invoiceDetailTable.total_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("total"));
        //                             invoiceDetailTable.setInvoiceDate_detail(invoiceClicked.getInvoiceDate());
        //                             invoiceDetailTable.setTotal_detail(String.valueOf(invoiceClicked.getTotal()));
        //                             invoiceDetailTable.setInvoiceNo_detail(String.format("%03d", getIndex() + 1));
        //                             invoiceDetailTable.tableviewDetail.setItems(invoiceDetailTable.invoicesDetailList);


        //                             Dialog<ButtonType> dialog = new Dialog<>();
        //                             dialog.setDialogPane(invoiceDetailDialogPane);
        //                             dialog.setTitle("Invoice Detail");
        //                             dialog.showAndWait();
        //                         } catch (IOException e) {
        //                             e.printStackTrace();
        //                         }
        //                     });
        //                     setGraphic(btnDetail);
        //                     setText(null);
        //                 }
        //             }
        //         };
        //         return cell;
        //     }
        // };

        // colDetail.setCellFactory(cellFactory);
        // invoiceTableView.setItems(invoices);
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException, SQLException {
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
                    
                    invoices.add(new Invoice(
                        // addInvoice.getComboboxStaffID(),
                        123,
                        addInvoice.getDatePickerDates(), 
                        addInvoice.getComboboxStaffID(), 
                        100.0 // Get total from DB
                    ));

                    colIDInvoice.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceID"));
                    colDate.setCellValueFactory(new PropertyValueFactory<Invoice, String>("invoiceDate"));
                    colTotal.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("Total"));
                    colStaff.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("Staff"));
                    // colStaffName.setCellFactory(new PropertyValueFactory<Staff, String>("Staff Name"));
                    Callback<TableColumn<Staff, String>, TableCell<Staff, String>> staffNamecellFactory = new Callback<TableColumn<Staff, String>, TableCell<Staff, String>>() {
                        @Override
                        public TableCell<Staff, String> call(final TableColumn<Staff, String> param) {
                            final TableCell<Staff, String> cell = new TableCell<Staff, String>() {
                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        setText(addInvoice.getTextfiledStaff());
                                    }
                                }
                            };
                            return cell;
                        }
                    };

                    colStaffName.setCellFactory(staffNamecellFactory);


                    Callback<TableColumn<Invoice, String>, TableCell<Invoice, String>> DetailcellFactory = new Callback<TableColumn<Invoice, String>, TableCell<Invoice, String>>() {
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

                                                // invoiceDetail.setBookID_detail(null);

                                                // invoiceDetailTable.invoicesDetailList.add(new InvoiceDetail(123, 456, "Book_1", 56.000, 10, 5000));
                                                // invoiceDetailTable.invoicesDetailList.add(new InvoiceDetail(672, 182, "Book_2", 45.000, 12, 5200));
                                                // invoiceDetailTable.invoicesDetailList.add(new InvoiceDetail(736, 456, "Book_3", 12.000, 18, 9800));

                                                // invoiceDetailTable.invoicesDetailList.add(new InvoiceDetail(
                                                //     invoiceClicked.getInvoiceID(),
                                                //     123,
                                                //     "Book_1",
                                                //     23.000,
                                                //     10,
                                                //     1000
                                                // ));

                                                // invoiceDetailTable.invoicesDetailList = ControllDB.getDetailsFromDB(invoiceClicked.getInvoiceID());

                                                invoiceDetailTable.invoiceID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("invoiceID"));
                                                invoiceDetailTable.bookID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("bookID"));
                                                invoiceDetailTable.bookTitle_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("bookTitle"));
                                                invoiceDetailTable.unitPrice_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Double>("unitPrice"));
                                                invoiceDetailTable.quantity_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("quantity"));
                                                // invoiceDetailTable.total_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("total"));
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
                    colDetail.setCellFactory(DetailcellFactory);
                    invoiceTableView.setItems(invoices);
                
                }
            }
        }

        else if (event.getSource() == btnExit) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm to exit program !");
            alert.setContentText("Do you want to exit ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) { 
                javafx.application.Platform.exit();
            }
        }

        else if (event.getSource() == btnBooks) {
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene bookScene = new Scene(root);
            primaryStage.setScene(bookScene);
            primaryStage.setTitle("Books Management");
            primaryStage.show(); 
        }

        
        else if (event.getSource() == btnSuppliers) {
            Parent root = FXMLLoader.load(getClass().getResource("Suppliers.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene supplierScene = new Scene(root);
            primaryStage.setScene(supplierScene);
            primaryStage.setTitle("Suppliers Management");
            primaryStage.show(); 
        }

        else if (event.getSource() == btnStaffs) {
            Parent root = FXMLLoader.load(getClass().getResource("Staffs.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene staffScene = new Scene(root);
            primaryStage.setScene(staffScene);
            primaryStage.setTitle("Staffs Management");
            primaryStage.show(); 
        }

        else if (event.getSource() == btnInvoiceDashboard) {
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene dashboardScene = new Scene(root);
            primaryStage.setScene(dashboardScene);
            primaryStage.setTitle("Dashboard");
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
                updateInvoice.setTextfiledTotal(String.valueOf(clickedInvoice.getTotal()));
                updateInvoice.setDatePickerDates(clickedInvoice.getInvoiceDate());
                updateInvoice.setTextfiledStaff(String.valueOf(clickedInvoice.getStaff()));
        
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
                                invoice.setStaff(Integer.parseInt(updateInvoice.getTextfiledStaff().getText()));
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
