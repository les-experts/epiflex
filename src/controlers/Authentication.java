package controlers;

import javax.servlet .*;
import javax.servlet.http .*;

import mappers.UserMapper;
import models.User;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentication extends ControlerServlet {

	private String linkIfFailed = "login/Connecter.html";
	private String linkIfConnected = "/MarketPlace";

	protected String getLink(){
		return this.linkIfFailed;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse reponse){
		AuthenticationHandler authHandler = new AuthenticationHandler(request);
		if(authHandler.isConnected()){
			this.connectionPage(request,reponse);
		}
		else{
			this.failPage(request,reponse);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse reponse){
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

			boolean validPOST = verifString(username) && verifString(password);
			AuthenticationHandler authHandler = new AuthenticationHandler(request);

			if(validPOST){
		    UserMapper mapper = UserMapper.getInstance();
				try{
		    	User user = mapper.authentification(username, authHandler.wordToMD5(password));
					if(user != null){
						HttpSession session = request.getSession();
						session.setAttribute("user",user);
						this.connectionPage(request,reponse);
					}
					else{
						this.failPage(request,reponse);
					}

				}
				catch(Exception e){
					this.failPage(request,reponse);
				}
			}
			else{
				this.failPage(request,reponse);
			}
	}

	private void connectionPage(HttpServletRequest request, HttpServletResponse reponse){
		try{
			(request.getRequestDispatcher(this.linkIfConnected)).forward(request ,reponse);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	private void failPage(HttpServletRequest request, HttpServletResponse reponse){
		try{
			(request.getRequestDispatcher(this.linkIfFailed)).forward(request ,reponse);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	private boolean verifString(String stringVarif){
		return true;
	}
}
