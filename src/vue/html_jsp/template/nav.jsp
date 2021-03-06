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
        <% if(isConnected){%>
        <li><a href="/epiflex/mesProduit">Mes produits</a></li>
        <%}%>
        <li><a href="/epiflex/Panier"><i class="material-icons">shopping_cart</i></a></li>
        <% if(isConnected){%>
        <li><a href="/epiflex/Messagerie"><i class="material-icons">email</i></a></li>
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
      <div class="row">
        <div class="input-field col s12 center">
          <button class="btn waves-effect waves-light" type="submit" name="action">Se connecter
            <i class="material-icons right">send</i>
          </button>
          <a type="button" class="btn btn-flat waves-effect modal-close">Annuler</a>
        </div>
      </div>
    </form>
    <form method="GET" action="Inscription">
      <div class="input-field col s12">
        <p>Pas encore inscrit ? </p>
        <button class="btn waves-effect waves-light green" type="submit" name="action">Créer un compte
        </button>
      </div>
    </form>
</div>
