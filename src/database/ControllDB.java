package database;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;

public class ControllDB {

    //them data vao bang tblBook
    public static boolean insertValuesIntoBooks(Book book){

        Connection con = ConnectToDB.getConnection();

        try {
            String tableName = "books";
            String sql = "insert into " + tableName + " values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            int id = book.getId();
            String title = book.getTitle();
            String author = book.getAuthor();
            String publisher = book.getPublisher();
            int year = book.getYear();
            String category = book.getCategory();
            Double price = book.getPrice();
            int stock = book.getStock();
            pst.setInt(1, id);
            pst.setString(2, title);
            pst.setString(3, author);
            pst.setString(4, publisher);
            pst.setInt(5, year);
            pst.setString(6, category);
            pst.setDouble(7, price);
            pst.setInt(8,stock);
            int isUpdate = pst.executeUpdate();
            if(isUpdate != 0){
                System.out.println("Insert Success!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            con.close();
            System.out.println("Close connection success!");
        } catch (SQLException e) {
            System.out.println("Close connection fail!");
            e.printStackTrace();
        }

        return false;
    }

    //Ham xoa tren database: truyen vao mot quyen sach nhung xoa dua tren id
    public static boolean deleteFromBooks(Book book){
        
        Connection con = ConnectToDB.getConnection();

        try {
            String tableName = "Books";
            String sql = "delete from " + tableName + " where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            int id = book.getId();
            // int year = book.getYear();
            // int stock = book.getStock();
            // Double price = book.getPrice();
            // String title = book.getTitle();
            // String imageSrc = book.getImageSrc();
            // String author = book.getAuthor();
            // String publisher = book.getPublisher();
            // String category = book.getCategory();
            pst.setInt(1, id);
            // pst.setInt(2, year);
            // pst.setInt(3,stock);
            // pst.setDouble(4, price);
            // pst.setString(5, title);
            // pst.setString(6, imageSrc);
            // pst.setString(7, author);
            // pst.setString(8, publisher);
            // pst.setString(9, category);~
            int isUpdate = pst.executeUpdate();
            if(isUpdate != 0){
                System.out.println("Delete Success!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            con.close();
            System.out.println("Close connection success!");
        } catch (SQLException e) {
            System.out.println("Close connection fail!");
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateBooks(Book book){
        try {
            PreparedStatement pst = ConnectToDB.getConnection().prepareStatement("update books set title=?, author=?, publisher=?, year=?, category=?, price=?, stock=? where id=?");
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
            // TODO Auto-generated catch block
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
    // public static void main(String[] args) {
    //     // Book t = new Book(345, 2022, 2, 238000, "RICH DAD POOR DAD", null, "Robert T.Kiyosaki", "Plata Publishing", "Personal finance");
    //     // ControllDB.deleteFromBook(t);
    //     // try {
    //     //     PreparedStatement pst = ConnectToDB.getConnection().prepareStatement("insert into books values(?,?,?,?,?,?,?,?)");
    //     //     pst.setInt(1, 21);
    //     //     pst.setString(2, "Ngo van manh");
    //     //     pst.setString(3, "Manh");
    //     //     pst.setString(4, "mmmm");
    //     //     pst.setInt(5, 2003);
    //     //     pst.setString(6, "The loai");
    //     //     pst.setDouble(7, 100);
    //     //     pst.setInt(8, 2000);
    //     //     pst.executeUpdate();
    //     // } catch (SQLException e) {
    //     //     // TODO Auto-generated catch block
    //     //     e.printStackTrace();
    //     // }
    // }
}
