package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;
import models.Category;

import mappers.CategoryMapper;

/**
 * Gère le marketplace, page principale du site.
 * @author Alexis Melo Da Silva
 */
public class MarketPlace extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		return "marketplace/index.jsp";
	}

	/**
	 * Permet de gérer une requête en POST.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
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
	 * Ajoute les categories en attribut de session.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	private void commonPostGetAttributes(HttpServletRequest requete, HttpServletResponse reponse) {
		CategoryMapper catmap = CategoryMapper.getInstance();

		List<Category> categories = catmap.allCategories();

		requete.setAttribute("categories", categories);
	}

	/**
	 * Retourne une liste comprenant les chemins vers les css de la vue.
	 * @return ArrayList<String> liste des chemins vers les css
	 */
  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("marketplace.css");
		return LinkCss;
	}

	/**
	 * Retourne une liste comprenant les chemins vers les fichiers js de la vue.
	 * @return ArrayList<String> liste des chemins vers les fichiers js
	 */
	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("marketplace.js");
		return srcJS;
	}

}
