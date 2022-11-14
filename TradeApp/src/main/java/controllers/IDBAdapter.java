package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import classes.Game_product;

public interface IDBAdapter{
	boolean DataIsCorrect(String login, String password) throws SQLException;
	boolean PasswordIsCorrect(String password) throws SQLException;
	boolean RegistrationNewUser(String login, String password);
	ArrayList<Game_product> GetGameCatalog() throws SQLException;
	String GetGameKey(String id);
	void ReturnConnectToPool();
}
