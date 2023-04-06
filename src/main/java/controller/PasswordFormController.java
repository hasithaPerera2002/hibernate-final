package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordFormController {


    public JFXTextField txtUserId;
    public JFXPasswordField txtPassWord;

    @FXML
    private JFXButton btnDone;

    @FXML
    void btnDoneOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) btnDone.getScene().getWindow();

        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(("/view/NewUserForm.fxml")))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(window);
        stage.centerOnScreen();
        stage.showAndWait();
        window.hide();
    }
    public void initialize() {

        txtUserId.requestFocus();

    }


}
