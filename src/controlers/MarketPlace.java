package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;

public class MarketPlace extends ControlerServlet {

	protected String getLink(){
		return "marketplace/index.jsp";
	}

  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("marketplace.css");
		return LinkCss;
	}

}