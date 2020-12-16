<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="models.User"%>

<%
      boolean isConnected = (boolean) request.getAttribute("isConnected");
      User user = (User) request.getSession().getAttribute("user");
%>

<div class="row">
  <div class="col s12">
    <h2>Mes informations</h2>
  </div>
</div>
<form method="POST" action="/epiflex/toto">
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">account_circle</i>
      <input name="pseudo" id="pseudo" type="text" class="validate" value="<%= user.getPseudo() %>">
      <label for="pseudo">Pseudo</label>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12 m5 offset-m1">
      <i class="material-icons prefix">face</i>
      <input name="firstname" id="firstname" type="text" class="validate" value="<%= user.getFirstname() %>">
      <label for="firstname">Prenom</label>
    </div>
    <div class="input-field col s12 m5">
      <input name="lastname" id="lastname" type="text" class="validate" value=" <%= user.getLastname() %>">
      <label for="lastname">Nom</label>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">email</i>
      <input name="email" id="email" type="text" class="validate" value=" <%= user.getEmail() %>">
      <label for="email">Email</label>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">home</i>
      <input name="address" id="address" type="text" class="validate" value=" <%= user.getAddress() %>">
      <label for="address">Adresse</label>
    </div>
  </div>
  <div class="row">
    <div class="col s12 center-align">
      <button class="btn waves-effect waves-light" type="submit" name="action">Confirmer les modifications
      <i class="material-icons right">send</i>
    </div>
  </div>
</form>
