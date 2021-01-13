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

public class CommentServlet extends ControlerServlet {

  protected String getLink(){
  	return "comment/aProduct.jsp";
  }

  @Override
	public void doPost(HttpServletRequest request, HttpServletResponse reponse){

    System.out.println("test p1");
    int rank = Integer.parseInt(request.getParameter("rank"));
    int idProduct = Integer.parseInt(request.getParameter("id"));
    String comment = request.getParameter("comment");
    AuthenticationHandler handler = new AuthenticationHandler(request);
    User user = handler.loadUser();
    System.out.println("test p2");
    boolean validPOST = verifInt(idProduct) && verifInt(rank) && verifText(comment) && user != null;
    String url = "/Product?id="+idProduct;
    CommentMapper commMapper = CommentMapper.getInstance();
    boolean hasAlreadyPost = commMapper.hasAlreadyPost(idProduct,user.getId());
    if(validPOST && !hasAlreadyPost){
      commMapper.insert(idProduct, user.getId(), rank, comment);
    }
    retoureProduit(request,reponse,url);
    System.out.println("test p3");
	}

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse reponse){

    System.out.println("test-1");
    int idProduct = Integer.parseInt(request.getParameter("id"));
    //int idProduct = 5;
    System.out.println("test0");
    CommentMapper mapper = CommentMapper.getInstance();
    ProductMapper promap = ProductMapper.getInstance();
    try{
      AuthenticationHandler handler = new AuthenticationHandler(request);
      User user = handler.loadUser();
      boolean hasAlreadyPost = true;
      if(handler.isConnected()){
        hasAlreadyPost = mapper.hasAlreadyPost(idProduct,user.getId());

      }
      request.setAttribute("listComment", mapper.getCommentByProduct(idProduct));
      request.setAttribute("hasAlreadyPost", hasAlreadyPost);
      request.setAttribute("product", promap.productById(idProduct));
      request.setAttribute("isConnected", handler.isConnected());
      request.setAttribute("username", (user == null)?"Visiteur":handler.loadUser().getPseudo());
      (request.getRequestDispatcher("src/vue/html_jsp/comment/aProduct.jsp")).forward(request ,reponse);
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
  }

  public boolean verifInt(int data){
    return data <= 5 && data >= 1;
  }
  public boolean verifText(String data){
    return true;
  }

  private void retoureProduit(HttpServletRequest request, HttpServletResponse reponse, String url){
    try {
      reponse.sendRedirect(request.getContextPath() + url); //ça s'est mal passé mal si on est arrivé ici
    } catch (IOException e) {
      System.out.println("Echec de la redirection");
    }
  }
}
