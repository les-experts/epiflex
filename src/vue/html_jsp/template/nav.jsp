<%@ page contentType="text/plain; charset=UTF-8" %>
<header>
  <nav>
    <div class="green nav-wrapper">
      <a href="/epiflex/MarketPlace" class="brand-logo center">Marketplace</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="#">Mes produits</a></li>
        <li><a href="#"><i class="material-icons">shopping_cart</i></a></li>
        <li><a href="#"><i class="material-icons">email</i></a></li>
        <li><a href="#" data-toggle="modal" data-target="#myModal"><i class="material-icons right" data-toggle="modal" data-target="#myModal">face</i>Alexis</a></li>
      </ul>
    </div>
  </nav>
</header>

<div class="modal" id="myModal">
   <div class="modal-dialog">
      <div class="modal-content">
         <!-- Modal Header -->
         <form method="POST" action="/Authentication">
           <div class="modal-header">
              <h4 class="modal-title">Connexion</h4>
           </div>
           <!-- Modal body -->
           <div class="modal-body">
               <table>
                 <tr>
                    <td><label for="username">Nom d'utilisateur</label></td>
                    <td><input id="username" type="text" name="username"></td>
                 </tr>
                 <tr>
                   <td><label for="password">Mot de passe</label></td>
                   <td><input id="password" type="password" name="password"></td>
                 </tr>
               </table>
           </div>
           <!-- Modal footer -->
           <div class="modal-footer">
               <input type="submit" class="btn btn-primary" value="envoyer">
              <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
         </form>
      </div>
   </div>
</div>
