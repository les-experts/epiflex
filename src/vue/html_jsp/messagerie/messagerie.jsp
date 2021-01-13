<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.User"%>

<%
  ArrayList<User> users = (ArrayList<User>) request.getAttribute("usersTalkedBefore");
  if (users != null && !users.isEmpty() ){
%>
<div class="row center-align">
  <div class="col"><h2>Sélectionnez la personne à qui vous souhaitez envoyer un message<h2></div>
</div>
<div class="row center-align">
  <% for (User usr : users) {
  %>
    <div class="col s3">
      <a class="waves-effect waves-light btn btn-contacter black"><%=usr.getPseudo()%></a>
    </div>
  <% } %>
</div>
<% } else { %>
<div class="row">
  <span>Vous n'avez jamais discuté avec un autre utilisateur.</span>
</div>
<% } %>
