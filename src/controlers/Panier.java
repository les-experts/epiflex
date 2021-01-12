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
    List<Integer> idsPanier = (List<Integer>)session.getAttribute("panier");
    List<Product> products = new ArrayList<Product>();
    if(idsPanier!=null){
      ProductMapper pmap = ProductMapper.getInstance();
      for(Integer i: idsPanier)
        products.add(pmap.productById(i));
    }
    for(Product p: products)
      System.out.println(p.getTitle());
    session.setAttribute("products",products);
    this.view(requete,reponse);
  }

  public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
    HttpSession session = requete.getSession();
    List<Integer> idsPanier = (List<Integer>)session.getAttribute("panier");
  }
}
