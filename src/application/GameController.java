package application;

import java.awt.Label;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
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
				
		int[] o = model.setGridSizeDialogue().get();
//		gameboardcanvas.grid.setGrid(o[0], o[1]);
		gameboardcanvas.grid.setCellstatus(o[0], o[1], 1);
		//The lines below are for testing, to be removed
		txtArea.setText(o[0] + "\n" + o[1]);
		txtArea.appendText("\n"+gameboardcanvas.grid.toString());
		//The lines above, are for testing, to be removed
			
    }
	// Code for about dialog
	@FXML
	void mnu_AboutDialogPressed(ActionEvent event) {
		Alert about = new Alert(AlertType.INFORMATION);
		about.setTitle("About");
		about.setHeaderText(null);
		about.setContentText("John Conway's Game Of Life"+"\n\n"+"The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970"+"\n"+"The 'game' is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves or, for advanced players, by creating patterns with particular properties."+"\n\n"+"Rules"+"\n"+"1. Any live cell with fewer than two live neighbours dies, as if caused by under-population."+"\n"+"2. Any live cell with two or three live neighbours lives on to the next generation."+"\n"+"3. Any live cell with more than three live neighbours dies, as if by over-population."+"\n"+"4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."+"\n\n\n\n\n\n\n\n\n"+"Test");
		about.setResizable(true);
		about.initStyle(StageStyle.UNDECORATED);
		
		ButtonType buttonTypeCancel = new ButtonType("I understand", ButtonData.CANCEL_CLOSE);
		about.getButtonTypes().setAll(buttonTypeCancel);
		
		about.showAndWait();
	}
//	Test txt area lars
	/*
	Exception ex = new FileNotFoundException("Could not find file blabla.txt");
	
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	ex.printStackTrace(pw);
	String exceptionText = sw.toString();
	
	Label label = new Label("Test TXT Area");
	*/
// Advanced Menu
	@FXML
	void rbn_AdvancedMenuPressed(ActionEvent event){
		Alert advanced = new Alert(AlertType.INFORMATION);
		advanced.setTitle("Advanced");
		advanced.setHeaderText(null);
		advanced.setContentText("Advanced Menu is now enabled! \n\nGood luck!");
		advanced.setResizable(true);
		
		ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		advanced.getButtonTypes().setAll(buttonTypeCancel);
		
		advanced.showAndWait();
	}
	
	
	@FXML
	void mnu_StatsMenuPressed(ActionEvent event){
		Alert stats = new Alert(AlertType.INFORMATION);
		stats.setTitle("Stats");
		stats.setHeaderText(null);
		stats.setResizable(false);
		stats.setContentText("Here will stats about the game be");
		stats.initStyle(StageStyle.UNDECORATED);
		
		ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		stats.getButtonTypes().setAll(buttonTypeCancel);
		
		stats.showAndWait();
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
    	//Get the pattern
    	String pattern = filemanager.getPattern(filemanager.parseFile(filemanager.openFile()));
    	//Show the pattern
    	txtArea.setText(pattern + "\n");
    	//Draw the pattern
    	txtArea.appendText(gameboardcanvas.drawTWO(gc, pattern));
    	
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