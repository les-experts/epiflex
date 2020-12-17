<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="models.User"%>

<%
      boolean isConnected = (boolean) request.getAttribute("isConnected");
      User user = null;
      if(isConnected){
        user = (User) request.getSession().getAttribute("user");
      }

%>

<div class="row">
  <div class="col s12">
    <h2>Achat</h2>
  </div>
</div>
<hr>
<form method="POST" action="/epiflex/MarketPlace">
<div class="row">
  <div class="col s12">
    <h3>Livraison</h3>
      <div class="row">
        <div class="input-field col s12 m5 offset-m1">
          <i class="material-icons prefix">face</i>
          <input name="firstname" id="firstname" type="text" class="validate" value="
            <% if(isConnected){ %>
                  <%=user.getFirstname()%>
              <%  }%>">
          <label for="firstname">Prenom</label>
        </div>
        <div class="input-field col s12 m5">
          <input name="lastname" id="lastname" type="text" class="validate" value="
            <% if(isConnected){ %>
                  <%=user.getLastname()%>
              <%  }%>">
          <label for="lastname">Nom</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12 m8 offset-m2">
          <i class="material-icons prefix">email</i>
          <input name="email" id="email" type="text" class="validate" value="
            <% if(isConnected){ %>
                  <%=user.getEmail()%>
              <%  }%>">
          <label for="email">Email</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12 m8 offset-m2">
          <i class="material-icons prefix">home</i>
          <input name="address" id="address" type="text" class="validate" value="
            <% if(isConnected){ %>
                  <%=user.getAddress()%>
              <%  }%>">
          <label for="address">Adresse</label>
        </div>
      </div>
  </div>
</div>

<hr>

<div class="row">
  <h3>Paiement</h3>
</div>
<div class="row">
  <div class="col s2">
    <p>Type de paiement :</p>
  </div>
  <div class="col s6">
      <div id="input-field-select-paiement" class="input-field">
        <h1>
        <select id="select-paiement" name="paiement">
          <option value="" disabled>Choisir un paiement</option>
          <option value="0" selected>Carte bancaire</option>
          <option value="1" selected>Paypal</option>
        </select>
      </div>
  </div>
</div>
<div class="row">
  <div class="col s12 center-align">
    <button class="btn waves-effect waves-light" type="submit" name="action">Valider et payer
    <i class="material-icons right">send</i>
  </div>
</div>
</form>
