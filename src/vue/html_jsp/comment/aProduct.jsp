<%@ page import="java.util.List"%>
<%@ page import="models.Comment"%>
<%@ page import="models.Product"%>

<%
  System.out.println("commentaires du produit : " + request.getAttribute("product"));

  Product product = (Product)request.getAttribute("product");

%>

<section class="commentHistory">
  <%
  boolean isConnected = (boolean) request.getAttribute("isConnected");
  String username = (String) request.getAttribute("username");
  List<Comment> listComment = (List<Comment>) request.getAttribute("listComment");

  for (Comment val : listComment) {
    %>
    <article>
      <header>
        <span class="author"><%=val.getPseudo()%></span>
        <span class="rank"><%
        int nbEtoile = 0;
        for(int i = 0; i<5;i++){
          if(nbEtoile<val.getRating()){
            nbEtoile++;
            %><img class="etoile" src="statique/etoile-full.png"><%
          }else{
            %><img class="etoile" src="statique/etoile-empty.png"><%
          }
        }%></span>
        <%-- <span class="date"><%=val.getDate()%></span> --%>
      </header>
      <aside>
        <%=val.getText()%>
      </aside>
    </article>
  <%
}
if(isConnected){
%>
  <div class="container form_comment">
    <h2>Donne ton avis sur ce produit !</h2>
    <form method="POST" action="Comment">
      <input name="id" type="hidden" value="<%=product.getId()%>">
      <div class="form_ranking">
        <img src="statique/etoile-full.png" class="form_ranking_star" data="1">
        <img src="statique/etoile-full.png" class="form_ranking_star" data="2">
        <img src="statique/etoile-full.png" class="form_ranking_star" data="3">
        <img src="statique/etoile-full.png" class="form_ranking_star" data="4">
        <img src="statique/etoile-full.png" class="form_ranking_star" data="5">
        <input id="input_rank" name="rank" type="hidden" value="5">
      </div>
      <div class="input-field col s6">
        <i class="material-icons prefix">mode_edit</i>
        <textarea id="icon_prefix2" name="comment" class="materialize-textarea"></textarea>
        <label for="icon_prefix2"><%=username%></label>
      </div>
      <div class="form_ranking_btn">
        <button class="btn waves-effect waves-light" type="submit" name="action">Envoyer
          <i class="material-icons right">send</i>
        </button>
      </div>
    </form>
  </div>
  <%}%>
</section>
