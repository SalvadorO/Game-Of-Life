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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileManagement {

//    private void saveFile(ActionEvent event) throws IOException {
//    	System.out.println(txtAreaInput.getText());
//    	
//    	OutputStream os = new FileOutputStream("hans.txt");
//    	Writer osw = new OutputStreamWriter(os);
//    	
//    	osw.write("Hei");
//    	osw.close();
//
//    }
    
    
	private void readFile() 	{
	

		String file=openFile().toString();
//		txtAreaOutput.setText(file+"\n");

		Path path = Paths.get(".");
		System.out.println(path);
		
		InputStream is = new FileInputStream(file);
		Reader isr = new InputStreamReader(is);
				
		int i = 0;
		
		try (BufferedReader br = new BufferedReader(isr)) {
			while ((i = isr.read()) != -1){
				txtAreaOutput.appendText((char) i+" ");
			}
		} 
		
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
				
	}
	
	public File openFile(){
		
		ExtensionFilter ef = new ExtensionFilter("Text, *.txt","*.txt");
		
		FileChooser fc = new FileChooser();
		fc.setTitle("Open");
		fc.getExtensionFilters().addAll(ef);
	
		File f = fc.showOpenDialog(null);
		return f;
	}
}
