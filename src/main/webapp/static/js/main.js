  function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    // files is a FileList of File objects. List some properties.
    var output = [];
    for (var i = 0, f; file = files[i]; i++) {

        var reader = new FileReader();
        reader.readAsText(file, "UTF-8");
        reader.onload = function (evt) {
         $.ajax({
             type: "PUT",
             dataType: "text/plain",
             url: "http://localhost:8080/wagesystem/introduce/"+evt.target.result,
             success: function(data){
                alert(data);
             }
         });
//            document.getElementById("fileContents").innerHTML = evt.target.result;
        }
        reader.onerror = function (evt) {
            window.alert( "error reading file");
        }
    }
  }

$(function(){
  $( "#files" ).bind('change', handleFileSelect);
});