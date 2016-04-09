package application;

import java.awt.event.MouseAdapter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.awt.MouseInfo;
import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.event.MouseMotionAdapter;
import javafx.scene.input.MouseEvent;

public class GameController implements Initializable{
	
	protected GameboardCanvas gameboardcanvas =  new GameboardCanvas();
	protected GraphicsContext gc;
	protected FileManagement filemanager = new FileManagement();
	protected golDialog dialog = new golDialog();

	@FXML
	protected Canvas img;
	
    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
	@FXML
    private Button btn_Reset, btn_PlayStop, btn_Quit, btn_Next, cmi_adv;
	
	@FXML
	private Slider Sld_Speed;
	
	@FXML
	private HBox HB_Advanced;
	
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
			 		golDialog.AboutDialogue();
			 }
			
			/*
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
			*/
			
			// Stats dialog
			@FXML
				void mnu_StatsMenuPressed(ActionEvent event){
					golDialog.StatsDialogue();
			    	HB_Advanced.setVisible(true);
			}
			
			// Shapes dialog
			@FXML
				void mnu_ShapesPressed(ActionEvent event)	{
					golDialog.ShapesDialogue();
			}
		
			
			

	/**
	 * NOTE: use txtArea for testing output
	 * @param event
	 * @author hd
	 */
    @FXML
    void btn_PlayStopPressed(ActionEvent event) {
    	gameboardcanvas.draw(gc);
//    	Disabling the play button after pressed
    	btn_PlayStop.setText("Stop");
    	//btn_PlayStop.setDisable(true);

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
    	//Get the file content as an array
    	String[] input = (filemanager.parseFile(filemanager.openFile()));
    	//Show the content in output area
    	txtArea.clear();
       	for (String e : input)
       		txtArea.appendText(e + "\n");
    	
       	//parse and show the pattern
       	txtArea.appendText("\n" + filemanager.getHeader(input));
//       	gameboardcanvas.drawTWO(gc, input[2]);
    	
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
    
    @FXML
    void btn_Next(ActionEvent event){
    	// This will show the next gen and stop there
    	gameboardcanvas.grid.nextGeneration(null, gc);
    }
    
    @FXML
    void cmi_adv(ActionEvent event){
    	// This will activate the advanced menu
//    	Sld_Speed.setVisible(true);
//    	System.out.println("The Speed slider is now visible");
//    	Sld_SpeedSlider.isVisible();
//		Sld_SpeedSlider.setDisable(false);
//		Sld_SpeedSlider.setVisible(true);
//    	cmi_adv.setSelected(true);
    	
//		golDialog.StatsDialogue();
		
//    	HB_Advanced.setVisible(true);
//    	cmi_adv.setOnAction(new EventHandler<ActionEvent>() {
//    		public void handle(ActionEvent e)	{
//    			System.out.println("YES!");
//    		}
//    	});
    }
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = img.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
    }   
    
    protected static EventHandler<MouseEvent> mouseHandlerClicked = new EventHandler <MouseEvent>()	{
		@Override
		public void handle(MouseEvent event) {
			System.out.println("Click! "+event.getX()+" "+event.getY());
		}
    };
    protected static EventHandler<MouseEvent> mouseHandlerDragged = new EventHandler <MouseEvent>()	{
		@Override
		public void handle(MouseEvent event) {
			System.out.println("DRAAAG!! "+event.getX()+" "+event.getY());
		}
    };
    
}