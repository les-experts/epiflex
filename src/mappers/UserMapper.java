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
		String req = "SELECT USR_id, ROL_id, USR_lastname, USR_firstname, USR_email, USR_address, USR_pseudo FROM User WHERE USR_pseudo = ? AND USR_password = ?;";
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setString(1,username);
			ps.setString(2,password_md5);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				RoleMapper rolmap = RoleMapper.getInstance();
				User user = new User(rs.getInt("USR_id"), rolmap.roleById(rs.getInt("ROL_id")), rs.getString("USR_lastname"),rs.getString("USR_firstname"),rs.getString("USR_email"),rs.getString("USR_address"),rs.getString("USR_pseudo"));
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

	public User userById(int id) {
		String req = "SELECT USR_id, ROL_id, USR_lastname, USR_firstname, USR_email, USR_address, USR_pseudo FROM user WHERE USR_id = ?;";
		//je fais epxres de pas prendre le mdp dans la requête pck ça sert à rien + faille de sécurité ??
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				User usr = new User();
				usr.setId(rs.getInt("USR_id"));

				RoleMapper rolmap = RoleMapper.getInstance();
				usr.setRole(rolmap.roleById(rs.getInt("ROL_id")));

				usr.setLastname(rs.getString("USR_lastname"));
				usr.setFirstname(rs.getString("USR_firstname"));
				usr.setEmail(rs.getString("USR_email"));
				usr.setAddress(rs.getString("USR_address"));
				usr.setPseudo(rs.getString("USR_pseudo"));
				return usr;
			}
			else
				return null;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public void updateUser(User user){
		String req = "UPDATE User SET USR_pseudo = ?, USR_firstname = ?, USR_lastname = ?, USR_email = ?, USR_address = ? WHERE USR_id = ?";
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setString(1,user.getPseudo());
			ps.setString(2,user.getFirstname());
			ps.setString(3,user.getLastname());
			ps.setString(4,user.getEmail());
			ps.setString(5,user.getAddress());
			ps.setInt(6,user.getId());
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public String getPassword(int id){
		String req = "SELECT USR_password FROM User WHERE USR_id = ?";
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("USR_password");
			}
			else
				return null;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public void updatePassword(int id, String newPasswordMD5){
		System.out.println("updatePassword");
		String req = "UPDATE User SET USR_password = ? WHERE USR_id = ?";
		try{
			PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setString(1,newPasswordMD5);
			ps.setInt(2,id);
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void insertUser(String pseudo, String firstname, String lastname, String email, String address, String password){
		int idUser = this.maxID()+1;
		String req = "INSERT INTO User(USR_id,ROL_id,USR_lastname,USR_firstname,USR_email,USR_address,USR_password,USR_pseudo) VALUES (?,?,?,?,?,?,?,?);";
		try{
      PreparedStatement ps = this.conn.prepareStatement(req);
			ps.setInt(1,idUser);
			ps.setInt(2,2);
			ps.setString(3,lastname);
			ps.setString(4,firstname);
			ps.setString(5,email);
			ps.setString(6,address);
			ps.setString(7,password);
			ps.setString(8,pseudo);
			ps.executeUpdate();
    }
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public int maxID(){
		String req = "SELECT max(USR_id) AS nb FROM User";
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

	public static void main(String[] args) {
			UserMapper usrMap = UserMapper.getInstance();
			User usr = usrMap.authentification("Zaneriis","130f9805895c3045eb2c854c119e84c3");
			usr = usrMap.authentification("Zaneris","130f9805895c3045eb2c854c119e84c3");
	}
}
