package client.controllers;

import client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.User;
import server.dao.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

import static client.utils.LogicUtils.createDialog;
import static server.constants.FxmlConstants.LOGIN_VIEW;

public class SignUpController {
    @FXML
    public Label signUpStatus;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;

    @FXML
    private Button returnSignInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Label validate;

    @FXML
    void returnSignIn(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, LOGIN_VIEW);
    }

    @FXML
    void signUp(ActionEvent event) throws SQLException {
        UserDAO userDAO = new UserDAO();

        String username = inputUsername.getText();
        String password = inputPassword.getText();

        if(username.trim().equals("") || password.trim().equals("")) {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Khoan nào cán bộ",
                    "", "Vui lòng nhập đủ username và password!"
            );
        } else {
            User user = new User(username, password);
            userDAO.addUserWithUsernamePassword(user);
            signUpStatus.setText("Sign up successfully!");
        }
    }
}