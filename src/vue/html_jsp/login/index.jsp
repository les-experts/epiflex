<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <%-- <jsp:include page="head.html" /> --%>
    <title>Epi'flex</title>
  </head>

  <body class="container">

      <%-- <jsp:include page="epiflex_epiceries.html" />
      <jsp:include page="header.jsp" /> --%>

      <main>
      	<form method="POST" action="/Authentication">
          <label for="username">Nom d'utilisateur</label>
          <input id="username" type="text" name="username">
          <label for="password">Mot de passe</label>
          <input id="password" type="password" name="password">
          <input type="submit" value="envoyer">
      	</from>
      </main>

      <%-- <jsp:include page="footer.jsp" /> --%>

  </body>
<html>
