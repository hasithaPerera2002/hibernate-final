package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PaymentsKeyMoneyFormController {
    @FXML
    private AnchorPane dashboardContext2;

    @FXML
    private JFXComboBox<?> cmbStudentId;

    @FXML
    private Label lblRoomTytpeId;

    @FXML
    private Label lblRoomId;

    @FXML
    private JFXButton btnDone;

    @FXML
    private Label lblKeyMoneyAmount;

    @FXML
    private JFXCheckBox paidBox;

    @FXML
    void btnDoneOnAction(ActionEvent event) {

    }
}
