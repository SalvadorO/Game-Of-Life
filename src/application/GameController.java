package application;


import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


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
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameController implements Initializable{
	//int gridWid = 30;
//	int gridHei = 30;
	protected GameboardCanvas gameboardcanvas =  new GameboardCanvas();
	protected static GraphicsContext gc;
	protected FileManagement filemanager = new FileManagement();
	protected golDialog dialog = new golDialog();
	Timeline timeline = new Timeline();
	

	@FXML
	protected Canvas img;
	
    @FXML
    private MenuItem mnu_FileOpen,  mnu_FileSave, mnu_SetupGridsize;
	
	@FXML
    private Button btn_Reset, btn_PlayStop, btn_Quit, btn_Next, cmi_adv, btn_GridSize;
	
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
				
		int[] newgridsize = dialog.setGridSizeDialogue().get();
		if (newgridsize != null) {
			System.out.println(newgridsize[0] + "\n" + newgridsize[1]);
		}
		
		
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
    	
    	
    	
    	
    	
//    	Disabling the play button after pressed
    	//btn_PlayStop.setDisable(true);
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
    void btn_ResetPressed(ActionEvent event) {
    
    gc.clearRect(0, 0, img.getWidth(), img.getHeight());
    gameboardcanvas = new GameboardCanvas();
    Grid.draw(gc, img);
    	
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
    	gameboardcanvas.grid.oneGen(gc, img);
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
		Grid.draw(gc, img);
		timeline = new Timeline(new KeyFrame(
    	        Duration.millis(125),
    	        butt -> gameboardcanvas.grid.oneGen(gc, img)));
    	timeline.setCycleCount(Animation.INDEFINITE);
		
    }
    
    protected static EventHandler<MouseEvent> mouseHandlerClicked = new EventHandler <MouseEvent>()	{
		@Override
		public void handle(MouseEvent event) {
			
			System.out.println("Click! "+event.getX()+" "+event.getY());
			int x = (int) (event.getX()/Grid.testCellSize);
			int y = (int) (event.getY()/Grid.testCellSize);
			y = y-2;
			//GameboardCanvas.GridX =  (int)( event.getX()/Grid.testCellSize);
			//GameboardCanvas.GridY =  (int)( event.getY()/Grid.testCellSize);
			if (x >= 0  && y > -2)
				if (x < Grid.gamegrid.length)
					if (y < Grid.gamegrid[0].length)	
			
		Grid.updateGameGrid(x, y, gc);
		
		
		}
    };
    protected static EventHandler<MouseEvent> mouseHandlerDragged = new EventHandler <MouseEvent>()	{
		@Override
		public void handle(MouseEvent event) {
			System.out.println("DRAAAG!! "+event.getX()+" "+event.getY());
		//	GameboardCanvas.GridX =  (int)( event.getX()/Grid.testCellSize);
		//	GameboardCanvas.GridY =  (int)( event.getY()/Grid.testCellSize);
			int x = (int) (event.getX()/Grid.testCellSize);
			int y = (int) (event.getY() /Grid.testCellSize);
			y = y-2;
			if (x >= 0  && y > -2)
			if (x < Grid.gamegrid.length)
				if (y< Grid.gamegrid[0].length)
			Grid.updateGameGrid(x, y, gc);
			
		}
    };
}