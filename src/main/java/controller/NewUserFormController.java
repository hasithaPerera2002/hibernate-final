package controller;

import DTO.UserDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.UserService;
import util.Regex;
import service.ServiceTypes;
import util.TextFields;

import java.util.Objects;

public class NewUserFormController {
    UserService userService= ServiceFactory.getInstance().getService(ServiceTypes.USER_SERVICE);
    @FXML
    private AnchorPane dashboardContext2;

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
    private JFXButton btnSave;

    @FXML
    private Label lblID;

    @FXML
    private JFXTextField txtOldPassword;

    @FXML
    private JFXTextField txtNewPassWord;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (Regex.setTextColor(TextFields.NAME, txtFname)){
            if (Regex.setTextColor(TextFields.NAME, txtSname)){
                if (Regex.setTextColor(TextFields.PHONE, txtContactNo)) {
                    if (Regex.setTextColor(TextFields.EMAIL, txtEmail)) {
                        if (Regex.setTextColor(TextFields.LANKAN_ID, txtNIC)) {
                            if (Regex.setTextColor(TextFields.ADDRESS, txtAddress)) {
                                if (Regex.setTextColor(TextFields.PASSWORD, txtOldPassword)) {
                                    if (Regex.setTextColor(TextFields.PASSWORD, txtNewPassWord)) {
                                        if (Objects.equals(txtOldPassword.getText(), txtNewPassWord.getText())) {
                                            int add = userService.add(new UserDTO(lblID.getText(), txtFname.getText(),
                                                    txtSname.getText(), txtEmail.getText(),
                                                    txtContactNo.getText(),
                                                    txtNewPassWord.getText(), txtAddress.getText()
                                                    , txtNIC.getText()));
                                            if (add > 0) {
                                                new Alert(Alert.AlertType.CONFIRMATION, "USER ADDED SUCCESSFULLY").show();
                                                dashboardContext2.getScene().getWindow().hide();
                                            } else {
                                                new Alert(Alert.AlertType.ERROR, "USER NOT ADDED").show();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

    }
    public void initialize(){
        lblID.setText(userService.getNewID());
    }
    public void txtOldPasswordOnAction(ActionEvent actionEvent) {
    }

    public void txtNewPassWordOnAction(ActionEvent actionEvent) {
    }
}
