import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseController {
	CryptPassword CP = new CryptPassword();
	
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
		ResultSet result = this.GetSelectResult("Select * from user_table");
		String a_login;
		String a_password;
		while (result.next()) {
            a_login = result.getString("login");
            a_password = result.getString("password");
            
            if(a_login.equals(login) && a_password.equals(CP.getCryptPassword(password))) {
            	return true;
            }
        }
		return false;
	}
	//============================================================
	public boolean RegistrationNewUser(String login, String password){
		String SELECT_SQL = "insert into user_table(login, password) values('"+login +"', '"+CP.getCryptPassword(password)+"')";
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
		public String GetGameKey(String id){
			int int_id = Integer.parseInt(id);
			ResultSet result;
			try {
				result = this.GetSelectResult("Select game_key from game_keys where id = " + id);
			
			
				String name = null;
				while (result.next()){
					name = result.getString("game_key");
				}
				return name;
			} catch (SQLException e) {
				System.out.println("22222222222222222222222222222222222222222222222");
				System.out.println(e.getMessage());
				return "123123123";
			}
			
		}
}
