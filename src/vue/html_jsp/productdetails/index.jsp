<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="models.Product"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
  Product product = (Product)request.getAttribute("product");
  application.log("dans la jsp :" + product.getTitle());
  session.setAttribute("product",product);
  List<Integer> panier;
  if(session.getAttribute("panier")!=null){
    application.log("ya un panier frero");
  }
  else{
    application.log("pas de panier");
  }
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
    <form method="POST" action="Product">
      <div class="row center-align" >
        <div class="col s6">
          <button class="btn waves-effect waves-light" type="submit" name="action" >Ajouter au panier
          <i class="material-icons right">add_shopping_cart</i>
        </div>
        <div class="col s6">
          <a type="button" class="btn btn-flat  red lighten-1 ">Contacter le vendeur</a>
        </div>
      </div>
    </form>
  </div>
</div>

<script>
  var idProductForAjax = "<%out.print(product.getId());%>";
</script>
<div id="commentSection" class="container">
</div>
