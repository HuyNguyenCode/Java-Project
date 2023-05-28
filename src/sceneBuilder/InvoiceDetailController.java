package sceneBuilder;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class InvoiceDetailController {

    @FXML
    private Text bookID_detail;

    @FXML
    private RowConstraints first_row;

    @FXML
    private GridPane gridpaneDetail;

    @FXML
    private Text invoiceID_detail;

    @FXML
    private Text invoiceDate_detail;

    @FXML
    private Text quantity_detail;

    @FXML
    private Text total_detail;

    @FXML
    private Text unitPrice_detail;

    @FXML
    private Text invoiceNo_detail;

    public void setInvoiceNo_detail(String invoiceNo) {
        this.invoiceNo_detail.setText(invoiceNo);
    }

    public void setBookID_detail(String bookID) {
        this.bookID_detail.setText(bookID);
    }

    public void setFirst_row(RowConstraints first_row) {
        this.first_row = first_row;
    }

    public void setGridpaneDetail(GridPane gridpaneDetail) {
        this.gridpaneDetail = gridpaneDetail;
    }

    public void setInvoiceID_detail(String invoiceID) {
        this.invoiceID_detail.setText(invoiceID);;
    }

    public void setQuantity_detail(String quantity) {
        this.quantity_detail.setText(quantity);
    }

    public void setTotal_detail(String total) {
        this.total_detail.setText(total);
    }

    public void setUnitPrice_detail(String unitPrice) {
        this.unitPrice_detail.setText(unitPrice);
    }

    public void setInvoiceDate_detail(String invoiceDate) {
        this.invoiceDate_detail.setText(invoiceDate);;
    }
    
}
