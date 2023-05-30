package model;

public class Invoice {
    int invoiceID;
    String invoiceDate;
    String staff;
    double total;
    

    public Invoice(int invoiceID, String invoiceDate, String staff, double total) {
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
