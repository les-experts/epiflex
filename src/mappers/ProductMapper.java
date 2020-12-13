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


public class ProductMapper {

  private static ProductMapper instance;
  private Connection conn;

  private ProductMapper() throws SQLException{
    try{
      ConnectionDB connDB = ConnectionDB.getInstance();
      this.conn = connDB.getConnection();
    }
    catch(SQLException e){e.printStackTrace();}
  }

  public static ProductMapper getInstance() {
    try{
      if (instance == null) {
          instance = new ProductMapper();
      }
      return instance;
    }catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  public List<Product> allProducts() {
    String req = "SELECT PRO_id, PRO_title, PRO_picture, PRO_description, USR_id, PRO_price, CAT_id, PRO_date FROM Product";
    try{
      PreparedStatement ps = this.conn.prepareStatement(req);
      ResultSet rs = ps.executeQuery();
      List<Product> allProducts = new ArrayList<Product>();

      while(rs.next()){
        Product product = new Product();
        product.setId(rs.getInt("PRO_id"));
        product.setTitle(rs.getString("PRO_title"));
        product.setPicture(rs.getString("PRO_picture"));
        product.setDescription(rs.getString("PRO_description"));

        UserMapper userMap = UserMapper.getInstance();
        product.setUser(userMap.userById(rs.getInt("USR_id"))); //!!!!!!!!!

        product.setPrice(rs.getFloat("PRO_price"));

        CategoryMapper catMap = CategoryMapper.getInstance();
        product.setCategory(catMap.categoryById(rs.getInt("CAT_id")));

        product.setDate(rs.getDate("PRO_date"));
        allProducts.add(product);
      }
      return allProducts;
    }
    catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }
}
