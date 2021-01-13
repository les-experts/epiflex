package controlers;

import javax.servlet .*;
import javax.servlet.http .*;

import mappers.UserMapper;
import models.User;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe qui permet de gérer l'authentification sur le site.
 * Hérite de la classe abstraite ControlerServlet.
 * @author Leo Pacary
 */
public class Authentication extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		//on renvoie vers le MarketPlace car il n'y a pas vue associé à cette servlet.
		return "/MarketPlace";
	}

	/**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
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

	/**
	 * Permet de gérer une requête en POST.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
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

	/**
	 * Renvoie vers la vue.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	private void connectionPage(HttpServletRequest request, HttpServletResponse reponse){
		try{
			(request.getRequestDispatcher(this.getLink())).forward(request ,reponse);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Renvoie vers la vue d'erreur.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	private void failPage(HttpServletRequest request, HttpServletResponse reponse){
		try{
			reponse.sendRedirect(request.getContextPath() + this.getLink());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	private boolean verifString(String stringVarif){
		return true;
	}
}
