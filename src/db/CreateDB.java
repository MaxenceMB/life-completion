package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	
	public static void main(String[] args) {
		Connection con = ConnectDB.getConnection();
		
		dropTables(con);
		createTables(con);
		
		ConnectDB.closeConnection();
	}
	
	private static void createTables(Connection con) {
		System.out.println("createTables()");
		
		try {
			Statement stmt = con.createStatement();
			
			// Table Achievement
			stmt.executeUpdate("CREATE TABLE Achievement ("
							 + "idAchievement INTEGER PRIMARY KEY AUTOINCREMENT, "
							 + "type TEXT NOT NULL, "
							 + "title TEXT UNIQUE NOT NULL, "
							 + "description TEXT, "
							 + "level TEXT, "
							 + "completed INTEGER DEFAULT 0, "
							 + "createDate TEXT NOT NULL, "
							 + "completeDate TEXT, "
							 + "stepsNeeded INTEGER DEFAULT NULL, "
							 + "stepsDone INTEGER DEFAULT NULL);");
			System.out.println("   OK - Create Table Achievement");
			
		} catch (SQLException e) {
			System.out.println("CREATE TABLES ERROR");
			e.printStackTrace();
		}
	}
	
	private static void dropTables(Connection con) {
		System.out.println("dropTables()");

		try {
			Statement stmt = con.createStatement();
			
			// Table achievement
			stmt.executeUpdate("DROP TABLE Achievement;");
			System.out.println("   OK - Drop Table Achievement");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
