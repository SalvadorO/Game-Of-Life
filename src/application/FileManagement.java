package application;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileManagement {

 	public String readShape(){
	
 	//Method should take txt, and not read a file	
	//Consider moving to Model class
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
	    	
	return statusCount(pattern,y_value);
}
	



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
		
//		ExtensionFilter filterTextfiles = new ExtensionFilter("Text, *.txt","*.txt");
		ExtensionFilter filterGoLfiles = new ExtensionFilter("GoL, *.rle","*.rle");

		FileChooser fc = new FileChooser();
		fc.setTitle("Open");
		fc.getExtensionFilters().addAll(filterGoLfiles);
	
		File f = fc.showOpenDialog(null);
		return f;
	}
}
