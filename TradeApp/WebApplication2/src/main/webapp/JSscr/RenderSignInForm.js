function createSignInForm(idForm){
	var buttonIn = 
	"position: relative;"
	+"top: 20px;";

	
	var buttonUp =
	"position: absolute;"
 	+"top: 20px;"
  	+"right: 20px;";

	
	var del_elements = document.getElementsByTagName("form");
	for(var i = 0; i < del_elements.length; i++)
		del_elements[i].remove();
	
	var reg_form = document.createElement("form");
	reg_form.setAttribute("id", idForm);
	
	var pr = document.createElement("p");
	pr.setAttribute("align", "center");
	
	var inp_log = document.createElement("input");
	var inp_pass = document.createElement("input");
	
	inp_log.setAttribute("type", "text");
	inp_pass.setAttribute("type", "text");
	
	inp_log.setAttribute("id", "login_bar");
	inp_pass.setAttribute("id", "password_bar");
	
	pr.innerHTML += "Логин: <br>";
	pr.appendChild(inp_log);
	pr.appendChild(document.createElement("br"));  
	pr.innerHTML += "Пароль:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(inp_pass);
	
	
	var button_container_in = document.createElement("div");
	
	var signIn_button = document.createElement("input");
	signIn_button.setAttribute("type", "button");
	signIn_button.setAttribute("id", "buttonContainer");
	signIn_button.setAttribute("style", buttonIn);
	signIn_button.setAttribute("value", "Авторизация");
	signIn_button.setAttribute("onClick", "signIn()");
	
	var signUp_button = document.createElement("input");
	signUp_button.setAttribute("type", "button");
	signUp_button.setAttribute("id", "buttonContainer");
	signUp_button.setAttribute("style", buttonUp);
	signUp_button.setAttribute("value", "Регистрация");
	signUp_button.setAttribute("onClick", "createSignUpForm(signUp_form)");
	    	   
	    	    
	button_container_in.appendChild(signIn_button);
	button_container_in.appendChild(signUp_button);
	pr.appendChild(button_container_in);
	reg_form.appendChild(pr);
	document.body.appendChild(reg_form);
}