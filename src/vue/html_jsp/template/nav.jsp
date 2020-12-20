<%@ page contentType="text/plain; charset=UTF-8" %>
<%
      boolean isConnected = (boolean) request.getAttribute("isConnected");
      String username = (String) request.getAttribute("username");
%>
<header>
  <nav>
    <div class="green nav-wrapper">
      <a href="/epiflex/MarketPlace" class="brand-logo center">Marketplace</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="#">Mes produits</a></li>
        <li><a href="#"><i class="material-icons">shopping_cart</i></a></li>
        <% if(isConnected){%>
        <li><a href="#"><i class="material-icons">email</i></a></li>
        <li><a href="/epiflex/Profile" class="waves-effect waves-light"><i class="material-icons right">face</i><%=username%></a></li>
        <%}else{%>
        <li><a class="waves-effect waves-light modal-trigger" href="#modal-profil">S'authentifier</a></li>
        <%}%>
      </ul>
    </div>
  </nav>
</header>

<form method="POST" action="Authentication" id="modal-profil" class="modal">
    <div class="modal-content">
      <div class="row center-align">
        <h4>Connexion à Epi'Flex</h4>
      </div>

      <div class="row">
        <div class="input-field col s12">
          <i class="material-icons prefix">face</i>
          <input name="username" id="pseudo" type="text" class="validate">
          <label for="pseudo">Nom d'utilisateur</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <i class="material-icons prefix">lock_outline</i>
          <input name="password" id="password" type="password" class="validate">
          <label for="password">Mot de passe</label>
        </div>
      </div>

    </div>
    <div class="modal-footer">
      <button class="btn waves-effect waves-light" type="submit" name="action">Se connecter
        <i class="material-icons right">send</i>
      </button>
      <a type="button" class="btn btn-flat waves-effect modal-close">Annuler</a>
    </div>
</form>

</div>
