package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Navigation;
import util.Routs;

import java.io.IOException;

public class DashBoardFormController {

    public JFXButton btnLogOut;
    @FXML
    private AnchorPane dashBoardContext;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnRooms;

    @FXML
    private JFXButton btnUser;

    @FXML
    private JFXButton btnPayments;

    @FXML
    private AnchorPane mainDashBoardContext;

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Navigation.getInstance().navigation(Routs.DASHBOARD_CONTEXT, mainDashBoardContext);

    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) throws IOException {
        Navigation.getInstance().navigation(Routs.ROOM_FORM, mainDashBoardContext);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        Navigation.getInstance().navigation(Routs.STUDENT_FORM, mainDashBoardContext);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        Navigation.getInstance().navigation(Routs.USER_CONTEXT, mainDashBoardContext);


    }

    public void initialize() throws IOException {
        Navigation.getInstance().navigation(Routs.DASHBOARD_CONTEXT, mainDashBoardContext);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.getInstance().navigation(Routs.LOGIN_FORM, mainDashBoardContext);
        mainDashBoardContext.getScene().getWindow().hide();

    }

    public void btnPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.getInstance().navigation(Routs.PAYMENT_FORM,mainDashBoardContext);
    }
}
