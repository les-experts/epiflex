package back;
import java.io.*;
import java.text.*;
import java.util.*;

public class Product implements Serializable {
  private int id;
  private String title;
  private String picture;
  private float price;
  private static SimpleDateFormat formatter = new SimpleDateFormat( " E d MMM yyyy ,H : m : s " , Locale.FRANCE ) ;
  private Date date = new Date () ;
  private int idUser;
  private Category category;

  public Product() {}

  public int getId() { return this.id; }

  public void setId(int id) { this.id=id; }

  public String getTitle() { return this.title; }

  public void setTitle(String title) { this.title=title; }

  public String getPicture() { return this.picture; }

  public void setPicture(String picture) { this.picture=picture; }

  public float getPrice() { return this.price; }

  public void setPrice(float price) { this.price=price; }

  public String getDate() { return formatter.format(date); }

  public int getIdUser() { return this.idUser; }

  public void setIdUser(int idUser) { this.idUser=idUser; }

  public Category getCategory() { return this.category; }

  public void setCategory(Category category) { this.category=category; }

}
