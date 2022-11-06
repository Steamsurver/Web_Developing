package classes;

import controllers.DBAdapter;
import controllers.IDBAdapter;
import interfaces.DBPool;
import interfaces.IDBPool;

public class ClassFactory {
	static public IDBPool injectDBPool() {
		return new DBPool();
	}
	
	static public IDBAdapter injectDBController() {
		return new DBAdapter();
	}
	
	static public CryptPassword injectCryptPassword() {
		return new CryptPassword();
	}
}

