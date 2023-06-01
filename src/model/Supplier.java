package model;

public class Supplier {
    private int id;
    private String supplierName;
    private String address;
    private String phone;

    public Supplier(int id, String supplierName, String address, String phone) {
        this.id = id;
        this.supplierName = supplierName;
        this.address = address;
        this.phone = phone;
    }

    public Supplier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}