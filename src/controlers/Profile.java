package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.io.*;
import java.util.ArrayList;
import models.User;

public class Profile extends ControlerServlet {

	protected String getLink(){
		return "profile/index.jsp";
	}

}
