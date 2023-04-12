package controller;

import DTO.UserDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.ServiceTypes;
import service.UserService;
import util.Navigation;
import util.Regex;
import util.TextFields;

import java.util.Objects;

public class UserFormController {
    private UserService userService= ServiceFactory.getInstance().getService(ServiceTypes.USER_SERVICE);
    private UserDTO userDTO=Navigation.getInstance().getUserDTO();

    public JFXTextField txtEmail;

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
    private JFXPasswordField txtOldPassword;

    @FXML
    private JFXTextField txtNewPassWord;

    @FXML
    private JFXButton btnDelete;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    public void initialize(){
       // setData();
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if(Regex.setTextColor(TextFields.ADDRESS,txtAddress)&&Regex.setTextColor(TextFields.NAME,txtFname)
        &&Regex.setTextColor(TextFields.NAME,txtSname) && Regex.setTextColor(TextFields.EMAIL,txtEmail) &&
                Regex.setTextColor(TextFields.PHONE,txtContactNo) && Regex.setTextColor(TextFields.LANKAN_ID,txtNIC)&&
        Regex.setTextColor(TextFields.PASSWORD,txtOldPassword) &&
                Regex.setTextColor(TextFields.PASSWORD,txtNewPassWord)){

            if (userDTO.getPassword() == null) System.out.println("======== user old password is null ============");
            if ((Objects.equals(txtOldPassword.getText(), userDTO.getPassword()))) {
                if (!Objects.equals(txtNewPassWord.getText(), txtOldPassword.getText())) {

                    if (0 < userService.update(new UserDTO(lblID.getText(), txtFname.getText(), txtSname.getText(), txtEmail.getText(), txtContactNo.getText(),
                            txtNewPassWord.getText(), txtAddress.getText(), txtNIC.getText()))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "UPDATED SUCCESSFULLY").show();
                        loadData();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "UPDATED ERROR").show();
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING,"OLD PASSWORD CANNOT BE CURRENT PASSWORD").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"OLD PASSWORD HAS TO BE CURRENT PASSWORD").show();
            }

        }
    }

    private void loadData() {

        txtOldPassword.clear();
        txtNewPassWord.clear();
        this.userDTO=userService.getUser(lblID.getText());
        setData();
    }

    private void setData() {
       lblID.setText(userDTO.getId());
        txtFname.setText(userDTO.getfName());
        txtAddress.setText(userDTO.getAddress());
        txtSname.setText(userDTO.getsName());
        txtContactNo.setText(userDTO.getContactNo());
        txtNIC.setText(userDTO.getNic());
        txtEmail.setText(userDTO.getEmail());

    }
}
