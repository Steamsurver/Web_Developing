export default function IOmodule(){
	this.flagAsync = false;
	this.userToken = undefined;
	this.userAccessLevel = undefined;
}

IOmodule.signIn = async function(login, password){
	let response = await fetch("api/SignIn", {
  	method: 'POST',
  	headers: {
    'Content-Type': 'application/json;charset=utf-8'
  	},
  	body: JSON.stringify([login, password])
	});
	
	if(response.ok){
		let result  = await response.json();
		this.userToken = result.password;
		this.userAccessLevel = result.accessLevel;
		}
	else
		throw response.status;
}
  
  
IOmodule.getUserAccessLevel = function(){
	return this.userAccessLevel;
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
	
IOmodule.postNewProduct = async function(name, cost, desc, imgsrc){
    let response = await fetch("api/Catalog/PostProduct", {
  	method: 'POST',
  	headers: {
    'Content-Type': 'application/json;charset=utf-8',
    'logs': JSON.stringify([this.userToken])
  	},
  	body: JSON.stringify([name, cost, desc, imgsrc])
  	
	});
	
	
	if(!response.ok)
		throw response.status;
	}
//*********************************************