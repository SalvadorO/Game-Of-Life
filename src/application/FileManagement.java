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
 	public String getPattern(String[] filecontent)	{
	
	 	//Remove end of file marker ('!')
		String fc = filecontent[2].replaceAll("\\!", "");
		return fc;
}





/**
 * Method takes a file and returns the content as String array.
 * Method assumes files uses the convention; 
 * 
 * @return filecontent as a String array; 
 * pos 0 contains metadata
 * pos 1 contains header
 * pos 2 contains pattern
 * @author hd
 */
public String[] parseFile(File f) 	{

	File file = f;
	String filecontent[] = new String[3];
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
	filecontent[0] = metadata.toString();
	filecontent[1] = header.replaceAll("\\s+","");
	filecontent[2] = pattern.toString();
	
	return filecontent;
			
}
	
/**
	 * The method takes the filecontent as a String, and returns the header
	 * element as elements in an array
	 * @param filecontent
	 * @return returnedHeader array 
	 * (position 0 holds x value
	 * position 1 holds y value
	 * position 2 holds rule value (if present))
	 * @author hd
	 */
	public String[] getHeader(String[] filecontent)	{
		
		// Initialize local variable header with the header part of the filecontent array
	String header = filecontent[1];
	
		// split header into info elements, ',' separates info elements
	String[] headerelements = header.split(",");
	
		//return the header as a String array
	return headerelements;
}



	/**
	 * TODO: to be completed
	 * Method receives the filecontent as a String and saves this to the default file (created if not existing)
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
		
		ExtensionFilter filterGoLfiles = new ExtensionFilter("GoL, *.rle","*.rle");

		FileChooser fc = new FileChooser();
		fc.setTitle("Open");
		fc.getExtensionFilters().addAll(filterGoLfiles);
	
		File f = fc.showOpenDialog(null);
		return f;
	}
}
