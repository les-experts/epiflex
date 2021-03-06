package mappers;
import models.Product;
import models.User;
import models.Category;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

/**
 * Mapper permettant de faire le lien entre le site et la base de données pour les produits.
 * Respecte le pattern singleton.
 * @author Alexis Melo Da Silva, Alexandre Vigneron
 */
public class ProductMapper {

  /**
   * Instance du mapper.
   */
  private static ProductMapper instance;

  /**
   * Connexion à la base via le driver.
   */
  private Connection conn;

  /**
   * Constructeur de la classe.
   */
  private ProductMapper() throws SQLException{
    try{
      ConnectionDB connDB = ConnectionDB.getInstance();
      this.conn = connDB.getConnection();
    }
    catch(SQLException e){e.printStackTrace();}
  }

  /**
   * Retourne une instance de mapper. Cette instance est unique.
   * @return ProductMapper instance du mapper
   */
  public static ProductMapper getInstance() {
    try{
      if (instance == null) {
          instance = new ProductMapper();
      }
      return instance;
    }catch(SQLException e){
      System.out.println("error getInstance");
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retourne tous les produits.
   * @return List<Product> liste des produits
   */
  public List<Product> allProducts() {
    String req = "SELECT PRO_id, PRO_title, PRO_description, USR_id, PRO_price, CAT_id, PRO_date FROM Product;";

    try{
      PreparedStatement ps = this.conn.prepareStatement(req);
      ResultSet rs = ps.executeQuery();
      List<Product> allProducts = new ArrayList<Product>();

      while(rs.next()){
        Product product = new Product();
        product.setId(rs.getInt("PRO_id"));
        product.setTitle(rs.getString("PRO_title"));
        //product.setPicture(rs.getString("PRO_picture"));
        product.setDescription(rs.getString("PRO_description"));

        UserMapper userMap = UserMapper.getInstance();
        product.setUser(userMap.userById(rs.getInt("USR_id"))); //!!!!!!!!!

        product.setPrice(rs.getFloat("PRO_price"));

        CategoryMapper catMap = CategoryMapper.getInstance();
        product.setCategory(catMap.categoryById(rs.getInt("CAT_id")));

        //product.setDate(rs.getDate("PRO_date"));
        allProducts.add(product);
      }
      return allProducts;
    }
    catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retourne tous les produits d'un utilisateur.
   * @param int idUrs id de l'utilisateur
   * @return List<Product> liste des produits
   */
  public List<Product> productsByUser(int idUrs) {
    String req = "SELECT PRO_id, PRO_title, PRO_description, USR_id, PRO_price, CAT_id, PRO_date FROM Product WHERE USR_id = ?;";
    try{
      PreparedStatement ps = this.conn.prepareStatement(req);
      ps.setInt(1,idUrs);
      ResultSet rs = ps.executeQuery();

      List<Product> allProducts = new ArrayList<Product>();
      while(rs.next()){
        Product pro = new Product();
        pro.setId(rs.getInt("PRO_id"));
        pro.setTitle(rs.getString("PRO_title"));
        //product.setPicture(rs.getString("PRO_picture"));
        pro.setDescription(rs.getString("PRO_description"));

        UserMapper userMap = UserMapper.getInstance();
        pro.setUser(userMap.userById(rs.getInt("USR_id"))); //!!!!!!!!!

        pro.setPrice(rs.getFloat("PRO_price"));

        CategoryMapper catMap = CategoryMapper.getInstance();
        pro.setCategory(catMap.categoryById(rs.getInt("CAT_id")));
        allProducts.add(pro);
      }
      return allProducts;
    }
    catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retourne un produit en fonction de l'id.
   * @param int idPro id du produit
   * @return Product le produit
   */
  public Product productById(int idPro) {
    String req = "SELECT PRO_id, PRO_title, PRO_description, USR_id, PRO_price, CAT_id, PRO_date FROM Product WHERE PRO_id = ?;";
    try{
      PreparedStatement ps = this.conn.prepareStatement(req);
      ps.setInt(1,idPro);
      ResultSet rs = ps.executeQuery();
      if(rs.next()){
        Product pro = new Product();
        pro.setId(rs.getInt("PRO_id"));
        pro.setTitle(rs.getString("PRO_title"));
        //product.setPicture(rs.getString("PRO_picture"));
        pro.setDescription(rs.getString("PRO_description"));

        UserMapper userMap = UserMapper.getInstance();
        pro.setUser(userMap.userById(rs.getInt("USR_id"))); //!!!!!!!!!

        pro.setPrice(rs.getFloat("PRO_price"));

        CategoryMapper catMap = CategoryMapper.getInstance();
        pro.setCategory(catMap.categoryById(rs.getInt("CAT_id")));
        return pro;
      }
      else
        return null;
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
    String req = "SELECT max(PRO_id) AS nb FROM Product";
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
   * @param String title titre du produit
   * @param String description description du produit
   * @param int userId id du vendeur
   * @param double price prix du produit
   * @param int catId catégorie du produit
   */
    public void insertProduct(String title, String description, int userId, double price, int catId){
      int idProduct = this.maxID()+1;
      String req = "INSERT INTO Product(PRO_id, PRO_title, PRO_description, USR_id, PRO_price, CAT_id, PRO_date) VALUES(?,?,?,?,?,?,date('now'))";
      try{
        PreparedStatement ps = this.conn.prepareStatement(req);
  			ps.setInt(1,idProduct);
  			ps.setString(2,title);
  			ps.setString(3,description);
  			ps.setInt(4,userId);
        ps.setDouble(5,price);
  			ps.setInt(6,catId);
  			ps.executeUpdate();
      }
  		catch(SQLException e){
  			e.printStackTrace();
  		}
    }

    public void deleteProduct(int pro_id){
      String req = "Delete from Product where PRO_id = ?";
      try{
        PreparedStatement ps = this.conn.prepareStatement(req);
  			ps.setInt(1,pro_id);
  			ps.executeUpdate();
      }
  		catch(SQLException e){
  			e.printStackTrace();
  		}
    }
}
