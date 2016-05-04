package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileReader;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * The Class FileManagement.
 */
public class FileManagement {

	/**
	 * The method takes the filecontent as a String, and returns the pattern element from it.
	 *
	 * TODO: have to refactor according to hashmap regime, migth be obsolete
	 * 
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
	
//	Instantiate local variables. HashMap type is a nice way of storing key-value pairs,
//	while Stringbuilder type is an efficient way of building strings 
	HashMap<String, String> filecontent = new HashMap<String, String>();
	StringBuilder metadata = new StringBuilder();
	String header = "";
	StringBuilder pattern = new StringBuilder();

//	Use try-catch block to cathc exceptions during file operation (reading lines from file)
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
	catch ( FileNotFoundException fnfe ) {
		errorDialog( "File not found" );
    }
    catch ( IOException ioe )
    {
    	errorDialog( "An error occured while reading file" );
	}
	
	filecontent.put("Metadata", metadata.toString());
	filecontent.put("Header", header.replaceAll("\\s+",""));
	filecontent.put("Pattern", pattern.toString());
		
	return filecontent;
}
	
/**
 * The method takes the header as a String, splits it and returns the header
 * elements as key-value pairs in a HashMap. 
 * Expected header elements: x, y, rule
 * Important notice; x indicates number of columns, y indicates number of rows, rule is optional
 * Important notice; the map may contain 
 * TODO: consider adding Throws if header somehow is corrupt
 * @author hd
 * @param String header
 * @return String[] headerelements
 */
	public HashMap<String, String> getHeaderArray(String header)	{

	String[] infoElement;
	HashMap<String, String> headerElements = new HashMap<String, String>();

// 	Split header String into info elements, ',' separates info elements. Store 
	String[] headerElementsArray = header.split(",");
	
//	Get all info elements from the array, and add to HashMap as key-value pairs
	for (int counter = 0; counter < headerElementsArray.length; counter++) {
		infoElement = headerElementsArray[counter].split("=");
		headerElements.put(infoElement[0], infoElement[1]);
	};
	
	return headerElements;
}


	/**
	 * TODO: to be completed
	 * Method receives the filecontent as a String and saves this to file (created if not existing).
	 *
	 * @author hd
	 * @param String filecontent
	 */
//	public File saveFile() {
//		
//		ExtensionFilter filterGoLfiles = new ExtensionFilter("RLE file, *.rle","*.rle");
//
//		FileChooser saveFile = new FileChooser();
//		saveFile.getExtensionFilters().addAll(filterGoLfiles);
//		
//		File savedFile = saveFile.showSaveDialog(null);
//		return savedFile;
//		
//	}


/**
 * Method provides the user with a File open-dialogue to select a file. Allowed file types are
 * filtered using ExtensionFilter class. Filetype is limited to RLE files.
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
	
	/**
	 * The method is a generalized alert pop-up box which is intended to be called by various catch blocks. It notifes the user that 
	 * something went wrong, and takes a message as a parameter. The user click OK button to verify.
	 * 
	 * @author hd
	 * @param String the message to be shown to the user (used as HeaderText in the popup box)
	 */
	public void errorDialog(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("File problem");
		alert.setHeaderText(message);
		alert.show();
			
	}
}
