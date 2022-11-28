package model.interfaces;

import java.util.List;
import db_adapter.DB_Adapter;
import model.classes.GameProduct;
import model.classes.UserPerson;

public interface IProductProcessor {
	UserPerson checkUserData(String login, String password);
	boolean registrateNewUser(String login, String password);
	boolean postProduct(String name, String cost, String desc, String img);
	String getGameKey(String id_key);
	List<GameProduct> getFullCatalog();
	Boolean checkToken(String token);
	void injectAdapter(DB_Adapter repository);
}
