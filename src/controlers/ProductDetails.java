package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;

public class ProductDetails extends ControlerServlet {

	protected String getLink(){
		return "productdetails/index.jsp";
	}

  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
    LinkCss.add("productdetails.css");
    LinkCss.add("comment.css");
		return LinkCss;
	}

	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
		srcJS.add("productdetails.js");
		srcJS.add("comment.js");
		return srcJS;
	}
}
