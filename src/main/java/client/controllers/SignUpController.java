package client.controllers;

import client.Client;
import client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.User;
import server.dao.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

import static client.ClientTemp.client_tmp;
import static client.utils.LogicUtils.checkInput;
import static client.utils.LogicUtils.createDialog;
import static server.constants.FxmlConstants.LOGIN_VIEW;

public class SignUpController {
    @FXML
    private Label signUpStatusLabel;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;

    @FXML
    private Button returnSignInButton;

    @FXML
    private Button signUpButton;

    @FXML
    void returnSignIn(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, LOGIN_VIEW);
    }

    @FXML
    void signUp(ActionEvent event) throws SQLException, IOException {
        //client_tmp = new Client("127.0.0.1", 8080);

        UserDAO userDAO = new UserDAO();
        String username;
        String password;

        username = inputUsername.getText();
        password = inputPassword.getText();

        if (username.equals("") || password.equals("")) {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Đăng ký thất bại",
                    "", "Vui lòng nhập đầy đủ thông tin!"
            );
            //return;
        } else if(checkInput(username) == false || checkInput(password) == false){
            createDialog(
                    Alert.AlertType.WARNING,
                    "Đăng ký thất bại",
                    "", "Vui lòng nhập đúng định dạng!"
            );
        }
        else {
            String signUpMsg = "signup;" + username + ";" + password;
            client_tmp.sendMessage(signUpMsg);

            String serverRes = client_tmp.readMessage();

            String signUpStatus = serverRes.split(";")[1];
            if (signUpStatus.equals("1")) {
                createDialog(
                        Alert.AlertType.INFORMATION,
                        "Đăng ký thành công",
                        "", "Vui lòng đăng nhập để tiếp tục!"
                );
                ViewUtils viewUtils = new ViewUtils();
                viewUtils.changeScene(event, LOGIN_VIEW);
            } else {
                createDialog(
                        Alert.AlertType.WARNING,
                        "Đăng ký thất bại",
                        "", "Tài khoản đã tồn tại!"
                );
            }
        }
    }
}