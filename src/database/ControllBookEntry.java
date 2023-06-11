package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import model.BookEntry;

public class ControllBookEntry {
    public static ObservableList<BookEntry> getListFromBookEntry(){
        ObservableList<BookEntry> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = ConnectToDB.getConnection().createStatement().executeQuery("select entry_id, date, employee_id, supplier_id, total_amount from book_entry");
            while(rs.next()){
                list.add(new BookEntry(rs.getInt(1), rs.getDate(2).toString(), rs.getInt(3), rs.getInt(4), rs.getDouble(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
