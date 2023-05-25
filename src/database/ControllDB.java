package database;
import java.sql.*;

import model.Book;

public class ControllDB {

    //them data vao bang tblBook
    public static boolean insertValuesIntoBook(Book book){

        Connection con = ConnectToDB.getConnection();

        try {
            String tableName = "tblBook";
            String sql = "insert into " + tableName + " values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            int id = book.getId();
            int year = book.getYear();
            int stock = book.getStock();
            Double price = book.getPrice();
            String title = book.getTitle();
            String imageSrc = book.getImageSrc();
            String author = book.getAuthor();
            String publisher = book.getPublisher();
            String category = book.getCategory();
            pst.setInt(1, id);
            pst.setInt(2, year);
            pst.setInt(3,stock);
            pst.setDouble(4, price);
            pst.setString(5, title);
            pst.setString(6, imageSrc);
            pst.setString(7, author);
            pst.setString(8, publisher);
            pst.setString(9, category);
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
    public static boolean deleteFromBook(Book book){
        
        Connection con = ConnectToDB.getConnection();

        try {
            String tableName = "tblBook";
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
            // pst.setString(9, category);
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

    // public static void main(String[] args) {
    //     Book t = new Book(345, 2022, 2, 238000, "RICH DAD POOR DAD", null, "Robert T.Kiyosaki", "Plata Publishing", "Personal finance");
    //     ControllDB.deleteFromBook(t);
    // }
}
