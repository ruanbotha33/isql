/**
 * 
 */
package org.isql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author RuBotha
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		if (args.length != 0) {
			try {
				Connection db = new DatabaseInit().DatabaseOpen();
				DataRead dr = new DataRead(db);
				System.out.println("Connected to SQL");
				ArrayList<String> lines = new ArrayList<String>();
				String row = "";
				String header = "";
				if (args[0].toUpperCase().equals("q".toUpperCase())) {
					System.out.println("Option from Query");
					if (args.length >= 2) {
						ResultSet rs = dr.ReadFromString(args[1]);
						int columnCount = dr.countColumnsInTable(rs);
						System.out.println("Query:  " + args[1]);
						if (args.length >= 3) {
							// write the header
							if (args.length >= 4) {
								header = args[3];
							} else {
								System.out.println("No Header Argument, Defaulting to Column Names");
								for (int i = 1; i <= columnCount; i++) {
									String name = rs.getMetaData().getColumnName(i);
									if (columnCount + 1 > i) {
										header += name;
									} else {
										header += name + "|";
									}
								}
							}
							lines.add(header);
							while (rs.next()) {
								row = "";
								for (int i = 1; i <= columnCount; i++) {
									if (columnCount + 1 > i) {
										row += rs.getString(i);
									} else {
										row += rs.getString(i) + ",";
									}
								}
								lines.add(row);
								count++;
							}
							FileWriter fr = new FileWriter(args[2]);
							fr.WriteToFile(lines);
						} else {
							System.out.println("There was no file path argument");
						}
						System.out.println("Query executed with :  " + count + "  records");
					} else {
						System.out.println("There was no query argument");
					}
				} else if (args[0].toUpperCase().equals("f".toUpperCase())) {
					System.out.println("Option from File");
					if (args.length >= 2) {
						System.out.println("Input File Name: " + args[1]);
						ArrayList<ResultSet> res = dr.ReadFromFile(args[1]);
						int fileCount = 1;
						if(args.length >= 3){
							System.out.println("Output File Name: " + args[2]);
							lines.clear();
							int queryCount = 1;
							for(ResultSet rs : res){
								int columnCount = dr.countColumnsInTable(rs);
								if (args.length >= 4) {
									header = args[3];
								} else {
									System.out.println("No Header Argument, Defaulting to Column Names");
									for (int i = 1; i <= columnCount; i++) {
										String name = rs.getMetaData().getColumnName(i);
										if (columnCount + 1 > i) {
											header += name;
										} else {
											header += name + "|";
										}
									}
								}
								lines.add(header);
								count = 0;
								while(rs.next()){
									row = "";
									for (int i = 1; i <= columnCount; i++) {
										if (columnCount + 1 > i) {
											row += rs.getString(i);
										} else {
											row += rs.getString(i) + ",";
										}
									}
									lines.add(row);
									count++;
								}
								FileWriter fr = new FileWriter(args[2] + fileCount);
								fr.WriteToFile(lines);
								System.out.println("Query :" + queryCount);
								queryCount++;
								fileCount++;
								System.out.println("Query executed with :  " + count + "  records");
							}
						}else{
							System.out.println("There is no output file name argument");
						}
					} else {
						System.out.println("There is no Input File Argument");
					}
				} else {
					System.out.println("No valid arguments found");
				}

			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Arguments not found!");
			System.out.println("Q [Query String] [Path to writefile]");
			System.out.println("F [Path to query file] [Path to writefile]");
		}

	}

}
