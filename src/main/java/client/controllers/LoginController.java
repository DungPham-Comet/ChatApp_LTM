package client.controllers;

import client.Client;
import client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static client.ClientTemp.client_tmp;
import static client.utils.HandleRecv.recvContent;
import static client.utils.LogicUtils.checkInput;
import static client.utils.LogicUtils.createDialog;
import static server.constants.FxmlConstants.HOME_VIEW;
import static server.constants.FxmlConstants.SIGNUP_VIEW;

public class LoginController {

    public LoginController() {
        client_tmp = new Client("127.0.0.1", 8080);
    }

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Label loginStatusLabel;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        //client_tmp = new Client("127.0.0.1", 8080);
        //client.start();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username.equals("") || password.equals("")) {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Đăng nhập thất bại",
                    "", "Vui lòng nhập đầy đủ thông tin!"
            );
        } else if(checkInput(username) == false || checkInput(password) == false){
            createDialog(
                    Alert.AlertType.WARNING,
                    "Đăng nhập thất bại",
                    "", "Vui lòng nhập đúng định dạng!"
            );
        }
        else {
            String loginMsg = "login;" + username + ";" + password;
            client_tmp.sendMessage(loginMsg);

            String serverRes = client_tmp.readMessage();

            String loginStatus = recvContent(serverRes);
            if (loginStatus.equals("1")) {
                ViewUtils viewUtils = new ViewUtils();
                viewUtils.changeScene(event, HOME_VIEW);
            } else if (loginStatus.equals("2")) {
                //loginStatusLabel.setText("Account is being logged in from other device");
                createDialog(
                        Alert.AlertType.WARNING,
                        "Đăng nhập thất bại",
                        "", "Tài khoản đang được đăng nhập trên thiết bị khác!");
            } else {
                //loginStatusLabel.setText("Login Fail");
                createDialog(
                        Alert.AlertType.WARNING,
                        "Đăng nhập thất bại",
                        "", "Sai tên đăng nhập hoặc mật khẩu!");
            }
        }

    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        //client_tmp = new Client("127.0.0.1", 8080);
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, SIGNUP_VIEW);
    }

}
