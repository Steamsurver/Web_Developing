package model.classes;
import db_adapter.DB_Adapter;

public class ClassFactory {
	static public CryptPassword injectCryptPassword() {
		return new CryptPassword();
	}

	static public DB_Adapter injectDBAdapter() {
		return new DB_Adapter();
	}
}

