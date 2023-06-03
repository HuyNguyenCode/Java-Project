package sceneBuilder;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddInvoiceController implements Initializable{
    
    @FXML
    private ComboBox<Integer> comboboxStaffID;

    @FXML
    private DatePicker datePickerDates;

    @FXML
    private TextField textfiledStaff;


    private ObservableList<Integer> staffIDList = FXCollections.observableArrayList();     
    
    public void initialize(URL location, ResourceBundle resources) {
      
        setComboboxStaffID();

        textfiledStaff.textProperty().bind(
            Bindings.when(comboboxStaffID.getSelectionModel().selectedItemProperty().isNull())
            .then("")
            // .otherwise(comboboxStaffID.getSelectionModel().selectedItemProperty().asString())
            .otherwise("Hello")
        );

        // System.out.println(getComboboxStaffID());
        
    }


    public void setComboboxStaffID() {
        staffIDList.add(1); 
        staffIDList.add(2); 
        staffIDList.add(3); 
        this.comboboxStaffID.getItems().addAll(staffIDList);
        // System.out.println(comboboxStaffID.getValue());
    }

    public Integer getComboboxStaffID() {
        return comboboxStaffID.getValue();
    }


    public String getDatePickerDates() {
        return datePickerDates.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
    }

    public String getTextfiledStaff() {
        return textfiledStaff.getText();
    }
}
