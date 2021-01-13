package controlers;

import javax.servlet .*;
import javax.servlet.http .*;
import java.util.ArrayList;
import java.util.List;

import models.User;
import models.Product;

import mappers.MessageMapper;
import mappers.ProductMapper;

import java.io.IOException;

public class Discussion extends ControlerServlet {

	protected String getLink(){
		return "messagerie/messagerie.jsp";
	}
}
