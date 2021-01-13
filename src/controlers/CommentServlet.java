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

/**
 * Servlet permettant de gérer les commentaires des produits.
 * @author Leo Pacary
 */
public class CommentServlet extends ControlerServlet {

  /**
	 * Retourne le chemin vers la vue.
   * @return String chemin vers la vue
	 */
  protected String getLink(){
  	return "comment/aProduct.jsp";
  }

  /**
	 * Permet de gérer une requête en POST.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
	public void doPost(HttpServletRequest request, HttpServletResponse reponse){

    int rank = Integer.parseInt(request.getParameter("rank"));
    int idProduct = Integer.parseInt(request.getParameter("id"));
    String comment = request.getParameter("comment");
    AuthenticationHandler handler = new AuthenticationHandler(request);
    User user = handler.loadUser();
    boolean validPOST = verifInt(idProduct) && verifInt(rank) && verifText(comment) && user != null;
    String url = "/Product?id="+idProduct;
    CommentMapper commMapper = CommentMapper.getInstance();
    boolean hasAlreadyPost = commMapper.hasAlreadyPost(idProduct,user.getId());
    if(validPOST && !hasAlreadyPost){
      commMapper.insert(idProduct, user.getId(), rank, comment);
    }
    retoureProduit(request,reponse,url);
	}

  /**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse reponse){

    int idProduct = Integer.parseInt(request.getParameter("id"));
    //int idProduct = 5;
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
