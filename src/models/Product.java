package models;
import java.io.*;
import java.text.*;
import java.util.*;

public class Product implements Serializable {
  private int id;
  private String title;
  private String picture;
  private float price;
  private String description;
  private static SimpleDateFormat formatter = new SimpleDateFormat( " E d MMM yyyy ,H : m : s " , Locale.FRANCE ) ;
  private Date date;
  private User user;
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

  public String getDescription() { return this.description; }

  public void setDescription(String description) { this.description=description; }

  public String getDate() { return formatter.format(date); }

  public void setDate(Date date) {
      this.date = date;
  }

  public User getUser() { return this.user; }

  public void setUser(User idUser) { this.user=user; }

  public Category getCategory() { return this.category; }

  public void setCategory(Category category) { this.category=category; }

}
