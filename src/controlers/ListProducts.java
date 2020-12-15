package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;
import models.Product;
import mappers.ProductMapper;

public class ListProducts extends ControlerServlet {

  	protected String getLink(){
  		return "marketplace/products.jsp";
  	}

  	private void commonPostGetAttributes(HttpServletRequest requete, HttpServletResponse reponse) {
  		ProductMapper pdtmap = ProductMapper.getInstance();

  		List<Product> products = pdtmap.allProducts();

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
