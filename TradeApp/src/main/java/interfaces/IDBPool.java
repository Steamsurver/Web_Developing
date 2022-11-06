package interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBPool {
	Connection getConnection() throws SQLException;
	void returnConnection(Connection conn) throws SQLException;
}
