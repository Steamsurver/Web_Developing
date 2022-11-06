var signInRendering = (function() {
//*********************************************  

var root = undefined;


function _render() {
	var buttonIn = 
	"position: relative;"
	+"top: 20px;";
	
	var buttonUp =
	"position: absolute;"
 	+"top: 20px;"
  	+"right: 20px;";
	
	var del_forms = root.getElementsByTagName("form");
	var del_len = del_forms.length;
	for(var i = del_len-1; i > -1; i--){
		del_forms[i].remove();
	}

	var reg_form = createForm("signIn_form", null);
	var pr = document.createElement("p");
	pr.setAttribute("align", "center");
	
	var inp_log = createTextbar("login_bar", null, "");
	var inp_pass =  createTextbar("password_bar", null, "");
	
	pr.innerHTML += "Логин: <br>";
	pr.appendChild(inp_log);
	pr.appendChild(document.createElement("br"));  
	pr.innerHTML += "Пароль:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(inp_pass);
	
	var button_container_in = document.createElement("div");
	var signIn_button = createButton("buttonContainer", buttonIn, "Авторизация", "triggerSignInButton()", undefined);
	var signUp_button = createButton("buttonContainer", buttonUp, "Регистрация", "triggerSignUpForm()", undefined);
	    	   
	button_container_in.appendChild(signIn_button);
	button_container_in.appendChild(signUp_button);
	pr.appendChild(button_container_in);
	reg_form.appendChild(pr);
	root.appendChild(reg_form);
	document.body.appendChild(root);
}    


function _init(_root) {
   root = _root; 
   _render();
}


return {
  renderSignIn: _init  
 };

//*********************************************   
})();