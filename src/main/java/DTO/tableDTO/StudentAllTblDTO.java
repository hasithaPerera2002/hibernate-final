package DTO.tableDTO;

import javafx.scene.control.Button;

public class StudentAllTblDTO {
    private String id;
    private String firstName;
    private String secondName;
    private String contactNo;
    private String nic;
    private String address;
    private String roomId;
    private Button btn;

    public StudentAllTblDTO(String id, String fName, String sName, String contactNo, String nic, String address, String roomId, Button btn) {
        this.setId(id);
        this.setfName(fName);
        this.setSecondName(sName);
        this.setContactNo(contactNo);
        this.setNic(nic);
        this.setAddress(address);
        this.setRoomId(roomId);
        this.setBtn(btn);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setfName(String fName) {
        this.firstName = fName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
