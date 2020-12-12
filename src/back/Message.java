package back;
import java.io.*;
import java.text.*;
import java.util.*;

public class Message implements Serializable {
  private int id;
  private String text;
  private static SimpleDateFormat formatter = new SimpleDateFormat( " E d MMM yyyy ,H : m : s " , Locale.FRANCE ) ;
  private Date date;
  private User sender;
  private User receiver;

  public Message() {}

  public int getId() { return this.id; }

  public void setId(int id) { this.id=id; }

  public String getText() { return this.text; }

  public void setText(String text) { this.text=text; }

  public String getDate() { return formatter.format(date); }

  public void setDate(Date date) { this.date=date; }

  public User getReceiver() { return this.receiver; }

  public void setReceiver(User receiver) { this.receiver=receiver; }

  public User getSender() { return this.sender; }

  public void setSender(User sender) { this.sender=sender; }

}
