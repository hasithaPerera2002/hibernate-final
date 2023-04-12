package controller;

import DTO.StudentDTO;
import DTO.tableDTO.StudentTblDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import service.ServiceFactory;
import service.ServiceTypes;
import service.StudentService;
import util.Paid;

import java.util.Objects;

public class PaymentFormController {


    public AnchorPane paymentConetxt;
    private ObservableList<StudentTblDTO> studentDTOS = FXCollections.observableArrayList();
    private ObservableList<StudentDTO> allStudent = FXCollections.observableArrayList();
    private ObservableList<StudentDTO> allStudentForTbl = FXCollections.observableArrayList();
    private ObservableList<StudentDTO> cmbData = FXCollections.observableArrayList();
    @FXML
    private TableView<StudentTblDTO> tblPayments;
    private StudentService service = ServiceFactory.getInstance().getService(ServiceTypes.STUDENT_SERVICE);

    @FXML
    private JFXComboBox<StudentDTO> cmbStudentId;

    @FXML
    private Label lblRoomTypeId;

    @FXML
    private Label lblRoomId;


    @FXML
    private JFXButton btnDone;

    @FXML
    private Label lblKeyMoneyStatus;

    @FXML
    private JFXCheckBox paidBox;

    @FXML
    private Label lblFee;

    @FXML
    private TableColumn<StudentTblDTO, String> colId;

    @FXML
    private TableColumn<StudentTblDTO, String> colName;

    @FXML
    private TableColumn<StudentTblDTO, String> colRoomId;

    @FXML
    private TableColumn<StudentTblDTO, String> colRoomTypeId;

    @FXML
    private TableColumn<StudentTblDTO, Double> colFee;

    @FXML
    private TableColumn<StudentTblDTO, String> colStatus;

    public void initialize() {
        colFee.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("paid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        cmbStudentId.setConverter(new StringConverter<StudentDTO>() {
            @Override
            public String toString(StudentDTO studentTblDTO) {
                if (studentTblDTO == null) {
                    return "";
                }
                return (studentTblDTO.getId() + " | " + studentTblDTO.getFname());
            }
            @Override
            public StudentDTO fromString(String s) {
                return null;
            }
        });
        setData();
    }

    private void setData() {
        try {
            this.allStudent = service.getAll();
            for (StudentDTO a:allStudent
                 ) {
                if (a.getPaid().equals(Paid.NOT_PAID)){
                    cmbData.add(a);
                }
            }
            cmbStudentId.setItems(cmbData);
            setTable();

        } catch (NullPointerException w) {
            w.printStackTrace();
        }
    }

    private void setTable() {
//        ObservableList<StudentTblDTO> u = service.getUnPaidStudent();
        this.allStudentForTbl = service.getAll();
        studentDTOS.clear();
        for (StudentDTO a : allStudentForTbl
        ) {
            studentDTOS.add(new StudentTblDTO(a.getId(), a.getRoomsDTO().getId(), a.getFname(), a.getRoomsDTO().getRoomTypeID(), a.getRoomsDTO().getKeymoney(),
                    a.getPaid().name()));
        }

        tblPayments.setItems(studentDTOS);

    }

    @FXML
    void btnDoneOnAction(ActionEvent event) {
        if (cmbStudentId.getSelectionModel().getSelectedItem() != null) {
            if (paidBox.isSelected()) {
                StudentDTO selectedItem = cmbStudentId.getSelectionModel().getSelectedItem();
                selectedItem.setPaid(Paid.PAID);
                if (0<service.update(selectedItem)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                    setTable();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "not 0Updated").show();

                }
            } else {
                StudentDTO selectedItem = cmbStudentId.getSelectionModel().getSelectedItem();
                selectedItem.setPaid(Paid.NOT_PAID);
                if (0<service.update(selectedItem)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                    setTable();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "not Updated").show();
                    setData();
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "SELECT A Student").show();
        }
    }



    public void tblPaymentOnClick(MouseEvent mouseEvent) {

    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
        StudentDTO selectedItem = cmbStudentId.getSelectionModel().getSelectedItem();
        lblRoomId.setText(selectedItem.getRoomsDTO().getId());
        lblRoomTypeId.setText(selectedItem.getRoomsDTO().getRoomTypeID());
        lblFee.setText(String.valueOf(selectedItem.getRoomsDTO().getKeymoney()));
        if (selectedItem.getPaid().equals(Paid.PAID)) {
            paidBox.setSelected(true);
        }
    }
}
