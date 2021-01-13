package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.ArrayList;
import models.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import mappers.UserMapper;

/**
 * Permet de gérer l'insccription sur le site (création d'un nouveau compte).
 * @author Alexandre Vigneron
 */
public class Inscription extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		return "inscription/index.jsp";
	}

	/**
	 * Retourne une liste comprenant les chemins vers les css de la vue.
	 * @return ArrayList<String> liste des chemins vers les css
	 */
  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
		return LinkCss;
	}

	/**
	 * Retourne une liste comprenant les chemins vers les fichiers js de la vue.
	 * @return ArrayList<String> liste des chemins vers les fichiers js
	 */
	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		return srcJS;
	}

	/**
	 * Permet de gérer une requête en POST.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
  public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
    System.out.println("doPost de inscription");
    HttpSession session = requete.getSession();
		String pseudo = requete.getParameter("pseudo");
    String firstname = requete.getParameter("firstname");
    String lastname = requete.getParameter("lastname");
    String email = requete.getParameter("email");
    String address = requete.getParameter("address");
    String password = requete.getParameter("password");
    if(!pseudo.equals("") && !firstname.equals("") && !lastname.equals("") && !email.equals("") && !address.equals("") && !password.equals("")){
      UserMapper mapper = UserMapper.getInstance();
      try{
        password = wordToMD5(password);
      }
      catch(NoSuchAlgorithmException e){e.printStackTrace();}
      mapper.insertUser(pseudo, firstname, lastname, email, address, password);
      requete.setAttribute("errorForm",false);
      try{
				(requete.getRequestDispatcher("MarketPlace")).forward(requete ,reponse);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
    }
    else{
      requete.setAttribute("errorForm",true);
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
    System.out.println("doGet");
    this.view(requete,reponse);
  }

	/**
	 * Permet d'encrypter le mot de passe pour le stockage dans la base de données.
	 * @param le mot de passe à encrypter
	 * @return String le mot de passe encrypté
	 */
  public String wordToMD5(String password) throws NoSuchAlgorithmException {
    MessageDigest m = MessageDigest.getInstance("MD5");
    m.reset();
    m.update(password.getBytes());
    byte[] digest = m.digest();
    BigInteger bigInt = new BigInteger(1,digest);
    String hashtext = bigInt.toString(16);
    // Now we need to zero pad it if you actually want the full 32 chars.
    while(hashtext.length() < 32 ){
      hashtext = "0"+hashtext;
    }
    return hashtext;
  }



}
