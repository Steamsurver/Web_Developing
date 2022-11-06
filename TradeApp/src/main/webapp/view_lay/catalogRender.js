var catalogRendering = (function() {
//*********************************************  

var root = undefined;
var catalog_data = undefined
var messCont = createDiv("contentMessage", null, "");

function _render(){
	createMessage("");

	//===========================================
	var del_elements = root.getElementsByTagName("form");
	for(var i = 0; i < del_elements.length; i++)
		del_elements[i].remove();


	var catalog_form = createForm("catalog_form", null);
		
	//===========================Пользователь==================================
	var cont = createDiv("dropdownContainer", null, "");
	var img = document.createElement("img");
	
	var dropContent = createDiv("dropdownContent", null, "");
	
	
	var dropLine = createDiv("dropdownContentLine", null, "");
	dropLine.setAttribute("onclick", "triggerSignInForm()");
	dropLine.innerHTML = "Выйти";
	
	var dropLine2 = createDiv("dropdownContentLineMessage", null, "");
	dropLine2.innerHTML = "Ключи";
	
	img.setAttribute("src",  "images/user_icon.png");
	img.setAttribute("id", "userImg");
	
	
	
	//===============
	
	var block_mess_click = true;
	dropLine2.addEventListener("click", function style(){
		if(block_mess_click){
			messCont.setAttribute("style", "display: block;");
			block_mess_click = false;
		}else{
			messCont.setAttribute("style", "display: none;");
			block_mess_click = true;
		}
	});
	//===============
	
	
	//===============
	for(i = 0; i < catalog_data.length; i++){
		var pr_c = document.createElement("div");
		
		_createCatalogElement(catalog_data[i], pr_c);
		
		catalog_form.appendChild(pr_c);
		catalog_form.appendChild(document.createElement("br"));
	}
	
	
	
	//===============
	dropContent.appendChild(dropLine2);
	dropContent.appendChild(dropLine);
	dropContent.appendChild(messCont);
	
	cont.appendChild(img);
	cont.appendChild(dropContent);
	catalog_form.appendChild(cont);
	root.appendChild(catalog_form);
	//=========================================================================
}


function _createCatalogElement(element, paragraph){
	var button_style = "position: relative;"
	+"left: 17.7vw;"
	+"width: 8vw;"
	+"height: 3vw;"
	+"";
	
	paragraph.setAttribute("id", "styleCatalogElement");
		
	var image = createImg("styleCatalogImg", element.img_src, "200", "130");
	
	var name = createDiv("styleCatalogName", null, "");
	name.innerHTML = element.game_name;
	
	var cost = createDiv("styleCatalogCost", null, "");
	cost.innerHTML = element.cost + " р";
	
	var desc = createDiv("styleCatalogDesc", null, "");
	desc.innerHTML = element.desc;
	
	var buy_button =  createButton(element.id, button_style, "Купить", null, "buttonBuy");
	
	buy_button.addEventListener("click", function key(){
		messCont.innerHTML += triggerBuyGameKeyButton(element.id) + "<br>";
	});

	paragraph.appendChild(name);
	paragraph.appendChild(cost);
	paragraph.appendChild(image);
	paragraph.appendChild(buy_button);
	paragraph.appendChild(desc);
}


function _init(_root, _catalog_data) {
   root = _root;
   catalog_data = _catalog_data; 
   _render();
}


return {
  renderCatalog: _init  
 };

//*********************************************   
})();