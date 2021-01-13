package mappers;
import models.Message;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;
import java.sql.Date;

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
}
