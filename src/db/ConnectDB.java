package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private static Connection instance;
	
	public static Connection getConnection() {
		if(instance == null) {
			Connection con = null;
			
			try {
				Class.forName("org.sqlite.JDBC");
				String urlConnexion = "jdbc:sqlite:life.db";
				con = DriverManager.getConnection(urlConnexion);

				System.out.println("Connection OK");
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Connection KO");
				e.printStackTrace();
			}
			
			instance = con;
		}
		
		return instance;
	}
	
	public static void closeConnection() {
		try {
			instance.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
