package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
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
	
// 	Declaring the gameboardcanvas object, sending default values to the constructor
	private GameboardCanvas gameboardcanvas =  new GameboardCanvas(50, 50);
	
//	Declaring the gc object (Graphics contect)
	private GraphicsContext gc;

//	Declaring the filemanager object
	private FileManagement filemanager = new FileManagement();
	
//	The dialog object
	private golDialog dialog = new golDialog();

//	Declaring the timeline object
	private Timeline timeline = new Timeline();
	
//	Declaring the Gameboard object
	@FXML
	private Canvas Gameboard;

//	Declaring mnu_FileOpen, mnu_FileSave, mnu_SetupGridsize variables
    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
//  Declaring the Button type variables
	@FXML
    private Button btn_Reset, btn_PlayStop, btn_Quit, btn_Next, btn_GridSize;
	
//	Declaring the SLD_Speed object (Slider type)
	@FXML
	private Slider Sld_Speed;

//	Declaring the HB_Advanced (HBox type)
	@FXML
	private HBox HB_Advanced;
	
//	Variable for counting number of generations played
	private int CountGen;
	
	
	/**
	 * When the menu item "SetupGridsize" is pressed, the setgridsize dialog() method
	 * is called.
	 * The returned values are sent to the GameBoardCanvas setGridmethod to set the grid size
	 * and the grid is redrawn accordingly
	 * @author hd
	 * @param event
	 */
	@FXML
	public void mnu_SetupGridsizePressed(ActionEvent event) {
		int[] newgridsize = dialog.setGridSizeDialogue().get();

		//Check for null or -1; null means nothing was returned and -1 means Cancel was pressed in dialog
		//-2 indicates validation failed
		System.out.println("Returned value: " + newgridsize[0]);
		
		if (newgridsize != null)
			if ( ! ( (newgridsize[0] == -1) || (newgridsize[0] == -2) ) ) {
				gameboardcanvas.grid.setGrid(newgridsize[0],newgridsize[1]);
				gameboardcanvas.grid.draw(gc, Gameboard);
		}

	}
	
	/**
	 * Mnu_About dialog pressed.
	 *
	 * @author lars
	 * @param ActionEvent event
	 */
	@FXML
	public void mnu_AboutDialogPressed(ActionEvent event) {
 		dialog.AboutDialogue();
 	}

	/**
	 * Mnu_Stats menu pressed.
	 *
	 * @author lars
	 * @param event the event
	 */
	@FXML
	public void mnu_StatsMenuPressed(ActionEvent event){
		timeline.stop();
		btn_PlayStop.setText("Play");
		dialog.StatsDialogue();
	}

	/**
	 * Btn_PlayStop pressed.
	 *
	 * @author hd
	 * @param ActionEvent event
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
     * Method resets the Gameboardcanvas by gameboardcanvas gridobject's setGrid() method
     * and let the gameboardcanvas parameter point to this.
     * The current size is kept.
     * 
     * @author hd
     * @param ActionEvent event
     */
    @FXML
    public void btn_ResetPressed(ActionEvent event) {
    
    int currentNoOfColumns = gameboardcanvas.grid.getColumns();
    int currentNoOfRows = gameboardcanvas.grid.getRows();

//	Changes the button to Play and stops the timeline if reset is pressed while game is running
    if (btn_PlayStop.getText().equals("Play")){
    	timeline.stop();	
    	}	else		{
    		btn_PlayStop.setText("Play");	
    		timeline.stop();
    }
  
//  Resets the grid array by thworing the old and instatiate a new
    gameboardcanvas.grid.setGrid(currentNoOfColumns, currentNoOfRows);
    gameboardcanvas.grid.draw(gc, Gameboard);
    
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
    public void mnu_FileOpenPressed(ActionEvent event) {
    	
    	//Get a file object by using FileManagement class openFile() method
    	File file = filemanager.openFile();
    	//Check whether a file object is present
    	if (file != null){
    		//Get the file content as a HashMap (key-value pairs)
	    	HashMap<String, String> filecontent = filemanager.parseFile(file);
	    	
	    	System.out.println(filecontent.get("Metadata"));
	    	System.out.println(filecontent.get("Header"));
	    	System.out.println(filecontent.get("Pattern"));
	    	
	    	//Check if size of shape exceedes current gameboard
	    	String[] header = filemanager.getHeaderArray(filecontent.get("Header")); 
	    	//Get the y and x values (c indicates column hence is x value)
	    	String[] c = header[0].split("=");
	    	String[] r = header[1].split("=");
	    	
	       	//If shape is not bigger than gamegrid array;
	    	//parse the pattern, update gamegrid array accordingly and redraw gameboard (i.e draw the laoded pattern)
	       	if (!gameboardcanvas.shapeBiggerThanGameboard(Integer.parseInt(c[1]), Integer.parseInt(r[1]))){
	       		gameboardcanvas.parsePattern(filecontent.get("Pattern"));
	       	   	gameboardcanvas.grid.draw(gc, Gameboard);
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
    public void mnu_FileSavePressed(ActionEvent event){
    	filemanager.saveFile();
    }
    
    
	/**
	 * Btn_Quit pressed.
	 *
	 * @param event the event
	 */
    @FXML
    public void btn_QuitPressed(ActionEvent event) {
    	System.exit(0);
    }
    
    /**
     * Btn_Next.
     *
     * @param event the event
     */
    @FXML
    public void btn_Next(ActionEvent event){
    	// This will show the next gen and stop there
    	gameboardcanvas.grid.oneGen(gc, Gameboard);
    	CountGen++;
    }
    
    /**
     * CheckMenuItem
     *
     * @author lars
     * @param event the event
     */
    @FXML
    public void mnu_AdvancedMenuPressed(ActionEvent event){
    	System.out.println("test");
    	dialog.AdvancedDialogue();
    }
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = Gameboard.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gameboardcanvas.grid.draw(gc, Gameboard);
		// Animation
		// Used for updating next generation
		timeline = new Timeline(new KeyFrame
				(Duration.millis(125), Kv -> {
					gameboardcanvas.grid.oneGen(gc, Gameboard);
					CountGen++;
					}));
    	timeline.setCycleCount(Animation.INDEFINITE);
    	
		// lets us connect the mouse event that is in controller class in some way
       Gameboard.setOnMouseClicked(mouseHandlerClicked);
       Gameboard.setOnMouseDragged(mouseHandlerDragged);
		
    }
    
    /** The mouse handler clicked. */
    public EventHandler<MouseEvent> mouseHandlerClicked = new EventHandler <MouseEvent>()		{
		@Override
		public void handle(MouseEvent event) {
		
			int x = (int) Math.floor((event.getX() / gameboardcanvas.grid.getCellSize()));
			int y = (int) Math.floor((event.getY() / gameboardcanvas.grid.getCellSize()));
			
		gameboardcanvas.grid.DrawOnGameGrid(x, y, gc);
		}
    };
    
    /** The mouse handler dragged. */
    public EventHandler<MouseEvent> mouseHandlerDragged = new EventHandler <MouseEvent>()		{
		@Override
		public void handle(MouseEvent event) {

			int x = (int) Math.floor((event.getX() / gameboardcanvas.grid.getCellSize()));
			int y = (int) Math.floor((event.getY() / gameboardcanvas.grid.getCellSize()));
			
		gameboardcanvas.grid.DrawOnGameGrid(x, y, gc);
		}
    };

}