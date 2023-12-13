package server.constants;

public class DatabaseConstants {
    private final String DATABASE = "jdbc:mysql://localhost:3306/chat_app";
    private final String USERNAME = "root";
    private final String PASSWORD = "24082002";

    public String getDATABASE() {
        return DATABASE;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
