package client.controllers;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Label loginStatusLabel;

    @FXML
    private TextField passwordTextField;


    @FXML
    private TextField usernameTextField;

    @FXML
    void login(ActionEvent event) throws SQLException {

        Client client = new Client("127.0.0.1", 8080);
        //client.start();

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        client.sendUsername(username);
        client.sendPassword(password);

        String loginStatus = client.readMessage();
        loginStatusLabel.setText(loginStatus);
    }

}
