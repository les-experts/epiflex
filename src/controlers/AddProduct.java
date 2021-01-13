package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import models.User;
import models.Category;
import models.Product;
import mappers.CategoryMapper;
import mappers.ProductMapper;

/**
 * AddProduct est la classe permettant de gérer l'ajout d'un produit.
 * Hérite de la classe abstraite ControlerServlet.
 * @author Alexandre Vigneron
 */
public class AddProduct extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		return "addproduct/index.jsp";
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
    srcJS.add("addproduct.js");
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
    String title = requete.getParameter("title");
    String price = requete.getParameter("price");
    int category = Integer.parseInt(requete.getParameter("categorie"));
    String description = requete.getParameter("description");
    if(title != null && this.isDouble(price) && description!=null){
        ProductMapper productMapper = ProductMapper.getInstance();
        if (productMapper!=null){
          productMapper.insertProduct(title, description, user.getId(),Double.parseDouble(price), category);
        }
        requete.setAttribute("errorForm",false);
    }
    else{
      requete.setAttribute("errorForm",true);
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
    System.out.println("doGet");
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
	 * Permet de vérifier si un String est un double.
	 * @param str String à vérifier
	 * @return boolean : true si str est un double
	 */
  public static boolean isDouble(String str) {
    try{
      Double.parseDouble(str);
      return true;
    }catch(NumberFormatException e){
      return false;
    }
  }
}
