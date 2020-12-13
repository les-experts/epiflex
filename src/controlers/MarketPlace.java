package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;

import mappers.ProductMapper;

public class MarketPlace extends ControlerServlet {

	protected String getLink(){
		return "marketplace/index.jsp";
	}

	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){

		ProductMapper pdtmap = ProductMapper.getInstance();

		requete.setAttribute("products", pdtmap.allProducts());

		this.view(requete,reponse);
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
