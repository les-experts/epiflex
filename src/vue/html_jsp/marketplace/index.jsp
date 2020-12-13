<%@ page contentType="text/html; charset=UTF-8" %>

<form action="#" method="GET" id="row-recherche" class="row">

  <div class="col-recherche col s6">
    <div class="row">
      <div class="col s12">
        <div class="input-field">
          <input name="mots_cles" id="mots_cles" type="text" class="validate">
          <label for="mots_cles">Mots-clés : AOP, Picardie, Manioc, ...</label>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col s12">
        <div class="row center-align">
          <span> Intervalle de prix </span>
        </div>
        <div class="row valign-wrapper">
          <div class="col s2">
            <span id="prix_min"></span>
            <input type="number" readonly class="hidden-input" name="prix_min">
          </div>
          <div class="col s8">
            <div id="slider-prix"></div>
          </div>
          <div class="col s2">
            <span id="prix_max"></span>
            <input type="number" readonly class="hidden-input" name="prix_max">

          </div>
        </div>

      </div>
    </div>

  </div>
  <div class="col-recherche col s6">
    <div class="row">
      <div id="input-field-select-categorie" class="input-field">
        <select id="select-categorie" name="categorie">
          <option value="" disabled>Choisir une catégorie</option>
          <option value="0" selected>Toutes les catégories</option>
          <option value="1">Viennoiseries</option>
          <option value="2">Fruits & Légumes</option>
          <option value="3">Produits Laitiers</option>
          <option value="4">Drogues éco-responsables</option>
        </select>
      </div>
    </div>
    <div class="row center-align">
      <button id="button-rechercher" class="btn green waves-effect waves-light" type="submit" name="action">Rechercher
        <i class="material-icons right">send</i>
      </button>
    </div>
  </div>
</form>

<jsp:include page="products.jsp" />
