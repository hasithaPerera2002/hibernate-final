package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Navigation;
import util.Routs;

import java.io.IOException;

public class PaymentFormController {

    public JFXButton btnKeyMoney;
    public AnchorPane paymentConetxt;


    @FXML
    private JFXComboBox<?> cmbStudentId;

    @FXML
    private Label lblRoomTypeId;

    @FXML
    private Label lblRoomId;

    @FXML
    private Label lblKeyMoney;

    @FXML
    private JFXButton btnDone;

    @FXML
    private Label lblKeyMoneyStatus;

    @FXML
    private JFXCheckBox paidBox;

    @FXML
    private Label lblFee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colRoomTypeId;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    void btnDoneOnAction(ActionEvent event) {

    }

    @FXML
    void tblPayments(ActionEvent event) {

    }

    public void btnKeyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) paymentConetxt.getScene().getWindow();
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PaymentKeyMoneyForm.fxml"))));
        stage.initOwner(window);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        stage.centerOnScreen();
    }
}
