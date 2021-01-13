package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import models.Product;
import mappers.ProductMapper;

/**
 * Permet de gérer le système de panier du site.
 * @author Alexandre Vigneron, Alexis Melo Da Silva
 */
public class Panier extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		return "panier/index.jsp";
	}

	/**
	 * Retourne une liste comprenant les chemins vers les css de la vue.
	 * @return ArrayList<String> liste des chemins vers les css
	 */
  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("panier.css");
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
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
  public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
    HttpSession session = requete.getSession();

		// récupération ou création du panier
		List<Product> panier = (List<Product>)session.getAttribute("panier");
		if (panier ==null) {
			panier = new ArrayList<Product>();
		}

		String proId = requete.getParameter("prodId"); //on voit si on veut ajouter un produit au panier
		if (proId != null) { //on ajoute le produit au panier
			ProductMapper promap = ProductMapper.getInstance();

			Product newProduct = promap.productById(Integer.parseInt(proId));
			Boolean insert = true;

			for (Product pro : panier) {
				if (pro.getId() == newProduct.getId()) {
					insert = false;
					break;
				}
			}

			if (insert) {
				panier.add(newProduct);
			}
			session.setAttribute("product",null); // on enleve le produit qu'on viens d'ajouter à la session
		}

		session.setAttribute("panier", panier);
    this.view(requete,reponse);
  }

}
