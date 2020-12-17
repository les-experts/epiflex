package controlers;

import javax.servlet .*;
import javax.servlet.http .*;

import mappers.CommentMapper;
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

    int rank = Integer.parseInt(request.getParameter("rank"));
    int idProduct = Integer.parseInt(request.getParameter("idProduct"));
    String comment = request.getParameter("comment");
    AuthenticationHandler handler = new AuthenticationHandler(request);
    User user = handler.loadUser();
    boolean validPOST = verifInt(idProduct) && verifInt(rank) && verifText(comment) && user != null;
    String url = "/Product/"+idProduct;

    if(validPOST){
      CommentMapper commMapper = CommentMapper.getInstance();
      commMapper.insert(idProduct, user.getId(), rank, comment);
    }
    retoureProduit(request,reponse,url);
	}

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse reponse){
    int idProduct = Integer.parseInt(request.getParameter("id"));
    //int idProduct = 5;
    CommentMapper mapper = CommentMapper.getInstance();
    request.setAttribute("listComment", mapper.getCommentByProduct(idProduct));
    try{
      AuthenticationHandler handler = new AuthenticationHandler(request);
      request.setAttribute("isConnected", handler.isConnected());
      request.setAttribute("username", (handler.loadUser() == null)?"Visiteur":handler.loadUser().getPseudo());
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
    try{
      (request.getRequestDispatcher(url)).forward(request ,reponse);
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
  }
}
