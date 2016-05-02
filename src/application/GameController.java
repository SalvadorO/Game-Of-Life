package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


//Class GameController

public class GameController implements Initializable{
	
// 	Decalring the gameboardcanvas object
	private GameboardCanvas gameboardcanvas =  new GameboardCanvas();
	
//	Declaring the gc object (Graphics contect)
	private static GraphicsContext gc;

//	Declaring the filemanager object
	private FileManagement filemanager = new FileManagement();
	
//	The dialog object
	private golDialog dialog = new golDialog();

//	Declaring the timeline object
	protected Timeline timeline = new Timeline();
	
//	Declaring the Gameboard object
	@FXML
	private Canvas Gameboard;

//	Declaring mnu_FileOpen, mnu_FileSave, mnu_SetupGridsize variables
    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
//  Declaring the Button type variables
	@FXML
    private Button btn_Reset, btn_PlayStop, btn_Quit, btn_Next, btn_GridSize, cmi_adv;
	
//	Declaring the SLD_Speed object (Slider type)
	@FXML
	private Slider Sld_Speed;

//	Declaring the HB_Advanced (HBox type)
	@FXML
	private HBox HB_Advanced;
	
	
	/**
	 * When the menu item "SetupGridsize" is pressed, the setgridsize dialog() method
	 * is called.
	 * The returned values are sent to the GameBoardCanvas setGridmethod to set the grid size
	 * and the grid is redrawn accordingly
	 * @author hd
	 * @param event
	 */
	@FXML
	protected void mnu_SetupGridsizePressed(ActionEvent event) {
		int[] newgridsize = dialog.setGridSizeDialogue().get();

		//Check for null or -1; null means nothing was returned and -1 means Cancel was pressed in dialog
		//-2 indicates validation failed
		System.out.println("Returned value: " + newgridsize[0]);
		
		if (newgridsize != null)
			if ( ! ( (newgridsize[0] == -1) || (newgridsize[0] == -2) ) ) {
				gameboardcanvas.grid.setGrid(newgridsize[0],newgridsize[1]);
				Grid.draw(gc, Gameboard);
		}

	}
	
	/**
	 * Mnu_About dialog pressed.
	 *
	 * @author lars
	 * @param ActionEvent event
	 */
	@FXML
	protected void mnu_AboutDialogPressed(ActionEvent event) {
 		golDialog.AboutDialogue();
 	}

	/**
	 * Mnu_Stats menu pressed.
	 *
	 * @author lars
	 * @param event the event
	 */
	@FXML
	protected void mnu_StatsMenuPressed(ActionEvent event){
		golDialog.StatsDialogue();
	}

	/**
	 * Btn_PlayStop pressed.
	 *
	 * @author hd
	 * @param event the event
	 */
    @FXML
    public void btn_PlayStopPressed(ActionEvent event) {

    	if (btn_PlayStop.getText().equals("Stop")){
    		btn_PlayStop.setText("Play");
	    	timeline.stop();
    	}
    	else{
    		btn_PlayStop.setText("Stop");	
    		timeline.play();
    	}
    }

    /**
     * Method resets the Gameboardcanvas by creating a new gameboardcanvas object
     * and let the gameboardcanvas parameter point to this 
     * It also clears the graphicscontext object.
     * TODO: do not reset to 50x50, but actual grid size
     *
     * @author hd
     * @param event the event
     */
    @FXML
    protected void btn_ResetPressed(ActionEvent event) {
    
    gc.clearRect(0, 0, Gameboard.getWidth(), Gameboard.getHeight());
    
    gameboardcanvas = new GameboardCanvas();
    Grid.draw(gc, Gameboard);
    
    //Changes the button to Play and stops the timeline if reset is pressed
    if (btn_PlayStop.getText().equals("Play")){
    	timeline.stop();	
    	}	else		{
    		btn_PlayStop.setText("Play");	
    		timeline.stop();
    }
	}
 
    
    /**
     * Method called when user selects File - Open
     * It checks if the shape to be loaded is within the gamegrid boundaries
     * TODO: checking procedure should be a separate method in a model class.
     *
     * @author hd
     * @param ActionEvent event
     */
    @FXML
    protected void mnu_FileOpenPressed(ActionEvent event) {
    	
    	//Get a file object by using FileManagement class openFile() method
    	File file = filemanager.openFile();
    	//Check whether a file object is present
    	if (file != null){
    		//Get the file content as an array
	    	String[] filecontent = filemanager.parseFile(file);
	    	    	
	    	//Check if size of shape exceedes current gameboard
	    	String[] header = filemanager.getHeader(filecontent); 
	    	//Get the y and x values (c indicates column hence is x value)
	    	String[] c = header[0].split("=");
	    	String[] r = header[1].split("=");
	    	
	       	//If shape is not bigger than gamegrid array;
	    	//parse the pattern, update gamegrid array accordingly and redraw gameboard (i.e draw the laoded pattern)
	       	if (!gameboardcanvas.shapeBiggerThanGameboard(Integer.parseInt(c[1]), Integer.parseInt(r[1]))){
	       		gameboardcanvas.parsePattern(filecontent[2]);
	       	   	Grid.draw(gc, Gameboard);
	       	}
    	}
    }

    /**
     * TODO: to be implemented.
     *
     * @author hd
     * @param ActionEvent event 
     */
    @FXML
    protected void mnu_FileSavePressed(ActionEvent event){
    	FileManagement.saveFile();
    }
    
    
	/**
	 * Btn_Quit pressed.
	 *
	 * @param event the event
	 */
    @FXML
    protected void btn_QuitPressed(ActionEvent event) {
    	System.exit(0);
    }
    
    /**
     * Btn_Next.
     *
     * @param event the event
     */
    @FXML
    protected void btn_Next(ActionEvent event){
    	// This will show the next gen and stop there
    	gameboardcanvas.grid.oneGen(gc, Gameboard);
    }
    
    /**
     * CheckMenuItem
     *
     * @author lars
     * @param event the event
     */
    @FXML
    protected void cmi_adv(ActionEvent event){
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
		gc = Gameboard.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		Grid.draw(gc, Gameboard);
		// Animation
		// Used for updating next generation
		timeline = new Timeline(new KeyFrame(
    	        Duration.millis(125),
    	        Kv -> gameboardcanvas.grid.oneGen(gc, Gameboard)));
    	timeline.setCycleCount(Animation.INDEFINITE);
    	
		// lets us connect the mouse event that is in controller class in some way
       Gameboard.setOnMouseClicked(GameController.mouseHandlerClicked);
       Gameboard.setOnMouseDragged(GameController.mouseHandlerDragged);
		
    }
    
    /** The mouse handler clicked. */
    protected static EventHandler<MouseEvent> mouseHandlerClicked = new EventHandler <MouseEvent>()		{
		@Override
		public void handle(MouseEvent event) {
		
			int x = (int) Math.floor((event.getX() / Grid.cellSize));
			int y = (int) Math.floor((event.getY() / Grid.cellSize));
			
		Grid.DrawOnGameGrid(x, y, gc);
		}
    };
    
    /** The mouse handler dragged. */
    protected static EventHandler<MouseEvent> mouseHandlerDragged = new EventHandler <MouseEvent>()		{
		@Override
		public void handle(MouseEvent event) {

			int x = (int) Math.floor((event.getX() / Grid.cellSize));
			int y = (int) Math.floor((event.getY() / Grid.cellSize));
			
		Grid.DrawOnGameGrid(x, y, gc);
		}
    };

}