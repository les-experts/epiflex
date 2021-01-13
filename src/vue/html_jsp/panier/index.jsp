<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="models.Product"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<div class="row">
  <div class="col s12">
    <h1>Mon panier</h1>
  </div>
</div>

<%
  List<Product> products = (ArrayList<Product>)session.getAttribute("panier");
  if(products!=null){
    int somme = 0;
    for (Product prod : products) {
      somme += prod.getPrice();
%>

<div class="row row-produit-panier valign-wrapper">
  <div class="col s6 imagecolumn">
      <img src="<%=prod.getPicturePath()%>" width=500 height=320>
  </div>
  <div class="col s6 ">
    <div class="row center-align">
        <h2 id="titleProduct"><%=prod.getTitle() %></h2>
    </div>
    <div class="row center-align">
      <p id="priceProduct"><%=prod.getPrice() %>€</p>
    </div>
    <div class="row center-align">
      <div class="col s6">
        <a class="waves-effect waves-teal btn-flat" href="/epiflex/Product?id=<%=prod.getId()%>">Détails</a>
      </div>
      <form method="POST" action="/epiflex/SuppressionPanier" class="col s6">
        <input id="productToDelete" name="productToDelete" type="hidden" value="<%=prod.getId()%>">
        <button class="btn waves-effect waves-light" type="submit">Supprimer du panier
        <i class="material-icons right">remove_shopping_cart</i>
      </button>
      </form>
    </div>
  </div>
</div>


<% }
  %>
<div class="row row-panier">
  <div class="col s12 m10 offset-m1">
    <div class="col s12 center-align">
      <p id="price">Prix total : <%=somme%>€</p>
    </div>
  </div>
  <form method="POST" action="/epiflex/PurchasePage">
    <div class="col s12 center-align">
      <button class="btn waves-effect waves-light" type="submit" name="confirmForm" >Valider la commande
      <i class="material-icons right">send</i>
    </div>
  </form>
</div>
<%}
  else{%>
      <div class="row row-panier">
        <div class="col s12">
          <p>Aucun produit dans le panier</p>
        </div>
      </div>
  <%}%>
