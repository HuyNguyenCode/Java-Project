package database;

import model.Book;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ControlBooks {
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

    public static Integer getBookIDFromName(String name){
        try {
            ResultSet rs = ConnectToDB.getConnection().createStatement().executeQuery("select book_id from books where title = '" + name + "'");
            if(rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
}
