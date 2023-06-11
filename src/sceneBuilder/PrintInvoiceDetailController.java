package sceneBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import model.Invoice;
import model.InvoiceDetail;

public class PrintInvoiceDetailController {

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

    public Invoice invoiceClicked ;
    public int invoiceID;

    public void setInvoiceClicked(Invoice invoiceClicked) {
        this.invoiceClicked = invoiceClicked;
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
    
    public void setInvoiceID(int invoiceID){
        this.invoiceID = invoiceID;
    }
}
