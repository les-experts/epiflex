package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import mappers.ProductMapper;
import models.Product;
import models.User;
import java.util.List;
import java.io.IOException;

/**
 * Permet à un administrateur de supprimer un produit.
 * @author Leo Pacary
 */
public class DeleteProductServlet extends ControlerServlet {

  /**
	 * Retourne le chemin vers la vue.
   * @return String chemin vers la vue
	 */
  protected String getLink(){
  	return "marketplace/index.jsp";
  }


  /**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse reponse) throws IOException{
    AuthenticationHandler handler = new AuthenticationHandler(request);
    User user = handler.loadUser();

    if(user != null && user.getRole().getId() == 1){
      ProductMapper mapper = ProductMapper.getInstance();
      List<Product> productList = mapper.productsByUser(user.getId());
      int idProduct = Integer.parseInt(request.getParameter("id"));
      mapper.deleteProduct(idProduct);
			request.setAttribute("productList", productList);
    }

    reponse.sendRedirect(request.getContextPath() + "/MarketPlace");
  }
}
