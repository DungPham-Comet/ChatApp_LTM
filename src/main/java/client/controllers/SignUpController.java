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
    void signUp(ActionEvent event) throws SQLException {
        //client_tmp = new Client("127.0.0.1", 8080);
        client_tmp.sendMessage("signup");

        if (client_tmp.readMessage().equals("signup accepted")) {
            UserDAO userDAO = new UserDAO();
            String username;
            String password;

            username = inputUsername.getText();
            password = inputPassword.getText();

            if (username.equals("") || password.equals("")) {
//                createDialog(
//                        Alert.AlertType.WARNING,
//                        "Đăng ký thất bại",
//                        "", "Vui lòng nhập đầy đủ thông tin!"
//                );
                //return;
            } else {
                client_tmp.sendUsername(username);
                client_tmp.sendPassword(password);

                String signUpStatus = client_tmp.readMessage();

                if (signUpStatus.equals("1")) {
                    createDialog(
                            Alert.AlertType.INFORMATION,
                            "Đăng ký thành công",
                            "", "Vui lòng đăng nhập để tiếp tục!"
                    );
                } else {
                    createDialog(
                            Alert.AlertType.WARNING,
                            "Đăng ký thất bại",
                            "", "Username này đã tồn tại!"
                    );
                }
            }


        } else {
            signUpStatusLabel.setText("Sign Up Fail");
        }
    }
}