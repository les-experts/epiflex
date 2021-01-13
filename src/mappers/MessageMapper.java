package mappers;
import models.Message;
import models.User;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.io.*;

public class MessageMapper {

  private static MessageMapper instance;
  private Connection conn;

  private MessageMapper() throws SQLException{
    try{
      ConnectionDB connDB = ConnectionDB.getInstance();
      this.conn = connDB.getConnection();
    }
    catch(SQLException e){e.printStackTrace();}
  }

  public static MessageMapper getInstance() {
    try{
      if (instance == null) {
          instance = new MessageMapper();
      }
      return instance;
    }catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  public int maxID(){
    String req = "SELECT max(MSG_id) AS nb FROM Message";
    try{
      ResultSet rs = this.conn.createStatement().executeQuery(req);
      rs.next();
      return rs.getInt("nb");
    }
    catch(SQLException e){
      e.printStackTrace();
      return 0;
    }
  }

  public void insert(int USR_sender, int USR_receiver, String MSG_content){

    int idmess = this.maxID()+1;
    String req = "INSERT INTO Message(MSG_id, USR_sender, USR_receiver, MSG_content, MSG_date) VALUES(?,?,?,?,date('now'))";

    try{
      PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,idmess);
			ps.setInt(2,USR_sender);
			ps.setInt(3,USR_receiver);
			ps.setString(4,MSG_content);
			ps.executeUpdate();
    }
		catch(SQLException e){
			e.printStackTrace();
			return;
		}
  }

  public List<User> allUsersTalkedBefore(int sender) {
    String req = "SELECT USR_receiver from Message where USR_sender = ?";

    try {
      PreparedStatement ps = this.conn.prepareStatement(req);
      ps.setInt(1, sender);
      ResultSet rs = ps.executeQuery();

      List<User> allUsers = new ArrayList<User>();

      while(rs.next()) {
        UserMapper usmap = UserMapper.getInstance();
        int idReceiver = rs.getInt("USR_receiver");
        User receiver = usmap.userById(idReceiver);

        allUsers.add(receiver);
      }
      return allUsers;

    } catch(SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<Message> allMessagesReceived(int receiver) {
    String req = "SELECT MSG_id, USR_sender, USR_receiver, MSG_content, MSG_date from Message where USR_receiver = ?";

    try {
      PreparedStatement ps = this.conn.prepareStatement(req);
      ps.setInt(1, receiver);
      ResultSet rs = ps.executeQuery();

      List<Message> allMessages = new ArrayList<Message>();

      while(rs.next()) {
        Message msg = new Message();
        msg.setId(rs.getInt("MSG_id"));

        UserMapper usmap = UserMapper.getInstance();

        User receiverObject = usmap.userById(receiver);

        int idSender = rs.getInt("USR_sender");
        User sender = usmap.userById(idSender);

        msg.setSender(sender);
        msg.setReceiver(receiverObject);

        allMessages.add(msg);
      }
      return allMessages;

    } catch(SQLException e) {
      e.printStackTrace();
      return null;
    }

  }

  public List<Message> allMessagesBetweenUsers(int receiver, int sender) {
    String req = "SELECT MSG_id, USR_sender, USR_receiver, MSG_content, MSG_date from Message where USR_receiver = ? and USR_sender = ?";

    try {
      PreparedStatement ps = this.conn.prepareStatement(req);
      ps.setInt(1, receiver);
      ps.setInt(2, sender);
      ResultSet rs = ps.executeQuery();

      List<Message> allMessages = new ArrayList<Message>();

      while(rs.next()) {
        Message msg = new Message();
        msg.setId(rs.getInt("MSG_id"));

        UserMapper usmap = UserMapper.getInstance();

        int idReceiver = rs.getInt("USR_receiver");
        User receiverObject = usmap.userById(idReceiver);

        int idSender = rs.getInt("USR_sender");
        User senderObject = usmap.userById(idSender);

        msg.setSender(senderObject);
        msg.setReceiver(receiverObject);

        allMessages.add(msg);
      }
      return allMessages;

    } catch(SQLException e) {
      e.printStackTrace();
      return null;
    }

  }
}
