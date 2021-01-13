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

/**
 * Mapper permettant de faire le lien entre le site et la base de données pour les commentaires.
 * Respecte le pattern singleton.
 * @author Leo Pacary
 */
public class CommentMapper {

  /**
   * Instance du mapper.
   */
  private static CommentMapper instance;

  /**
   * Connexion à la base via le driver.
   */
  private Connection conn;

  /**
   * Constructeur de la classe.
   */
  private CommentMapper() throws SQLException{
    try{
      ConnectionDB connDB = ConnectionDB.getInstance();
      this.conn = connDB.getConnection();
    }
    catch(SQLException e){e.printStackTrace();}
  }

  /**
   * Retourne une instance de mapper. Cette instance est unique.
   * @return CommentMapper instance du mapper
   */
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

  /**
   * Retourne les commentaires d'un produit.
   * @param int idProduct id du produit
   * @return List<Comment> la liste des commentaires sur le produit
   */
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

  /**
   * Retourne l'id maximum compris dans la table.
   * @return int id id max
   */
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

  /**
   * Permet d'insérer un commentaire.
   * @param int PRO_id id du produit
   * @param int USR_id id de l'User
   * @param int COM_rating notation du produit
   * @param String COM_content le texte du commentaire
   */
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

  /**
   * Permet de vérifier si un utilisateur a déja posté un commentaire pour ce produit.
   * @param int idProduct id du produit concerné
   * @param int idUser id de l'utilisateur
   * @return boolean true si l'utilisateur a déja posté un commentaire pour ce produit
   */
  public boolean hasAlreadyPost(int idProduct, int idUser){
    String req = "SELECT count(*) as nb from COMMENT where PRO_id = ? AND	USR_id = ?";
    try{
      PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,idProduct);
			ps.setInt(2,idUser);
			ResultSet rs = ps.executeQuery();
      rs.next();
      return rs.getInt("nb") != 0;
    }
    catch(SQLException e){
      e.printStackTrace();
      return false;
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
