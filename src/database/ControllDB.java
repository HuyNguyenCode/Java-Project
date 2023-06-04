package database;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.BarChartData;
import model.Book;
import model.Invoice;
import model.InvoiceDetail;
import model.LineChartData;
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
            String sql = "delete from " + tableName + " where book_id = ?";
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
            e.printStackTrace();
        }
        return null;
    }

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
            String sql = "delete from invoice where invoice_id = " + invoice.getInvoiceID();
            int checkDelete = ConnectToDB.getConnection().createStatement().executeUpdate(sql);
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

    public static int countBooksFromDB(){
        try {
            String sql = "select sum(stock) from books";
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

    public static ObservableList<BarChartData> getBarChartDataFromDB(){
        ObservableList<BarChartData> list = FXCollections.observableArrayList();
        try {
            String sql = "select category, count(stock) "+
            "from books "+
            "group by category";
            Statement st = ConnectToDB.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(new BarChartData(
                    rs.getString(1),
                    rs.getInt(2)
                ));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<LineChartData> getLineChartDataFromDB(){
        ObservableList<LineChartData> list = FXCollections.observableArrayList();
        try {
            String sql = "select year(i.date) as year, sum(i.total_amount) as Revenue "+
            "from invoice i, invoice_detail d "+
            "where i.invoice_id = d.invoice_id "+
            "group by year(i.date)";
            Statement st = ConnectToDB.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(new LineChartData(
                    rs.getString(1),
                    rs.getDouble(2)
                ));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String project_id = "javaproject-388811";
    private String client_id = "1006621371158-l2qimjeo8puouv34j5ce20t5rf5cp6gd.apps.googleusercontent.com";
    private String client_secret = "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";
    private String redirect_uris = "http://localhost";
    private String auth_uri = "https://accounts.google.com/o/oauth2/auth";
    private String token_uri = "https://oauth2.googleapis.com/token";
    private String auth_provider_x509_cert_url = "https://www.googleapis.com/oauth2/v1/certs";
    
    public void loginAsGoogle() {
        // DefaultFacebookClient

        WebView webview = new WebView();
        WebEngine eg = webview.getEngine();
        eg.load(auth_provider_x509_cert_url);
        eg.locationProperty().addListener((obs, oldLocation, newLocation) -> {
            if(newLocation != null && newLocation.startsWith("http://localhost")) {
                int codeOffset = newLocation.indexOf("code=");
                String code = newLocation.substring(codeOffset + "code=".length());
                token_uri += code;
            
            }
        });
    }
}
