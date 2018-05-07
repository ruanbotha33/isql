package org.isql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseInit {
	
	public DatabaseInit() {
		// TODO Auto-generated constructor stub
	}
	
	public Connection DatabaseOpen() throws ClassNotFoundException, SQLException{
		
		try {
			HashMap<String, String> hm = new DataRead().ReadDBConfig("db.config");
			String userName = hm.get("username");
			String password = hm.get("password");

			String url = hm.get("connection");

			Class.forName(hm.get("driver"));
			Connection conn = DriverManager.getConnection(url, userName, password);
			
			return conn;
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("No db.config file found");
			System.out.println(e.toString());
			return null;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			return null;
		} 
	}
}
