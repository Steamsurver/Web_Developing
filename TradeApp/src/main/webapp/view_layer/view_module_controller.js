import view_module_render from '../view_layer/view_module_render.js'
import view_module_adminController from '../view_layer/view_module_adminController.js'
import IOmodule from '../data_layer/IOmodule.js'

	export default function view_module_controller(){	//модуль для привязки функций
	}													//

	view_module_controller.init = function(root, messageField){			//
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
	
	
	
	view_module_controller.handlerSignIn = function(login, password){						//
		IOmodule.signIn(login, password).then(()=>{											//	обработчик отрисовки 
			this.current_user_login = login;												//	на основе авторизации
			this.current_user_password = password;											//
			if(IOmodule.getUserAccessLevel()=="admin"){
				view_module_adminController.init(this.root, this.messageField);				//	обработчик для администратора 
				view_module_adminController.handlerSignIn(login, password);					//
				return;																		
			}																				
																							
			IOmodule.getCatalog().then((resolve) =>{										//	при успешной авторизации будет 
					this.current_calatog = resolve;											//	вызвана функция отрисовки каталога
					view_module_render.cleanPage();												
					view_module_render.renderCatalogPage(this.current_calatog);
					_pullFuncToCatalog();
					this.messageField.innerHTML = "";	
				}).catch((error)=>{console.log(error);
						this.messageField.innerHTML = "Проблема в сервером, попробуйте подключиться позже.<br>";		
					});	
			}).catch((error)=>{console.log(error);
				this.messageField.innerHTML = "Указанные данные не верны.<br>";});
	}																						



	view_module_controller.handlerSignUp = function(login, password){						  //					
		IOmodule.signUp(login, password).then(()=> {										  //	обработчик отрисовки
			view_module_render.cleanPage();													  //	на основе реристрации
			view_module_render.renderSignInPage();											  //	если на сервере получилось
			_bindSignInEvents(_enter, _enterRegistration); //------ВЫЗОВ BIND-------		  //	успешно зарегестрировать
		}).catch((error)=>{console.log(error);												  //
				this.messageField.innerHTML = "Указанные данные не подходят для регистрации.<br>";
			});																				  //	пользователя, то перебрасывает		
	}																						  //	на окно авторизации
	
	
	view_module_controller.startHandler = function(){										  //
		view_module_render.renderSignInPage();												  // обработчик для стартовых событий 
		_bindSignInEvents(_enter, _enterRegistration); //------ВЫЗОВ BIND-------			  // при открытии окна
	}																						  //		
	
	
	//======================================Локальные функции===============================================
	//======================================SIGN_IN_SETTINGS===================================
	function _bindSignInEvents(){								//  
		let eventArray = new Array();							//
		for(let i = 0; i < arguments.length; i++)				//	Привязка ивентов к кнопкам
			eventArray[i] = arguments[i];						//	на форме авторизации
																//	
		view_module_render.setSignInEvents(eventArray);			//
	}															//
	
	function _enter(){
		let login = view_module_render.getSignInData()[0];
		let password = view_module_render.getSignInData()[1];
		view_module_controller.handlerSignIn(login, password);
	}
	
	function _enterRegistration(){
		view_module_render.cleanPage();
		view_module_render.renderSignUpPage();
		_bindSignUpEvents(_enterDataToRegistration, _backToSignIn); //------ВЫЗОВ BIND-------
	}
	
	
	//======================================SIGN_UP_SETTINGS==================================
	function _bindSignUpEvents(){								//
		let eventArray = new Array();							//
		for(let i = 0; i < arguments.length; i++)				//	Привязка ивентов к кнопкe
			eventArray[i] = arguments[i];						//  на форме регистрации
																//
		view_module_render.setSignUpEvents(eventArray);			//
	}															//
	
	function _enterDataToRegistration(){
		let login = view_module_render.getSignUpData()[0];
		let password = view_module_render.getSignUpData()[1];
		console.log(password + login);
		view_module_controller.handlerSignUp(login, password);
	}
	
	function _backToSignIn(){
		view_module_render.cleanPage();
		view_module_render.renderSignInPage();
		_bindSignInEvents(_enter, _enterRegistration); //------ВЫЗОВ BIND-------
	}
	
	
	//======================================GAME_CATALOG_SETTINGS==================================
	
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
		


