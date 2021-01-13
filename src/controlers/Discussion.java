package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;

import models.User;
import models.Message;
import models.Product;

import mappers.UserMapper;
import mappers.MessageMapper;
import mappers.ProductMapper;

import java.io.IOException;
import java.lang.Integer;

/**
 * Permet de gérer la messagerie.
 * @author Leo Pacary
 */
public class Discussion extends ControlerServlet {

	/**
	 * Retourne le chemin vers la vue.
	 * @return String chemin vers la vue
	 */
	protected String getLink(){
		return "messagerie/disscution.jsp";
	}

	/**
	 * Retourne une liste comprenant les chemins vers les fichiers js de la vue.
	 * @return ArrayList<String> liste des chemins vers les fichiers js
	 */
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("messagerie.js");
		return srcJS;
	}

	/**
	 * Permet de gérer une requête en GET.
	 * @param HttpServletRequest requete
	 * @param HttpServletRequest reponse
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse reponse){
		AuthenticationHandler handler = new AuthenticationHandler(request);
		User user = handler.loadUser();
		if(user != null && request.getParameter("id") != null){
		 	 int idOther = Integer.parseInt(request.getParameter("id"));
			 if(idOther < 1){
				 try {
	 				reponse.sendRedirect(request.getContextPath() + "/MarketPlace");
	 			} catch (Exception e) {
	 				System.out.println("Echec de la redirection");
	 			}
			}else{
				MessageMapper mapper = MessageMapper.getInstance();
				UserMapper mapperuser = UserMapper.getInstance();
				User other = mapperuser.userById(idOther);
				List<Message> messages = mapper.allMessagesBetweenUsers(idOther,user.getId());
				request.setAttribute("Message", messages);
				request.setAttribute("Other", other);
			}
			try {
    		(request.getRequestDispatcher("src/vue/html_jsp/messagerie/disscution.jsp")).forward(request ,reponse);
			}
			catch (Exception e) {
				return;
			}
		}
	}
}
