<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="models.Product"%>

  <%
    List<Product> products = (List<Product>) request.getAttribute("productList");
    if (products != null && !products.isEmpty() ){
    %>
    <div class="row" id="row-products">

        <% for (Product prod : products) {
          int maxLength = 20;
          String title = prod.getTitle();
          int endSubstr = Math.min(maxLength, title.length());
          String endString = "";
          if (endSubstr == maxLength) {
            endString = "...";
          }
        %>

        <div class="col s6 m4 l3">

          <div class="card hoverable small">

            <div class="activator card-image waves-effect waves-block waves-light">
              <img class="activator" src="<%=prod.getPicturePath()%>">
            </div>

            <div onclick="location.href='/epiflex/Product?id=<%=prod.getId()%>';" class="card-content">
              <span class="card-title center-align grey-text text-darken-4"><%=title.substring(0,endSubstr) + endString %></span>
              <div class="row center-align row-price">
                <span><%=prod.getPrice()%>€</span>
              </div>
            </div>

            <div class="card-reveal">
              <span class="card-title grey-text text-darken-4"><%=title.substring(0,endSubstr) + endString %><i class="material-icons right">close</i></span>
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
    <div  class="row center-align" id="row-products">
      <h2>Aucun produit n'a été trouvé</h2>
    </div>
    <% } %>

    <div>
      <a class="btn_add_product btn-floating btn-large waves-effect waves-light" href="/epiflex/AddProduct"><i class="material-icons">add</i></a>
    </div>
