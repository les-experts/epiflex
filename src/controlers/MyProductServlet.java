package controlers;

import javax.servlet .*;
import javax.servlet.http .*;

import mappers.CommentMapper;
import mappers.ProductMapper;
import models.Comment;
import models.User;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.lang.Integer;
import models.Product;
import java.util.List;

/**
 * Permet de gérer la page Mes Produits.
 * @author Leo Pacary
 */
public class MyProductServlet extends ControlerServlet {

  /**
   * Chemin vers la vue.
   */
  protected String link = "myProduct/index.jsp";

  /**
   * Retourne le chemin vers la vue.
   * @return String chemin vers la vue
   */
  protected String getLink(){
  	return this.link;
  }

  /**
	 * Permet de gérer une requête en POST.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
	public void doPost(HttpServletRequest request, HttpServletResponse reponse){
	}

  /**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse reponse){
    AuthenticationHandler handler = new AuthenticationHandler(request);
    User user = handler.loadUser();
    if(user == null){
      this.link = "marketplace/index.jsp";
      this.view(request,reponse);
    }
    else{
      ProductMapper mapper = ProductMapper.getInstance();
      List<Product> productList = mapper.productsByUser(user.getId());

			request.setAttribute("productList", productList);
        this.view(request,reponse);
    }
  }
}
