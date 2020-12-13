package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;

public class Authentication extends HttpServlet {
	
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse) {
	    String username = requete.getParameter("username");
	    String password = requete.getParameter("password");
	}
}
