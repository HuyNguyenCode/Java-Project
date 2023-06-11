package sceneBuilder;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import database.ControllBooks;
import database.ControlInvoiceDetails;
import database.ControlInvoices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
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
import model.Tool;
import javafx.stage.Stage;
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
    private Button btnPrint;

    @FXML
    private AnchorPane updateInvoiceDetail;

    private Stage primaryStage;
    
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
            listBooks = ControllBooks.getListFromBooks();
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
    void handleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == btnDeleteInvoiceDetail) {

            InvoiceDetail clickedInvoiceDetail = tableviewDetail.getSelectionModel().getSelectedItem();

            if(invoicesDetailList.isEmpty()) {
                Tool.showAlert(Alert.AlertType.ERROR, "Empty board error!", "Unable to update the information in the table because the table is empty !");
            } else  {
                Optional<ButtonType> result = Tool.showConfirmAlert("Confirm to delete a invoice !", "Do you want to delete a invoice ?");
                if (result.get() == ButtonType.OK) { 
                    boolean isDelete = ControlInvoiceDetails.deleteInvoiceDetail(invoiceID, clickedInvoiceDetail.getBookID());
                    if(isDelete == false){
                        Tool.showAlert(Alert.AlertType.ERROR, 
                        "Delete Error !", 
                        "Try again...");
                    }
                    tableviewDetail.getItems().removeAll(clickedInvoiceDetail);
                    setTotal_detail(ControlInvoices.getInvoiceTotal(invoiceID).toString());
                }
            }
        } 
        
        if (event.getSource() == btnAddInvoiceDetail) {

            boolean checkInsert = ControlInvoiceDetails.insertValuesIntoInvoiceDetails(invoiceID, ControllBooks.getBookIDFromName(getBookTitleCombobox()), Integer.parseInt(getQuantityTextfield().getText()));

            if(checkInsert == false){
                Tool.showAlert(Alert.AlertType.INFORMATION, "Delete fail !", "Try again with less quantity !");
            }

            invoicesDetailList.add(ControlInvoiceDetails.getInvoiceDetail(invoiceID, getBookTitleCombobox()));
            setTotal_detail(ControlInvoices.getInvoiceTotal(invoiceID).toString());
            
            invoiceID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("invoiceID"));
            bookID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("bookID"));
            bookTitle_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("bookTitle"));
            unitPrice_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Double>("unitPrice"));
            quantity_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("quantity"));
            tableviewDetail.setItems(invoicesDetailList);
            
            Tool.loadScene(InvoiceDetail.class,"Invoice", event);
        }

        if (event.getSource() == btnPrint) {
            
            FXMLLoader fxmlLoader = Tool.getFxml(InvoiceDetailTableController.class, "PrintInvoiceDetail");
            
            Node root = fxmlLoader.load();
            
            PrintInvoiceDetailController printInvoiceDetail = fxmlLoader.getController();
            printInvoiceDetail.invoiceID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("invoiceID"));
            printInvoiceDetail.bookID_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("bookID"));
            printInvoiceDetail.bookTitle_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, String>("bookTitle"));
            printInvoiceDetail.unitPrice_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Double>("unitPrice"));
            printInvoiceDetail.quantity_detail.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("quantity"));
            printInvoiceDetail.setInvoiceDate_detail(invoiceDate_detail.getText());
            printInvoiceDetail.setTotal_detail(total_detail.getText());
            printInvoiceDetail.setInvoiceNo_detail(invoiceNo_detail.getText());
            printInvoiceDetail.tableviewDetail.setItems(invoicesDetailList);
    
            System.out.println("Invoice Detail Printer!");
            PrinterJob job = PrinterJob.createPrinterJob();
            if(job != null){
                job.showPrintDialog(primaryStage); 
                job.printPage(root);
                job.endJob();
            }
        }
    }
    
    public void setInvoiceID(int invoiceID){
        this.invoiceID = invoiceID;
    } 
        
}
