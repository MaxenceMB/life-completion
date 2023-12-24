package app;

import java.sql.Connection;
import db.ConnectDB;

public class Main {

	public static void main(String[] args) {
		Connection con = ConnectDB.getConnection();
	}

}
