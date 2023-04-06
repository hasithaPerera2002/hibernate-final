package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Navigation;
import util.Routs;

import java.io.IOException;

public class LoginFormController {



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
        Navigation.getInstance().navigation(Routs.DASHBOARD_FORM,mainContext);
        mainContext.getScene().getWindow().hide();
    }

    public void addNewOnClick(MouseEvent mouseEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PasswordForm.fxml"))));
        Stage window = (Stage) mainContext.getScene().getWindow();
        stage.initOwner(window);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();



    }
}
