package brooklynbridgepoker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
@author Copyright 2014 Piotr Dzwiniel
 
Sample of use:
 
int option = 0;
String[] buttons = {"Yes", "No"};
ChooseStage window = new ChooseStage(buttons, "Question",
"Are you sure you want to close The Application?", 350, 100);
window.showAndWait();
option = window.getOption();
if (option == 1) {
	System.exit(-1);
}
*/

public class ChooseStage extends Stage {

	private String[] stageButtons;
	private String stageTitle;
	private String contentMessage;
	private int stageWidth;
	private int stageHeight;
	
	private int option = 0;
	
	public ChooseStage(String[] buttons, String t, String m, int w, int h) {
		
		super();
		
		this.stageButtons = buttons;
		this.stageTitle = t;
		this.contentMessage = m;
		this.stageWidth = w;
		this.stageHeight = h;
		
		String styleSheet = ChooseStage.class.getResource("ChooseStage.css").toExternalForm();
		
		/* layout */
		BorderPane layout = new BorderPane();
		
		/* layout -> center -> message box */
		VBox contentBox = new VBox();
		TextArea textArea = new TextArea(contentMessage);
		textArea.setWrapText(true);
		textArea.setEditable(false);
		textArea.setId("textArea");
		textArea.getStylesheets().add(styleSheet);
		
		contentBox.getChildren().add(textArea);
		contentBox.setAlignment(Pos.CENTER);
		
		/* layout -> bottom -> buttons box */
		HBox buttonsBox = new HBox(20);
		
		for (int i = 0; i < stageButtons.length; i++) {
			int b = i + 1;
			Button button = new Button(" " + stageButtons[i] + " ");
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					option = b;
					close();
				}
			});
			buttonsBox.getChildren().add(button);
		}
		
		buttonsBox.setAlignment(Pos.CENTER);
		
		layout.setCenter(contentBox);
		layout.setBottom(buttonsBox);
		BorderPane.setMargin(contentBox, new Insets(20, 10, 10, 10));
		BorderPane.setMargin(buttonsBox, new Insets(10, 10, 20, 10));
		
		Scene scene = new Scene(layout, stageWidth, stageHeight);
		
		this.setScene(scene);
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle(stageTitle);
		this.centerOnScreen();
		this.setResizable(false);
	}

	public String[] getStageButtons() {
		return stageButtons;
	}

	public void setStageButtons(String[] stageButtons) {
		this.stageButtons = stageButtons;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public void setStageTitle(String stageTitle) {
		this.stageTitle = stageTitle;
	}

	public String getContentMessage() {
		return contentMessage;
	}

	public void setContentMessage(String contentMessage) {
		this.contentMessage = contentMessage;
	}

	public int getStageWidth() {
		return stageWidth;
	}

	public void setStageWidth(int stageWidth) {
		this.stageWidth = stageWidth;
	}

	public int getStageHeight() {
		return stageHeight;
	}

	public void setStageHeight(int stageHeight) {
		this.stageHeight = stageHeight;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}
}