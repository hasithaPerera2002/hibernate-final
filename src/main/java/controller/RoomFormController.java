package controller;

import DTO.RoomTypesDTO;
import DTO.RoomsDTO;
import DTO.tableDTO.RoomTblDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import service.RoomsService;
import service.ServiceFactory;
import service.ServiceTypes;
import util.Regex;
import util.TextFields;

import java.util.Optional;

public class RoomFormController {

    public JFXTextField txtKeymoney;
    private RoomsService roomsService = ServiceFactory.getInstance().getService(ServiceTypes.ROOM_SERVICE);
    private ObservableList<RoomTblDTO> tblDTOS = FXCollections.observableArrayList();

    @FXML
    private AnchorPane dashboardContext2;


    @FXML
    private JFXComboBox<RoomTypesDTO> cmbRoomTypeId;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<RoomTblDTO> tblStudent;

    @FXML
    private TableColumn<RoomTblDTO, String> colId;

    @FXML
    private TableColumn<RoomTblDTO, String> colRoomTypeId;

    @FXML
    private TableColumn<RoomTblDTO, String> colRoomType;

    @FXML
    private TableColumn<RoomTblDTO, Double> colKeyMoney;

    @FXML
    private TableColumn<RoomTblDTO, Button> colOption;

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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeID"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        txtId.setEditable(false);
        btnSave.setDisable(true);
        clear();

        ObservableList<RoomTypesDTO> roomTypes = roomsService.getRoomTypes();
        cmbRoomTypeId.setDisable(true);
        cmbRoomTypeId.setItems(roomTypes);
        cmbRoomTypeId.setConverter(new StringConverter<RoomTypesDTO>() {
            @Override
            public String toString(RoomTypesDTO roomTypesDTO) {
                return (roomTypesDTO.getRoomTypeId() + " | " + roomTypesDTO.getRoomType());
            }

            @Override
            public RoomTypesDTO fromString(String s) {
                return null;
            }
        });
        setTable();
    }

    private void setTable() {
        ObservableList<RoomsDTO> allRooms;
        allRooms = roomsService.getAllRooms();
        for (RoomsDTO a : allRooms
        ) {
            Button button = new Button();
            button.setStyle("-fx-background-color: rgb(230, 57, 70);");
            button.setText("delete");
            button.setOnAction(actionEvent -> {
                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "DO YOU WANT TO DELETE", ButtonType.YES, ButtonType.NO).showAndWait();
                if (type.get() == ButtonType.YES) {
                    if (0 < roomsService.delete(a))
                        new Alert(Alert.AlertType.CONFIRMATION, "Room deleted successfully").show();
                    setTable();
                    clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error while deleting").show();
                }
            });
            tblDTOS.add(new RoomTblDTO(a.getId(), a.getRoomTypeID(), a.getRoomType().name(),a.getKeymoney(),button));
        }
        tblStudent.setItems(tblDTOS);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (cmbRoomTypeId.getSelectionModel().getSelectedItem() != null) {

            if (Regex.setTextColor(TextFields.DOUBLE, txtKeymoney)) {
                RoomTypesDTO selectedItem = cmbRoomTypeId.getSelectionModel().getSelectedItem();
                if (0 < roomsService.add(new RoomsDTO(txtId.getText(), selectedItem.getRoomTypeId(),
                        selectedItem.getRoomType(), selectedItem.getKeyMoney()))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "ROOM ADDED SUCCESSFULLY").show();
                    clear();
                    btnSave.setDisable(true);

                } else {
                    new Alert(Alert.AlertType.ERROR, "ROOM NOT ADDED").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "SELECT ROOM TYPE").show();
        }
    }

    public void cmbRIDOnAction(ActionEvent actionEvent) throws NullPointerException {

        double keyMoney = cmbRoomTypeId.getSelectionModel().getSelectedItem().getKeyMoney();
        txtKeymoney.setText(String.valueOf(keyMoney));
        btnSave.setDisable(false);
    }

    public void tblRoomOnClick(MouseEvent mouseEvent) {
        tblStudent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }
}
