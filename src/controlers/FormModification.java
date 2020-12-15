package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.ArrayList;
import models.User;
import mappers.UserMapper;

public class FormModification extends ControlerServlet {

	protected String getLink(){
		return "profile/index.jsp";
	}

  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("profile.css");
		return LinkCss;
	}

	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		return srcJS;
	}

  @Override
  public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
    HttpSession session = requete.getSession();
    User user = (User)session.getAttribute("user");
    System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + user.getId());
    String pseudo = requete.getParameter("pseudo");
    String firstname = requete.getParameter("firstname");
    String lastname = requete.getParameter("lastname");
    String email = requete.getParameter("email");
    String address = requete.getParameter("address");
    if(!user.getPseudo().equals(pseudo)){
      user.setPseudo(pseudo);
    }
    if(!user.getFirstname().equals(firstname)){
      user.setFirstname(firstname);
    }
    if(!user.getLastname().equals(lastname)){
      user.setLastname(lastname);
    }
    if(!user.getEmail().equals(email)){
      user.setEmail(email);
    }
    if(!user.getAddress().equals(address)){
      user.setAddress(address);
    }
    UserMapper mapper = UserMapper.getInstance();
    mapper.updateUser(user);
    session.setAttribute("user",user);
    this.view(requete,reponse);
  }

  @Override
  public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
    HttpSession session = requete.getSession();
    User user = (User)session.getAttribute("user");
    System.out.println(user.getId());
    String pseudo = requete.getParameter("pseudo");
    String firstname = requete.getParameter("firstname");
    String lastname = requete.getParameter("lastname");
    String email = requete.getParameter("email");
    String address = requete.getParameter("address");
    if(!user.getPseudo().equals(pseudo)){
      user.setPseudo(pseudo);
    }
    if(!user.getFirstname().equals(firstname)){
      user.setFirstname(firstname);
    }
    if(!user.getLastname().equals(lastname)){
      user.setLastname(lastname);
    }
    if(!user.getEmail().equals(email)){
      user.setEmail(email);
    }
    if(!user.getAddress().equals(address)){
      user.setAddress(address);
    }
    UserMapper mapper = UserMapper.getInstance();
    mapper.updateUser(user);
    session.setAttribute("user",user);
    this.view(requete,reponse);
  }
}
