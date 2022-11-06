var signUpRendering = (function() {
//*********************************************  

var root = undefined;


function _render() {
	var buttonIn = 
	"position: relative;"
	+"top: 20px;";

	
	var buttonBack =
	"position: absolute;"
 	+"top: 20px;"
  	+"right: 20px;";
  	
	createMessage("");
	var del_forms = root.getElementsByTagName("form");
	var del_len = del_forms.length;
	for(var i = del_len-1; i > -1; i--){
		del_forms[i].remove();
	}
	
	var reg_form = createForm("signUp_form", null);
	var pr = document.createElement("p");
	pr.setAttribute("align", "center");
	
	pr.innerHTML += "Регистрация<br>";
	
	var inp_log = createTextbar("reg_login_bar", null, "");
	var inp_pass = createTextbar("reg_password_bar", null, "");
	
	pr.innerHTML += "Логин:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(inp_log);
	pr.appendChild(document.createElement("br"));  
	pr.innerHTML += "Пароль:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(inp_pass);
	
	var button_container = document.createElement("div");
	var reg_button = createButton("buttonContainer", buttonIn, "Отправить данные", "triggerSignUpButton()", undefined);
	var back_button = createButton("buttonContainer", buttonBack, "Назад", "triggerSignInForm()", undefined);
	
	button_container.appendChild(reg_button);
	button_container.appendChild(back_button);
	pr.appendChild(button_container);
	reg_form.appendChild(pr); 
	root.appendChild(reg_form);
}    


function _init(_root) {
   root = _root; 
   _render();
}


return {
  renderSignUp: _init  
 };

//*********************************************   
})();