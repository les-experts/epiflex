package mappers;
import models.Role;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoleMapper {

  private static RoleMapper instance;
  private Connection conn;

  private RoleMapper() throws SQLException{
    try{
      ConnectionDB connDB = ConnectionDB.getInstance();
      this.conn = connDB.getConnection();
    }
    catch(SQLException e){e.printStackTrace();}
  }

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
