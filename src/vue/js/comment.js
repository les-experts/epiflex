$.ajax({
  dataType: "html",
  type : 'GET',
  url: "/epiflex/Comment",
  data: "id=5",
  success: function(html_content,status){
    $("#commentSection").html(html_content);
    start_onclick();
  }
});

function start_onclick(){
  $(".form_ranking_star").click(function(){
      var nStar = $(this).attr("data");
      $("#input_rank").attr("value",nStar);
      $(".form_ranking_star").each(function(){
        if($(this).attr("data") <= nStar){
          $(this).attr("src","statique/etoile-full.png");
        }else {
          $(this).attr("src","statique/etoile-empty.png");
        }
      })
    }
  );
}
