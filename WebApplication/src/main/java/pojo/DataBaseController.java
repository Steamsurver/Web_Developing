package pojo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseController {
	//============================================================
	private Connection GetConnectToBase()  throws IOException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		 Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		 Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/UsersbaseWEB", "postgres", "ge6eu6udge");
         return conn;
	}
	//============================================================
	private ResultSet GetSelectResult(String select) throws SQLException{
		Connection this_conn = null;
		try {
			this_conn = this.GetConnectToBase();
			
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
        PreparedStatement preparedStatement = this_conn.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet;
	}
	//============================================================
	private void EnterQwery(String qwery) throws SQLException {
		Connection this_conn = null;
		try {
			this_conn = this.GetConnectToBase();
			
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
        Statement statement = this_conn.createStatement();
        statement.executeUpdate(qwery);
	}
	//============================================================
	public boolean DataIsCorrect(String login, String password) throws SQLException {
		ResultSet result = this.GetSelectResult("Select * from directors_table");
		String a_login;
		String a_password;
		while (result.next()) {
            a_login = result.getString("d_login");
            a_password = result.getString("d_password");
            
            if(a_login.equals(login) && a_password.equals(password)) {
            	return true;
            }
        }
		return false;
	}
	//============================================================
		public String GetDirectorId(String login, String password) throws SQLException {
			ResultSet result = this.GetSelectResult("Select * from directors_table");
			String a_login;
			String a_password;
			while (result.next()) {
	            a_login = result.getString("d_login");
	            a_password = result.getString("d_password");
	            
	            if(a_login.equals(login) && a_password.equals(password)) {
	            	return result.getString("d_id");
	            }
	        }
			return null;
		}
		//============================================================
	public void RegistrationNewDirector(String id, String login, String password) throws SQLException{
		String SELECT_SQL = "insert into directors_table(d_id, d_login, d_password) values('"+id+"', '"+login +"', '"+password+"')";
		this.EnterQwery(SELECT_SQL);
	}
	//============================================================
	public List<String> GatTableData(String id) throws SQLException{
		List<String> qweryFromDB = new ArrayList<String>();
		String SELECT_SQL = "select * from subject_table\r\n" + "where ref_id = '"+ id +"';";
		ResultSet result = this.GetSelectResult(SELECT_SQL);
		
		while (result.next()) {
			qweryFromDB.add(result.getString("sub_id"));
			qweryFromDB.add(result.getString("sub_name"));
			qweryFromDB.add(result.getString("sub_secondname"));
		}
		return qweryFromDB;
	}
	//============================================================
	
	public void EnterDeleteQwery(String id) throws SQLException{
		String SELECT_SQL = "DELETE FROM subject_table WHERE sub_id = '"+ id +"';";
		this.EnterQwery(SELECT_SQL);
	}
	//============================================================
	
	public void EnterAdditionQwery(String id, String name, String secondName, String ref_id) throws SQLException{
		String SELECT_SQL = "insert into subject_table values('"+id+"', '"+name +"', '"+secondName+"', '"+ref_id+"')";
		this.EnterQwery(SELECT_SQL);
	}
	//============================================================
}
