package models;
import java.io.*;
import java.text.*;
import java.util.*;

public class Comment implements Serializable {
  private int id;
  private String pseudo;
  private int rating;
  private String text;

  public Comment() {}

  public int getId() { return this.id; }

  public void setId(int id) { this.id=id; }

  public String getText() { return this.text; }

  public void setText(String text) { this.text=text; }

  public String getPseudo() { return this.pseudo; }

  public void setPseudo(String pseudo) { this.pseudo=pseudo; }

  public int getRating() { return this.rating; }

  public void setRating(int rating) { this.rating=rating; }

}
