  function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    // files is a FileList of File objects. List some properties.
    var output = [];
    for (var i = 0, f; file = files[i]; i++) {

        var reader = new FileReader();
        reader.readAsText(file, "UTF-8");
        reader.onload = function (evt) {
         $.ajax({
             type: "POST",
//             dataType: "application/json",
             url: "http://localhost:8080/wagesystem/introduce",
             data:evt.target.result,
             success: function(data){
                $.each(data, function() {
                    $("#returnVal").append(" <tr><td>"+ this.id +"</td><td>"+this.name +"</td><td>"+ this.wage  +"</td></tr>");
                });
             },
            error: function( jqXHR, textStatus, errorThrown ){
              alert(textStatus +"; "+errorThrown);
            }
         });
        }
        reader.onerror = function (evt) {
            window.alert( "error reading file");
        }
    }
  }

$(function(){
  $( "#files" ).bind('change', handleFileSelect);
});