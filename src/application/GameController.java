package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

public class GameController {
	
	private GOLModel g_model = new GOLModel();
		
	@FXML
    private GridPane grd_Gameboard;

    @FXML
    private MenuItem mnu_FileOpen;

    @FXML
    private MenuItem mnu_FileSave;
	
	@FXML
    private Button btn_Pause;
	
    @FXML	
    private Button btn_Play;
    
    @FXML
    private Button bnt_Cancel;
    
    @FXML
    void btn_PlayPressed(ActionEvent event) {
    	g_model.setCell(grd_Gameboard);
	    	
	    }
    
    @FXML
    void btn_ResetPressed(ActionEvent event) {
    	g_model.setCell(grd_Gameboard);
	    	
	    }
    
    
    @FXML
    void mnu_FileOpenPressed(ActionEvent event) {
    	System.out.println("Open selected");
    	FileManagement fm = new FileManagement();
    	fm.openFile();
    }
    @FXML
    void mnu_FileSavePressed(ActionEvent event) {
    	System.out.println("Save selected");
    }
	
    @FXML
    protected void bnt_CancelPressed(ActionEvent event) {
    	System.exit(0);
    }
	
}





 

