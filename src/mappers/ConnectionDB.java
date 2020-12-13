package mappers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static ConnectionDB instance;
    private Connection conn;

    private ConnectionDB() throws SQLException {
      try {
            // db parameters
            String url = "jdbc:sqlite:db/epiflex.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
      return this.conn;
    }

    public static ConnectionDB getInstance() throws SQLException {
      if (instance == null) {
          instance = new ConnectionDB();
      }
      else if (instance.getConnection().isClosed()) {
          instance = new ConnectionDB();
      }
      return instance;
    }

    public static void main(String[] args) {
      try{
        ConnectionDB databaseConnection = ConnectionDB.getInstance();
        databaseConnection.getConnection();
      }
      catch(SQLException e){e.printStackTrace();}

    }

}
