package database;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Invoice;

public class ControlInvoices {
    public static ObservableList<Invoice> getListFromInvoices() throws SQLException{
        ObservableList<Invoice> list = FXCollections.observableArrayList();
        String sql = "select i.invoice_id, i.date, e.employee_id, e.name, i.total_amount "+
                        "from invoice as i, employees as e "+
                        "where i.employee_id = e.employee_id";
        Statement st = ConnectToDB.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            list.add(new Invoice(
                rs.getInt(1), 
                rs.getDate(2).toString(),
                rs.getInt(3),
                rs.getString(4),
                rs.getDouble(5)
            ));
        }
        return list;
    }

    public static boolean insertValuesIntoInvoices(String date, int staffID){
        try {
            String sql = "insert into invoice(date, employee_id) values ('"+ date +"'"+", " + staffID + ")";
            Statement st = ConnectToDB.getConnection().createStatement();
            int isUpdate = st.executeUpdate(sql);
            if(isUpdate != 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateInvoice(Invoice invoice){
        try {
            String sql = "update invoice set date = '" + invoice.getInvoiceDate() + "', employee_id = " + invoice.getStaffID() + " where invoice_id = " + invoice.getInvoiceID();
            int isUpdate = ConnectToDB.getConnection().createStatement().executeUpdate(sql);
            if(isUpdate != 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteFromInvoices(Invoice invoice){
        try {
            String sql;
            sql = "delete from invoice_detail where invoice_id = " + invoice.getInvoiceID();
            int checkDelete = ConnectToDB.getConnection().createStatement().executeUpdate(sql);
            sql = "delete from invoice where invoice_id = " + invoice.getInvoiceID();
            checkDelete = ConnectToDB.getConnection().createStatement().executeUpdate(sql);
            if(checkDelete != 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Invoice getLastestInvoice(){
        try {
            String sql = "select top 1 i.invoice_id, i.date, e.employee_id, e.name, i.total_amount "+ 
            "from invoice as i, employees as e "+
            "where i.employee_id = e.employee_id "+
            "order by i.invoice_id desc";
            ResultSet rs = ConnectToDB.getConnection().createStatement().executeQuery(sql);
            if(rs.next()){
                return new Invoice(
                    rs.getInt(1), 
                    rs.getDate(2).toString(),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Double getInvoiceTotal(int invoiceID){
        try {
            ResultSet rs = ConnectToDB.getConnection().createStatement().executeQuery("select total_amount from invoice where invoice_id = " + invoiceID);
            if(rs.next()){
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int countInvoicesFromDB(){
        try {
            String sql = "select count(*) from invoice where total_amount is not null";
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
