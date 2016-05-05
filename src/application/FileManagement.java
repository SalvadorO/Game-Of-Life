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
	 * FileManagement class keeps all operations related to managing files.
	 * This includes opening and parsing
	 * @author Hans Dragnes
	 */
	class FileManagement {
	
		
	/**
	 * The method takes a file and returns the content as a HashMap (key-value pairs).
	 * The method assumes files is formatted according to the convention; http://www.conwaylife.com/wiki/Run_Length_Encoded; where
	 * - First line contains header information: x='value', y='value', [optional]rule='value'
	 * - Remaining lines contains the payload, the actual pattern. 
	 * - The first line may be preceded with one or more lines beginning with hashtag; #
	 * 
	 * @author Hans Dragnes
	 * @param File f
	 * @return HashMap<String><String> containing the elements; metadata, header and pattern;
	 */
	protected HashMap<String, String> parseFile(File f) {
	
		File file = f;
		
	//	Instantiate local variables. HashMap type is a nice way of storing key-value pairs dynamically,
	//	while Stringbuilder type is an efficient way of building strings 
		HashMap<String, String> filecontent = new HashMap<String, String>();
		StringBuilder metadata = new StringBuilder();
		String header = "";
		StringBuilder pattern = new StringBuilder();
	
	//	Use try-catch block to catch exceptions during file operation (reading lines from file)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = null;
	//	Do - while loop until end of file (indicated by line == null)
			do{
			line = br.readLine();
				if (line != null){
	//	One or more lines may start with hashtag, this indicates metadata
					if (line.startsWith("#")){
						metadata.append(line);
					}
	//	If line does not start with hashtag, and the header variable is empty, the line is a header
					else {
						if (header == "")
							header = line;
	//	Otherwise, the line is a part of the pattern, hence added to the patternvariable
						else
							pattern.append(line);
					}
				};
		
			}while (line != null);	
		} 
	//	Throw exception (notify user) if file is not found
		catch ( FileNotFoundException fnfe ) {
			errorDialog( "File not found" );
	    }
	//	Throw exception (notify user) if an error occurs during file processing
		catch ( IOException ioe )
	    {
	    	errorDialog( "An error occured while reading file" );
		}
		
//		Populate HashMap with data
		filecontent.put("metadata", metadata.toString());
	//	Removing spaces from header String
		filecontent.put("header", header.replaceAll("\\s+",""));
		filecontent.put("pattern", pattern.toString());
			
		return filecontent;
	}
		
	/**
	 * The method takes the header as a String, splits it and returns the header
	 * elements as key-value pairs in a HashMap. 
	 * Expected header element keys: x, y, rule
	 * Important notice; x indicates number of columns, y indicates number of rows, rule is optional
	 * 
	 * @author Hans Dragnes
	 * @param header as a String
	 * @return HashMap <String, String> headerElements
	 */
		protected HashMap<String, String> getHeaderArray(String header) {
	
		String[] infoElement;
		HashMap<String, String> headerElements = new HashMap<String, String>();
	
	// 	Split header String into info elements, ',' separates info elements 
		String[] headerElementsArray = header.split(",");
		
	//	Get all info elements from the array, and add to HashMap as key-value pairs
		for (int counter = 0; counter < headerElementsArray.length; counter++) {
			infoElement = headerElementsArray[counter].split("=");
			headerElements.put(infoElement[0].toLowerCase(), infoElement[1]);
		};
		return headerElements;
	}
	
	/**
	 * Method provides the user with a File open-dialogue to select a file. Allowed file types are
	 * filtered using ExtensionFilter class. Filetype is limited to RLE files.
	 * @author Hans Dragnes
	 * @return File object: The file, or null if none is selected
	 */
		protected File openFile() {
			
			ExtensionFilter filterGoLfiles = new ExtensionFilter("RLE file, *.rle","*.rle");
	
			FileChooser openFile = new FileChooser();
			openFile.getExtensionFilters().addAll(filterGoLfiles);
		
			File f = openFile.showOpenDialog(null);
			
			return f;
		}
		
		/**
		 * The method is a generalized alert pop-up box which is intended to be called by various catch blocks (not limited to).
		 * It notifes the user that something went wrong, and shows the parameterized message. 
		 * The user clicks OK button to acknowledge.
		 * 
		 * @author Hans Dragnes
		 * @param String the message to be shown to the user (used as HeaderText in the popup box)
		 * 
		 */
		protected void errorDialog(String message) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("File problem");
			alert.setHeaderText(message);
			alert.show();		
		}
		
	
	//	/**
	//	 * TODO: To be implemented
	//	 * Method receives the filecontent as a String and saves this to file (created if not existing).
	//	 *
	//	 * @author hd, lars
	//	 * @param String filecontent
	//	 */
	//	private File saveFile() {
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
	
	}
