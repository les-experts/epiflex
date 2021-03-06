package models;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Javabean représentant un utilisateur.
 * @author Alexandre Vigneron
 */
public class User implements Serializable {
  private int id;
  private Role role;
  private String lastname;
  private String firstname;
  private String email;
  private String address;
  private String pseudo;

  public User() { }

  public User(int id, Role role, String lastname, String firstname, String email, String address, String pseudo) {
    this.id=id;
    this.role=role;
    this.lastname=lastname;
    this.firstname=firstname;
    this.email=email;
    this.address=address;
    this.pseudo=pseudo;
  }

  public int getId() { return this.id; }

  public void setId(int id) { this.id=id; }

  public Role getRole() { return this.role; }

  public void setRole(Role role) { this.role=role; }

  public String getLastname() { return this.lastname; }

  public void setLastname(String lastname) { this.lastname=lastname; }

  public String getFirstname() { return this.firstname; }

  public void setFirstname(String firstname) { this.firstname=firstname; }

  public String getEmail() { return this.email; }

  public void setEmail(String email) { this.email=email; }

  public String getAddress() { return this.address; }

  public void setAddress(String address) { this.address=address; }

  public String getPseudo() { return this.pseudo; }

  public void setPseudo(String pseudo) { this.pseudo=pseudo; }

}
