package models;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Javabean représentant un commentaire.
 * @author Leo Pacary
 */
public class Comment implements Serializable {
  private int id;
  private String pseudo;
  private int rating;
  private String text;
  private static SimpleDateFormat formatter = new SimpleDateFormat( " E d MMM yyyy ,H : m : s " , Locale.FRANCE ) ;
  private Date date;

  public Comment() {}

  public int getId() { return this.id; }

  public void setId(int id) { this.id=id; }

  public String getText() { return this.text; }

  public void setText(String text) { this.text=text; }

  public String getPseudo() { return this.pseudo; }

  public void setPseudo(String pseudo) { this.pseudo=pseudo; }

  public int getRating() { return this.rating; }

  public void setRating(int rating) { this.rating=rating; }

  public String getDate() { return formatter.format(date); }

  public void setDate(Date date) {
      this.date = date;
  }

}
