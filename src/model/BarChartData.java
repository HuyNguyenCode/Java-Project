package model;

public class BarChartData {
    private String x;
    private Integer y;
    public BarChartData(String x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public String getX() {
        return x;
    }
    public void setX(String x) {
        this.x = x;
    }
    public Integer getY() {
        return y;
    }
    public void setY(Integer y) {
        this.y = y;
    }
    
}
