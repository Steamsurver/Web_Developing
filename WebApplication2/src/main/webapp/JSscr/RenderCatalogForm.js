var global_mess = "";

function lay(global_id_exchanger){
		getKey(global_id_exchanger);
	}
	
	
function createCatalog(catalog_name, catalog_data){
	createMessage("");

	//===========================================
	var del_elements = document.getElementsByTagName("form");
	for(var i = 0; i < del_elements.length; i++)
		del_elements[i].remove();

	var catalog_form = document.createElement("form");
	catalog_form.setAttribute("id", catalog_name);
		
	//===========================Пользователь==================================
	var cont = document.createElement("div");
	var img = document.createElement("img");
	var dropContent = document.createElement("div");
	var dropLine = document.createElement("div");
	var dropLine2 = document.createElement("div");
	
	
	cont.setAttribute("id", "dropdownContainer");
	img.setAttribute("src",  "images/user_icon.png");
	img.setAttribute("id", "userImg");
	
	dropContent.setAttribute("id", "dropdownContent");
	dropLine.setAttribute("id", "dropdownContentLine");
	dropLine.setAttribute("onclick", "createSignInForm(signIn_form)");
	dropLine.innerHTML = "Выйти";
	
	//===============
	dropLine2.setAttribute("id", "dropdownContentLineMessage");
	dropLine2.innerHTML = "Ключи";
	
	var messCont = document.createElement("div");
	messCont.setAttribute("id", "contentMessage");
	
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
		
		createCatalogElement(catalog_data[i], pr_c);
		
		catalog_form.appendChild(pr_c);
		catalog_form.appendChild(document.createElement("br"));
	}
	
	
	
	//===============
	dropContent.appendChild(dropLine2);
	dropContent.appendChild(dropLine);
	dropContent.appendChild(messCont);
	
	cont.appendChild(img);
	cont.appendChild(dropContent);
	
	//=========================================================================
	
	
	catalog_form.appendChild(cont);
	document.body.appendChild(catalog_form);
	
	for(i = 0; i < document.getElementsByClassName("buttonBuy").length; i++){
		//global_id_exchanger = catalog_data[i].id;
		console.log(document.getElementsByClassName("buttonBuy")[i]);
		document.getElementsByClassName("buttonBuy")[i].setAttribute("onclick", "lay("
		+document.getElementsByClassName('buttonBuy')[i].getAttribute('id') + ")");
	}
}

function createCatalogElement(element, paragraph){
	var button_style = "position: relative;"
	+"left: 17.7vw;"
	+"width: 8vw;"
	+"height: 3vw;"
	+"";
	
	paragraph.setAttribute("id", "styleCatalogElement");
		
	var image = document.createElement("img");
	image.setAttribute("id", "styleCatalogImg");
	image.setAttribute("src", element.img_src);
	image.setAttribute("width", "200");
	image.setAttribute("height", "130");
	
	
	var name = document.createElement("div");
	name.setAttribute("id", "styleCatalogName");
	name.innerHTML = element.game_name;
	
	
	var cost = document.createElement("div");
	cost.setAttribute("id", "styleCatalogCost");
	cost.innerHTML = element.cost + " р";
	
	
	var desc = document.createElement("div");
	desc.setAttribute("id", "styleCatalogDesc");
	desc.innerHTML = element.desc;
	
	
	var buy_button = document.createElement("input");
	buy_button.setAttribute("type", "button");
	buy_button.setAttribute("id", element.id);
	buy_button.setAttribute("class", "buttonBuy");
	buy_button.setAttribute("value", "Купить");
	buy_button.setAttribute("style", button_style);

	
	paragraph.appendChild(name);
	paragraph.appendChild(cost);
	paragraph.appendChild(image);
	paragraph.appendChild(buy_button);
	paragraph.appendChild(desc);
	
}
