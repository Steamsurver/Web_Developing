import {createButton, createForm, createTextbar} from '../view_layer/elementCreator.js'

export default class SignInRender {
//*********************************************  

constructor(root){
	this.root = root
	this.signIn_button = undefined;
	this.signUp_button = undefined;
	this.inp_log = undefined;
	this.inp_pass = undefined;
}

setRootPoint(root){
	this.root = root;
}

render() {
	let buttonIn = 
	"position: relative;"
	+"top: 20px;";
	
	let buttonUp =
	"position: absolute;"
 	+"top: 20px;"
  	+"right: 20px;";

	let reg_form = createForm("signIn_form", null);
	reg_form.setAttribute("class", "signInForm");
	let pr = document.createElement("p");
	pr.setAttribute("align", "center");
	
	this.inp_log = createTextbar("login_bar", null, "");
	this.inp_pass = createTextbar("password_bar", null, "");
	
	pr.innerHTML += "Логин: <br>";
	pr.appendChild(this.inp_log);
	pr.appendChild(document.createElement("br"));  
	pr.innerHTML += "Пароль:";
	pr.appendChild(document.createElement("br"));  
	pr.appendChild(this.inp_pass);
	
	let button_container_in = document.createElement("div");
	this.signIn_button = createButton("buttonContainer", buttonIn, "Авторизация", null, undefined);
	this.signUp_button = createButton("buttonContainer", buttonUp, "Регистрация", null, undefined);
	    	   
	button_container_in.appendChild(this.signIn_button);
	button_container_in.appendChild(this.signUp_button);
	pr.appendChild(button_container_in);
	reg_form.appendChild(pr);
	this.root.appendChild(reg_form);
}
//*********************************************   

setSignInEvents(events){
	this.signIn_button.addEventListener("click", events[0]);
	this.signUp_button.addEventListener("click", events[1]);
}

getTextBars(){
	return [document.getElementById("login_bar").value, document.getElementById("password_bar").value];
}
}