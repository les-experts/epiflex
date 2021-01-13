<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.User"%>
<%@ page import="models.Message"%>


<%
  User user = (User) request.getAttribute("Other");
  ArrayList<Message> messages = (ArrayList<Message>) request.getAttribute("Message");
  if (user != null){
%>
  <div>
    <h1><%=user.getPseudo()%></h1>
  </div>

<%
  for (Message val : messages) {
    %>
    <div>
      <p><%=val.getText()%></p>
    </div>
    <%
  }
%>
  <div>
  </div>
<%
  }
%>
