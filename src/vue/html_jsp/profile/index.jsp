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
<div class="row">
  <div class="col s12">
    <%if(request.getAttribute("errorPassword") != null){
      boolean errorPassword = (boolean) request.getAttribute("errorPassword");
      if(errorPassword){
        out.println("<p style=\"color:red; font-size:100%; text-align:center;\">Erreur : échec de la modification du mot de passe.</p>");
      }
    }%>
</div>
<form method="POST" action="/epiflex/Profilee">
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
      <button class="btn red waves-effect waves-light modal-trigger" type="button" name="action" href="#modal-password">Modifier le mot de passe
      <i class="material-icons right">lock</i>
    </div>
  </div>
  <div class="row">
    <div class="col s12 center-align">
      <button class="btn waves-effect waves-light" type="submit" name="confirmForm" >Confirmer les modifications
      <i class="material-icons right">send</i>
    </div>
  </div>
<div class="row">
  <div class="col s12 left-align">
    <button class="btn waves-effect red waves-light" type="submit" name="deconnection" >Se deconnecter
    <i class="material-icons right">logout</i>
  </div>
</div>
</form>


<form method="POST" action="PasswordModification" id="modal-password" class="modal">
    <div class="modal-content">
      <div class="row center-align">
        <h4>Modification de mot de passe</h4>
      </div>

      <div class="row">
        <div class="input-field col s12">
          <i class="material-icons prefix">lock</i>
          <input name="oldPassword" id="oldPassword" type="password" class="validate">
          <label for="oldPassword">Mot de passe actuel</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <i class="material-icons prefix">lock_outline</i>
          <input name="newPassword" id="newPassword" type="password" class="validate">
          <label for="newPassword">Nouveau mot de passe</label>
        </div>
      </div>

    </div>
    <div class="modal-footer">
      <button class="btn waves-effect waves-light" type="submit" name="action">Confirmer la modification
        <i class="material-icons right">send</i>
      </button>
      <a type="button" class="btn btn-flat waves-effect modal-close">Annuler</a>
    </div>
</form>
