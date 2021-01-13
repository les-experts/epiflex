package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;

import models.User;

import mappers.MessageMapper;

public class Messagerie extends ControlerServlet {

	protected String getLink(){
		return "messagerie/messagerie.jsp";
	}

	@Override
	public void doPost(HttpServletRequest requete, HttpServletResponse reponse){
    HttpSession session = requete.getSession();

    User sender = (User) session.getAttribute("user");

    String proId = requete.getParameter("prodId");
    String message = requete.getParameter("message");

		this.view(requete,reponse);
	}

	@Override
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse){
		this.view(requete,reponse);
	}

  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("messagerie.css");
		return LinkCss;
	}

	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("marketplace.js");
		return srcJS;
	}

}
