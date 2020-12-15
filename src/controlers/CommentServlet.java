package controlers;

import javax.servlet .*;
import javax.servlet.http .*;

import mappers.CommentMapper;
import models.Comment;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommentServlet extends ControlerServlet {

  protected String getLink(){
  	return "comment/aProduct.jsp";
  }

  @Override
	public void doPost(HttpServletRequest request, HttpServletResponse reponse){
  	  return;
	}

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse reponse){
    int idProduct = Integer.parseInt(request.getParameter("id"));
    //int idProduct = 5;
    CommentMapper mapper = CommentMapper.getInstance();
    request.setAttribute("listComment", mapper.getCommentByProduct(idProduct));
    try{
      (request.getRequestDispatcher("src/vue/html_jsp/comment/aProduct.jsp")).forward(request ,reponse);
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
  }
}
