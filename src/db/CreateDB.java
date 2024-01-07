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
			
			/*
			// Table Achievement
			stmt.executeUpdate("CREATE TABLE Achievement ("
							 + "idAchievement TEXT UNIQUE PRIMARY KEY, "
							 + "title TEXT UNIQUE NOT NULL, "
							 + "description TEXT, "
							 + "type TEXT NOT NULL, "
							 + "level TEXT NOT NULL, "
							 + "completed INTEGER DEFAULT 0, "
							 + "createDate TEXT NOT NULL, "
							 + "completeDate TEXT, "
							 + "stepsNeeded INTEGER DEFAULT 0, "
							 + "stepsDone INTEGER DEFAULT 0);");
			System.out.println("   OK - Create Table Achievement");
			*/
			
			// Table Week
			stmt.executeUpdate("CREATE TABLE Week ("
					 		 + "idWeek TEXT UNIQUE PRIMARY KEY, "
					 		 + "date TEXT NOT NULL, "
					 		 + "text TEXT UNIQUE NOT NULL, "
					 		 + "grade REAL DEFAULT 0);");
	System.out.println("   OK - Create Table Week");
			
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
			/*
			stmt.executeUpdate("DROP TABLE Achievement;");
			System.out.println("   OK - Drop Table Achievement");
			*/
			
			// Table week
			stmt.executeUpdate("DROP TABLE Week;");
			System.out.println("   OK - Drop Table Week");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
