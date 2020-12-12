package back;
import java.io.*;
import java.text.*;
import java.util.*;

public class User implements Serializable {
  private int id;
  private String role;
  private String lastname;
  private String firstname;
  private String email;
  private String address;
  private String pseudo;

  public User() {}

  public int getId() { return this.id; }

  public void setId(int id) { this.id=id; }

  public String getRole() { return this.role; }

  public void setRole(String role) { this.role=role; }

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
