package database;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Staff;

public class ControllStaffs {
    public static ObservableList<Staff> getListFromStaffs() throws SQLException{
        ObservableList<Staff> list = FXCollections.observableArrayList();
        String sql = "Select * from employees";
        Statement st = ConnectToDB.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String phone = rs.getString(3);
            String email = rs.getString(4);
            list.add(new Staff(id, name, phone, email));
        }

        return list;
    }

    public static int countStaffsFromDB(){
        try {
            String sql = "select count(*) from employees";
            Statement st = ConnectToDB.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
