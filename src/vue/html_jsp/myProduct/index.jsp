<%@ page import="java.util.List"%>
<%@ page import="models.Product"%>

<section class="myProduct">
  testtetstets
  <%
  List<Product> listProduct = (List<Product>) request.getAttribute("productList");
  for (Product val : listProduct) {
    %>
    <article>
      <% out.print(val.getTitle()); %> <br>
      <% out.print(val.getPicturePath()); %> <br>
      <% out.print(val.getPrice()); %> <br>
      <% out.print(val.getDescription()); %> 


    </article>
<% }
%>
</section>
