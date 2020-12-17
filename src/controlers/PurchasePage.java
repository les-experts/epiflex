package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.ArrayList;

public class PurchasePage extends ControlerServlet {

	protected String getLink(){
		return "purchasepage/index.jsp";
	}

  @Override
  protected ArrayList<String> getCSS(){
		ArrayList<String> LinkCss = new ArrayList<String>();
		LinkCss.add("purchasepage.css");
		return LinkCss;
	}

	@Override
	protected ArrayList<String> getJS(){
		ArrayList<String> srcJS = new ArrayList<String>();
    srcJS.add("purchasepage.js");
		return srcJS;
	}


}
