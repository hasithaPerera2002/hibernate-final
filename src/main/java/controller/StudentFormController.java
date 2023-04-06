package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Navigation;
import util.Routs;

import java.io.IOException;

public class StudentFormController {

    public JFXComboBox cmbRoomID;
    public JFXButton btnKeyMoney;
    public AnchorPane dashboardContext2;


    @FXML
    private JFXTextField txtFname;

    @FXML
    private JFXTextField txtSname;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXComboBox<?> cmbRoomType;

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

    }

    public void btnKeyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) dashboardContext2.getScene().getWindow();
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PaymentKeyMoneyForm.fxml"))));
        stage.initOwner(window);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        stage.centerOnScreen();


    }
}
