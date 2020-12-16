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

	/*
	private void commonPostGetAttributes(HttpServletRequest requete, HttpServletResponse reponse) {
		ProductMapper pdtmap = ProductMapper.getInstance();
		//faut changer ca bg https://stackoverflow.com/questions/8715474/servlet-and-path-parameters-like-xyz-value-test-how-to-map-in-web-xml
		Product product = null;

		try {
			String pathInfo = requete.getPathInfo();

			System.out.println(pathInfo);

			String[] pathParts = pathInfo.split("/");

			System.out.println(pathParts);

			int idProd = Integer.parseInt(pathParts[pathParts.length-1]);

			System.out.println("Je cherche objet d'id : "+idProd);

			product = pdtmap.productById(idProd);

		} catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
				// epiflex/Products/depẑlê -> NumberFormatException
				// epiflex/Products/ -> ArrayIndexOutOfBoundsException
				// epiflex/Products -> NullPointerException
				System.out.println(e);
		}

		//tout s'est bien passé si product != null
		requete.setAttribute("product", product);
	}*/

	/*
	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
		this.commonPostGetAttributes(requete, reponse);

		if (requete.getAttribute("product") == null) {
			try {
				reponse.sendRedirect("/epiflex/MarketPlace");
				return;

			} catch (IOException e) {
				System.out.println(e);
				//il faut trouver un moyen de mettre page erreur qui marche à 100%;
			}
		}
		//this.view(requete, reponse);
	}

	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse) {
		this.commonPostGetAttributes(requete, reponse);

		if (requete.getAttribute("product") == null) {
			try {
				reponse.sendRedirect("/epiflex/MarketPlace");
				return;

			} catch (IOException e) {
				System.out.println(e);
				//il faut trouver un moyen de mettre page erreur qui marche à 100%;
			}
		}
		//this.view(requete, reponse);

		try {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/src/vue/html_jsp/productdetails/index.jsp");
			rd.include(requete, reponse);
		} catch(Exception e) {
			System.out.println(e);
		}

	}*/
}
