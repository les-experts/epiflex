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
			System.out.println("coucou");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
			UserMapper usrMap = UserMapper.getInstance();
			User usr = usrMap.authentification("Zaneriis","130f9805895c3045eb2c854c119e84c3");
			System.out.println(usr.getId() + " " + usr.getPseudo() + " " + usr.getRole());
			usr = usrMap.authentification("Zaneris","130f9805895c3045eb2c854c119e84c3");
			System.out.println(usr.getId() + " " + usr.getPseudo() + " " + usr.getRole());

	}
}
