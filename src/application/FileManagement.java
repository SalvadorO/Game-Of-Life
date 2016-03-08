package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale.FilteringMode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileManagement {

 	public String readShape(){
	
 	//method should take txt, and not read a file	
	String txt = readFile();
	
	//set header and pattern variables
	String[] payload = txt.split(":");
	String header = payload[0];
	String pattern = payload[1];
	int x_value = 0;
	int y_value = 0;
	
	String[] headerelements = header.split(",");
	
	//Get x and y values and set x and y variables
	//Consider separate this as a method
	for (int i = 0; i < headerelements.length; i++){
		String[] keyvalue = headerelements[i].split("=");
		if (keyvalue[0].equals("x") )
			x_value = Integer.parseInt(keyvalue[1]);
		if (keyvalue[0].equals("y") )
			y_value = Integer.parseInt(keyvalue[1]);
	}
 
	pattern = pattern.replaceAll("\\!", "");
	String[] line = pattern.split("\\$");
	    	
	return statusCount(pattern,y_value);
}
	//Parse  pattern, consider moving this to Model class, and refactor; patternParser
	//should not create an array, just draw on grid directly 
	
//	int[][] shape = new int[x_value][y_value];
//	String[] patternArray = pattern.split("\\$");
//	int element = 0;
//	
//	for (int i = 0; i < y_value; i++)	{
//		char[] charArray = patternArray[i].toCharArray();
//		
//		for (int x = 0; x < x_value; x++){
//			element = charArray[x];	
//			shape[x][i] = element;
//			
//			
//		}
//	}
//	

//******************
		
	
	//Count various cells method
	//consider moving this to Model class
	//look into how to not require paramter int y
public String statusCount(String pattern, int y){
	int multiplier = 1;
	int deadcounter = 0;
	int lifecounter = 0;
	char element = 0;
	boolean digit = false;
	int y_value = y;
	
	String[] patternArray = pattern.split("\\$");
	    	//verify the legnth of the for loop (was y_value)
	for (int i = 0; i < y_value; i++){
		char[] charArray = patternArray[i].toCharArray();
		
		for (int j = 0; j < charArray.length; j++){
		element = charArray[j];
		//Consider using switch
			if (Character.isDigit(element)){
				if (digit){
					multiplier = multiplier * 10;
					multiplier += Character.getNumericValue(element);
					digit = false;
				}
				else {
					multiplier = Character.getNumericValue(element);
					digit = true;
				};
				
			}
			else if (element == 'b'){
				deadcounter += multiplier;
				multiplier = 1;
				digit = false;
				//ij=element[0]
			}
			else if (element == 'o'){
				lifecounter += multiplier;
				multiplier = 1;
				digit = false;
				}
    		}
		
	}
	
	String returnValue = ("Number of dead: " + deadcounter);
	returnValue += ("\nNumber of living cells: " + lifecounter);
	
	return returnValue;
}
	// end work area

/**
 * Method reads a file and returns the content as String.
 * Method uses openFile() method for the user dialogue.
 * @return filecontent as a String as header:pattern
 * @throws IOException
 * @author hd
 */
public String readFile() 	{

	File file=openFile();
	String header = "";
	String pattern = "";
	String line = null;		
	String filecontent = "";
	
	try (BufferedReader br = new BufferedReader(new FileReader(file))) {

		do{
			line = br.readLine();
				if (line != null && !line.startsWith("#")){
					if (header == "")
						header = line;
					else
						pattern += line;
				};
		
		}while (line != null);
	} 
	catch (IOException e) {
		System.err.format("IOException: %s%n", e);
	}
		
	return header.replaceAll("\\s+","")+":"+pattern;
			
}
	
	/**
	 * Method receives content as a String and saves this to the default file (created if not existing)
	 * 
	 * @param filecontent
	 * @return boolean true if content was saved successfully
	 * @author hd
	 */
	public boolean saveFile(String filecontent) {
    	
		
    	OutputStream os;
		try {
			os = new FileOutputStream("hans.txt");
	    	Writer osw = new OutputStreamWriter(os);
	    	osw.write(filecontent);
	    	osw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		
		return true;

    }
	
	
	
	
	/**
	 * Method provides the user with a File open-dialogue to select a file. Allowed file types are
	 * filtered using ExtensionFilter class.
	 * @return f as a File object
	 * @author hd
	 */
	public File openFile(){
		
		ExtensionFilter filterTextfiles = new ExtensionFilter("Text, *.txt","*.txt");
		ExtensionFilter filterGoLfiles = new ExtensionFilter("GoL, *.rle","*.rle");

		FileChooser fc = new FileChooser();
		fc.setTitle("Open");
		fc.getExtensionFilters().addAll(filterTextfiles, filterGoLfiles);
	
		File f = fc.showOpenDialog(null);
		return f;
	}
}
