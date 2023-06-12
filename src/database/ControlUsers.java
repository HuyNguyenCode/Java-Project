package database;

import java.sql.*;

import model.User;

public class ControlUsers {
    public static String getPasswordFromDB(String email){
        try {
            String res = "";
            Statement st = ConnectToDB.getConnection().createStatement();
            String sql = "select password from Users where email = '" + email + "'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                res = rs.getString(1);
                return res;
            }
            return "-1";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean insertValuesIntoUsers(User user){
        try {
            PreparedStatement pst = ConnectToDB.getConnection().prepareStatement("insert into users values (?,?,?)");
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getFullName());
            pst.setString(3, user.getPassword());
            int isUpdate = pst.executeUpdate();
            if(isUpdate != 0){
                System.out.println("Insert User Success!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Insert User Fail!");
        }
        return false;
    }

    public static User getUserFromDB(String email){
        String sql = "select * from users where email = '" + email + "'";
        Statement st;
        try {
            st = ConnectToDB.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return new User(rs.getString(2), rs.getString(1), rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
