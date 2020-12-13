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

	protected String getLink(){
		return "login/index.jsp";
	}


	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
	    String username = requete.getParameter("username");
	    String password = requete.getParameter("password");

			User user = loadUser(requete);
			if(user != null){
				try{
					(requete.getRequestDispatcher("src/vue/html_jsp/Connecter.html")).forward(requete ,reponse);
				}
				catch(Exception e){
					e.printStackTrace();
					return;
				}
			}

			if(!verifString(username) || !verifString(password)){
				this.view(requete,reponse);
			}
	    UserMapper mapper = UserMapper.getInstance();
			try{
	    	user = mapper.authentification(username, this.wordToMD5(password));

				if(user == null){
					this.view(requete,reponse);
				}
				HttpSession session = requete.getSession();
				session.setAttribute("user",user.getPseudo());
				try{
					(requete.getRequestDispatcher("src/vue/html_jsp/Connecter.html")).forward(requete ,reponse);
				}
				catch(Exception e){
					e.printStackTrace();
					return;
				}
			}
			catch(Exception e){
				this.view(requete,reponse);
			}
	}

	private User loadUser(HttpServletRequest requete){
		HttpSession session = requete.getSession();
		return (User) session.getAttribute("user");
	}

	private String wordToMD5(String password) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(password.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		System.out.println(password+" into "+hashtext);
		return hashtext;
	}

	private boolean verifString(String stringVarif){
		return true;
	}

}
