package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.ArrayList;
import models.User;
import mappers.UserMapper;
import java.security.NoSuchAlgorithmException;

public class PasswordModification extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		return "profile/index.jsp";
	}

	/**
	 * Retourne une liste comprenant les chemins vers les css de la vue.
	 * @return ArrayList<String> liste des chemins vers les css
	 */
  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("profile.css");
		return LinkCss;
	}

	/**
	 * Retourne une liste comprenant les chemins vers les fichiers js de la vue.
	 * @return ArrayList<String> liste des chemins vers les fichiers js
	 */
	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("profile.js");
		return srcJS;
	}

	/**
	 * Permet de gérer une requête en POST.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
  public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
    HttpSession session = requete.getSession();
    User user = (User)session.getAttribute("user");
    AuthenticationHandler authHandler = new AuthenticationHandler(requete);
    String oldPassword = requete.getParameter("oldPassword");
    String newPassword = requete.getParameter("newPassword");
    System.out.println(oldPassword);
    System.out.println(newPassword);
    try{
      String oldPasswordReq = authHandler.wordToMD5(oldPassword);
      UserMapper mapper = UserMapper.getInstance();
      String oldPasswordDB = mapper.getPassword(user.getId());
      if(oldPasswordDB.equals(oldPasswordReq)){
        mapper.updatePassword(user.getId(), authHandler.wordToMD5(newPassword));
        requete.setAttribute("errorPassword",false);
      }
      else{
        requete.setAttribute("errorPassword",true);
      }
      this.view(requete,reponse);
    }
    catch(Exception e){e.printStackTrace();}

  }

	/**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
  public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
  }
}
