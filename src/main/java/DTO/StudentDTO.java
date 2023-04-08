package DTO;

public class StudentDTO {
    private String id;
    private String fname;
    private String sname;
    private String address;
    private String contact;
    private String nic;
    private RoomsDTO roomsDTO;

    public StudentDTO(String id, String fname, String sname, String address, String contact, String nic, RoomsDTO roomsDTO) {
        this.setId(id);
        this.setFname(fname);
        this.setSname(sname);
        this.setAddress(address);
        this.setContact(contact);
        this.setNic(nic);
        this.setRoomsDTO(roomsDTO);


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public RoomsDTO getRoomsDTO() {
        return roomsDTO;
    }

    public void setRoomsDTO(RoomsDTO roomsDTO) {
        this.roomsDTO = roomsDTO;
    }
}
