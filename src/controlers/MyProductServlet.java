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

public class MyProductServlet extends ControlerServlet {

  protected String link = "myProduct/index.jsp";
  protected String getLink(){
  	return this.link;
  }

  @Override
	public void doPost(HttpServletRequest request, HttpServletResponse reponse){
	}

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse reponse){
    AuthenticationHandler handler = new AuthenticationHandler(request);
    User user = handler.loadUser();
    if(user == null){
      System.out.println("Test User Null");
      this.link = "marketplace/index.jsp";
      this.view(request,reponse);
    }
    else{
      System.out.println("Test User HERE");
      ProductMapper mapper = ProductMapper.getInstance();
      List<Product> productList = mapper.productsByUser(user.getId());

        System.out.println("Test User 1");
			request.setAttribute("productList", productList);
        System.out.println("Test User 2");
        this.view(request,reponse);
        System.out.println("Test User 3S");
    }
  }
}
