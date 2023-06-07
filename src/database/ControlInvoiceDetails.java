package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InvoiceDetail;
import java.sql.*;

public class ControlInvoiceDetails {
    public static boolean deleteInvoiceDetail(int invoiceID, int bookID){
        try {
            int isDelete = ConnectToDB.getConnection().createStatement().executeUpdate("delete from invoice_detail where invoice_id = "+invoiceID+" and book_id = " + bookID);
            if(isDelete != 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ObservableList<InvoiceDetail> getDetailsFromDB(int invoiceID){
        ObservableList<InvoiceDetail> list = FXCollections.observableArrayList();
        try {
            String sql = "select d.invoice_id, b.book_id, b.title, d.unit_price, d.quantity, d.unit_price * d.quantity "+
            "from invoice_detail as d, books as b "+
            "where d.invoice_id = ? and d.book_id = b.book_id";
            PreparedStatement pst = ConnectToDB.getConnection().prepareStatement(sql);
            pst.setInt(1, invoiceID);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new InvoiceDetail(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getInt(5),
                    rs.getDouble(6)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static InvoiceDetail getInvoiceDetail(int invoiceID, String bookID){
        try {
            String sql = "select d.invoice_id, b.book_id, b.title, d.unit_price, d.quantity, d.unit_price * d.quantity " +
            "from invoice_detail as d, books as b " + 
            "where d.invoice_id = ? and b.title = ? and d.book_id = b.book_id";
            PreparedStatement pst = ConnectToDB.getConnection().prepareStatement(sql);
            pst.setInt(1, invoiceID);
            pst.setString(2, bookID);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return new InvoiceDetail(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getInt(5),
                    rs.getDouble(6)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insertValuesIntoInvoiceDetails(int invoiceID, int bookID, int quantity){
        try {
            String sql = "INSERT INTO invoice_detail (invoice_id, book_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement pst = ConnectToDB.getConnection().prepareStatement(sql);
            pst.setInt(1, invoiceID);
            pst.setInt(2, bookID);
            pst.setInt(3, quantity);
            int isExecute = pst.executeUpdate();
            if(isExecute != 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
