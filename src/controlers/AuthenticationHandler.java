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
 * @author Leo Pacary
 */
public class AuthenticationHandler {

    /**
     * Requete.
     */
    private HttpServletRequest request;

    /**
     * Constructeur de la classe.
     * @param HttpServletRequest request
     */
    public AuthenticationHandler(HttpServletRequest request){
      this.request = request;
    }

    /**
     * Permet de retourner l'utilisateur de la session.
     * @return User
     */
  	public User loadUser(){
  		HttpSession session = this.request.getSession();
  		return (User) session.getAttribute("user");
  	}

    /**
     * Permet de vérifier si un utilisateur est connecté.
     * @return boolean true si il y a un utilisateur de connecté
     */
  	public boolean isConnected(){
  		return this.loadUser() != null;
  	}

    /**
     * Permet d'encrypter le mot de passe pour le stockage dans la base de données.
     * @param le mot de passe à encrypter
     * @return String le mot de passe encrypté
     */
  	public String wordToMD5(String password) throws NoSuchAlgorithmException {
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
  		return hashtext;
  	}
}
