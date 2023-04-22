package controller;

import DTO.RoomsDTO;
import DTO.RoomsStuDTO;
import DTO.StudentDTO;
import DTO.tableDTO.StudentAllTblDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import service.RoomsService;
import service.ServiceFactory;
import service.ServiceTypes;
import service.StudentService;
import util.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class StudentFormController {


    public JFXButton btnKeyMoney;
    public AnchorPane dashboardContext2;
    public TableColumn<StudentAllTblDTO, String> colRoomId;
    public JFXComboBox<RoomsDTO> cmbRoom;
    public DatePicker datePickerFrom;
    public DatePicker datePickerTo;
    private StudentService studentService = ServiceFactory.getInstance().getService(ServiceTypes.STUDENT_SERVICE);
    private RoomsService roomsService = ServiceFactory.getInstance().getService(ServiceTypes.ROOM_SERVICE);
    private ObservableList<StudentAllTblDTO> tblDTOS = FXCollections.observableArrayList();
//    private  SSer

    @FXML
    private JFXTextField txtFname;

    @FXML
    private JFXTextField txtSname;

    @FXML
    private JFXTextField txtContactNo;


    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<StudentAllTblDTO> tblStudent;

    @FXML
    private TableColumn<StudentAllTblDTO, String> colId;

    @FXML
    private TableColumn<StudentAllTblDTO, String> colFname;

    @FXML
    private TableColumn<StudentAllTblDTO, String> colSname;

    @FXML
    private TableColumn<StudentAllTblDTO, String> colConatctNo;

    @FXML
    private TableColumn<StudentAllTblDTO, String> colNIC;

    @FXML
    private TableColumn<StudentAllTblDTO, String> colAddress;

    @FXML
    private TableColumn<StudentAllTblDTO, Button> colOption;

    @FXML
    private JFXTextField txtSearchId;


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (Regex.setTextColor(TextFields.NAME, txtFname) && Regex.setTextColor(TextFields.NAME, txtSname) &&
                Regex.setTextColor(TextFields.PHONE, txtContactNo) &&
                Regex.setTextColor(TextFields.ADDRESS, txtAddress)
                && Regex.setTextColor(TextFields.LANKAN_ID, txtNIC)) {
            if (datePickerFrom.getValue() != null && datePickerTo.getValue() != null) {
                LocalDate toValue = datePickerTo.getValue();
                LocalDate fromValue = datePickerFrom.getValue();
                Period between = Period.between(fromValue, toValue);
                if ((Math.abs(between.getMonths()) >= 1)) {
                    if (cmbRoom.getSelectionModel().getSelectedItem() != null) {
                        StudentDTO studentDTO = new StudentDTO(txtId.getText(), txtFname.getText(),
                                txtSname.getText(), txtAddress.getText(), txtContactNo.getText(), txtNIC.getText(),
                                fromValue, toValue, Paid.NOT_PAID,
                                cmbRoom.getSelectionModel().getSelectedItem());
                        RoomsDTO selectedItem = cmbRoom.getSelectionModel().getSelectedItem();

                        if (0 < studentService.add(studentDTO) && roomsService.update(
                                new RoomsStuDTO(selectedItem, studentDTO))) {
                            new Alert(Alert.AlertType.CONFIRMATION, "STUDENT ADDED SUCCESSFULLY").show();
setTable();
                        } else {
                            new Alert(Alert.AlertType.CONFIRMATION, "STUDENT NOT ADDED").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "SELECT A ROOM ").show();
                    }
                    clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "DATE DURATION HAVE TO BETWEEN 1 MONTH ").show();
                }


            } else {
                new Alert(Alert.AlertType.ERROR, "SELECT  DATE DURATION").show();
            }
        }
    }

    private void clear() {
        txtId.setText(studentService.getNewId());
        txtAddress.clear();
        txtFname.clear();
        txtSname.clear();
        txtContactNo.clear();
        txtNIC.clear();
        datePickerFrom.setValue(null);
        datePickerTo.setValue(null);
        cmbRoom.getSelectionModel().select(null);
        txtSearchId.clear();

    }

    public void initialize() {
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colConatctNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colSname.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        setNewId();
        txtId.setDisable(true);
        cmbRoom.setItems(roomsService.getAllRoomsWithoutStudents());
        cmbRoom.setConverter(new StringConverter<RoomsDTO>() {
            @Override
            public String toString(RoomsDTO roomsDTO) {
                return (roomsDTO.getId() + " | " + roomsDTO.getRoomTypeID() + " | " + roomsDTO.getRoomType() + " | " + roomsDTO.getKeymoney());
            }

            @Override
            public RoomsDTO fromString(String s) {
                return null;
            }
        });
      setTable();
    }

    private void setTable() {
        ObservableList<StudentDTO> all = studentService.getAll();
        tblDTOS.clear();
        all.stream().forEach(studentDTO -> {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: rgb(230, 57, 70);");
                    button.setText("delete");
                    button.setOnAction(actionEvent -> {
                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "DO YOU WANT TO DELETE", ButtonType.YES, ButtonType.NO).showAndWait();
                        if (type.get() == ButtonType.YES) {
                            if (0 < studentService.delete(studentDTO))
                                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully").show();
                            setTable();
                            clear();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Error while deleting").show();
                        }
                    });
                    tblDTOS.add(new StudentAllTblDTO(studentDTO.getId(), studentDTO.getFname(), studentDTO.getSname(), studentDTO.getContact(),
                            studentDTO.getNic(), studentDTO.getAddress(), studentDTO.getRoomsDTO().getId(), button));
                }
        );
        tblStudent.setItems(tblDTOS);
    }

    private void setNewId() {
        txtId.setText(studentService.getNewId());
    }

    public void btnKeyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.getInstance().navigation(Routs.PAYMENT_FORM, dashboardContext2);
    }
}
