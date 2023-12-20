package client.controllers;

import client.Client;
import client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static client.ClientTemp.client_tmp;
import static server.constants.FxmlConstants.HOME_VIEW;
import static server.constants.FxmlConstants.SIGNUP_VIEW;

public class LoginController {

    public LoginController(){
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
        client_tmp.sendMessage("login");
        if (client_tmp.readMessage().equals("login accepted")) {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();

            client_tmp.sendUsername(username);
            client_tmp.sendPassword(password);

            String loginStatus = client_tmp.readMessage();
            //loginStatusLabel.setText(loginStatus);

            if (loginStatus.equals("1")) {
                ViewUtils viewUtils = new ViewUtils();
                viewUtils.changeScene(event, HOME_VIEW);
            } else if (loginStatus.equals("2")) {
                loginStatusLabel.setText("Account is being logged in from other device");
            } else {
                loginStatusLabel.setText("Login Fail");
            }
        } else {
            loginStatusLabel.setText("Login Fail");
        }
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        //client_tmp = new Client("127.0.0.1", 8080);
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, SIGNUP_VIEW);
    }

}
