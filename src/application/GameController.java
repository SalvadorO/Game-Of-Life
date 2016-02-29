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
    private Canvas canvas;

    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
	@FXML
    private Button btn_Pause, btn_Play, bnt_Cancel;
	
	//TODO: This component is for testing purposes. To be removed
	@FXML
    private TextArea txtArea;
    
	/**
	 * Method request an array from Grid. Draws this on the canvas
	 * Code should be moved to model later on. Enabled here for testing purposes
	 * @param event
	 * @author hd
	 */

	@FXML
    void mnu_SetupGridsizePressed(ActionEvent event) {
		
    }
	
    @FXML
    void btn_PlayPressed(ActionEvent event) {

    	model.draw(gc);
    }

    /**
     * Implemented testing of setting a specific cell and show the 1d representation in the output area
     * Just using the reset button to help kicking the method
     * @param event
     */

    @FXML
    void btn_ResetPressed(ActionEvent event) {


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

		System.out.println("Controller initialized");

	}

}





 

