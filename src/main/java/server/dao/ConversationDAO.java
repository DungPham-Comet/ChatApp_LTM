package server.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConversationDAO extends DAO{
    public ConversationDAO(){
        super();
    }

    public void addUserToConversation(int UserID, int ConID, int RoleID){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO user_conversation(user_id, conversation_id, role_id)\n"
                    + "VALUES(?,?,?)");
            preparedStatement.setInt(1, UserID);
            preparedStatement.setInt(2, ConID);
            preparedStatement.setInt(3, RoleID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}