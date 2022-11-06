var IOclass = (function() {
//*********************************************  
var flagAsync = false;
var userToken = undefined;

function _signIn(login, password){
	var data = JSON.stringify([login, password]);
	var xhr = new XMLHttpRequest();
  	xhr.open("POST", "api/SignIn", flagAsync);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
    
    if(xhr.status == "200"){
		userToken = JSON.parse(xhr.responseText);
		return true;
	}
	else{
		return false;
	}
}
  

function _signUp(login, password){
	var data = JSON.stringify([login, password]);
	var xhr = new XMLHttpRequest();
  	xhr.open("POST", "api/SignUp", flagAsync);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
    
    if(xhr.status == "200"){
		return true;
	}
	else{
		return false;
	}
}


function _getCatalog(){
	var xhr = new XMLHttpRequest();
  	var data = JSON.stringify([userToken]);
  	xhr.open("POST", "api/Catalog/GameCatalog", flagAsync);
  	xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
    
    var catalogJSON = new Array();
   if(xhr.status == "200"){
    	for(i = 0; i < JSON.parse(xhr.responseText).length; i++){
    		catalogJSON[i] = JSON.parse(xhr.responseText)[i];
    	}
    	return catalogJSON;
    }
    
    return xhr.responseText; 
}


function _getKey(id_game){
	var data = JSON.stringify([userToken+"", id_game+""]);
	var xhr = new XMLHttpRequest();
  	xhr.open("POST", "api/Key", flagAsync);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
    
    if(xhr.status == "200"){
		console.log(xhr.responseText);
		return JSON.parse(xhr.responseText);
	}
	
}


return {
  checkSignIn: _signIn,
  trunSignUp: _signUp,
  getCatalog: _getCatalog,  
  getKey: _getKey  
 };

//*********************************************   
})();