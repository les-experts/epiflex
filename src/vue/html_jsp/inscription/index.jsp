<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="models.User"%>

<%
      boolean isConnected = (boolean) request.getAttribute("isConnected");
      User user = (User) request.getSession().getAttribute("user");
      if(isConnected)
        out.println(user.getPseudo());

%>
<div class="row">
  <div class="col s12">
    <h2>Inscription à Epiflex</h2>
  </div>
</div>
<div class="row">
  <div class="col s12 center">
    <h3>Bienvenue chez les épiflexeurs !</h3>
  </div>
</div>
<div class="row">
  <div class="col s12">
    <%if(request.getAttribute("errorForm") != null){
      boolean errorForm = (boolean)request.getAttribute("errorForm");
      if(errorForm){
        out.println("<p style=\"color:red; font-size:100%; text-align:center;\">Erreur : veuillez remplir tous les champs.</p>");
      }
    }%>
  </div>
</div>
<form method="POST" action="Inscription">
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">account_circle</i>
      <input name="pseudo" id="pseudo" type="text" class="validate">
      <label for="pseudo">Pseudo</label>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12 m5 offset-m1">
      <i class="material-icons prefix">face</i>
      <input name="firstname" id="firstname" type="text" class="validate">
      <label for="firstname">Prenom</label>
    </div>
    <div class="input-field col s12 m5">
      <input name="lastname" id="lastname" type="text" class="validate">
      <label for="lastname">Nom</label>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">email</i>
      <input name="email" id="email" type="text" class="validate">
      <label for="email">Email</label>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">home</i>
      <input name="address" id="address" type="text" class="validate">
      <label for="address">Adresse</label>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">lock</i>
      <input name="password" id="password" type="password" class="validate">
      <label for="password">Mot de passe</label>
    </div>
  </div>
  <div class="row">
    <div class="col s12 center-align">
      <button class="btn waves-effect waves-light" type="submit" name="confirmForm" >Valider l'inscription
      <i class="material-icons right">send</i>
    </div>
  </div>
</form>
