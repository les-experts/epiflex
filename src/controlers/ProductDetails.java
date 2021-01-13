package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;

import models.Product;
import models.User;
import mappers.ProductMapper;

import java.io.IOException;

/**
 * Gère la page de détails d'un produit.
 * @author Alexandre Vigneron
 */
public class ProductDetails extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		return "productdetails/index.jsp";
	}

	/**
	 * Retourne une liste comprenant les chemins vers les css de la vue.
	 * @return ArrayList<String> liste des chemins vers les css
	 */
  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
		LinkCss.add("productdetails.css");
    LinkCss.add("comment.css");
		return LinkCss;
	}

	/**
	 * Retourne une liste comprenant les chemins vers les fichiers js de la vue.
	 * @return ArrayList<String> liste des chemins vers les fichiers js
	 */
	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("productdetails.js");
		srcJS.add("comment.js");
		return srcJS;
	}

	/**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		try {
			int id = Integer.parseInt(requete.getParameter("id"));
			ProductMapper pmap = ProductMapper.getInstance();

			Product pro = pmap.productById(id);

			if (pro != null) {

				requete.setAttribute("product", pro);
		    AuthenticationHandler handler = new AuthenticationHandler(requete);
		    User user = handler.loadUser();
				if(user != null && user.getRole().getId() == 1){
					requete.setAttribute("admin", true);
				}
				else{
					requete.setAttribute("admin", false);
				}
				this.view(requete,reponse);
				return; //on arrête la méthode sinon ça continue
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			reponse.sendRedirect(requete.getContextPath() + "/MarketPlace"); //ça s'est mal passé si on est arrivé ici
		} catch (IOException e) {
			System.out.println("Echec de la redirection");
		}

	}

}
