package util;

import DTO.UserDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static Navigation navigation;
    private AnchorPane anchorPane;
    private UserDTO userDTO;
    public static Navigation getInstance() {
        if (navigation == null) {
            navigation = new Navigation();
        }
        return navigation;
    }
public void navigation(Routs routs, AnchorPane anchorPane) throws IOException {
        this.anchorPane = anchorPane;
        switch(routs){
            case DASHBOARD_CONTEXT:setUI("DashBoardContextForm");break;
            case DASHBOARD_FORM:setNewPane("DashBoardForm");break;
            case STUDENT_FORM:setUI("StudentForm");break;
            case USER_CONTEXT:setUI("UserForm");break;
            case ROOM_FORM:setUI("RoomForm");break;
            case LOGIN_FORM:setNewPane("LoginForm");break;
            case KEYMONEY_FORM:setNewPane("PaymentKeyMoneyForm");break;
            case PAYMENT_FORM:setUI("PaymentForm");break;
        }
}

    private void setUI(String s) throws IOException {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/"+s+".fxml")));
    }
    private void setNewPane(String s) throws IOException {

        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/"+s+".fxml"))));

        stage.centerOnScreen();
        stage.show();
//        Stage window = (Stage) anchorPane.getScene().getWindow();
//        window.close();

    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
