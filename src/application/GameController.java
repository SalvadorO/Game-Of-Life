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
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javafx.scene.input.MouseEvent;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 

public class GameController implements Initializable{
	
	public GameboardCanvas gameboardcanvas =  new GameboardCanvas();
	protected static GraphicsContext gc;
	protected FileManagement filemanager = new FileManagement();
	protected golDialog dialog = new golDialog();

	@FXML
	protected Canvas img;
	
    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
	@FXML
    private Button btn_Reset, btn_PlayStop, btn_Quit, btn_Next, btn_GridSize;
	
	@FXML
	private Slider Sld_Speed;
	
	@FXML
	private HBox HB_Advanced;
	
	//TODO: This component is for testing purposes. To be removed when done
	@FXML
    private TextArea txtArea;
	
	@FXML
	private CheckMenuItem cmi_adv;

	
	/**
	 * When the menu item "SetupGridsize" is pressed, the gameboardcanvs' setgridsize dialog method
	 * is called.
	 * The returned values are sent to the GameBoardCanvas setGridmethod to set the grid size
	 * @param event
	 * @author hd
	 */
	@FXML
    void mnu_SetupGridsizePressed(ActionEvent event) {
			
		
		/* TODO: fix error when exiting setupgridsize
		 * TODO: connect new gridsize values to gridsize array
		 * TODO: update "current gridsize", maybe implement a getGridsize method in grid
		 */
		
		int[] newgridsize = dialog.setGridSizeDialogue().get();
		gameboardcanvas.grid.setGrid(newgridsize[0], newgridsize[1]);
		
		
//		dialog.setGridSizeDialogue();
		
		
//		testing by printing the returned values on the outputarea
		
			
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
    	Grid.draw(gc);
//    	Disabling the play button after pressed
    	btn_PlayStop.setText("Stop");
    	//btn_PlayStop.setDisable(true);
    	
    	
    	gameboardcanvas.grid.setCellstatus(0, 3, 1);
    	
    	gameboardcanvas.grid.setCellstatus(3, 3, 1);
    	
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
    
    
    /**
     * Method opens a file input dialog to let the user choose a .rle file.
     * The pattern is subsequently drawn on the canvas
     * @param event
     * @author hd
     */
    @FXML
    void mnu_FileOpenPressed(ActionEvent event) {
 
    	/*TODO: lag en model-klasse
    	 * ved innlasting av fil: 
    	 * 1) nullstill gamegrid 
    	 * 2) oppdater med innlastett fil
    	 * 3) tegn på canvas
    	 * 4) Oppdater drawmetode til å støtte dette
    	 */
    	
    	
    	//Open a file object by calling the filemanager class
    	File file=filemanager.openFile();
    	//Get the file content as an array
    	String[] filecontent = (filemanager.parseFile(file));
    	//Show the content in console for testing
    	String s="";
       	for (String e : filecontent){
       		s += e;
       		s += "\n";
       	}
       	System.out.println(s);
//       	System.out.println(filemanager.getPattern(filecontent));
       	//parse and show the pattern
       	
    	
//	TODO: draw the pattern
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
			
			int x = (int) event.getX();
			int y = (int) event.getY();
			if (x >= 0  && y > 0)
				if (x < Grid.gamegrid.length-1)
					if (y < Grid.gamegrid.length-1)	
			
		Grid.updateGameGrid(x, y, gc);
		
		
		}
    };
    protected static EventHandler<MouseEvent> mouseHandlerDragged = new EventHandler <MouseEvent>()	{
		@Override
		public void handle(MouseEvent event) {
			System.out.println("DRAAAG!! "+event.getX()+" "+event.getY());
			
//			gameboardcanvas.setCodY() = (int) event.getX()/Grid.cellSize;
			int x = (int) event.getX();
			int y = (int) event.getY();
			if (x >= 0  && y > 0)
			if (x < Grid.gamegrid.length-1)
				if (y < Grid.gamegrid[0].length-1)
			Grid.updateGameGrid(x, y, gc);
			
		}
    };
    
    
    
}