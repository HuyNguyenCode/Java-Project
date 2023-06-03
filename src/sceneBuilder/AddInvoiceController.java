package sceneBuilder;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import database.ControllDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Staff;

public class AddInvoiceController implements Initializable{
    
    @FXML
    private ComboBox<Integer> comboboxStaffID;

    @FXML
    private DatePicker datePickerDates;

    @FXML
    private TextField textfiledStaff;


    private ObservableList<Integer> staffIDList = FXCollections.observableArrayList();   
    
    private ObservableList<Staff> listStaffs;
    
    public void initialize(URL location, ResourceBundle resources) {
        setComboboxStaffID();
        comboboxStaffID.setOnAction(event -> {
            Integer id = comboboxStaffID.getValue();
            if(id != null){
                textfiledStaff.setText(binding(id));
            }
            else textfiledStaff.setText("");
        });
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

    public void setComboboxStaffID() {
        try {
            listStaffs = ControllDB.getListFromStaffs();
            for(Staff st : listStaffs){
                staffIDList.add(st.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        this.comboboxStaffID.getItems().addAll(staffIDList);
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
