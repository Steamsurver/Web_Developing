package interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import jakarta.enterprise.context.ApplicationScoped;

public class DBPool implements IDBPool{
	private int maxPoolSize = 100;
	private int connNum = 0;
	private static final String SQL_VERIFYCONN = "select 1";
	private Stack<Connection> freePool = new Stack<>();
	private Set<Connection> occupiedPool = new HashSet<>();
	
	//======================================================================================
	public Connection getConnection() throws SQLException {
		Connection conn = null;

		if (isFull()) {
			throw new SQLException("ERROR THE CONNECTION POOL IS FOOL.");
		}

		conn = getConnectionFromPool();

		if (conn == null) {
			conn = createNewConnectionForPool();
		}
		
		conn = makeAvailable(conn);
		return conn;
	}
	//======================================================================================
	
	public void returnConnection(Connection conn) throws SQLException {
		if (conn == null) {
			throw new NullPointerException();
		}
		if (!occupiedPool.remove(conn)) {
			throw new SQLException("The connection is returned already or it isn't for this pool");
		}
		freePool.push(conn);
	}
	//======================================================================================
	
	private boolean isFull() {
		return ((freePool.size() == 0) && (connNum >= maxPoolSize));
	}
	//======================================================================================

	private Connection createNewConnectionForPool() throws SQLException {
		Connection conn = createNewConnection();
		connNum++;
		occupiedPool.add(conn);
		return conn;
	}
	//======================================================================================
	
	public Connection createNewConnection() throws SQLException {
	    return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/UsersbaseWEB", "postgres", "ge6eu6udge");
	}
	//======================================================================================

	private Connection getConnectionFromPool() {
		Connection conn = null;
		if (freePool.size() > 0) {
			conn = freePool.pop();
			occupiedPool.add(conn);
		}
		return conn;
	}
	//======================================================================================

	private Connection makeAvailable(Connection conn) throws SQLException {
		if (isConnectionAvailable(conn)) {
			return conn;
		}

		occupiedPool.remove(conn);
		connNum--;
		conn.close();

		conn = createNewConnection();
		occupiedPool.add(conn);
		connNum++;
		return conn;
	}
	//======================================================================================

	private boolean isConnectionAvailable(Connection conn) {
		try (Statement st = conn.createStatement()) {
			st.executeQuery(SQL_VERIFYCONN);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
