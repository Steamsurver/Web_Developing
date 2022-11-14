package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classes.CryptPassword;
import classes.Game_product;
import classes.ClassFactory;
import interfaces.IDBPool;

public class DBAdapter implements IDBAdapter{
	
	private IDBPool DBpool = null;
	private Connection conn = null;
	private CryptPassword CP = new CryptPassword();
	
	
	//============================================================
	private void GetConnectToBase() throws SQLException {
		this.addDBPool(ClassFactory.injectDBPool());
		this.conn = DBpool.getConnection();
	}
	
	//============================================================
	private ResultSet GetSelectResult(String select) throws SQLException{
		this.GetConnectToBase();
		PreparedStatement preparedStatement = this.conn.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        this.ReturnConnectToPool();
		return resultSet;
	}
	//============================================================
	private void EnterQwery(String qwery) throws SQLException {
		this.GetConnectToBase();
		Statement statement = this.conn.createStatement();
        statement.executeUpdate(qwery);
        this.ReturnConnectToPool();
	}
	//============================================================
	@Override
	public boolean DataIsCorrect(String login, String password) throws SQLException {
		ResultSet result = this.GetSelectResult("Select * from user_table");
		String a_login;
		String a_password;
		while (result.next()) {
            a_login = result.getString("login");
            a_password = result.getString("password");
            
            if(a_login.equals(login) && a_password.equals(password)) {
            	return true;
            }
        }
		return false;
	}
	//============================================================
	@Override
	public boolean PasswordIsCorrect(String password) throws SQLException{
		ResultSet result = this.GetSelectResult("Select * from user_table");
		String a_password;
		while (result.next()) {
            a_password = result.getString("password");
            
            if(a_password.equals(password)) {
            	return true;
            }
        }
		return false;
	}
	//============================================================
	@Override
	public boolean RegistrationNewUser(String login, String password){
		String SELECT_SQL = "insert into user_table(login, password) values('"+login +"', '"+password+"')";
		try {
			if(!login.isEmpty() && !password.isEmpty()) {
				this.EnterQwery(SELECT_SQL);
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	//============================================================
	@Override
	public ArrayList<Game_product> GetGameCatalog() throws SQLException {
		ResultSet result = this.GetSelectResult("Select * from product");
		ArrayList<Game_product> return_list = new ArrayList<Game_product>();
		int iter = 0;
		
		while (result.next()){
			Game_product add_prod = new Game_product();
			
			add_prod.id = result.getInt("id");
			add_prod.cost = result.getInt("cost");
			add_prod.game_name = result.getString("game_name");
			add_prod.desc = result.getString("description");
			add_prod.img_src = result.getString("img_source");
			return_list.add(iter, add_prod);
            iter++;
		}
		return return_list;
	}
	
	//============================================================
	@Override
	public String GetGameKey(String id) {
		ResultSet result;
		try {
			result = this.GetSelectResult("Select game_key from game_keys where id = " + id);
			
			String name = null;
			while (result.next()){
				name = result.getString("game_key");
			}
			return name;
		} catch (SQLException e) {
			return "123123123";
		}
	}	
	
	//============================================================
	@Override
	public void ReturnConnectToPool() {
		try {
			this.DBpool.returnConnection(this.conn);
			this.conn = null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//============================================================
	
	private void addDBPool(IDBPool pool) {
		if(this.DBpool == null) {
			this.DBpool = pool;
		}
	}
	

}
