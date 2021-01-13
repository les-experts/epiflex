package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;

/**
 * Classe abtraite permettant de gérer les servlets liés à une jsp.
 * @author Leo Pacary
 */
public abstract class ControlerServlet extends HttpServlet {

	/**
	 * Permet de gérer une requête en POST.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse) {
	  this.view(requete,reponse);
	}

	/**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		this.view(requete,reponse);
	}

	/**
	 * Permet de fixer les attributs dans la requête (notamment les liens vers les fichiers à import)
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
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

	/**
	 * Retourne une liste comprenant les chemins vers les css de la vue.
	 */
	protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
		return LinkCss;
	}

	/**
	 * Retourne une liste comprenant les chemins vers les fichiers js de la vue.
	 */
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		return srcJS;
	}

	/**
	 * Retourne le chemin vers la vue.
	 */
	protected abstract String getLink();
}
