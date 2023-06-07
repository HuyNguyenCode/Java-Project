package model;

public class BookEntry {
    private int entryID;
    private String entryDate;
    private int staffID;
    private int supplierID;
    private double total;

    public BookEntry(int entryID, String entryDate, int staffID, int supplierID, double total) {
        this.entryID = entryID;
        this.entryDate = entryDate;
        this.staffID = staffID;
        this.supplierID = supplierID;
        this.total = total;
    }

    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
