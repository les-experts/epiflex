function ajaxSearch() {

  var prixMax = "prix_max="+$("#slider-prix").slider("values", 1);
  var prixMin = "prix_min="+$("#slider-prix").slider("values", 0);
  var keywords = "mots_cles="+$("#mots_cles").val();
  var cat = "categorie="+$("#select-categorie").val();

  var data = [prixMax, prixMin, keywords, cat];

  $.ajax({
    type: 'GET',
    url: 'ListProducts',
    data: data.join("&"),
    success: function(data) {
      console.log("c bon frere");

      $("#products").html(data);

    },
    error: function(xhr, status, error) {
      var err = eval("(" + xhr.responseText + ")");
      console.log(err.Message);
    }
  });
}

$(document).ready(function(){

  $("#select-categorie").formSelect();

  $("#select-categorie").on("change", function() {
      ajaxSearch();
  });

  $("#slider-prix").slider({
    range: true,
    min: 0,
    max: 950,
    values : [ 0, 950],
    stop: function( event, ui ) {
      ajaxSearch();
    },
    slide: function(event, ui) {
      $("#prix_min").text( ui.values[ 0 ] + "€");
      $("#prix_max").text( ui.values[ 1 ] + "€");
      $("input[name='prix_min']").val( ui.values[ 0 ]);
      $("input[name='prix_max']").val( ui.values[ 1 ]);
    }
  });

  $("#prix_min").text( $("#slider-prix").slider("values", 0) + "€");
  $("#prix_max").text( $("#slider-prix").slider( "values", 1 ) + "€");

  $("input[name='prix_min']").val( $("#slider-prix").slider("values", 0));
  $("input[name='prix_max']").val( $("#slider-prix").slider( "values", 1 ));

  ajaxSearch();

  $("#mots_cles").keyup(function() {
    ajaxSearch();
  });

});
