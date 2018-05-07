package org.isql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DataRead {
	
	private Connection conn;
	
	public DataRead(Connection conn){
		setConn(conn);
	}
	//default
	public DataRead() {
		// TODO Auto-generated constructor stub
	}

	public ResultSet ReadFromString(String query) throws SQLException{
		
		Statement statement = conn.createStatement();
        String queryString = query;
        ResultSet rs = statement.executeQuery(queryString);
		
		return rs;
	}
	
	public ArrayList<ResultSet> ReadFromFile(String filePath) throws SQLException, IOException{
		
		String s = new String();
        StringBuffer sb = new StringBuffer();
	
		FileReader fr = new FileReader(new File(filePath));
		// be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
	
	    BufferedReader br = new BufferedReader(fr);

	    while((s = br.readLine()) != null)
	    {
	    	sb.append(s);
	    }
	    br.close();

	    // here is our splitter ! We use ";" as a delimiter for each request
	    // then we are sure to have well formed statements
	    String[] inst = sb.toString().split(";");
	    
	    Statement statement = conn.createStatement();
        ArrayList<ResultSet> rs  = new ArrayList<ResultSet>();
	    
	    for(int i = 0; i<inst.length; i++)
        {
            // we ensure that there is no spaces before or after the request string
            // in order to not execute empty statements
            if(!inst[i].trim().equals(""))
            {
                rs.add(statement.executeQuery(inst[i]));
                System.out.println("Executing>>"+inst[i]);
            }
        }
		
		return rs;
	}
	
	public int countColumnsInTable(ResultSet rset) throws SQLException {
	    int count = 16;
	    ResultSetMetaData rsMetaData = rset.getMetaData();
	    count = rsMetaData.getColumnCount();
	    // Remember to clean up
	    return count;
	}
	
	public HashMap<String, String> ReadDBConfig(String filePath) throws IOException{
		
		HashMap<String, String> hm = new HashMap<>();
		
		FileReader fr = new FileReader(new File(filePath));
		// be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
	
	    BufferedReader br = new BufferedReader(fr);
	    
	    String s = new String();
	    
	    while((s = br.readLine()) != null)
	    {
	    	hm.put(s.split("=",2)[0], s.split("=",2)[1]);
	    }
	    br.close();
		
		return hm;
	}

	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
