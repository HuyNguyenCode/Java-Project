package model;

public class Invoice {
    int invoiceID;
    String invoiceDate;
    String staff;
    double total;
    int amount;
    String productName;
    
    public Invoice(int invoiceID, String invoiceDate, String staff, double total, int amount, String productName) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.staff = staff;
        this.total = total;
        this.amount = amount;
        this.productName = productName;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
