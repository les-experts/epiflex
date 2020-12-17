<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="models.Product"%>

<%
  Product product = (Product)request.getAttribute("product");
%>

<div class="row">
  <div class="col s6">
      <img class="img-responsive materialboxed" src="<%=product.getPicturePath()%>" alt="Image" />
  </div>
  <div class="col s6 productDetails">
    <div class="row center-align">
      <h1><%=product.getTitle()%></h1>
      <div class="col s6">
        <h2>Vendeur : <%=product.getUser().getPseudo()%></h2>
      </div>
      <div class="col s6">
        <h2><%=product.getPrice()%>€</h2>
      </div>
    </div>
    <div class="row">
      <div class="col s12">
        <div class="card-panel teal">
          <span class="white-text"><%=product.getDescription()%>
          </span>
        </div>
      </div>
    </div>
    <div class="row center-align" >
      <div class="col s6">
        <a type="button" class="btn btn-flat light-green">Ajouter au panier</a>
      </div>
      <div class="col s6">
        <a type="button" class="btn btn-flat  red lighten-1 ">Contacter le vendeur</a>
      </div>
    </div>
  </div>
</div>

<script>
  var idProductForAjax = "<%out.print(product.getId());%>";
</script>
<div id="commentSection" class="container">
</div>
