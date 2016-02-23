package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class GameController {
	
	protected GOLModel g_model = new GOLModel();
	protected FileManagement filemanager = new FileManagement();
	
	@FXML
    private GridPane grd_Gameboard;

    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave;
	
	@FXML
    private Button btn_Pause, btn_Play, bnt_Cancel;
	
	//TODO: This component is for testing purposes. To be removed
	@FXML
    private TextArea txtArea	;
    
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
    	txtArea.setText(filemanager.readFile());
    }
    
    @FXML
    void mnu_FileSavePressed(ActionEvent event) {
    	String filecontent = txtArea.getText();
    	filemanager.saveFile(filecontent);
    }
	
    @FXML
    protected void bnt_CancelPressed(ActionEvent event) {
    	System.exit(0);
    }
	
}





 

