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

//TODO: consider rewrite or returning an array, consisting metatada:header:pattern

public class FileManagement {

	/**
	 * The method takes the filecontent as a String, and returns the pattern element from it
	 * @param filecontent
	 * @return String pattern
	 * @author hd
	 */
 	public String getPattern(String filecontent)	{
	
//Sets header and pattern variables
	String[] payload = filecontent.split(":");
	String header = payload[0];
	String pattern = payload[1];

//Remove end of file marker ('!')
	pattern = pattern.replaceAll("\\!", "");

	return pattern;
}

 	/**
 	 * The method takes the filecontent as a String, and returns the header element
 	 * @param file
 	 * @return
 	 */
 	public String getHeader(String file)	{
//Sets header and pattern variables
		String[] payload = file.split(":");
		String header = payload[0];
 			

//Get x and y values and set x and y variables

 			String[] headerelements = header.split(",");
 			int x_value = 0;
 			int y_value = 0;
 			
 			for (int i = 0; i < headerelements.length; i++){
 				String[] keyvalue = headerelements[i].split("=");
 				if (keyvalue[0].equals("x") )
 					x_value = Integer.parseInt(keyvalue[1]);
 				if (keyvalue[0].equals("y") )
 					y_value = Integer.parseInt(keyvalue[1]);
 			}

 			return header;
 		}




/**
 * Method takes a file and returns the content as String.
 * 
 * @return filecontent as a String as 'header:pattern'
 * @throws IOException
 * @author hd
 */
public String parseFile(File f) 	{

	File file = f;
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
