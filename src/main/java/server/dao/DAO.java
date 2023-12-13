package server.dao;

import server.constants.DatabaseConstants;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    protected Connection con;

    public DAO() {
        DatabaseConstants DbCon = new DatabaseConstants();
        String jdbcURL = DbCon.getDATABASE();
        String jdbcUsername = DbCon.getUSERNAME();
        String jdbcPassword = DbCon.getPASSWORD(); //please change information to connect to DB
        System.out.println(jdbcURL);
        System.out.println(jdbcUsername);
        System.out.println(jdbcPassword);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection to database failed");
        }
    }
}
