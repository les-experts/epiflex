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

	private String linkIfFailed = "login/index.jsp";
	private String linkIfConnected = "src/vue/html_jsp/login/Connecter.html";

	protected String getLink(){
		return this.linkIfFailed;
	}

	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		AuthenticationHandler authHandler = new AuthenticationHandler();
		if(authHandler.isConnected(requete)){
			try{
				(requete.getRequestDispatcher(linkIfConnected)).forward(requete ,reponse);
			}
			catch(Exception e){
				e.printStackTrace();
				return;
			}
		}
		else{
			super.doGet(requete,reponse);
		}
	}

	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
	    String username = requete.getParameter("username");
	    String password = requete.getParameter("password");

			boolean validPOST = verifString(username) && verifString(password);
			AuthenticationHandler authHandler = new AuthenticationHandler();

			if(validPOST){
		    UserMapper mapper = UserMapper.getInstance();
				try{
		    	User user = mapper.authentification(username, authHandler.wordToMD5(password));
					if(user != null){
						HttpSession session = requete.getSession();
						session.setAttribute("user",user);
						try{
							(requete.getRequestDispatcher(linkIfConnected)).forward(requete ,reponse);
						}
						catch(Exception e){
							e.printStackTrace();
							return;
						}
					}
					else{
						try{
							(requete.getRequestDispatcher(linkIfConnected)).forward(requete ,reponse);
						}
						catch(Exception e){
							e.printStackTrace();
							return;
						}
					}

				}
				catch(Exception e){
					this.view(requete,reponse);
				}
			}
			else{
				try{
					(requete.getRequestDispatcher(linkIfConnected)).forward(requete ,reponse);
				}
				catch(Exception e){
					e.printStackTrace();
					return;
				}
			}
	}

	private boolean verifString(String stringVarif){
		return true;
	}
}
