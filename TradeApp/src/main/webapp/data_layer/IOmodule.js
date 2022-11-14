export default function IOmodule(){
	this.flagAsync = false;
	this.userToken = undefined;
}

IOmodule.signIn = async function(login, password){
	let response = await fetch("api/SignIn", {
  	method: 'POST',
  	headers: {
    'Content-Type': 'application/json;charset=utf-8'
  	},
  	body: JSON.stringify([login, password])
	});
	
	if(response.ok)
		this.userToken = await response.json();
	else
		throw response.status;
}
  

IOmodule.signUp = async function(login, password){
    let response = await fetch("api/SignUp", {
  	method: 'GET',
  	headers: {
    'logs': JSON.stringify([login, password])
  	}
	});
	console.log(JSON.stringify([login, password]));
		if(!response.ok)
			throw response.status;
	}


IOmodule.getCatalog = async function(){
    let catalogJSON = new Array();
    let response = await fetch("api/Catalog/GameCatalog", {
  	method: 'GET',
  	headers: {
    'logs': JSON.stringify([this.userToken])
  	},
	});
    
    if(response.ok){
    	catalogJSON = await response.json();
    	return catalogJSON;
    }else
    	throw response.status;
}


IOmodule.getKey = function(id_game){
	let xhr = new XMLHttpRequest();
  	xhr.open("GET", "api/Key", this.flagAsync);
    xhr.setRequestHeader("logs", JSON.stringify([this.userToken+"", id_game+""]));
    xhr.send();
    
    if(xhr.status == "200"){
		console.log(xhr.responseText);
		return JSON.parse(xhr.responseText);
	}
	
}
//*********************************************