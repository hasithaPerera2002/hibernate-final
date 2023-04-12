package DTO.tableDTO;

import util.Paid;

public class StudentTblDTO {
    private String id;
    private String roomId;
    private String name;
    private String roomTypeId;
    private double keyMoney;
    private String paid;


    public StudentTblDTO(String id, String roomId, String name, String roomTypeId, double keyMoney,String paid) {
        this.setId(id);
        this.setRoomId(roomId);
        this.setName(name);
        this.setRoomTypeId(roomTypeId);
        this.setKeyMoney(keyMoney);
        this.setPaid(paid);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public double getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(double keyMoney) {
        this.keyMoney = keyMoney;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
