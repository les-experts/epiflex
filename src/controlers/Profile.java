package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.ArrayList;
import models.User;
import mappers.UserMapper;

/**
 * Gère le profil d'un utilisateur et la modification de ses informations.
 * @author Alexandre Vigneron
 */
public class Profile extends ControlerServlet {

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
		if(requete.getParameter("confirmForm")!=null){
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
		}
		else{
			session.removeAttribute("user");
			try{
				(requete.getRequestDispatcher("MarketPlace")).forward(requete ,reponse);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
    this.view(requete,reponse);
  }

	/**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
  public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		this.view(requete,reponse);
  }
}
