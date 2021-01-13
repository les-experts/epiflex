<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="models.Product"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

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

    <div class="row center-align">
        <%
              boolean isConnected = (boolean) request.getAttribute("isConnected");
              boolean isAdmin = (boolean) request.getAttribute("admin");
        %>

        <form method="GET" action="Panier" class="col <% if (isConnected) { out.print("s6"); } else { out.print("s12"); } %> ">
          <input id="prodId" name="prodId" type="hidden" value="<%=product.getId()%>">
          <button class="btn waves-effect waves-light green lighten-1" type="submit">Ajouter au panier</button>
        </form>

        <% if(isConnected) {
        %>

        <div class="col <% if (isAdmin) { out.print("s3"); } else { out.print("s6"); } %>">
          <a type="button" class="waves-effect waves-light modal-trigger btn red lighten-1" href="#modal-premier-message">Contacter le vendeur</a>
        </div>

        <% if(isAdmin) {
        %>
        <div class="col s3">
          <a type="button" class="waves-effect waves-light modal-trigger btn red lighten-1" href="/epiflex/Delete?id=<%=product.getId()%>">Supprimer l'article</a>
        </div>
        <%
          }
        %>


        <form method="POST" action="Messagerie" id="modal-premier-message" class="modal">
          <div class="modal-content">
            <div class="row center-align">
              <h4>Demandez un renseignement à <%=product.getUser().getPseudo()%> !</h4>
            </div>

            <input id="prodId" name="prodId" type="hidden" value="<%=product.getId()%>">

            <div class="row">
              <div class="input-field col s12">
                <i class="material-icons prefix">message</i>

                <textarea name="message" id="message" class="materialize-textarea" data-length="200"></textarea>
                <label for="message">Textarea</label>

              </div>
            </div>

            <div class="row">
              <div class="input-field col s12 center">
                <button class="btn waves-effect waves-light" type="submit" name="action">Envoyer
                  <i class="material-icons right">send</i>
                </button>
                <a type="button" class="btn btn-flat waves-effect modal-close">Annuler</a>
              </div>
            </div>
          </div>
        </form>

        <% } %>

    </div>

    <div class="row">
      <div class="col s12">
        <div class="card-panel teal">
          <span class="white-text text_desc"><%=product.getDescription()%>
          </span>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  var idProductForAjax = "<%out.print(product.getId());%>";
</script>
<div id="commentSection" class="container">
</div>
