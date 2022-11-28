import * as elementCreator from '../elementCreator.js'
	//===============================selector variables=================================
	let elementContainer = undefined;
	let buttonContainer = undefined;
	let buttonAddOk = elementCreator.createButton(null, null, "Отправить запрос", null, "buttonBuy");
	
	//==============================================================================


	export default class AdminSettingsRender {
	//*********************************************  

	constructor(root){
		this.root = root;
		this.settingsForm = undefined;
	}

	setRootPoint(root){
		this.root = root;
	}

	render() {
		this.settingsForm = elementCreator.createForm(null, null);
		this.settingsForm.setAttribute("class", "settingWrapper");
		
		_renderSettingsSelector(this.settingsForm);
		
		this.root.appendChild(this.settingsForm);
	}
	
	setAddButtonEvent(event){
		buttonAddOk.addEventListener("click", event);
	}
	
	getAddProductInformation(){
		let arrayInform = new Array();
		arrayInform.push(document.getElementById("name").value);
		arrayInform.push(document.getElementById("cost").value);
		arrayInform.push(document.getElementById("desc").value);
		arrayInform.push(document.getElementById("img").value);
		
		return arrayInform;
	}
	//*********************************************   
}
	
	//===============================Locale function================================


function _renderSettingsSelector(form){
	buttonContainer = document.createElement("ul");
	buttonContainer.setAttribute("class", "settingsButtonContainer");
	
	let buttonAddProduct = elementCreator.createButton (null, null, "Добавить в каталог", null, "settingButton");
	let buttonDelProduct = elementCreator.createButton (null, null, "Удалить из каталога", null, "settingButton");
	let buttonAcceptReg = elementCreator.createButton  (null, null, "Подтверждение регистрации", null, "settingButton");
	//-----
	buttonAddProduct.addEventListener("click", ()=>{
		_renderAddCatalogSettings(form)
	});
	//-----
	
	let list1 = document.createElement("li");
	let list2 = document.createElement("li");
	let list3 = document.createElement("li");
	
	list1.appendChild(buttonAddProduct);
	list2.appendChild(buttonDelProduct);
	list3.appendChild(buttonAcceptReg);
	
	buttonContainer.appendChild(list1);
	buttonContainer.appendChild(list2);
	buttonContainer.appendChild(list3);
	
	form.appendChild(buttonContainer);
}


function _renderAddCatalogSettings(form){
	if(form.getElementsByClassName("addSettingContainer")[0] !== undefined)
		form.getElementsByClassName("addSettingContainer")[0].remove();
	
	elementContainer = document.createElement("ul");
	elementContainer.setAttribute("class", "addSettingContainer");
	
	let name 	   = elementCreator.createTextbar("name", null, "");
	name.setAttribute("class", "addSettingBar");
	
	let cost 	   = elementCreator.createTextbar("cost", null, "");
	cost.setAttribute("class", "addSettingBar");
	
	let img_source = elementCreator.createTextbar("img", null, "");
	img_source.setAttribute("class", "addSettingBar");
	
	let desc 	   = elementCreator.createTextbar("desc", null, "");
	desc.setAttribute("class", "addSettingBarDesc");
	
	let list1 = document.createElement("li");
	let list2 = document.createElement("li");
	let list3 = document.createElement("li");
	let list4 = document.createElement("li");
	
	list1.innerHTML += "Название<br>";
	list1.appendChild(name);
	list1.innerHTML += "<br><br>";
	
	list2.innerHTML += "Описание<br>";
	list2.appendChild(desc);
	list2.innerHTML += "<br><br>";
	
	list3.innerHTML += "Цена<br>";
	list3.appendChild(cost);
	list3.innerHTML += "<br><br>";
	
	list4.innerHTML += "Источник изображения<br>";
	list4.appendChild(img_source);
	list4.innerHTML += "<br><br>";
	
	elementContainer.appendChild(list1);
	elementContainer.appendChild(list2);
	elementContainer.appendChild(list3);
	elementContainer.appendChild(list4);
	elementCreator.createButton(null, null, "Отправить запрос", null, "buttonBuy");
	
	elementContainer.appendChild(buttonAddOk);
	form.appendChild(elementContainer);
}

//==============================================================================




