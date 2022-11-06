var current_user_login = undefined;
var current_user_password = undefined;
var current_calatog = undefined;

function triggerSignInButton(){
	var login = document.getElementById("login_bar").value;
	var password = document.getElementById("password_bar").value;
	if(IOclass.checkSignIn(login, password)){
		current_user_login = login;
		current_user_password = password;
		current_calatog = IOclass.getCatalog();
		
		if(current_calatog == "501"){
			createMessage("Проведите авторизацию.<br>");
		}else if(current_calatog == null){
			createMessage("Проблема в сервером, попробуйте подключиться позже.<br>");
		}else{
			triggerCatalogForm(current_calatog);
		}
	}else{
		createMessage("Указанные данные не верны.<br>");
	}
}


function triggerSignUpButton(){
	var login = document.getElementById("reg_login_bar").value;
	var password = document.getElementById("reg_password_bar").value;
	if(IOclass.trunSignUp(login, password)){
		triggerSignInForm();
	}else{
		createMessage("Указанные данные не подходят для регистрации.<br>");
	}
}


function triggerBuyGameKeyButton(id_key){
	var key = IOclass.getKey(id_key);
	
	if(key != '501')
		return key;
	else
		triggerSignInForm();
}