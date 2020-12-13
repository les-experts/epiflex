<%@ page contentType="text/html; charset=UTF-8" %>

        <div id="row-recherche" class="row">
          <div class="col s6">
            <div id="input-field-select-categorie" class="input-field">
              <select id="select-categorie">
                <option value="" disabled>Choisir une catégorie</option>
                <option value="0" selected>Toutes les catégories</option>
                <option value="1">Viennoiseries</option>
                <option value="2">Fruits & Légumes</option>
                <option value="3">Produits Laitiers</option>
                <option value="3">Drogues éco-responsables</option>
              </select>
            </div>
          </div>
          <div class="col s6">
            <div class="row">
              <div class="col s12">
                <div class="input-field">
                  <input placeholder="AOP, Raclette, Jambon sec" id="mots_cles" type="text" class="validate">
                  <label for="mots_cles">Mots-clés</label>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col s12">
                <div class="row">
                  <span> Intervalle de prix </span>
                </div>
                <div class="row">
                  <div class="col s2">
                    <input type="text" id="prix_min" readonly>
                  </div>
                  <div class="col s8">
                    <p>
                      <label for="amount">Price range:</label>
                      <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
                    </p>

                    <div id="slider-prix"></div>
                  </div>
                  <div class="col s2">
                    <input type="text" id="prix_max" readonly>
                  </div>
                </div>

              </div>
            </div>

          </div>
        </div>
  <script src="../js/marketplace.js"></script>
