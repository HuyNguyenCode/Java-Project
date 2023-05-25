package database;

import java.sql.*;

import model.Book;

public class ConnectToDB{
    
    private static Connection con;
    
    public static Connection getConnection(){
        
        System.out.println("Connecting Database............");
        
        try {
            String databaseName = "QLSach";
            String user = "sa";
            String password = "1";
            String url = "jdbc:sqlserver://localhost:1433;databasename=" + databaseName + ";encrypt=true;trustServerCertificate=true;";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);       
            
            System.out.println("Success!");     
        } catch (Exception e) {
            System.out.println("Connecting Error!");
        }
        return con;
    }
}