package mappers;
import models.User;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserMapper {

	private static UserMapper instance;
	private Connection conn;

	private UserMapper() throws SQLException{
		try{
			ConnectionDB connDB = ConnectionDB.getInstance();
			this.conn = connDB.getConnection();
		}
		catch(SQLException e){e.printStackTrace();}
	}

	public static UserMapper getInstance() {
		try{
			if (instance == null) {
					instance = new UserMapper();
			}
			return instance;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public User authentification(String username, String password_md5) {
		String req = "SELECT USR_id, ROL_label, USR_lastname, USR_firstname, USR_email, USR_address, USR_pseudo FROM User JOIN Role ON User.ROL_id=Role.ROL_id WHERE USR_pseudo = ? AND USR_password = ?;";
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setString(1,username);
			ps.setString(2,password_md5);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				User user = new User(rs.getInt("USR_id"), rs.getString("ROL_label"), rs.getString("USR_lastname"),rs.getString("USR_firstname"),rs.getString("USR_email"),rs.getString("USR_address"),rs.getString("USR_pseudo"));
				return user;
			}
			else
				return null;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}

	}

	// public static void main(String[] args) {
	// 	try{
	// 		UserMapper usrMap = UserMapper.getInstance();
	// 		User usr = usrMap.authentification("Zaneriis","130f9805895c3045eb2c854c119e84c3");
	// 		System.out.println(usr.getId() + " " + usr.getPseudo() + " " + usr.getRole());
	// 		usr = usrMap.authentification("Zaneris","130f9805895c3045eb2c854c119e84c3");
	// 		System.out.println(usr.getId() + " " + usr.getPseudo() + " " + usr.getRole());
	// 	}
	// 	catch(SQLException e){e.printStackTrace();}
	//
	// }
}
