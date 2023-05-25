package sceneBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UpdateInvoiceController {

    @FXML
    private DatePicker datePickerDates;

    @FXML
    private TextField textfiledAmount;

    @FXML
    private TextField textfiledID;

    @FXML
    private TextField textfiledProductName;

    @FXML
    private TextField textfiledStaff;

    @FXML
    private TextField textfiledTotal;

    public DatePicker getDatePickerDates() {
        return datePickerDates;
    }

    public void setDatePickerDates(String inputDates) {
        DateTimeFormatter customDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = LocalDate.parse(inputDates, customDateTimeFormatter);
        
        this.datePickerDates.setValue(localDate);
    }

    public TextField getTextfiledAmount() {
        return textfiledAmount;
    }

    public void setTextfiledAmount(String inputAmount) {
        this.textfiledAmount.setText(inputAmount);
    }

    public TextField getTextfiledID() {
        return textfiledID;
    }

    public void setTextfiledID(String inputID) {
        this.textfiledID.setText(inputID);
    }

    public TextField getTextfiledProductName() {
        return textfiledProductName;
    }

    public void setTextfiledProductName(String inputProductName) {
        this.textfiledProductName.setText(inputProductName); 
    }

    public TextField getTextfiledStaff() {
        return textfiledStaff;
    }

    public void setTextfiledStaff(String inputStaff) {
        this.textfiledStaff.setText(inputStaff);;
    }

    public TextField getTextfiledTotal() {
        return textfiledTotal;
    }

    public void setTextfiledTotal(String inputTotal) {
        this.textfiledTotal.setText(inputTotal);
    }    

}
