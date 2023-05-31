package model;


public class InvoiceDetail {
    int invoiceID;
    int bookID;
    String bookTitle;
    double unitPrice;
    int quantity;
    double total;
    

    public InvoiceDetail(int invoiceID, int bookID, String bookTitle, double unitPrice, int quantity, double total) {
        this.invoiceID = invoiceID;
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
    }

    public InvoiceDetail() {
    }
    
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }
    
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    
}

