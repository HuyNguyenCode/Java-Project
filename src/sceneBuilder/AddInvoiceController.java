package sceneBuilder;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddInvoiceController {

    @FXML
    private TextField textfiledID;

    @FXML
    private DatePicker datePickerDates;

    @FXML
    private TextField textfiledStaff;

    @FXML
    private TextField textfiledProductName;

    @FXML
    private TextField textfiledAmount;

    @FXML
    private TextField textfiledTotal;

    

    public int getTextfiledID() {
        return Integer.parseInt(textfiledID.getText());
    }

    public String getDatePickerDates() {
        return datePickerDates.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
    }

    public String getTextfiledProductName() {
        return textfiledProductName.getText();
    }

    public String getTextfiledStaff() {
        return textfiledStaff.getText();
    }

    public int getTextfiledAmount() {
        return Integer.parseInt(textfiledAmount.getText());
    }

    public Double getTextfiledTotal() {
        return Double.parseDouble(textfiledTotal.getText());
    }
}
