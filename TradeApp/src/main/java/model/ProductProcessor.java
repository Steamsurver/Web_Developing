package model;

import java.util.ArrayList;
import java.util.List;
import model.classes.CryptPassword;
import model.classes.GameProduct;
import model.classes.UserPerson;
import model.interfaces.IProductProcessor;
import model.classes.ClassFactory;
import db_adapter.DB_Adapter;
import db_adapter.entities.EProduct;
import db_adapter.entities.EUser;

public class ProductProcessor implements IProductProcessor{
	
	private DB_Adapter dbAdapter = ClassFactory.injectDBAdapter();
	private CryptPassword cryptP = null;
	
	@Override
	public UserPerson checkUserData(String login, String password) {
		this.injectCryptPassword(ClassFactory.injectCryptPassword());
		
      	try {
      		EUser user = dbAdapter.authorisateUser(login, cryptP.getCryptPassword(login + password));
    	  	if(user != null) {
    	  		UserPerson retPerson = new UserPerson();
    	  		retPerson.setAccessLevel(user.getAccessLevel());
    	  		retPerson.setLogin(user.getLogin());
    	  		retPerson.setPassword(user.getPassword());
    	  		return retPerson;
    	  	}
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO CHECK DATA FROM DB: " + e.getMessage());
      		return null;
      	}
      	return null;
	}
	
	
	@Override
	public boolean registrateNewUser(String login, String password) {
		this.injectCryptPassword(ClassFactory.injectCryptPassword());
		
		try {
			dbAdapter.registrateNewUser(login, cryptP.getCryptPassword(login + password));
			return true;
			
		} catch (Exception e) {
			System.out.println("ERROR TO REGISTRATE NEW DATA INTO DB: " + e.getMessage());
      		return false;
		}
	}
	
	
	@Override
	public String getGameKey(String log_data) {
		
		try { 
      		String key = dbAdapter.getKeyById(log_data);
      		return key;
      	}
      	catch (Exception e) {
      		System.out.println("GET KEY ERROR: " + e.getMessage());
      		return null;
      	}
		
	}
	
	
	@Override
	public List<GameProduct> getFullCatalog(){
		try {
			List<EProduct> dbProdList = dbAdapter.getFullCatalog();
			List<GameProduct> catalog = new ArrayList<GameProduct>();
      		for(int i = 0; i < dbProdList.size(); i++) {
      			GameProduct tempProd = new GameProduct();
      			tempProd.setCost(dbProdList.get(i).getCost());
      			tempProd.setDescription(dbProdList.get(i).getDescription());
      			tempProd.setGameName(dbProdList.get(i).getGameName());
      			tempProd.setImgSource(dbProdList.get(i).getImgSource());
      			tempProd.setId(dbProdList.get(i).getId());
      			catalog.add(tempProd);
      		}
			
      		return catalog;
      	}
      	catch (Exception e) {
      		System.out.println("GET CATALOG ERROR: " + e.getMessage());
      		return null;
      	}
	}	
	
	
	@Override
	public Boolean checkToken(String token) {
		this.injectCryptPassword(ClassFactory.injectCryptPassword());
		
		try {
    	  	if(dbAdapter.checkUserToken(token)) {
    	  		return true;
    	  	}
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO CHECK TOKEN FROM DB: " + e.getMessage());
      		return false;
      	}
		return false;
	}
	
	@Override
	public boolean postProduct(String name, String cost, String desc, String img) {
		try {
			if(dbAdapter.postProduct(name, cost, desc, img)) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("ERROR TO CHECK TOKEN FROM DB: " + e.getMessage());
      		return false;
		}
		return false;
	}
	
	
	@Override
	public void injectAdapter(DB_Adapter repository){
		this.dbAdapter = repository;
	}
	
	
	//=========================================================================================
      	
	private void injectCryptPassword(CryptPassword crypt) {
		if(this.cryptP == null)
			this.cryptP = crypt;
	}
	//=========================================================================================
}
