package controller;

import DTO.RoomTypesDTO;
import DTO.RoomsDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.RoomTypes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import service.RoomsService;
import service.ServiceFactory;
import service.ServiceTypes;
import util.Regex;
import util.RoomType;
import util.TextFields;

public class RoomFormController {

    public JFXTextField txtKeymoney;
    private RoomsService roomsService= ServiceFactory.getInstance().getService(ServiceTypes.ROOM_SERVICE);
    @FXML
    private AnchorPane dashboardContext2;


    @FXML
    private JFXComboBox<RoomTypesDTO> cmbRoomTypeId;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<?> tblStudent;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colRoomTypeId;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private JFXTextField txtSearchId;



    @FXML
    private JFXButton btnNew;

    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtId.setText(roomsService.getNewID());
        btnSave.setDisable(false);
        cmbRoomTypeId.setDisable(false);
        clear();

    }

    private void clear() {
        txtId.setText(roomsService.getNewID());
        cmbRoomTypeId.getSelectionModel().clearSelection();
        txtKeymoney.clear();
        txtKeymoney.setEditable(false);
    }

    public void initialize() {
        txtId.setEditable(false);
        btnSave.setDisable(true);
        clear();
        ObservableList<RoomsDTO> allRooms = roomsService.getAllRooms();
        ObservableList<RoomTypesDTO> roomTypes = roomsService.getRoomTypes();
        cmbRoomTypeId.setDisable(true);
        cmbRoomTypeId.setItems(roomTypes);
        cmbRoomTypeId.setConverter(new StringConverter<RoomTypesDTO>() {
            @Override
            public String toString(RoomTypesDTO roomTypesDTO) {
                return (roomTypesDTO.getRoomTypeId()+" | "+roomTypesDTO.getRoomType());
            }

            @Override
            public RoomTypesDTO fromString(String s) {
                return null;
            }
        });
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (cmbRoomTypeId.getSelectionModel().getSelectedItem()!=null) {

                if(Regex.setTextColor(TextFields.DOUBLE,txtKeymoney)) {
                    RoomTypesDTO selectedItem = cmbRoomTypeId.getSelectionModel().getSelectedItem();
                    if (0 < roomsService.add(new RoomsDTO(txtId.getText(), selectedItem.getRoomTypeId(),
                            selectedItem.getRoomType(), selectedItem.getKeyMoney()))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "ROOM ADDED SUCCESSFULLY").show();
                        clear();

                    } else {
                        new Alert(Alert.AlertType.ERROR, "ROOM NOT ADDED").show();
                    }
                }
        }else {
            new Alert(Alert.AlertType.ERROR, "SELECT ROOM TYPE").show();
        }
    }

    public void cmbRIDOnAction(ActionEvent actionEvent) throws NullPointerException{

        double keyMoney = cmbRoomTypeId.getSelectionModel().getSelectedItem().getKeyMoney();
        txtKeymoney.setText(String.valueOf(keyMoney));
        btnSave.setDisable(false);
    }
}
