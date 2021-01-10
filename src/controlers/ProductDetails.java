package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;

import models.Product;
import mappers.ProductMapper;

import java.io.IOException;

public class ProductDetails extends ControlerServlet {

	protected String getLink(){
		return "productdetails/index.jsp";
	}

  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
		LinkCss.add("productdetails.css");
    LinkCss.add("comment.css");
		return LinkCss;
	}

	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("productdetails.js");
		srcJS.add("comment.js");
		return srcJS;
	}

	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		try {
			int id = Integer.parseInt(requete.getParameter("id"));
			ProductMapper pmap = ProductMapper.getInstance();

			Product pro = pmap.productById(id);

			if (pro != null) {

				requete.setAttribute("product", pro);
				this.view(requete,reponse);
				return; //on arrête la méthode sinon ça continue
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			reponse.sendRedirect(requete.getContextPath() + "/MarketPlace"); //ça s'est mal passé mal si on est arrivé ici
		} catch (IOException e) {
			System.out.println("Echec de la redirection");
		}

	}

	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
		HttpSession session = requete.getSession();
		Product product = (Product)session.getAttribute("product");
		ArrayList<Integer> panier;
	  if(session.getAttribute("panier")!=null){
	    panier = (ArrayList<Integer>)session.getAttribute("panier");
	  }
	  else{
	    panier = new ArrayList<Integer>();
	  }
	  if(!panier.contains(product.getId()))
	    panier.add(product.getId());
	  session.setAttribute("panier", panier);
		requete.setAttribute("product",product);
		this.view(requete,reponse);
	}


}
