package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.File;
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
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


//Class GameController

 public class GameController implements Initializable{
	
//	 Declarations
	
//	Instatiates a GameboardCanvas object, with grid with default size 50x50
	protected GameboardCanvas gameboardcanvas =  new GameboardCanvas(50, 50);
	
	private GraphicsContext gc;

	private FileManagement filemanager = new FileManagement();
	
	private golDialog dialog = new golDialog();
	
	@FXML
	private Canvas Gameboard;

	@FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
    
    @FXML
    private CheckMenuItem cmi_Speed;
	
	@FXML
    private Button btn_Reset, btn_PlayStop, btn_Quit, btn_Next;
	
	@FXML
	private Slider Sld_Speed;

	@FXML
	private HBox HB_Speed;
	
	@FXML
	private Label lblGenerations;
	
	@FXML
	private TextField txtGenerations;
	
	/**
	 * Listens to the slider and sets the new slider value to the timeline, which increases the speed of the game
	 * @author Lars
	 */
	protected void ChangeSpeed(){
		Sld_Speed.valueProperty().addListener(new ChangeListener<Number>(){
			 public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val){
				gameboardcanvas.grid.getTimeline().setRate((double) new_val);
			}
		});
	}
	
	/**
	 * When the menu item "SetupGridsize" is pressed, the setgridsize dialog() method
	 * is called.
	 * The returned values are sent to the GameBoardCanvas setGridmethod to set the grid size
	 * and the grid is redrawn accordingly
	 * @author Hans Dragnes
	 * @param ActionEvent event
	 * 
	 */
	@FXML
	protected void mnu_SetupGridsizePressed(ActionEvent event) {
		int[] newgridsize = dialog.setGridSizeDialogue().get();

		//Check for null, -1 or -2; null means nothing was returned, -1 means Cancel was pressed and
		//-2 indicates input validation failed
		if (newgridsize != null) {
			if ( ! ( (newgridsize[0] == -1) || (newgridsize[0] == -2) ) ) {
				gameboardcanvas.grid.setGrid(newgridsize[0],newgridsize[1]);
				gameboardcanvas.grid.draw(gc, Gameboard);
			}
		}
	}
	
	/**
	 * Gets the About Dialogue when About is pressed
	 *
	 *@author Lars
	 * @param ActionEvent event
	 */
	@FXML
	private void mnu_AboutDialogPressed(ActionEvent event) {
 		dialog.AboutDialogue();
 	}

	/**
	 * Gets the Stats Dialogue when Stats is pressed
	 *
	 * @author Lars
	 * @param ActionEvent event
	 */
	@FXML
	private void mnu_StatsMenuPressed(ActionEvent event){
		//Stops the game when stats is pressed
		gameboardcanvas.grid.getTimeline().stop();
		btn_PlayStop.setText("Play");
		// Shows the Stats Dialogue
		dialog.StatsDialogue(gameboardcanvas.grid);
	}

	/**
	 * Will stop and start the game depending on btn_PlayStop text is set to stop or play 
	 *
	 * @author Lars, Hans Dragnes
	 * @param ActionEvent event
	 */
    @FXML
    private void btn_PlayStopPressed(ActionEvent event) {
    	if (btn_PlayStop.getText().equals("Stop")){
    		btn_PlayStop.setText("Play");
	    	gameboardcanvas.grid.getTimeline().stop();
    	}
    	else{
    		btn_PlayStop.setText("Stop");	
    		gameboardcanvas.grid.getTimeline().play();
    	}
    }

    /**
     * Method resets the Gameboardcanvas by gameboardcanvas gridobject's setGrid() method
     * and let the gameboardcanvas parameter point to this.
     * The current size is kept.
     * 
     * @author Lars, Hans Dragnes
     * @param ActionEvent event
     */
    @FXML
    private void btn_ResetPressed(ActionEvent event) {
    
//  Get and store the current gridsize 
    int currentNoOfColumns = gameboardcanvas.grid.getColumns();
    int currentNoOfRows = gameboardcanvas.grid.getRows();

//	Changes the button to Play and stops the timeline if reset is pressed while game is running
    if (btn_PlayStop.getText().equals("Play")){
    	gameboardcanvas.grid.getTimeline().stop();	
    	}	else		{
    		btn_PlayStop.setText("Play");	
    		gameboardcanvas.grid.getTimeline().stop();
    }
    // Resets the CountGen variable
    gameboardcanvas.grid.resetCountgen();
//  Resets the grid array by setting a new grid with current gridsize
    gameboardcanvas.grid.setGrid(currentNoOfColumns, currentNoOfRows);
    gameboardcanvas.grid.draw(gc, Gameboard);
	}
 
    
    /**
     * Method called when user selects File - Open
     * It checks if the shape to be loaded is within the gamegrid boundaries
     * 
     * @author Hans Dragnes
     * @param ActionEvent event
     */
    @FXML
    protected void mnu_FileOpenPressed(ActionEvent event) {
    	
    	//Get a file object by using FileManagement class openFile() method
    	File file = filemanager.openFile();
    	
    	//Check whether a file object is present
    	if (file != null){
    		//Get the file content as a HashMap (key-value pairs)
	    	HashMap<String, String> filecontent = filemanager.parseFile(file);
	    	
	    	//Check if size of shape exceeds current gameboard
	    	HashMap<String, String> headerelements = filemanager.getHeaderArray(filecontent.get("header")); 
	    	boolean shapeOk = gameboardcanvas.shapeWithinGamegridBoundaries(Integer.parseInt(headerelements.get("x")),
	    			Integer.parseInt(headerelements.get("y")));
	    	
	    	
	    	//If shape is not bigger than gamegrid array, parse the pattern,
	    	//update gamegrid array accordingly and redraw gameboard (i.e draw the laoded pattern)
	    	if (shapeOk) {
	       		gameboardcanvas.parsePattern(filecontent.get("pattern"));
	       	   	gameboardcanvas.grid.draw(gc, Gameboard);
	       	}
    	}
    }

    /**
     * TODO: To be implemented
     *
     * @author hd
     * @param ActionEvent event 
     */
    @FXML
    protected void mnu_FileSavePressed(ActionEvent event){
//    	filemanager.saveFile();
    }
    
    
	/**
	 * This will exit the game without any errors
	 *
	 * @param ActionEvent event
	 */
    @FXML
    private void btn_QuitPressed(ActionEvent event) {
    	System.exit(0);
    }
    
    /**
     * This will show the next gen and stop there, also add a tick to the CountGen variable
     * 
     * @param event the event
     * @author Lars, Salvador
     */
    @FXML
    private void btn_Next(ActionEvent event){
    	gameboardcanvas.grid.oneGen(gc, Gameboard);
    	// This stops the game when Next button is pressed
    	gameboardcanvas.grid.getTimeline().stop();
    	btn_PlayStop.setText("Play");
    }
       
    /**
     * This will show the HB_Box which contains the Speed Slider(sld_Speed), and allows you to change the speed of the game
     * 
     * @param
     * @author Lars 
     */
    @FXML
    protected void cmi_SpeedPressed(){
    	cmi_Speed.selectedProperty().addListener((ChangeListener<Boolean>) (ov, old_val, new_val) -> 
    	{if(HB_Speed.isVisible()){
    		HB_Speed.setVisible(false);
    	}	else	{
    		HB_Speed.setVisible(true);
    	}
    	});
    }
    
    /**
     * TODO: Cell Color
     * 
     * @param ActionEvent event
     * @author Lars 
     */
    @FXML
    protected void mnu_CellColorMenuPressed(ActionEvent event){
    	dialog.CellColorDialogue();

    }
    
    /**
     * TODO: Grid Color
     * 
     * @param ActionEvent event
     * @author Lars 
     */
    @FXML
    protected void mnu_GridColorMenuPressed(ActionEvent event){
    	dialog.GridColorDialogue();
    }
    
    /**
     * TODO: This initializes the ...
     * And sets the time between each KeyFrame to 1 secound.
     * 
     * @param
     * @author Lars, hd, Salvador
     */
    @Override
    
	public void initialize(URL location, ResourceBundle resources) {
		gc = Gameboard.getGraphicsContext2D();
//		gc.setFill(Color.BLACK);
		gameboardcanvas.grid.draw(gc, Gameboard);
		// Used for updating next generation
		gameboardcanvas.grid.setTimeline(new Timeline(new KeyFrame
				(Duration.seconds(1), Kv -> {
					gameboardcanvas.grid.oneGen(gc, Gameboard);
					})));
		// Sets the the timeline is going to go infinite times until stopped
    	gameboardcanvas.grid.getTimeline().setCycleCount(Animation.INDEFINITE);
    	
    	//Sets a listener to the Mouse clicked and dragged, and the Speed method
       Gameboard.setOnMouseClicked(this.mouseHandlerClicked);
       Gameboard.setOnMouseDragged(this.mouseHandlerDragged);
       ChangeSpeed();
       cmi_SpeedPressed();
    }
   
	/** 
	 * This will notice when you click the canvas and call a method which draws where you clicked on the grid
	 * 
     * @author Salvador, Lars 
     */
    private EventHandler<MouseEvent> mouseHandlerClicked = new EventHandler <MouseEvent>()		{
		@Override
		public void handle(MouseEvent event) {
		
			int x = (int) Math.floor((event.getX() / gameboardcanvas.grid.getCellSize()));
			int y = (int) Math.floor((event.getY() / gameboardcanvas.grid.getCellSize()));
			
		gameboardcanvas.grid.markCell(x, y, gc);

		}
    };
    
    /**
     * This will notice when you drag the canvas and call a method which draws where you dragged on the grid 
     * 
     * @author Salvador, Lars 
     */
    private EventHandler<MouseEvent> mouseHandlerDragged = new EventHandler <MouseEvent>()		{
		@Override
		public void handle(MouseEvent event) {

			int x = (int) Math.floor((event.getX() / gameboardcanvas.grid.getCellSize()));
			int y = (int) Math.floor((event.getY() / gameboardcanvas.grid.getCellSize()));
			
		gameboardcanvas.grid.markCell2(x, y, gc);
		}
    };

}