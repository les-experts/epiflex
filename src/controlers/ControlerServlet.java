package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;

public abstract class ControlerServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse) {
	  this.view(requete,reponse);
	}

	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		this.view(requete,reponse);
	}

	protected void view(HttpServletRequest requete, HttpServletResponse reponse){
	        System.out.println("Test User 2.1");
		AuthenticationHandler handler = new AuthenticationHandler(requete);
		try{
		        System.out.println("Test User 2.2");
				requete.setAttribute("isConnected", handler.isConnected());
				        System.out.println("Test User 2.3");
				requete.setAttribute("username", (handler.loadUser() == null)?"Visiteur":handler.loadUser().getPseudo());
								System.out.println("Test User 2.4");
				requete.setAttribute("link", this.getLink());
				        System.out.println("Test User 2.5");
				requete.setAttribute("csslink", this.getCSS());
				        System.out.println("Test User 2.6");
				requete.setAttribute("srcjs", this.getJS());
				        System.out.println("Test User 2.7");
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
