package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import service.RoomsService;
import service.ServiceFactory;

public class RoomFormController {
    public JFXComboBox cmbRoomTypeId;
    RoomsService roomsService= ServiceFactory.getInstance().getService(S);
    @FXML
    private AnchorPane dashboardContext2;

    @FXML
    private JFXTextField txtFname;

    @FXML
    private JFXComboBox<?> cmbRoomType;

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
    private JFXTextField txtRoomType;

    @FXML
    private JFXButton btnNew;

    @FXML
    void btnNewOnAction(ActionEvent event) {
        roomsService.getNewID();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    public void cmbRIDOnAction(ActionEvent actionEvent) {
    }
}
