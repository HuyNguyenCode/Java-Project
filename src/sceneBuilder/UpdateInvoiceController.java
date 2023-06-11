package sceneBuilder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.ControlStaffs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Staff;

public class UpdateInvoiceController {

    @FXML
    private DatePicker datePickerDates;
    
    @FXML
    private ComboBox<Integer> comboboxStaffID;

    @FXML
    private TextField textfiledStaff;

    private int invoiceID;

    private ObservableList<Integer> staffIDList = FXCollections.observableArrayList();     
    private ObservableList<Staff> listStaffs;
    
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }
    
    public String binding(Integer id){
        if(id == null){
            return null;
        }
        for(Staff st : listStaffs){
            if(st.getId() == id) return st.getStaffName();
        }
        return null;
    }

    public void setComboboxStaffID(int inputStaffID) {
        try {
            listStaffs = ControlStaffs.getListFromStaffs();
            for(Staff st : listStaffs){
                staffIDList.add(st.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.comboboxStaffID.getItems().addAll(staffIDList);
        this.comboboxStaffID.setValue(inputStaffID);
        comboboxStaffID.setOnAction(event -> {
            Integer id = comboboxStaffID.getValue();
            if(id != null){
                textfiledStaff.setText(binding(id));
            }
            else textfiledStaff.setText("");
        });
    }

    public Integer getComboboxStaffID() {
        return comboboxStaffID.getValue();
    }

    public DatePicker getDatePickerDates() {
        return datePickerDates;
    }

    public void setDatePickerDates(String inputDates) {
        DateTimeFormatter customDateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd");

        LocalDate localDate = LocalDate.parse(inputDates, customDateTimeFormatter);
        
        this.datePickerDates.setValue(localDate);
    }

    public String getTextfiledStaff() {
        return textfiledStaff.getText();
    }

    public void setTextfiledStaff(String textfiledStaff) {
        this.textfiledStaff.setText(textfiledStaff);;
    }
    
}
