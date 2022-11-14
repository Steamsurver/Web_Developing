import {createButton, createForm, createTextbar, createDiv, createImg, } from '../elementCreator.js'

export default class CatalogRender{
//*********************************************  
constructor(root){
	this.root = root;
	this.gameIds = undefined;
	this.buyButtons = undefined;
	this.messCont = undefined;
}

setRootPoint(root){
	this.root = root;
	this.gameIds = new Array();
	this.buyButtons = new Array();
	this.messCont = createDiv("contentMessage", null, "");
}

render(catalog_data){

	let catalog_form = createForm("catalog_form", null);
		
	//===========================Пользователь==================================
	let cont = createDiv("dropdownContainer", null, "");
	let img = document.createElement("img");
	
	let dropContent = createDiv("dropdownContent", null, "");
	
	
	let dropLine = createDiv("dropdownContentLine", null, "");
	dropLine.innerHTML = "Выйти";
	
	let dropLine2 = createDiv("dropdownContentLineMessage", null, "");
	dropLine2.innerHTML = "Ключи";
	
	img.setAttribute("src",  "images/user_icon.png");
	img.setAttribute("id", "userImg");
	
	
	
	//===============
	var block_mess_click = true;
	dropLine2.addEventListener("click", ()=>{
		if(block_mess_click){
			this.messCont.setAttribute("style", "display: block;");
			block_mess_click = false;
		}else{
			this.messCont.setAttribute("style", "display: none;");
			block_mess_click = true;
		}
	});
	//===============
	
	
	//===============
	for(let i = 0; i < catalog_data.length; i++){
		let pr_c = document.createElement("div");
		
		this._createCatalogElement(catalog_data[i], pr_c);
		
		catalog_form.appendChild(pr_c);
		catalog_form.appendChild(document.createElement("br"));
	}
	
	
	
	//===============
	dropContent.appendChild(dropLine2);
	dropContent.appendChild(dropLine);
	dropContent.appendChild(this.messCont);
	
	cont.appendChild(img);
	cont.appendChild(dropContent);
	catalog_form.appendChild(cont);
	this.root.appendChild(catalog_form);
	//=========================================================================
}

getGameIds(){
	return this.gameIds;
}

setFuncForBuyEvents(funcArray){
	for(let i = 0; i < funcArray.length; i++){
		this.buyButtons[i].addEventListener("click", ()=>{
			this.messCont.innerHTML += funcArray[i]() + "<br>";
	});
	}
}

_createCatalogElement(element, paragraph){
	let button_style = "position: relative;"
	+"left: 17.7vw;"
	+"width: 8vw;"
	+"height: 3vw;"
	+"";
	
	paragraph.setAttribute("id", "styleCatalogElement");
		
	let image = createImg("styleCatalogImg", element.img_src, "200", "130");
	
	let name = createDiv("styleCatalogName", null, "");
	name.innerHTML = element.game_name;
	
	let cost = createDiv("styleCatalogCost", null, "");
	cost.innerHTML = element.cost + " р";
	
	let desc = createDiv("styleCatalogDesc", null, "");
	desc.innerHTML = element.desc;
	
	let buy_button =  createButton(element.id, button_style, "Купить", null, "buttonBuy");
	this.gameIds.push(element.id);
	this.buyButtons.push(buy_button);

	paragraph.appendChild(name);
	paragraph.appendChild(cost);
	paragraph.appendChild(image);
	paragraph.appendChild(buy_button);
	paragraph.appendChild(desc);
}

//*********************************************   
};