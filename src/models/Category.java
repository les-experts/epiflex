package models;
import java.io.*;
import java.text.*;
import java.util.*;

public class Category implements Serializable {
  private int id;
  private String label;

  public Category() {}

  public int getId() { return this.id; }

  public void setId(int id) { this.id=id; }

  public String getLabel() { return this.label; }

  public void setLabel(String label) { this.label=label; }

}
