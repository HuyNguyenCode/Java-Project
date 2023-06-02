package database;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Invoice;
import model.Staff;
import model.Supplier;
import model.User;

public class ControllDB {

    public static Book getLastestBook(){
        Book res = null;
        String sql = "Select * from Books where book_id = (select max(book_id) from Books)";
        Statement st;
        try {
            st = ConnectToDB.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String publisher = rs.getString(4);
                int year = rs.getInt(5);
                String category = rs.getString(6);
                Double price = rs.getDouble(7);
                int stock = rs.getInt(8);
                res = new Book(id, year, stock, price, title,null, author, publisher, category);
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    //them data vao bang tblBook
    public static boolean insertValuesIntoBooks(Book book){
        try {
            String tableName = "books";
            String sql = "insert into " + tableName + " values(?,?,?,?,?,?,?)";
            PreparedStatement pst = ConnectToDB.getConnection().prepareStatement(sql);
            String title = book.getTitle();
            String author = book.getAuthor();
            String publisher = book.getPublisher();
            int year = book.getYear();
            String category = book.getCategory();
            Double price = book.getPrice();
            int stock = book.getStock();
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setString(3, publisher);
            pst.setInt(4, year);
            pst.setString(5, category);
            pst.setDouble(6, price);
            pst.setInt(7,stock);
            int isUpdate = pst.executeUpdate();
            if(isUpdate != 0){
                System.out.println("Insert Success!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Insert Fail!");
        return false;
    }

    //Ham xoa tren database: truyen vao mot quyen sach nhung xoa dua tren id
    public static boolean deleteFromBooks(Book book){
        try {
            String tableName = "Books";
            String sql = "delete from " + tableName + " where id = ?";
            PreparedStatement pst = ConnectToDB.getConnection().prepareStatement(sql);
            int id = book.getId();
            pst.setInt(1, id);
            int isUpdate = pst.executeUpdate();
            if(isUpdate != 0){
                System.out.println("Delete Success!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Delete Fail!");
        return false;
    }

    public static boolean updateBooks(Book book){
        try {
            PreparedStatement pst = ConnectToDB.getConnection().prepareStatement("update books set title=?, author=?, publisher=?, year=?, category=?, price=?, stock=? where book_id=?");
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getPublisher());
            pst.setInt(4, book.getYear());
            pst.setString(5, book.getCategory());
            pst.setDouble(6, book.getPrice());
            pst.setInt(7, book.getStock());
            pst.setInt(8, book.getId());
            int check = pst.executeUpdate();
            if(check != 0){
                System.out.println("Update Success!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update fail!");
        }
        return false;
    }

    public static ObservableList<Book> getListFromBooks() throws SQLException{
        ObservableList<Book> books = FXCollections.observableArrayList();
        Connection con = ConnectToDB.getConnection();
        String sql = "Select * from books";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String author = rs.getString(3);
            String publisher = rs.getString(4);
            int year = rs.getInt(5);
            String category = rs.getString(6);
            Double price = rs.getDouble(7);
            int stock = rs.getInt(8);
            Book book = new Book(id, year, stock, price, title,null, author, publisher, category);
            books.add(book);
        }
        return books;
    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Invoice> getListFromInvoices() throws SQLException{
        ObservableList<Invoice> list = FXCollections.observableArrayList();
        String sql = "select * from invoice";
        Statement st = ConnectToDB.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int invoiceId = rs.getInt(1);
            Date _date = rs.getDate(2);
            String date = _date.toString();
            double totalAmount = rs.getDouble(3);
            Integer _staffId = rs.getInt(4);
            String staffId = _staffId.toString();
            list.add(new Invoice(invoiceId,date,staffId,totalAmount));
        }
        return list;
    }

    public static ObservableList<Staff> getListFreomStaffs() throws SQLException{
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
