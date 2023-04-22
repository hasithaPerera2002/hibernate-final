package controller;

import DTO.RoomTypesDTO;
import DTO.RoomsDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import service.RoomsService;
import service.ServiceFactory;
import service.ServiceTypes;
import util.Regex;
import util.RoomType;
import util.TextFields;

import java.io.Console;

public class RoomEditFormController {
    RoomsService roomsService= ServiceFactory.getInstance().getService(ServiceTypes.ROOM_SERVICE);
    @FXML
    private AnchorPane dashboardContext2;

    @FXML
    private JFXComboBox<RoomsDTO> cmbRooms;

    @FXML
    private JFXComboBox<RoomTypesDTO> cmbRoomType;

    @FXML
    private JFXTextField txtKeymoney;

    ObservableList<RoomTypesDTO> roomTypesGlobal = roomsService.getRoomTypes();

    @FXML
    private JFXButton btnSave;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (cmbRoomType.getSelectionModel().getSelectedItem()!=null && cmbRooms.getSelectionModel().getSelectedItem()!=null
        && txtKeymoney.getText()!=null && Regex.setTextColor(TextFields.DOUBLE,txtKeymoney)){
            if (0< roomsService.update(new RoomsDTO(cmbRooms.getSelectionModel().getSelectedItem().getId(), cmbRooms.getSelectionModel().getSelectedItem().getRoomTypeID(), cmbRooms.getSelectionModel().getSelectedItem().getRoomType(), Double.parseDouble(txtKeymoney.getText())))) {
                new Alert(Alert.AlertType.CONFIRMATION,"UPDATED").show();
                btnSave.getScene().getWindow().hide();
            }
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"ERROR----SELECT-FROM-COMBO-BOX|FILL-TEXT-FIELD----").show();
        }
    }
    public void initialize(){

        cmbRooms.setConverter(new StringConverter<RoomsDTO>() {
            @Override
            public String toString(RoomsDTO roomsDTO) {
                return roomsDTO.getId()+" | "+roomsDTO.getRoomType()+" | "+roomsDTO.getKeymoney();
            }

            @Override
            public RoomsDTO fromString(String s) {
                return null;
            }
        });
        cmbRoomType.setConverter(new StringConverter<RoomTypesDTO>() {
            @Override
            public String toString(RoomTypesDTO roomTypesDTO) {
                return roomTypesDTO.getRoomType().name();
            }

            @Override
            public RoomTypesDTO fromString(String s) {
                return null;
            }
        });
        setComboBox();
    }

    public void setComboBox() {
        ObservableList<RoomsDTO> rooms = roomsService.getAllRooms();
        cmbRooms.setItems(rooms);
        ObservableList<RoomTypesDTO> roomTypes = roomsService.getRoomTypes();
        this.roomTypesGlobal=roomTypes;
        cmbRoomType.setItems(roomTypes);
    }


    public void cmbRoomsOnACtion(ActionEvent actionEvent) {
        RoomType roomType = cmbRooms.getSelectionModel().getSelectedItem().getRoomType();
        this.roomTypesGlobal.forEach(e->{
            if (roomType==e.getRoomType()){
                cmbRoomType.setValue(e);
                txtKeymoney.setText(String.valueOf(e.getKeyMoney()));
            }
        });

    }
}
