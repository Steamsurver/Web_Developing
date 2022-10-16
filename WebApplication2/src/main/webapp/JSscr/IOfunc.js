var signIn_form = "signIn_form";
var signUp_form = "signUp_form";
var catalog_form = "catalog_form";
var flagAsync = false;

function signIn(){
	var login = document.getElementById("login_bar").value;
	var password = document.getElementById("password_bar").value;
	
	var data = JSON.stringify([login, password]);
	
	var xhr = new XMLHttpRequest();
  	
  	xhr.open("POST", "api/signIn", flagAsync);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
    
    if(xhr.responseText == "1"){
		authorizationProcess(true);
	}
	else{
		authorizationProcess(false);
	}
}



function signUp(){
	var login = document.getElementById("reg_login_bar").value;
	var password = document.getElementById("reg_password_bar").value;
	
	var data = JSON.stringify([login, password]);
	
	var xhr = new XMLHttpRequest();
  	
  	xhr.open("POST", "api/signUp", flagAsync);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
    
    
    if(xhr.responseText == "1"){
		registrationProcess(true);
	}
	else{
		registrationProcess(false);
	}
}


function getCatalog(){
	var xhr = new XMLHttpRequest();
  	
  	xhr.open("GET", "api/catalog/getList", flagAsync);
    xhr.send();
    
    var catalogJSON = new Array();
    for(i = 0; i < JSON.parse(xhr.responseText).length; i++){
    	catalogJSON[i] = JSON.parse(xhr.responseText)[i];
    }
    return catalogJSON;
}


function getKey(id_game){
	var xhr = new XMLHttpRequest();
  	
  	xhr.open("POST", "api/keyGeter", flagAsync);
    xhr.setRequestHeader("Content-Type", "text/plain");
    xhr.send(id_game);
   	document.getElementById("contentMessage").innerHTML += xhr.responseText + "<br>";
}

