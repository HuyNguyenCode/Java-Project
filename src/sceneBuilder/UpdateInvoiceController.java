package sceneBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class UpdateInvoiceController {

    @FXML
    private DatePicker datePickerDates;
    
    @FXML
    private ComboBox<Integer> comboboxStaffID;

    private ObservableList<Integer> staffIDList = FXCollections.observableArrayList();     



    public DatePicker getDatePickerDates() {
        return datePickerDates;
    }

    public void setDatePickerDates(String inputDates) {
        DateTimeFormatter customDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy");

        LocalDate localDate = LocalDate.parse(inputDates, customDateTimeFormatter);
        
        this.datePickerDates.setValue(localDate);
    }
    
    public void setComboboxStaffID() {
        staffIDList.add(1); 
        staffIDList.add(2); 
        staffIDList.add(3); 
        this.comboboxStaffID.getItems().addAll(staffIDList);
    }

    public Integer getComboboxStaffID() {
        return comboboxStaffID.getValue();
    }
}
