package controller;

import DTO.UserDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.UserService;
import util.Regex;
import service.ServiceTypes;
import util.TextFields;

import java.io.IOException;
import java.util.Objects;

public class PasswordFormController {

    UserService userService;
    public JFXTextField txtUserId;
    public JFXPasswordField txtPassWord;

    @FXML
    private JFXButton btnDone;

    @FXML
    void btnDoneOnAction(ActionEvent event) throws IOException {
        if (Regex.setTextColor(TextFields.ID,txtUserId)) {
            if (Regex.setTextColor(TextFields.PASSWORD,txtPassWord)) {
                UserDTO user = userService.getUser(txtUserId.getText());
                if (user!= null) {
                    if (Objects.equals(txtPassWord.getText(), user.getPassword())) {
                        loadNext();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"WRONG PASSWORD").show();
                    }
            }else {
                new Alert(Alert.AlertType.ERROR,"CANNOT FIND USER ID").show();
                }
            }

        }
    }
    public void initialize() {
        userService= ServiceFactory.getInstance().getService(ServiceTypes.USER_SERVICE);
        txtUserId.requestFocus();

    }
    public void loadNext() throws IOException {
        Stage window = (Stage) btnDone.getScene().getWindow();
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(("/view/NewUserForm.fxml")))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(window);
        stage.centerOnScreen();
        stage.showAndWait();
        window.hide();
    }


}
