package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;
import models.Category;

import mappers.CategoryMapper;

public class MarketPlace extends ControlerServlet {

	protected String getLink(){
		return "marketplace/index.jsp";
	}

	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
		this.commonPostGetAttributes(requete, reponse );
		this.view(requete,reponse);
	}

	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		this.commonPostGetAttributes(requete, reponse );
		this.view(requete,reponse);
	}

	private void commonPostGetAttributes(HttpServletRequest requete, HttpServletResponse reponse) {
		CategoryMapper catmap = CategoryMapper.getInstance();

		List<Category> categories = catmap.allCategories();

		requete.setAttribute("categories", categories);
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
