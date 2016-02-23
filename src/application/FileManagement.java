package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    /**
     * Method reads a file and returns the content as String.
     * Method uses openFile() method for the user dialogue.
     * @return filecontent as a String
     * @throws IOException
     * @author hd
     */
	public String readFile() 	{
	
		String file=openFile().toString();
		String filecontent = null;
		int i = 0;
				
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			while ((i = br.read()) != -1){
				filecontent +=((char) i+" ");
			}
		} 
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
		return filecontent;
				
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
