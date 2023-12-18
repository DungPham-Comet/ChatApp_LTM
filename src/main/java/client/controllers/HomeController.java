package client.controllers;

import client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

import static client.ClientTemp.client_tmp;
import static server.constants.FxmlConstants.LOGIN_VIEW;

public class HomeController {

    @FXML
    private ImageView avaImage;

    @FXML
    private TextField findText;

    @FXML
    private Button logOutButton;

    @FXML
    void find(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        client_tmp.sendMessage("logout");
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, LOGIN_VIEW);
    }

}
