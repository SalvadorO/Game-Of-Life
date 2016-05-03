package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * The Class FileManagement.
 */
public class FileManagement {

	/**
	 * The method takes the filecontent as a String, and returns the pattern element from it.
	 * TODO: have to refactor according to hashmap regime, igth be obsolete
	 * @author hd
	 * @param String [] filecontent
	 * @return String
	 */
 	public String getPattern(String[] filecontent)	{
	
	 	//Remove end of file marker ('!')
		String openFile = filecontent[2].replaceAll("\\!", "");
		return openFile;
}


/**
 * Method takes a file and returns the content as a HashMap (key-value pairs).
 * Method assumes files uses the convention; http://www.conwaylife.com/wiki/Run_Length_Encoded; where
 * - First line contains header information: x='value', y='value', [optional]rule='value'
 * - Remaining lines contains the payload, the actual pattern. 
 * - The first line may be preceded with one or more lines beginning with hashtag; #
 *  
 * TODO: add inline comments
 * TODO: deal with corrupt files, i.e files not following the convention
 * @author hd
 * @param File f
 * @return HashMap<String><String> containing the elements; metadata, header and pattern;
 */
 	

public HashMap<String, String> parseFile(File f) {

	File file = f;
	
	HashMap<String, String> filecontent = new HashMap<String, String>();
	
	StringBuilder metadata = new StringBuilder();
	String header = "";
	StringBuilder pattern = new StringBuilder();

	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		String line = null;
		do{
		line = br.readLine();
			if (line != null){
				if (line.startsWith("#")){
					metadata.append(line);
				}
				else {
					if (header == "")
						header = line;
					else
						pattern.append(line);
				}
			};
	
		}while (line != null);	
	} 
	catch (IOException e) {
		System.err.format("IOException: %s%n", e);
	}
	
	filecontent.put("Metadata", metadata.toString());
	filecontent.put("Header", header.replaceAll("\\s+",""));
	filecontent.put("Pattern", pattern.toString());

	return filecontent;
}
	
/**
 * The method takes the header as a String, splits it and returns the header
 * elements as elements in an array. Example of header element: "x=3"
 * TODO: consider adding Throws if header somehow is corrupt
 * @author hd
 * @param String header
 * @return String[] headerelements
 */
	public String[] getHeaderArray(String header)	{
	
		// split header into info elements, ',' separates info elements
	String[] headerelements = header.split(",");
	
		//return the header as a String array
	return headerelements;
}


	/**
	 * TODO: to be completed
	 * Method receives the filecontent as a String and saves this to file (created if not existing).
	 *
	 * @author hd
	 * @param String filecontent
	 */
	public File saveFile() {
		
		ExtensionFilter filterGoLfiles = new ExtensionFilter("RLE file, *.rle","*.rle");

		FileChooser saveFile = new FileChooser();
		saveFile.getExtensionFilters().addAll(filterGoLfiles);
		
		File savedFile = saveFile.showSaveDialog(null);
		return savedFile;
		
	}
	/*--------------------------- Code i maybe need later --------------------
	try(PrintWriter out = new PrintWriter("Lars.txt"))
 	{
 		out.println("Dette er bare en test for � se om jeg klarer � lagre en fil med noe informasjon i seg. Etterhvert skal det kunne lagre som en RLE fil");
 		out.println("test5");
	}
catch(IOException ioe) {
ioe.printStackTrace();
 }
 * */


/**
 * Method provides the user with a File open-dialogue to select a file. Allowed file types are
 * filtered using ExtensionFilter class.
 * @author hd
 * @return File object: The file, or null if none is selected
 */
	public File openFile() {
		
		ExtensionFilter filterGoLfiles = new ExtensionFilter("RLE file, *.rle","*.rle");

		FileChooser openFile = new FileChooser();
		openFile.getExtensionFilters().addAll(filterGoLfiles);
	
		File f = openFile.showOpenDialog(null);
		
		return f;
	}
}
