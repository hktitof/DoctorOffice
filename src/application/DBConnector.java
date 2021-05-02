package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 3306;
	private static final String DB_NAME = "cabinet";
	
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://" + HOST +":"+ PORT +"/"+DB_NAME, USERNAME, PASSWORD);
		return connection;
		
	}

}
