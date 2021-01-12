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
  List<Product> products = (ArrayList<Product>)session.getAttribute("products");
  if(products!=null){
    int somme = 0;
    for (Product prod : products) {
      somme += prod.getPrice();
%>

<div class="row">
  <div class="col s12 m10 offset-m1">
    <div class="card horizontal">
      <div class="card-image">
        <img src="<%=prod.getPicturePath()%>" width=500 height=320>
      </div>
      <div class="card-stacked">
        <div class="card-content">
          <h2 id="titleProduct"><%=prod.getTitle() %></h2>
        </div>
        <div class="card-content">
          <p id="priceProduct"><%=prod.getPrice() %>€</p>
        </div>
        <div class="card-action">
          <a class="blue-text" href="/epiflex/Product?id=<%=prod.getId()%>">Plus d'infos</a>
        </div>
        <form action="Panier" method="POST">
          <div class="card-action">
            <button class="btn waves-effect waves-light" type="submit" name="confirmForm" >Supprimer du panier
            <i class="material-icons right">remove_shopping_cart</i>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<% }
  %>
<div class="row">
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
      <div class="row">
        <div class="col s12">
          <p>Aucun produit dans le panier</p>
        </div>
      </div>
  <%}%>
