<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Product"%>

<link rel="stylesheet" type="text/css" href="src/vue/css/products.css">

<%
  ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
  System.out.println(products);

  if (products != null) {
%>
  <div class="row" id="row-products">

      <% for (Product prod : products) { %>

      <div class="col s6 m3">

        <div class="card small">

          <div class="card-image waves-effect waves-block waves-light">
            <img class="activator" src="<%=prod.getPicturePath()%>">
          </div>

          <div class="card-content">
            <span class="card-title activator grey-text text-darken-4"><%=prod.getTitle()%><i class="material-icons right">more_vert</i></span>
            <p><a href="#">En savoir plus</a></p>
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
