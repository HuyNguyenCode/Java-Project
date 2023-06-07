package sceneBuilder;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import database.ControllDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Book;
import model.InvoiceDetail;

public class InvoiceDetailTableController implements Initializable{

    @FXML TableView<InvoiceDetail> tableviewDetail;
    @FXML TableColumn<InvoiceDetail, Integer> bookID_detail;
    @FXML TableColumn<InvoiceDetail, String> bookTitle_detail;
    @FXML TableColumn<InvoiceDetail, Integer> invoiceID_detail;
    @FXML TableColumn<InvoiceDetail, Integer> quantity_detail;
    @FXML TableColumn<InvoiceDetail, Double> unitPrice_detail;

    @FXML
    private Text invoiceDate_detail;

    @FXML
    private Text invoiceNo_detail;

    @FXML 
    private Text total_detail;

    @FXML
    private TextField quantityTextfield;

    @FXML
    private Button btnAddInvoiceDetail;

    @FXML
    private Button btnDeleteInvoiceDetail;

    @FXML
    private AnchorPane updateInvoiceDetail;
    
    ObservableList<InvoiceDetail> invoicesDetailList = FXCollections.observableArrayList();   

    TableRow<InvoiceDetail> newRow = new TableRow<>();

    @FXML
    private ComboBox<String> bookTitleCombobox;

    private ObservableList<String> bookTitleList = FXCollections.observableArrayList();     

    private int invoiceID;

    public void initialize(URL location, ResourceBundle resources) {
      
        setBookTitleCombobox();
        System.out.println(getBookTitleCombobox());
        
    }

    public void setBookTitleCombobox() {
        ObservableList<Book> listBooks;
        try {
            listBooks = ControllDB.getListFromBooks();
            for(Book book : listBooks){
                bookTitleList.add(book.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.bookTitleCombobox.getItems().addAll(bookTitleList);
    }

    public String getBookTitleCombobox() {
        return bookTitleCombobox.getValue();
    }

    public TextField getQuantityTextfield() {
        return quantityTextfield;
    }

    public void setQuantityTextfield(TextField quantityTextfield) {
        this.quantityTextfield = quantityTextfield;
    }


    public void setInvoiceDate_detail(String invoiceDate_detail) {
        this.invoiceDate_detail.setText(invoiceDate_detail);
    }

    public void setInvoiceNo_detail(String invoiceNo_detail) {
        this.invoiceNo_detail.setText(invoiceNo_detail);
    }

    public void setTotal_detail(String total_detail) {
        this.total_detail.setText(total_detail);
    }


    public void insert(int rowIndex, int invoiceID, int bookID, String bookTitle, double unitPrice, int quantity, double total, int editCol) {
        tableviewDetail.getItems().add(rowIndex, new InvoiceDetail (invoiceID, bookID, bookTitle, unitPrice, quantity,  total));
        tableviewDetail.getSelectionModel().clearAndSelect(rowIndex);
        tableviewDetail.edit(rowIndex, tableviewDetail.getColumns().get(editCol));
    }

    @FXML
    void handleClicks(MouseEvent event) {
        if (event.getSource() == btnDeleteInvoiceDetail) {

            InvoiceDetail clickedInvoiceDetail = tableviewDetail.getSelectionModel().getSelectedItem();

            if(invoicesDetailList.isEmpty()) {
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
                    boolean isDelete = ControllDB.deleteInvoiceDetail(invoiceID, clickedInvoiceDetail.getBookID());
                    if(isDelete == false){
                        Alert _alert = new Alert(Alert.AlertType.ERROR);
                        _alert.setTitle("Delete Error !");
                        _alert.setContentText("Try again...");
                        _alert.showAndWait();
                    }
                    tableviewDetail.getItems().removeAll(clickedInvoiceDetail);
                    setTotal_detail(ControllDB.getInvoiceTotal(invoiceID).toString());
                }
            }
        }

        else if (event.getSource() == btnAddInvoiceDetail) {

            boolean checkInsert = ControllDB.insertValuesIntoInvoiceDetails(invoiceID, ControllDB.getBookIDFromName(getBookTitleCombobox()), Integer.parseInt(getQuantityTextfield().getText()));

            if(checkInsert == false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete fail !");
                alert.setContentText("Try again with less quantity !");
                alert.showAndWait();
            }

            invoicesDetailList.add(ControllDB.getInvoiceDetail(invoiceID, getBookTitleCombobox()));
            setTotal_detail(ControllDB.getInvoiceTotal(invoiceID).toString());

            // invoicesDetailList.add(new InvoiceDetail(
            //     123,
            //     567,
            //     getBookTitleCombobox(),
            //     30.000,
            //     Integer.parseInt(getQuantityTextfield().getText()), 
            //     100.0
            // ));
            
            invoiceID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("invoiceID"));
            bookID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("bookID"));
            bookTitle_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("bookTitle"));
            unitPrice_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Double>("unitPrice"));
            quantity_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("quantity"));
            tableviewDetail.setItems(invoicesDetailList);
        }
    }
    
    public void setInvoiceID(int invoiceID){
        this.invoiceID = invoiceID;
    }
}
