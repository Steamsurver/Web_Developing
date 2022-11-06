package interfaces;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

import classes.CryptPassword;
import classes.Game_product;
import classes.ClassFactory;
import controllers.IDBAdapter;

public class ProductProcessor implements IProductProcessor{
	
	private IDBAdapter adapter = null;
	private CryptPassword cryptP = null;
	
	@Override
	public String checkUserData(String login, String password) {
		this.injectIDBController(ClassFactory.injectDBController());
		this.injectCryptPassword(ClassFactory.injectCryptPassword());
		
      	try {
    	  	if(adapter.DataIsCorrect(login, cryptP.getCryptPassword(login + password))) {
    	  		return cryptP.getCryptPassword(login + password);
    	  	}else {
    	  		return "0";
    	  	}
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO CHECK DATA FROM DB: " + e.getMessage());
      		return "0";
      	}
	}
	
	
	@Override
	public boolean registrateNewUser(String login, String password) {
		this.injectIDBController(ClassFactory.injectDBController());
		this.injectCryptPassword(ClassFactory.injectCryptPassword());
		
		
      	try {    
    	  	if(adapter.RegistrationNewUser(login, cryptP.getCryptPassword(login + password))) {
    	  		return true;
    	  	}else
    	  	{
    	  		return false;
    	  	}
    	  	
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO REGISTRATE NEW DATA INTO DB: " + e.getMessage());
      		return false;
      	}
	}
	
	@Override
	public String getGameKey(String log_data) {
		this.injectIDBController(ClassFactory.injectDBController());
		this.injectCryptPassword(ClassFactory.injectCryptPassword());
		
		
		
		return adapter.GetGameKey(log_data);
	}
	
	@Override
	public ArrayList<Game_product> getFullCatalog() {
		this.injectIDBController(ClassFactory.injectDBController());
		this.injectCryptPassword(ClassFactory.injectCryptPassword());
		
		Jsonb jsonb = JsonbBuilder.create();          
	 	ArrayList<Game_product> catalog;
	 	
      	try { 
      		catalog = adapter.GetGameCatalog();
      		return catalog;
      	}
      	catch (Exception e) {
      		System.out.println("GET CATALOG ERROR: " + e.getMessage());
      		return null;
      	}
	}	
	
	@Override
	public Boolean checkToken(String token) {
		this.injectIDBController(ClassFactory.injectDBController());
		this.injectCryptPassword(ClassFactory.injectCryptPassword());
		
		try {
    	  	if(adapter.PasswordIsCorrect(token)) {
    	  		return true;
    	  	}else {
    	  		return false;
    	  	}
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO CHECK TOKEN FROM DB: " + e.getMessage());
      		return true;
      	}
	}
	
	
	//=========================================================================================
	private void injectIDBController(IDBAdapter controller) {
		if(this.adapter == null)
			this.adapter = controller;
	}
      	
	private void injectCryptPassword(CryptPassword crypt) {
		if(this.cryptP == null)
			this.cryptP = crypt;
	}
	//=========================================================================================
}
