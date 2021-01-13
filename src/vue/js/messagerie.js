  function ajax_message(idOther){
    $.ajax({
      dataType: "html",
      type : 'GET',
      url: "/epiflex/Discussion",
      data: "id="+idOther,
      success: function(html_content,status){
        $("#messageContent").html(html_content);
      }
    });
  }
