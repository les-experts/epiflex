package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import mappers.ProductMapper;
import models.Product;
import models.User;
import java.util.List;
import java.io.IOException;

public class DeleteProductServlet extends ControlerServlet {

  protected String getLink(){
  	return "marketplace/index.jsp";
  }

  @Override
	public void doPost(HttpServletRequest request, HttpServletResponse reponse){
	}

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse reponse){
    AuthenticationHandler handler = new AuthenticationHandler(request);
    User user = handler.loadUser();
    if(user != null && user.getRole().getId() == 1){
      ProductMapper mapper = ProductMapper.getInstance();
      List<Product> productList = mapper.productsByUser(user.getId());
      int idProduct = Integer.parseInt(request.getParameter("id"));
      mapper.deleteProduct(idProduct);
			request.setAttribute("productList", productList);
        this.view(request,reponse);
    }
    else{
      try {
        reponse.sendRedirect(request.getContextPath() + "/MarketPlace");
      } catch (Exception e) {
        System.out.println("Echec de la redirection");
      }
    }
  }
}
