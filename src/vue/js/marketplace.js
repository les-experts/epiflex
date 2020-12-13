$(document).ready(function(){
  $("#select-categorie").formSelect();

  $("#slider-prix").slider({
    range: true,
    min: 0,
    max: 950,
    values : [ 0, 950],
    slide: function( event, ui ) {
      $("#prix_min").val( ui.values[ 0 ] + "€");
      $("#prix_max").val( ui.values[ 1 ] + "€");
    }
  });

  //$("#prix_min").val( $("#slider-prix").slider("value", 0) + "€");
  //$("#prix_max").val( $("#slider-prix").slider( "value", 1 ) + "€");
});
