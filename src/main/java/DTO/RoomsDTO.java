package DTO;

import util.RoomType;

public class RoomsDTO {
    private String id;
    private String roomTypeID;
    private RoomType roomType;
    private double keymoney;

    public RoomsDTO(String id, String roomTypeID, RoomType roomType, double keymoney) {
        this.setId(id);
        this.setRoomTypeID(roomTypeID);
        this.setRoomType(roomType);
        this.setKeymoney(keymoney);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(String roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getKeymoney() {
        return keymoney;
    }

    public void setKeymoney(double keymoney) {
        this.keymoney = keymoney;
    }
}
