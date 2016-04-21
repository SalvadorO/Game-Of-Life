package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.io.File;
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

public class GameController implements Initializable{
	
	private GameboardCanvas gameboardcanvas =  new GameboardCanvas();
	private static GraphicsContext gc;
	private FileManagement filemanager = new FileManagement();
	private golDialog dialog = new golDialog();

	Timeline timeline = new Timeline();
	
	@FXML
	private Canvas Gameboard;

    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
	@FXML
    private Button btn_Reset, btn_PlayStop, btn_Quit, btn_Next, cmi_adv, btn_GridSize;
	
	@FXML
	private Slider Sld_Speed;

	@FXML
	private HBox HB_Advanced;
	
	
	/**
	 * When the menu item "SetupGridsize" is pressed, the setgridsize dialog method
	 * is called.
	 * The returned values are sent to the GameBoardCanvas setGridmethod to set the grid size
	 * @param event
	 * @author hd
	 */
	@FXML
	protected void mnu_SetupGridsizePressed(ActionEvent event) {
		int[] newgridsize = dialog.setGridSizeDialogue().get();

			if (newgridsize != null) {
				gameboardcanvas.grid.setGrid(newgridsize[0],newgridsize[1]);
				Grid.draw(gc, Gameboard);
		}

			Grid.draw(gc, Gameboard);
	}
	
	/**
	 * 
	 * @param event
	 * @author lars
	 */
	@FXML
	protected void mnu_AboutDialogPressed(ActionEvent event) {
 		golDialog.AboutDialogue();
 	}

	/**
	 * 
	 * @param event
	 * @author lars
	 */
	@FXML
	protected void mnu_StatsMenuPressed(ActionEvent event){
		golDialog.StatsDialogue();
//		HB_Advanced.setVisible(true);			   
	}

	/**
	 * @param event
	 * @author hd
	 */
    @FXML
    protected void btn_PlayStopPressed(ActionEvent event) {

    	if (btn_PlayStop.getText().equals("Stop")){
    		btn_PlayStop.setText("Play");
	    	timeline.stop();	
    	}else{
    		btn_PlayStop.setText("Stop");	
    		timeline.play();
    	}
    	gameboardcanvas.grid.setCellstatus(8, 8, 1);
    	gameboardcanvas.grid.setCellstatus(16, 12, 1);
    }

    /**
     * Method resets the Gameboarcanvas by creating a new gameboardcanvas object
     * and let the gameboardcanvas parameter point to this 
     * It also clears the graphicscontext object
     * @param event
     * @author hd
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
    /*
    @FXML
    protected void btn_ResetPressed(ActionEvent event) {
    	
//  gc.clearRect(0, 0, Gameboard.getWidth(), Gameboard.getHeight());
    gc.clearRect(0, 0, Grid.gamegrid.length, Grid.gamegrid[0].length);
    
    
//    gc.clearRect(0, 0, Grid.gamegrid.length, Grid.gamegrid[0].length);
    gameboardcanvas = new GameboardCanvas();
    
    
//    if(){
//    	
//    }	else {
//    	gameboardcanvas = new GameboardCanvas();
//        Grid.draw(gc, Gameboard);
//    }
//    mnu_SetupGridsize.getOnAction()
//    gameboardcanvas.grid.setGrid(newgridsize[0], newgridsize[1]);
    
//    Grid.gamegrid.length 
//    Grid.gamegrid[0].length
//    Grid.draw(gc, Gameboard);
    
    
    //Changes the button to Play and stops the timeline if reset is pressed
    if (btn_PlayStop.getText().equals("Play")){
    	timeline.stop();	
    	}	else		{
    		btn_PlayStop.setText("Play");	
    		timeline.stop();
    }*/
    /**
     * Method called when user selects File - Open
     * It checks if the shape to be loaded is within the gamegrid boundaries
     * TODO: checking procedure should be a separate method in a model class
     * @param event
     * @author hd
     */
    @FXML
    protected void mnu_FileOpenPressed(ActionEvent event) {
    	//Get the file content as an array
    	File file = filemanager.openFile();
    	String[] filecontent = filemanager.parseFile(file);
    	    	
    	//Check if size of shape exceedes current gameboard
    	String[] header = filemanager.getHeader(filecontent); 
    	//Get the y and x values (c indicates column hence is x value)
    	String[] c = header[0].split("=");
    	String[] r = header[1].split("=");
    	 
    	//For testing
    	System.out.println(gameboardcanvas.shapeBiggerThanGameboard(Integer.parseInt(c[1]), Integer.parseInt(r[1])));
    	//End for testing
    	
       	//If shape is not bigger than gamegrid array;
    	//parse the pattern, update gamegrid array accordingly and redraw gameboard (i.e draw the laoded pattern)
       	if (!gameboardcanvas.shapeBiggerThanGameboard(Integer.parseInt(c[1]), Integer.parseInt(r[1]))){
       		gameboardcanvas.parsePattern(filecontent[2]);
       	   	Grid.draw(gc, Gameboard);
       	}
    }

    /**
     * TODO: to be implemented
     * @param event
     * @author hd
     */
    @FXML
    protected void mnu_FileSavePressed(ActionEvent event) {
//      	filemanager.saveFile(filecontent);
    }
    
	/**
	 * 
	 * @param event
	 */
    @FXML
    protected void btn_QuitPressed(ActionEvent event) {
    	System.exit(0);
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    protected void btn_Next(ActionEvent event){
    	// This will show the next gen and stop there
    	gameboardcanvas.grid.oneGen(gc, Gameboard);
    }
    
    /**
     * 
     * @param event
     * @author lars
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
    
    /**
     * 
     */
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
    
    /**
     * 
     */
    protected static EventHandler<MouseEvent> mouseHandlerClicked = new EventHandler <MouseEvent>()		{
		@Override
		public void handle(MouseEvent event) {
		
			int x = (int) Math.floor((event.getX() / Grid.cellSize));
			int y = (int) Math.floor((event.getY() / Grid.cellSize));
			
		Grid.updateGameGrid(x, y, gc);
		}
    };
    
    /**
     * 
     */
    protected static EventHandler<MouseEvent> mouseHandlerDragged = new EventHandler <MouseEvent>()		{
		@Override
		public void handle(MouseEvent event) {

			int x = (int) Math.floor((event.getX() / Grid.cellSize));
			int y = (int) Math.floor((event.getY() / Grid.cellSize));
			
		Grid.updateGameGrid(x, y, gc);
		}
    };
}