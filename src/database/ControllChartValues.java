package database;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BarChartData;
import model.LineChartData;

public class ControllChartValues {
    public static ObservableList<BarChartData> getBarChartDataFromDB(){
        ObservableList<BarChartData> list = FXCollections.observableArrayList();
        try {
            String sql = "select category, sum(stock) from books group by category";
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
            "from invoice i "+
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
}
