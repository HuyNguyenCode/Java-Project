package model;

public class Invoice {
    private int invoiceID;
    private String invoiceDate;
    int staffID;
    private String staff;
    private double total;
    

    public Invoice(int invoiceID, String invoiceDate, int staffID, String staff, double total) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.staffID = staffID;
        this.staff = staff;
        this.total = total;
    }


    public int getStaffID() {
        return staffID;
    }


    public void setStaffID(int staffID) {
        this.staffID = staffID;
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
    public String getStaff() {
        return staff;
    }
    public void setStaff(String staff) {
        this.staff = staff;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
