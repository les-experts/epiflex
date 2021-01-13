package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import models.Product;
import mappers.ProductMapper;

public class Panier extends ControlerServlet {

	protected String getLink(){
		return "panier/index.jsp";
	}

  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("panier.css");
		return LinkCss;
	}

	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		return srcJS;
	}

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
