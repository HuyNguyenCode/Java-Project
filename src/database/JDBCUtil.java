package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection c = null;

        try {
            //Register MySQL with DriverManager
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //Build url for server (localhost)
            //Parameters
            String url = "jdbc:mySQL://localhost:3306/doanjava?<props>"; 
            String userName = "root";
            String password = "";

            //Create a connection
            c = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void printInfor(Connection c) {
        if (c != null) {
            try {
                java.sql.DatabaseMetaData mtdt = c.getMetaData();
                Sysâtem.out.println(mtdt.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
