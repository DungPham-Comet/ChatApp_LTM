module mvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens client.controllers to javafx.fxml;
    exports client.controllers;

    exports client;
    opens client to javafx.fxml;

    exports server;
    opens server to javafx.fxml;

    opens server.controllers to javafx.fxml;
    exports server.controllers;


}