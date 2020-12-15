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

  public static void main(String[] args){
  CommentMapper mapper = CommentMapper.getInstance();
  List<Comment> comm = mapper.getCommentByProduct(5);
  for (Comment val : comm) {
    System.out.println(val.getPseudo());
  }
  }
}
