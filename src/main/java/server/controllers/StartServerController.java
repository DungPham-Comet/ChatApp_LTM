package server.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import server.Server;

public class StartServerController {

    @FXML
    private Button startButton;

    @FXML
    private Label startNotiLabel;

    @FXML
    void startServer(ActionEvent event) {
        int port = 8080;
        Server server = new Server(port);
        server.start();
    }

}
