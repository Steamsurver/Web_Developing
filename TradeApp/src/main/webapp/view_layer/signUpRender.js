import {createButton, createForm, createTextbar, createDiv, createImg, } from '../view_layer/elementCreator.js'

export default class SignUpRender {
//*********************************************  

constructor(root){
	this.root = root;
	this.inp_log = undefined;
	this.inp_pass = undefined;
	this.reg_button = undefined;
	this.back_button = undefined;
}

setRootPoint(root){
	this.root = root;
}

render() {
	var buttonIn = 
	"position: relative;"
	+"top: 20px;";

	
	var buttonBack =
	"position: absolute;"
 	+"top: 20px;"
  	+"right: 20px;";
  	
	//createMessage("");
	
	var reg_form = createForm("signUp_form", null);
	var pr = document.createElement("p");
	pr.setAttribute("align", "center");
	
	pr.innerHTML += "Регистрация<br>";
	
	this.inp_log = createTextbar("reg_login_bar", null, "");
	this.inp_pass = createTextbar("reg_password_bar", null, "");
	
	pr.innerHTML += "Логин:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(this.inp_log);
	pr.appendChild(document.createElement("br"));  
	pr.innerHTML += "Пароль:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(this.inp_pass);
	
	var button_container = document.createElement("div");
	this.reg_button = createButton("buttonContainer", buttonIn, "Отправить данные", null, undefined);
	this.back_button = createButton("buttonContainer", buttonBack, "Назад", null, undefined);
	button_container.appendChild(this.reg_button);
	button_container.appendChild(this.back_button);
	pr.appendChild(button_container);
	reg_form.appendChild(pr); 
	this.root.appendChild(reg_form);
}    
//*********************************************   
setSignUpEvents(events){	
	this.reg_button.addEventListener("click", events[0]);
	this.back_button.addEventListener("click", events[1]);
}

getTextBars(){
	return [document.getElementById("reg_login_bar").value, document.getElementById("reg_password_bar").value];
}

};