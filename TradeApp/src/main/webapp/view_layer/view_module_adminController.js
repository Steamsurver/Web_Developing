import view_module_render from '../view_layer/view_module_render.js'
import view_module_controller from '../view_layer/view_module_controller.js'
import IOmodule from '../data_layer/IOmodule.js'

	export default function view_module_adminController(){	//модуль для привязки функций
	}													//

	view_module_adminController.init = function(root, messageField){			//
		this.root = root;												// функция инициализации основных параметров
		this.messageField = messageField;								//	 root - кореной объект для рендеринга
																		//	 messageField - поле для вывода сообщений
		this.current_user_login = "";									//
		this.current_user_password = "";								//
		this.current_calatog = "";										//
																		//
		view_module_render.init(root);									//
		view_module_render.setRootPoint(root);
	}
	
	
	
	view_module_adminController.handlerSignIn = function(login, password){						//
		IOmodule.signIn(login, password).then(()=>{											//	обработчик отрисовки 
			this.current_user_login = login;												//	на основе авторизации
			this.current_user_password = password;											//	при успешной авторизации будет 
																							//	вызвана функция отрисовки каталога
			IOmodule.getCatalog().then((resolve) =>{
					this.current_calatog = resolve;
					view_module_render.cleanPage();							
					view_module_render.renderAdminNavPanel();
					_bindNavPanelEvents(_renderSettings, _renderCatalog);
								
					view_module_render.renderCatalogPage(this.current_calatog);
					_pullFuncToCatalog();
					this.messageField.innerHTML = "";		
					
				}).catch((error)=>{console.log(error);
						this.messageField.innerHTML = "Проблема в сервером, попробуйте подключиться позже.<br>";		
					});	
			}).catch((error)=>{console.log(error);
				this.messageField.innerHTML = "Указанные данные не верны.<br>";});
	}																						
	
	
	//======================================Локальные функции===============================================
	function _bindNavPanelEvents(){								//  
		let eventArray = new Array();							//
		for(let i = 0; i < arguments.length; i++)				//	Привязка ивентов к кнопкам
			eventArray[i] = arguments[i];						//	на форме авторизации
																//	
		view_module_render.setNavPanelEvents(eventArray);		//
	}															//
	
	
	function _renderSettings(){
		view_module_render.cleanPage();							
		view_module_render.renderAdminNavPanel();
		_bindNavPanelEvents(_renderSettings, _renderCatalog);
								
		view_module_render.renderAdminSettings();
		view_module_render.setAddButtonEvent__adminSettings(()=>{
			let information = view_module_render.getAddProductInformation__adminSettings();
			
			IOmodule.postNewProduct(information[0], information[1], information[2], information[3]);
			/*
			
			ПОКА НЕ ДОДЕЛАНО
			
			*/
			
		});
		
	}
	
	
	function _renderCatalog(){
		IOmodule.getCatalog().then((resolve) =>{
					this.current_calatog = resolve;
					view_module_render.cleanPage();							
					view_module_render.renderAdminNavPanel();
					_bindNavPanelEvents(_renderSettings, _renderCatalog);
											
					view_module_render.renderCatalogPage(this.current_calatog);
					_pullFuncToCatalog();	
				}).catch((error)=>{console.log(error);
						this.messageField.innerHTML = "Проблема в сервером, попробуйте подключиться позже.<br>";		
				});
	}
	
	
	function _pullFuncToCatalog(){								
			let funcArray = new Array();
			let gameIds = view_module_render.getGameIdData();	
								
			for(let i = 0; i < gameIds.length; i++){				
				funcArray[i] = function(){
					let key = IOmodule.getKey(gameIds[i]);
						if(key != undefined)
							return key;
				};		
								
			}		
															
			view_module_render.setFuncForBuyEvents(funcArray);					
		}
	