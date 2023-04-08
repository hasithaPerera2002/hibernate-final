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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceTypes;
import service.UserService;
import util.*;

import java.io.IOException;
import java.util.Objects;

public class LoginFormController {


    public JFXTextField txtUserName;
    UserService service = ServiceFactory.getInstance().getService(ServiceTypes.USER_SERVICE);
    @FXML
    private AnchorPane mainContext;
    @FXML
    private AnchorPane loginFormContext2;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        if (Regex.setTextColor(TextFields.ID,txtUserName)) {
            if (Regex.setTextColor(TextFields.PASSWORD,txtPassword)) {
                UserDTO user = service.getUser(txtUserName.getText());
                if (user!= null) {
                    if (Objects.equals(txtPassword.getText(), user.getPassword())) {
                        Navigation.getInstance().navigation(Routs.DASHBOARD_FORM, mainContext);
                        mainContext.getScene().getWindow().hide();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"WRONG PASSWORD").show();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR,"CANNOT FIND USER ID").show();
                }
            }

        }
    }


    public void addNewOnClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PasswordForm.fxml"))));
        Stage window = (Stage) mainContext.getScene().getWindow();
        stage.initOwner(window);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();


    }
}
