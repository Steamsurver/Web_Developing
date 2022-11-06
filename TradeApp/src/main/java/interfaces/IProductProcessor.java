package interfaces;

import java.util.ArrayList;
import java.util.List;

import classes.Game_product;
import jakarta.enterprise.context.ApplicationScoped;

public interface IProductProcessor {
	String checkUserData(String login, String password);
	boolean registrateNewUser(String login, String password);
	String getGameKey(String id_key);
	ArrayList<Game_product> getFullCatalog();
	Boolean checkToken(String token);
}
