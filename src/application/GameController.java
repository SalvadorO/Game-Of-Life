package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameController implements Initializable{
	
	protected GOLModel model = new GOLModel();
	protected GameboardCanvas gameboardcanvas =  new GameboardCanvas();
	protected GraphicsContext gc;
	protected FileManagement filemanager = new FileManagement();

	@FXML
	private Canvas img;
	
    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
	@FXML
    private Button btn_Pause, btn_Play, bnt_Cancel;
	
	//TODO: This component is for testing purposes. To be removed when done
	@FXML
    private TextArea txtArea;
    
	/**
	 * When the menu item "SetupGridsize" is pressed, the gameboardcanvs' setgridsize dialog method
	 * is called.
	 * The returned values are sent to the GameBoardCanvas setGridmethod to set the grid size
	 * @param event
	 */
	@FXML
    void mnu_SetupGridsizePressed(ActionEvent event) {
				
		int[] o = model.setGridSizeDialogue().get();
		gameboardcanvas.setGridSize(o[0], o[1]);
		
		//The lines below are for testing, to be removed
		txtArea.setText(o[0] + "\n" + o[1]);
		txtArea.appendText("\n"+gameboardcanvas.getGridAsString());
		//The lines above, are for testing, to be removed
			
    }
	// Code for about
	@FXML
	void mnu_HelpAboutPressed(ActionEvent event) {
		model.HelpAboutDialogue().get();
	}
	
	
    @FXML
    void btn_PlayPressed(ActionEvent event) {
    	model.draw(gc);
    }

    /**
     * Method resets the Gameboarcanvas by creating a new gameboardcanvas object
     * and let the gameboardcanvas parameter point to this 
     * It also clears the graphicscontext object
     * @param event
     * @author hd
     */
    @FXML
    void btn_ResetPressed(ActionEvent event) {
    	gameboardcanvas = new GameboardCanvas();
    	gc.clearRect(0, 0, img.getWidth(), img.getHeight());
    	
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
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = img.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
	}    
}