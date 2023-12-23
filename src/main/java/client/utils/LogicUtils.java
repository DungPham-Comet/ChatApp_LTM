package client.utils;

import javafx.scene.control.Alert;

public class LogicUtils {
    public static void createDialog(Alert.AlertType type, String title, String headerText, String contentText) {
        Alert warning = new Alert(type);
        warning.setTitle(title);
        warning.setHeaderText(headerText);
        warning.setContentText(contentText);
        warning.showAndWait();
    }

    public static boolean checkInput(String msg){
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) >= 'a' && msg.charAt(i) <= 'z' || msg.charAt(i) >= 'A' && msg.charAt(i) <= 'Z' || msg.charAt(i) >= '0' && msg.charAt(i) <= '9') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
