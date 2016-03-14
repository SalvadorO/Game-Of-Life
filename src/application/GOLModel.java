package application;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


public class GOLModel {
	
		
	/**
	 * Method that show a custom inputdialog box to the user.
	 * The dialog takes an x value and a y value.
	 * The values are returned to the calling method.
	 * TODO: consider if method should reside in another class, e.g. GameBoardCanvas
	 * @return String
	 */
	public Optional<int[]> setGridSizeDialogue()	{
		Optional<int[]>returnValue=null;
		
		Dialog<int[]> dialog = new Dialog<>();
		dialog.setTitle("Enter size of grid");
		dialog.setResizable(false);
		Label lbl_x = new Label("X-value:");
		Label lbl_y = new Label("Y-value:");
		TextField txt_x = new TextField();
		TextField txt_y = new TextField();
		
		GridPane grid = new GridPane();
		grid.add(lbl_x, 1, 1);
		grid.add(lbl_y, 2, 1);
		grid.add(txt_x, 1, 2);
		grid.add(txt_y, 2, 2);
		
		dialog.getDialogPane().setContent(grid);
//		Focuses the X-value when you enter the stage
		Platform.runLater(() -> txt_x.requestFocus());
		
		ButtonType buttonTypeOk = new ButtonType ("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType ("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeCancel, buttonTypeOk);
		
		dialog.setResultConverter(new Callback<ButtonType, int[]>() {
			@Override	
			public int[] call(ButtonType b) {
				if (b==buttonTypeOk){
					return new int[]{Integer.parseInt(txt_x.getText()), Integer.parseInt(txt_y.getText())};
				}
				return null;
			}
	    });
		
		Optional<int[]> result = dialog.showAndWait();
		if ( result.isPresent()){
			return result;
		}
		
		return returnValue;
	}			



		
}
	