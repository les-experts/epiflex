package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;
import models.Product;
import mappers.ProductMapper;
import java.util.OptionalInt;

public class ListProducts extends ControlerServlet {

    public OptionalInt tryParse(String value) {
      try {
          return OptionalInt.of(Integer.parseInt(value));
      } catch (NumberFormatException e) {
          return OptionalInt.empty();
      }
    }

  	protected String getLink(){
  		return "marketplace/products.jsp";
  	}

  	private void commonPostGetAttributes(HttpServletRequest requete, HttpServletResponse reponse) {
  		ProductMapper pdtmap = ProductMapper.getInstance();

      OptionalInt prixMax = tryParse(requete.getParameter("prix_max"));
      OptionalInt prixMin = tryParse(requete.getParameter("prix_min"));
      String keywords = requete.getParameter("mots_cles");
      OptionalInt catid = tryParse(requete.getParameter("categorie"));

      List<Product> products = pdtmap.allProducts();

      if (prixMax.isPresent()) {

        for (Product p : List.copyOf(products)) {
          if (p.getPrice() > prixMax.getAsInt()) {
            products.remove(p);
          }
        }
      }
      if (prixMin.isPresent()) {

        for (Product p : List.copyOf(products)) {
          if (p.getPrice() < prixMin.getAsInt()) {
            products.remove(p);
          }
        }
      }
      if (keywords != null) {
        keywords = keywords.toLowerCase(); //case insensitive matching

        for (Product p : List.copyOf(products)) {
          if (!p.getTitle().toLowerCase().contains(keywords) && !p.getDescription().toLowerCase().contains(keywords)) {
            products.remove(p);
          }
        }
      }
      if (catid.isPresent()) {

        if (catid.getAsInt() != 0) { // car 0 = "Toutes les catégories"
          for (Product p : List.copyOf(products)) {
            if (p.getCategory().getId() != catid.getAsInt()) {
              products.remove(p);
            }
          }
        }

      }

  		requete.setAttribute("products", products);
  	}

    @Override
    protected void view(HttpServletRequest requete, HttpServletResponse reponse){
      try{
          ServletContext sc = this.getServletContext();
          RequestDispatcher rd = sc.getRequestDispatcher("/src/vue/html_jsp/marketplace/products.jsp");
          rd.include(requete, reponse);
      }
      catch(Exception e){
        System.out.println(e);
      }
    }

  	@Override
  	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
  		this.commonPostGetAttributes(requete, reponse);
  		this.view(requete,reponse);
  	}

  	@Override
  	public void doGet(HttpServletRequest requete, HttpServletResponse reponse) {
  		this.commonPostGetAttributes(requete, reponse);
  		this.view(requete, reponse);
  	}
}
