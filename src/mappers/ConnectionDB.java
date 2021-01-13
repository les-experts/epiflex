package mappers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Permet d'établir la connexion à la base de donnée SQLITE avec le driver jdbc.
 * Respecte le pattern singloton.
 * @author Alexandre Vigneron
 */
public class ConnectionDB {

    /**
     * Instance de ConnectionDB.
     */
    private static ConnectionDB instance;

    /**
     * Connection à la base.
     */
    private Connection conn;

    /**
     * Constructeur de la classe.
     */
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

    /**
     * Retourne la connection à la db.
     * @return Connection
     */
    public Connection getConnection(){
      return this.conn;
    }

    /**
     * Retourne une instance de ConnectionDB.
     * @return ConnectionDB
     */
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
