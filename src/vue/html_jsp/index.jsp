<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="template/link.html" />
    <%

      ArrayList<String> list = (ArrayList<String>) request.getAttribute("csslink");
      for (String val : list) {
        %>
        <link href="src/vue/css/<%=val%>" rel="stylesheet">
        <%
      }
    %>
    <title>Remplacer_par_un_titre</title>
  </head>

  <body class="container">

      <jsp:include page="template/epiflex_epiceries.html" />

      <jsp:include page="template/nav.jsp" />

      <main>
        <!--Mettre le html là dedans
        Faites des trucs beaux en utilisant les grilles de materialize svp
        https://materializecss.com/grid.html-->

          <%-- <jsp:include page="login/index.jsp" /> --%>
            <% String linkMain =  (String)request.getAttribute("link"); %>
            <jsp:include page="<%=linkMain %>" />
      </main>

      <jsp:include page="template/footer.jsp" />

  </body>

  <!--mettre le js ici après le reste pour que la page s'affiche avant #astuce-->

<html>
