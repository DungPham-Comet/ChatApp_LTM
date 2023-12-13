package server.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    protected Connection con;

    public DAO() {
        String jdbcURL = "jdbc:mysql://localhost:3306/chat_app";
        String jdbcUsername = "root";
        String jdbcPassword = "24082002"; //please change information to connect to DB
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection to database failed");
        }
    }
}
