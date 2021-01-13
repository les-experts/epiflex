package mappers;
import models.Role;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Mapper permettant de faire le lien entre le site et la base de données pour les roles.
 * Respecte le pattern singleton.
 * @author Alexis Melo Da Silva
 */
public class RoleMapper {

  /**
   * Instance du mapper.
   */
  private static RoleMapper instance;

  /**
   * Connexion à la base via le driver.
   */
  private Connection conn;

  /**
   * Constructeur de la classe.
   */
  private RoleMapper() throws SQLException{
    try{
      ConnectionDB connDB = ConnectionDB.getInstance();
      this.conn = connDB.getConnection();
    }
    catch(SQLException e){e.printStackTrace();}
  }

  /**
   * Retourne une instance de mapper. Cette instance est unique.
   * @return RoleMapper instance du mapper
   */
  public static RoleMapper getInstance() {
    try{
      if (instance == null) {
          instance = new RoleMapper();
      }
      return instance;
    }catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retourne un role en fonction de l'id.
   * @param int idRol id du role
   * @return Role le role
   */
  public Role roleById(int idRol) {
		String req = "SELECT ROL_id, ROL_label FROM Role WHERE ROL_id = ?;";
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,idRol);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Role rol = new Role();
        rol.setId(rs.getInt("ROL_id"));
        rol.setLabel(rs.getString("ROL_label"));
				return rol;
			}
			else
				return null;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}

	}
}
