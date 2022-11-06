function registrationProcess(state){
	if(state == true){
		createSignInForm(signIn_form);
		createMessage("Registration is success!<br>");
	}else{
		createMessage("Registration is bad, please retry!<br>");
	}
}


function authorizationProcess(state){
	if(state == true){
		var catalog_date = new Array();
		catalog_date = getCatalog();
		createCatalog(catalog_form, catalog_date);
		
	}else{
		createMessage("Authorization is bad, please retry!<br>");
	}
}