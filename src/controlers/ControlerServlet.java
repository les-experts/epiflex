package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.io.*;

public abstract class ControlerServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse)  throws IOException {
	  this.view(requete,reponse);
	}

	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		this.view(requete,reponse);
	}

	protected void view(HttpServletRequest requete, HttpServletResponse reponse){
		AuthenticationHandler handler = new AuthenticationHandler(requete);
		try{
				requete.setAttribute("isConnected", handler.isConnected());
				requete.setAttribute("username", (handler.loadUser() == null)?"Visiteur":handler.loadUser().getPseudo());
				requete.setAttribute("link", this.getLink());
				requete.setAttribute("csslink", this.getCSS());
				requete.setAttribute("srcjs", this.getJS());
			(requete.getRequestDispatcher("src/vue/html_jsp/index.jsp")).forward(requete ,reponse);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
		return LinkCss;
	}

	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		return srcJS;
	}

	protected abstract String getLink();
}
