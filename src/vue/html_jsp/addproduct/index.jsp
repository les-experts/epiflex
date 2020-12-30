<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="models.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Category"%>

<%
      //boolean isConnected = (boolean) request.getAttribute("isConnected");
      //User user = (User) request.getSession().getAttribute("user");
%>

<div class="row">
  <div class="col s12">
    <h2>Ajout d'un produit à vendre</h2>
  </div>
</div>
<div class="row">
  <div class="col s12">
    <%if(request.getAttribute("errorForm") != null){
        boolean errorForm = (boolean) request.getAttribute("errorForm");
        if(errorForm){
          out.println("<p style=\"color:red; font-size:100%; text-align:center;\">Erreur : veuillez remplir tous les champs du formulaire</p>");
        }
        else{
          out.println("<p style=\"color:blue; font-size:100%; text-align:center;\">Votre produit a bien été ajouté à notre marketplace. </p>");
        }
    }%>
  </div>
</div>
<form method="POST" action="/epiflex/AddProduct">
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">title</i>
      <input name="title" id="title" type="text" class="validate" value="">
      <label for="title">Titre du produit</label>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12 m8 offset-m2">
      <i class="material-icons prefix">euro_symbol</i>
      <input name="price" id="price" type="text" class="validate" value="">
      <label for="price">Prix</label>
    </div>
  </div>
  <div class="row">
    <div id="input-field-select-categorie"  class="input-field col s12 m8 offset-m2">
      <select id="select-categorie" name="categorie">
        <option value="" disabled>Choisir une catégorie</option>
        <%
        ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
        if (categories != null) {
          for (Category cat : categories) {
        %>
        <option value="<%=cat.getId()%>"><%=cat.getLabel()%></option>

        <% }} %>
      </select>
    </div>
  </div>
  <div class="row">
      <div class="input-field col s12 m8 offset-m2">
        <i class="material-icons prefix">subject</i>
        <textarea id="description" name="description" class="materialize-textarea"></textarea>
        <label for="description">Description</label>
      </div>
  </div>
  <div class = "row">
      <div class = "col s12 m8 offset-m2">
        <i class="material-icons prefix">insert_photo</i>
         <label>Photo du produit</label>
         <div class = "file-field input-field">
            <div class = "btn">
               <span>Browse</span>
               <input type = "file"/>
            </div>
            <div class = "file-path-wrapper">
               <input class = "file-path validate" type = "text"
                  placeholder = "Upload file" />
            </div>
         </div>
       </div>
  <div class="row">
    <div class="col s12 center-align">
      <button class="btn waves-effect waves-light" type="submit" name="confirmAddProduct" >Ajouter le produit à mes ventes
      <i class="material-icons right">send</i>
    </div>
  </div>
</form>
