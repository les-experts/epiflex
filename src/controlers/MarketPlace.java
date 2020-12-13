package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;
import models.Product;

import mappers.ProductMapper;

public class MarketPlace extends ControlerServlet {

	protected String getLink(){
		return "marketplace/index.jsp";
	}

	private void attributsCommunsPostGet(HttpServletRequest requete, HttpServletResponse reponse) {
		ProductMapper pdtmap = ProductMapper.getInstance();

		System.out.println("jessaie de chopper les ojets");
		List<Product> products = pdtmap.allProducts();
		System.out.println("ca fait ca");

		requete.setAttribute("products", products);

	}

	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
		this.attributsCommunsPostGet(requete, reponse);
		this.view(requete,reponse);
	}

	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse) {
		this.attributsCommunsPostGet(requete, reponse);
		this.view(requete, reponse);
	}

  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("marketplace.css");
		return LinkCss;
	}

	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("marketplace.js");
		return srcJS;
	}

}
