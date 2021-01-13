package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import models.Product;
import mappers.ProductMapper;

public class SuppressionPanierServlet extends ControlerServlet {

	protected String getLink(){
		return "/Panier";
	}

  @Override
  public void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws IOException{

    // récupération ou création du panier
    List<Product> panier = (List<Product>)requete.getSession().getAttribute("panier");
    if (panier ==null) {
      reponse.sendRedirect(requete.getContextPath() + this.getLink());;
    }

    String productToDelete = requete.getParameter("productToDelete"); //on voit si on veut ajouter un produit au panier

    if (productToDelete != null) { //on ajoute le produit au panier
			for (Product pro : panier) {
				if (pro.getId() == Integer.parseInt(productToDelete)) {
					panier.remove(pro);
					break;
				}
			}
    }

    requete.getSession().setAttribute("panier", panier);
    reponse.sendRedirect(requete.getContextPath() + this.getLink());;

  }
}
