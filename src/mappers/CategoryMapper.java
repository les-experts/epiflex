package mappers;
import models.Category;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryMapper {

  private static CategoryMapper instance;
  private Connection conn;

  private CategoryMapper() throws SQLException{
    try{
      ConnectionDB connDB = ConnectionDB.getInstance();
      this.conn = connDB.getConnection();
    }
    catch(SQLException e){e.printStackTrace();}
  }

  public static CategoryMapper getInstance() {
    try{
      if (instance == null) {
          instance = new CategoryMapper();
      }
      return instance;
    }catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  public Category categoryById(int idCat) {
		String req = "SELECT CAT_id, CAT_label FROM Category WHERE CAT_id = ?;";
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,idCat);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Category cat = new Category();
        cat.setId(rs.getInt("CAT_id"));
        cat.setLabel(rs.getString("CAT_label"));
				return cat;
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
