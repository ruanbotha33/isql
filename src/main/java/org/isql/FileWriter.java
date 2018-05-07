/**
 * 
 */
package org.isql;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author RuBotha
 *
 */
public class FileWriter {
	
	private String filePath;
	
	public FileWriter(String filePath) {
		// TODO Auto-generated constructor stub
		setFilePath(filePath);
	}
	
	public void WriteToFile(List<String> list) throws IOException{
		List<String> lines = list;
		Path file = Paths.get(filePath);
		Files.write(file, lines, Charset.forName("UTF-8"));
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
