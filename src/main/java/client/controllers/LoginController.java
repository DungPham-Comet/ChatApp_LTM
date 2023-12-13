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
import static server.constants.FxmlConstants.SIGNUP_VIEW;

public class LoginController {

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
    void login(ActionEvent event) throws SQLException {
        client_tmp = new Client("127.0.0.1", 8080);
        //client.start();

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        client_tmp.sendUsername(username);
        client_tmp.sendPassword(password);

        String loginStatus = client_tmp.readMessage();
        loginStatusLabel.setText(loginStatus);
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, SIGNUP_VIEW);
    }

}
