package database;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supplier;

public class ControllSuppliers {
    public static ObservableList<Supplier> getListFromSuppliers() throws SQLException{
        ObservableList<Supplier> list = FXCollections.observableArrayList();
        String sql = "Select * from suppliers";
        Statement st = ConnectToDB.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            list.add(new Supplier(id, name, address, phone));
        }

        return list;
    }
}
