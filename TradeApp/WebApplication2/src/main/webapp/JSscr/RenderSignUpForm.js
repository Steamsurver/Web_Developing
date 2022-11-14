function createSignUpForm(idForm){
	var buttonIn = 
	"position: relative;"
	+"top: 20px;";

	
	var buttonBack =
	"position: absolute;"
 	+"top: 20px;"
  	+"right: 20px;";
  	
	createMessage("");
	
	var del_elements = document.getElementsByTagName("form");
	for(var i = 0; i < del_elements.length; i++)
		del_elements[i].remove();
	
	var reg_form = document.createElement("form");
	reg_form.setAttribute("id", idForm);
	
	var pr = document.createElement("p");
	pr.setAttribute("align", "center");
	
	pr.innerHTML += "Регистрация<br>";
	
	var inp_log = document.createElement("input");
	var inp_pass = document.createElement("input");
	
	inp_log.setAttribute("type", "text");
	inp_pass.setAttribute("type", "text");
	
	inp_log.setAttribute("id", "reg_login_bar");
	inp_pass.setAttribute("id", "reg_password_bar");
	
	pr.innerHTML += "Логин:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(inp_log);
	pr.appendChild(document.createElement("br"));  
	pr.innerHTML += "Пароль:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(inp_pass);
	
	
	var button_container = document.createElement("div");
	
	var reg_button = document.createElement("input");
	reg_button.setAttribute("type", "button");
	reg_button.setAttribute("id", "buttonContainer");
	reg_button.setAttribute("style", buttonIn);
	reg_button.setAttribute("value", "Отправить данные");
	reg_button.setAttribute("onClick", "signUp()");
	
	var back_button = document.createElement("input");
	back_button.setAttribute("type", "button");
	back_button.setAttribute("id", "buttonContainer");
	back_button.setAttribute("style", buttonBack);
	back_button.setAttribute("value", "Назад");
	back_button.setAttribute("onClick", "createSignInForm(signIn_form)");
	
	button_container.appendChild(reg_button);
	button_container.appendChild(back_button);
	pr.appendChild(button_container);
	reg_form.appendChild(pr); 
	document.body.appendChild(reg_form);
	
} 