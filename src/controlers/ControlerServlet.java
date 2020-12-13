package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;

public abstract class ControlerServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
	  this.view(requete,reponse);
	}
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		this.view(requete,reponse);
	}

	protected void view(HttpServletRequest requete, HttpServletResponse reponse){
		try{
				requete.setAttribute("link", this.getLink());
				requete.setAttribute("csslink", this.getCSS());
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
	protected abstract String getLink();
}
