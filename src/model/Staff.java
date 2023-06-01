package model;

public class Staff {
    private int id;
    private String staffName;
    private int phone;
    private String email;

    public Staff(int id, String staffName, int phone, String email) {
        this.id = id;
        this.staffName = staffName;
        this.phone = phone;
        this.email = email;
    }

    public Staff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    

    


}
