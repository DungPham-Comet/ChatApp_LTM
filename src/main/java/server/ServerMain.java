package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

import static javafx.application.Application.launch;

public class ServerMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/start_server.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat Application Server");
        primaryStage.show();

//        // Start server in a new thread
//        new Thread(() -> {
//            try {
//                int port = 8080;
//                Server server = new Server(port);
//                server.start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}


