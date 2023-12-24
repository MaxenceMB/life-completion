package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	
	public static void main(String[] args) {
		Connection con = ConnectDB.getConnection();
		
		dropTables();
		createTables();
		
		ConnectDB.closeConnection();
	}
	
	private static void createTables() {
		System.out.println("createTables()");
	}
	
	private static void dropTables() {
		System.out.println("dropTables()");
	}

}
