package DTO;

public class UserDTO {
    private String id;
    private String fName;
    private String sName;
    private String email;
    private String contactNo;
    private String password;
    private String address;

    public UserDTO(String id, String fName, String sName, String email, String contactNo, String password, String address) {
        this.setId(id);
        this.setfName(fName);
        this.setsName(sName);
        this.setEmail(email);
        this.setContactNo(contactNo);
        this.setPassword(password);
        this.setAddress(address);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
