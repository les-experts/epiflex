package mappers;
import models.Comment;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;
import java.sql.Date;


public class CommentMapper {

  private static CommentMapper instance;
  private Connection conn;

  private CommentMapper() throws SQLException{
    try{
      ConnectionDB connDB = ConnectionDB.getInstance();
      this.conn = connDB.getConnection();
    }
    catch(SQLException e){e.printStackTrace();}
  }

  public static CommentMapper getInstance() {
    try{
      if (instance == null) {
          instance = new CommentMapper();
      }
      return instance;
    }catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  public List<Comment> getCommentByProduct(int idProduct){
		String req = "SELECT COM_id, use.USR_pseudo, COM_rating, COM_content, COM_date FROM Comment com JOIN User use ON use.USR_id = com.USR_id WHERE PRO_id = ?;";
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,idProduct);
			ResultSet rs = ps.executeQuery();
      LinkedList<Comment> listComment = new LinkedList<Comment>();
			while(rs.next()){
				Comment comment = new Comment();
        comment.setId(rs.getInt("COM_id"));
        comment.setText(rs.getString("COM_content"));
        comment.setPseudo(rs.getString("USR_pseudo"));
        comment.setRating(rs.getInt("COM_rating"));
        //comment.setDate(new Date(rs.getDate("COM_date").getTime()));
        //comment.setDate(rs.getString("COM_date"));
        listComment.add(comment);
        //return comment;
			}
      return listComment;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
  }

  public int maxID(){
    String req = "SELECT max(COM_id) as nb from COMMENT";
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

  public void insert(int PRO_id, int USR_id, int COM_rating, String COM_content){
    int idcomm = this.maxID()+1;
    String req = "INSERT INTO Comment(COM_id,USR_id,COM_rating,COM_content,COM_date,PRO_id) VALUES(?,?,?,?,date('now'),?)";

    try{
      PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,idcomm);
			ps.setInt(2,USR_id);
			ps.setInt(3,COM_rating);
			ps.setString(4,COM_content);
			ps.setInt(5,PRO_id);
			ps.executeUpdate();
    }
		catch(SQLException e){
			e.printStackTrace();
			return;
		}
  }

  public static void main(String[] args){
  CommentMapper mapper = CommentMapper.getInstance();
  List<Comment> comm = mapper.getCommentByProduct(5);
  for (Comment val : comm) {
    System.out.println(val.getPseudo());
  }
  }
}
