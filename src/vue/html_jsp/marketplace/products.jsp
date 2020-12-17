<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Product"%>

<link rel="stylesheet" type="text/css" href="src/vue/css/products.css">

<%
  ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
  if (products != null) {
%>
  <div class="row" id="row-products">

      <% for (Product prod : products) {

          int maxLength = 15;

          String title = prod.getTitle();
          int endSubstr = Math.min(maxLength, title.length());
          String endString = "";
          if (endSubstr == maxLength) {
            endString = "...";
          }
         %>

      <div class="col s6 m3">

        <div class="card hoverable small">

          <div class="card-image waves-effect waves-block waves-light">
            <img class="activator" src="<%=prod.getPicturePath()%>">
          </div>

          <div class="card-content">
            <span class="card-title activator grey-text text-darken-4"><%=title.substring(0,endSubstr) + endString %><i class="material-icons right">more_vert</i></span>
            <p><a href="/epiflex/Product?id=<%=prod.getId()%>">En savoir plus</a></p>
          </div>

          <div class="card-reveal">
            <span class="card-title grey-text text-darken-4"><%=prod.getTitle()%><i class="material-icons right">close</i></span>
            <p><%=prod.getDescription()%></p>
          </div>

        </div>

      </div>

<% } %>

  </div>

<%
}
else {
%>
  <div class="row center-align" id="row-products">
    <h2>Aucun produit n'a été trouvé</h2>
  </div>
<% } %>
