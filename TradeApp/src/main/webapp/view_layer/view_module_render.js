import CatalogRender from '../view_layer/catalog/catalogRender.js'
import SignInRender from '../view_layer/signInRender.js'
import SignUpRender from '../view_layer/signUpRender.js'
import NavPanelRender from '../view_layer/viewElements/navPanel.js'
import AdminSettingsRender from '../view_layer/adminForms/adminSettings.js'

	export default function view_module_render(){
		
	}
	
	view_module_render.init = function(root){					//
		this.root = root;										// Инициализация основных полей
		this.signInRender = new SignInRender(this.root);		//
		this.signUpRender = new SignUpRender(this.root);		//
		this.catalogRender = new CatalogRender(this.root);		//
		this.navPanel = new NavPanelRender(this.root);
		this.adminSettingsRender = new AdminSettingsRender(this.root);
	}
	
	view_module_render.setRootPoint = function(root){			//
		this.root = root;										//	установка текущего 
		this.signInRender.setRootPoint(root);					//	корня для отрисовки
		this.signUpRender.setRootPoint(root);					//
		this.catalogRender.setRootPoint(root);					//
		this.navPanel.setRootPoint(root);
		this.adminSettingsRender.setRootPoint(root);
	}
	
	
	
	view_module_render.renderAdminNavPanel = function(){
		this.navPanel.adminRender();
	}
	
	view_module_render.setNavPanelEvents = function(events){				//	
		this.navPanel.setNavEvents(events);									//	устанока ивентов для класса SignInRender
	}	
	
	
	
	view_module_render.renderAdminSettings = function(){
		this.adminSettingsRender.render();
	}
	
	view_module_render.setAddButtonEvent__adminSettings = function(event){
		this.adminSettingsRender.setAddButtonEvent(event);
	}
	
	view_module_render.getAddProductInformation__adminSettings= function(){
		return this.adminSettingsRender.getAddProductInformation();
	}
	
	
	view_module_render.renderSignInPage = function(){
		this.signInRender.render();
	}
	
	view_module_render.renderSignUpPage = function(){
		this.signUpRender.render();
	}
	
	view_module_render.renderCatalogPage = function(catalog_data){
		this.catalogRender.render(catalog_data);
	}
	
	view_module_render.cleanPage = function(){			
		var del_forms = this.root.getElementsByTagName("form");			//	Очистка корня отрисовки
		var del_len = del_forms.length;									//	
		for(var i = del_len-1; i > -1; i--){							//
			del_forms[i].remove();										//
		}																//
	}																	//
	
	
	//====================================================================
	
	view_module_render.setSignInEvents = function(events){				//	
		this.signInRender.setSignInEvents(events);						//	устанока ивентов для класса SignInRender
	}																	//
	
	view_module_render.getSignInData = function(){						//
		return this.signInRender.getTextBars();							//	
	}																	//
	
	
	
	view_module_render.setSignUpEvents = function(events){				//
		this.signUpRender.setSignUpEvents(events);						//	устанока ивентов для класса SignUpRender
	}																	//
	
	view_module_render.getSignUpData = function(){						//
		return this.signUpRender.getTextBars();							//
	}																	//
	

	view_module_render.getGameIdData = function(){						//
		return this.catalogRender.getGameIds();							// получение информации по id продуктов каталога
	}																	//
	
	view_module_render.setFuncForBuyEvents = function(funcArray){		//
		this.catalogRender.setFuncForBuyEvents(funcArray);				// передача функций для Buy-ивентов каталога 
	}																	//
	
	//====================================================================
	