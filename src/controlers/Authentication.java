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
	private String linkIfConnected = "login/Connecter.html";
	private String linkView = linkIfFailed;

	protected String getLink(){
		return this.linkView;
	}

	private void setLink(String link){
		this.linkView = link;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse reponse){
		AuthenticationHandler authHandler = new AuthenticationHandler(request);
		if(authHandler.isConnected()){
			this.setLink(linkIfConnected);
			this.view(request,reponse);
		}
		else{
			super.doGet(request,reponse);
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
							this.setLink(linkIfConnected);
							this.view(request,reponse);
					}
					else{
						this.view(request,reponse);
					}

				}
				catch(Exception e){
					this.view(request,reponse);
				}
			}
			else{
				this.view(request,reponse);
			}
	}

	private boolean verifString(String stringVarif){
		return true;
	}
}
