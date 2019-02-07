
function fieldsToJson(){
        var obj = {};
        var form = document.getElementById( "periodform" );
		var elements = form.querySelectorAll( "input" );
		for( var i = 0; i < elements.length; ++i ) {
			var element = elements[i];
			var name = element.name;
			var value = element.value;

			if( name ) {
				obj[ name ] = value;
			}
		}
		return JSON.stringify( obj );
}

function sendMessage() {
   var xhr = new XMLHttpRequest();
   var url = "/stat";
   xhr.open("POST", url, true);
   xhr.setRequestHeader("Content-Type", "application/json");
   xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
             obj = JSON.parse(xhr.response);
                  document.querySelector("#fp").textContent =obj.cntvst;
                  document.querySelector("#numusr").textContent =obj.cntusr;
                  document.querySelector("#numloyal").textContent =obj.cntloyal;
              }
          };
   var json = fieldsToJson();
   xhr.send(json);
}