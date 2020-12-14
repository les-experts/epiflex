package controlers;

import javax.servlet .*;
import javax.servlet.http .*;

import mappers.UserMapper;
import models.User;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticationHandler {
  	public User loadUser(HttpServletRequest request){
  		HttpSession session = request.getSession();
  		return (User) session.getAttribute("user");
  	}

  	public boolean isConnected(HttpServletRequest request){
  		return loadUser(request) != null;
  	}

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
