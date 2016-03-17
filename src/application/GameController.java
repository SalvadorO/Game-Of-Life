package application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameController implements Initializable{
	
	protected GameboardCanvas gameboardcanvas =  new GameboardCanvas();
	protected GraphicsContext gc;
	protected FileManagement filemanager = new FileManagement();
	protected golDialog dialog = new golDialog();

	@FXML
	private Canvas img;
	
    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
	@FXML
    private Button btn_Reset, btn_PlayStop, btn_Quit;
	
	//TODO: This component is for testing purposes. To be removed when done
	@FXML
    private TextArea txtArea;
	
	/**
	 * When the menu item "SetupGridsize" is pressed, the gameboardcanvs' setgridsize dialog method
	 * is called.
	 * The returned values are sent to the GameBoardCanvas setGridmethod to set the grid size
	 * @param event
	 * @author hd
	 */
	@FXML
    void mnu_SetupGridsizePressed(ActionEvent event) {
				
		int[] o = dialog.setGridSizeDialogue().get();
//		testing by printing the returned values on the outputarea
		txtArea.setText(o[0] + "\n" + o[1]);
			
    }
			// Help dialog
			@FXML
			 void mnu_AboutDialogPressed(ActionEvent event) {
			 		dialog.AboutDialogue();
			 }
			
			
			// Advanced dialog
			@FXML
	    	private Slider Sld_SpeedSlider; 

			@FXML
				void rbn_AdvancedPressed(ActionEvent event)	{
//					Dialog.AdvancedDialogue();
//					System.out.println("test");
//					Sld_SpeedSlider.isVisible();
//					Sld_SpeedSlider.setDisable(false);
					Sld_SpeedSlider.setVisible(true);
					
			}
			// Stats dialog
			@FXML
				void mnu_StatsMenuPressed(ActionEvent event){
					dialog.StatsDialogue();
			}
			// Shapes dialog
			@FXML
				void mnu_ShapesPressed(ActionEvent event)	{
					dialog.ShapesDialogue();
			}
			
			
			// test knapp
			@FXML
			public Button btn_testPressed;
			
			@FXML
				void btn_testPressed(ActionEvent event){
				System.out.println("test");
				btn_testPressed.isDisabled();
			}
			
			
			

	/**
	 * NOTE: use txtArea for testing output
	 * @param event
	 * @author hd
	 */
    @FXML
    void btn_PlayStopPressed(ActionEvent event) {
    	gameboardcanvas.draw(gc);
//    	Disablig the play button after pressed
//    	btn_PlayStop.setText("Stop");
//    	btn_PlayStop.setDisable(true);

    	txtArea.appendText("\n" + gameboardcanvas.grid.toString());
    	gameboardcanvas.grid.setCellstatus(0, 3, 1);
    	txtArea.appendText("\n" + gameboardcanvas.grid.toString());
    	gameboardcanvas.grid.setCellstatus(3, 3, 1);
    	txtArea.appendText("\n" + gameboardcanvas.grid.toString());
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
    	//Get the file content 
    	String input = (filemanager.parseFile(filemanager.openFile()));
    	//Show the pattern
    	String[] output = input.split(":");
    	txtArea.setText(output[0] + "\n");
    	txtArea.appendText(output[1] + "\n");
    	txtArea.appendText(output[2] + "\n");
    	
    }
    
    @FXML
    void mnu_FileSavePressed(ActionEvent event) {
    	String filecontent = txtArea.getText();
    	filemanager.saveFile(filecontent);
    }
	
    @FXML
    protected void btn_QuitPressed(ActionEvent event) {
    	System.exit(0);
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = img.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
	}    
    
  
}