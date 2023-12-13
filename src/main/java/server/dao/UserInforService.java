package server.dao;

import java.sql.*;

import static server.constants.DatabaseConstants.*;

public class UserInforService {
    public static ResultSet getAllUser() throws SQLException {
        Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM user;");
        return preparedStatement.executeQuery();
    }

}
