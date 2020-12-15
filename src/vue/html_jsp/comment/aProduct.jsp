
<%@ page import="java.util.List"%>
<%@ page import="models.Comment"%>
<section class="commentHistory">
<%
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
%>
</section>
