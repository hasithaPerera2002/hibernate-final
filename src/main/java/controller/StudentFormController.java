package controller;

import DTO.RoomsDTO;
import DTO.RoomsStuDTO;
import DTO.StudentDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import service.RoomsService;
import service.ServiceFactory;
import service.ServiceTypes;
import service.StudentService;
import util.Regex;
import util.TextFields;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class StudentFormController {


    public JFXButton btnKeyMoney;
    public AnchorPane dashboardContext2;
    public TableColumn colRoomId;
    public JFXComboBox<RoomsDTO> cmbRoom;
    public DatePicker datePickerFrom;
    public DatePicker datePickerTo;
    private StudentService studentService = ServiceFactory.getInstance().getService(ServiceTypes.STUDENT_SERVICE);
    private RoomsService roomsService = ServiceFactory.getInstance().getService(ServiceTypes.ROOM_SERVICE);
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
    private TableView<?> tblStudent;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colFname;

    @FXML
    private TableColumn<?, ?> colSname;

    @FXML
    private TableColumn<?, ?> colConatctNo;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colOption;

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
                    System.out.println("===================================================");
                    if (cmbRoom.getSelectionModel().getSelectedItem() != null) {
                        StudentDTO studentDTO = new StudentDTO(txtId.getText(), txtFname.getText(),
                                txtSname.getText(), txtAddress.getText(), txtContactNo.getText(), txtNIC.getText(),
                                fromValue, toValue,
                                cmbRoom.getSelectionModel().getSelectedItem());
                        RoomsDTO selectedItem = cmbRoom.getSelectionModel().getSelectedItem();

                        if (0 < studentService.add(studentDTO) && roomsService.update(
                                new RoomsStuDTO(selectedItem, studentDTO))) {
                            new Alert(Alert.AlertType.CONFIRMATION, "STUDENT ADDED SUCCESSFULLY").show();

                        } else {
                            new Alert(Alert.AlertType.CONFIRMATION, "STUDENT NOT ADDED").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "SELECT A ROOM ").show();


                    }
                    clear();
                }else {
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
    }

    private void setNewId() {
        txtId.setText(studentService.getNewId());
    }

    public void btnKeyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) dashboardContext2.getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PaymentKeyMoneyForm.fxml"))));
        stage.initOwner(window);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        stage.centerOnScreen();


    }
}
