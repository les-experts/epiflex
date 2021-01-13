package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;

import models.User;
import models.Product;

import mappers.MessageMapper;
import mappers.ProductMapper;

import java.io.IOException;

/**
 * Permet de gérer la messagerie.
 * @author Alexis Melo Da Silva, Leo Pacary
 */
public class Messagerie extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		return "messagerie/messagerie.jsp";
	}

  private void commonPostGetAttributes(HttpServletRequest requete, HttpServletResponse reponse) {

    User sender = (User) requete.getSession().getAttribute("user");
    if (sender == null) {
      retourProduit(requete, reponse, "/MarketPlace");
    }

    MessageMapper msgmap = MessageMapper.getInstance();
    List<User> usersTalkedBefore = msgmap.allUsersTalkedBefore(sender.getId());
    requete.setAttribute("usersTalkedBefore", usersTalkedBefore);
  }

	/**
	 * Permet de gérer une requête en POST.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
    HttpSession session = requete.getSession();

    User sender = (User) session.getAttribute("user");
    String proId = requete.getParameter("prodId");

    String urlErreur = "/MarketPlace";
    if (proId != null) {
      urlErreur = "/Product?id="+proId;
    }

    String message = requete.getParameter("message");

    MessageMapper msgmap = MessageMapper.getInstance();
    ProductMapper promap = ProductMapper.getInstance();

    if (message == "" || sender == null || message == null) {
      retourProduit(requete, reponse, urlErreur);
    }

    try {
      int proIdInt = Integer.parseInt(proId);
      Product pro = promap.productById(proIdInt);
      User receiver = pro.getUser();
      msgmap.insert(sender.getId(), receiver.getId(),  message);

    } catch(Exception e) {
      retourProduit(requete, reponse, urlErreur);
    }

    this.commonPostGetAttributes(requete, reponse );
		this.view(requete,reponse);
	}

	/**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		this.commonPostGetAttributes(requete, reponse );
		this.view(requete,reponse);
	}

	/**
	 * Retourne une liste comprenant les chemins vers les css de la vue.
	 * @return ArrayList<String> liste des chemins vers les css
	 */
  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("messagerie.css");
		return LinkCss;
	}

	/**
	 * Retourne une liste comprenant les chemins vers les fichiers js de la vue.
	 * @return ArrayList<String> liste des chemins vers les fichiers js
	 */
	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("messagerie.js");
		return srcJS;
	}

	/**
	 * Redirirection
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  private void retourProduit(HttpServletRequest request, HttpServletResponse reponse, String url){
    try {
      reponse.sendRedirect(request.getContextPath() + url); //ça s'est mal passé mal si on est arrivé ici
    } catch (IOException e) {
      System.out.println("Echec de la redirection");
    }
  }

}
