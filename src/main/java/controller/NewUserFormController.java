package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class NewUserFormController {
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
    private JFXTextField txtAddress1;

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }
}