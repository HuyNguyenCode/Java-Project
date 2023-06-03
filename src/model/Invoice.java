package model;

public class Invoice {
    private int invoiceID;
    private String invoiceDate;
    private int staff;
    private double total;
    

    public Invoice(int invoiceID, String invoiceDate, int staff, double total) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.staff = staff;
        this.total = total;
    }


    public Invoice() {
    }


    public int getInvoiceID() {
        return invoiceID;
    }
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }
    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public Integer getStaff() {
        return staff;
    }
    public void setStaff(Integer staff) {
        this.staff = staff;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
