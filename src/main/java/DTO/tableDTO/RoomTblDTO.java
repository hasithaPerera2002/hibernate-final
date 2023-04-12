package DTO.tableDTO;

import javafx.scene.control.Button;

public class RoomTblDTO {
    private String id;
    private String roomTypeID;
    private String roomType;
    private double keyMoney;
    private Button btn;

    public RoomTblDTO(String id, String roomTypeID, String roomType, double keyMoney, Button btn) {
        this.setId(id);
        this.setRoomTypeID(roomTypeID);
        this.setRoomType(roomType);
        this.setKeyMoney(keyMoney);
        this.setBtn(btn);
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(double keyMoney) {
        this.keyMoney = keyMoney;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
