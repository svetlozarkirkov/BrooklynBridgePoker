
package brooklynbridgepoker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class BrooklynBridgePoker extends Application {
    
    public static ArrayList<PlayCard> defaultDeck() {   // this is the default deck with all cards with type PlayCard
		String[] faces = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		char[] suits = {'C','D','H','S'};
		int[] ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		ArrayList<PlayCard> deck = new ArrayList<PlayCard>();   // the list to hold the cards
		for (int i = 0; i < faces.length;i++){
			for (int j = 0; j < suits.length; j++){
				PlayCard card = new PlayCard();
				card.setRank(ranks[i]);
				card.setSuit(suits[j]);
				card.setFace(faces[i]);
                                card.setImgPath(faces[i]+suits[j]);
                                card.setFlippedCard();
				deck.add(card);
			}
		}
		for (int x = 0; x < 100; x++){
			Collections.shuffle(deck);  // shuffling the deck 100 times :D
		}
		return deck;
	}
    public static ArrayList<String> cpuNamesList() throws FileNotFoundException, IOException{     // random generation of cpu names
        ArrayList<String> names = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader("src/brooklynbridgepoker/resources/cpunames.txt"));
        String line = reader.readLine();
        while (line != null) {
          names.add(line);
          line = reader.readLine();
        }
        return names;
    }
    
    
    @Override
    public void start(Stage primaryStage) throws InterruptedException, IOException {
        
        DropShadow dropShadow = new DropShadow();   // dropshadow that is below the cards
        dropShadow.setOffsetX(9);
        dropShadow.setOffsetY(9);
        dropShadow.setColor(Color.rgb(50, 50, 50, 0.7));
        
        MotionBlur mb = new MotionBlur();   // motion blur for the background image
        mb.setRadius(25.0f);
        mb.setAngle(45.0f);
        
        Button newGame = new Button();  // button for New game
        newGame.setText("New Game");
        newGame.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        newGame.setEffect(dropShadow);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {     // what new game button does - right now nothing :D

            }
        });
        
        Button options = new Button();  // button options
        options.setText("Options");
        options.setTranslateY(50);
        options.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        options.setEffect(dropShadow);
        options.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) { // what options does - right now nothing
                
            }
        });
        
        Button exitGame = new Button();     //exit game button
        exitGame.setText("Exit");
        exitGame.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        exitGame.setTranslateY(100);
        exitGame.setEffect(dropShadow);
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {     // exits the program brutally :D //zy: no more :D
            	int option = 0;
				ChooseStage chooseStage = new ChooseStage(new String[] {"Yes", "No"}, 
						"End of Brooklyn Bridge Poker?", "Close Brooklyn Bridge Poker now?", 
						350, 100);
				chooseStage.showAndWait();
				option = chooseStage.getOption();
				
				if (option == 1) {
					System.exit(-1);
				}
            }
        });
        
        Image background = new Image("/brooklynbridgepoker/images/mainmenubackground.jpg");     // main menu background
        ImageView bckg = new ImageView(background);
        bckg.setScaleX(0.7);   // smaller horizontally
        bckg.setScaleY(0.7);   // smaller vertically
        bckg.setOpacity(80);    // 
        bckg.setEffect(mb);     // using the motion blur
 
        StackPane root = new StackPane();
        root.getChildren().add(bckg);   // adding the buttons and background to the root
        root.getChildren().add(newGame);
        root.getChildren().add(options);
        root.getChildren().add(exitGame);
        
        // below is for testing, will be deleted
        
        ArrayList<PlayCard> currentDeck = defaultDeck();    // creating new deck from the default one
        Random rand = new Random();     // random
        int randomIndex = rand.nextInt(currentDeck.size()); // random index from the current deck size
        int x = -270;   // positions of x and y of the first card
        int y = -170;
        
        for (int i = 0; i < 5; i++){
            Image testCard = new Image(currentDeck.get(randomIndex).getImgPath());  // the image of the random card
            ImageView tstCrd = new ImageView(testCard);     
            tstCrd.setBlendMode(BlendMode.SRC_ATOP);
            tstCrd.setScaleX(1);    // how big horizontally
            tstCrd.setScaleY(1);    // how big vertically
            tstCrd.setTranslateX(x);    // move into x position
            tstCrd.setTranslateY(y);    // move into y position
            tstCrd.setEffect(dropShadow);   // uses the dropshadow effect

            currentDeck.remove(randomIndex);    // removes the card from the current deck
            randomIndex = rand.nextInt(currentDeck.size()); // new random index from the cards left
            
            x+=100; // new position to the right of the old card
            
            root.getChildren().add(tstCrd); // adding the card to the root
            
            Path path = new Path();     // animation stuff :D
            path.getElements().add(new MoveTo(-800,-150));
            path.getElements().add(new CubicCurveTo(x-100, y, x-85, y, x, y));
            path.getElements().add(new CubicCurveTo(x,y+30,x,y+50,x,y));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(tstCrd);
            pathTransition.setOrientation(PathTransition.OrientationType.NONE);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(false);
            pathTransition.play();
        }
        ArrayList<String> getCPUnames = cpuNamesList();
        
        // above is for testing, to be deleted
       
        Screen screen = Screen.getPrimary();        // starting with maximized window
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setFullScreen(true);
        
        primaryStage.setTitle("Brooklyn Bridge Poker"); // title of the window
           Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);   // the window cannot be resized
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
