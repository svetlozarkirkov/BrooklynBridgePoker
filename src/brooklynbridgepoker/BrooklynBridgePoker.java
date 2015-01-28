
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class BrooklynBridgePoker extends Application {
    
    public static ArrayList<PlayCard> defaultDeck() {
		String[] faces = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		char[] suits = {'C','D','H','S'};
		int[] ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		ArrayList<PlayCard> deck = new ArrayList<PlayCard>();
		for (int i = 0; i < faces.length;i++){
			for (int j = 0; j < suits.length; j++){
				PlayCard card = new PlayCard();
				card.setRank(ranks[i]);
				card.setSuit(suits[j]);
				card.setFace(faces[i]);
                                card.setImgPath(faces[i]+suits[j]);
				deck.add(card);
			}
		}
		for (int x = 0; x < 100; x++){
			Collections.shuffle(deck);
		}
		return deck;
	}
    
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(9);
        dropShadow.setOffsetY(9);
        dropShadow.setColor(Color.rgb(50, 50, 50, 0.7));
        
        MotionBlur mb = new MotionBlur();
        mb.setRadius(25.0f);
        mb.setAngle(45.0f);
        
        Button newGame = new Button();
        newGame.setText("New Game");
        newGame.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        newGame.setEffect(dropShadow);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {

            }
        });
        
        Button options = new Button();
        options.setText("Options");
        options.setTranslateY(50);
        options.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        options.setEffect(dropShadow);
        options.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        Button exitGame = new Button();
        exitGame.setText("Exit");
        exitGame.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        exitGame.setTranslateY(100);
        exitGame.setEffect(dropShadow);
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        Image background = new Image("/brooklynbridgepoker/images/mainmenubackground.jpg");
        ImageView bckg = new ImageView(background);
        bckg.setScaleX(0.45);
        bckg.setScaleY(0.45);
        bckg.setOpacity(80);
        bckg.setEffect(mb);
 
        StackPane root = new StackPane();
        root.getChildren().add(bckg);
        root.getChildren().add(newGame);
        root.getChildren().add(options);
        root.getChildren().add(exitGame);
        
        ArrayList<PlayCard> currentDeck = defaultDeck();
        Random rand = new Random();
        int randomIndex = rand.nextInt(currentDeck.size());
        int x = -270;
        int y = -170;
        
        for (int i = 0; i < 5; i++){
            Image testCard = new Image(currentDeck.get(randomIndex).getImgPath());
            ImageView tstCrd = new ImageView(testCard);
            tstCrd.setBlendMode(BlendMode.SRC_ATOP);
            tstCrd.setScaleX(1);
            tstCrd.setScaleY(1);
            tstCrd.setTranslateX(x);
            tstCrd.setTranslateY(y);
            tstCrd.setEffect(dropShadow);

            currentDeck.remove(randomIndex);
            randomIndex = rand.nextInt(currentDeck.size());
            
            x+=100;
            
            root.getChildren().add(tstCrd);
            
            Path path = new Path();
            path.getElements().add(new MoveTo(-800,-150));
            path.getElements().add(new CubicCurveTo(x-100, y, x-85, y, x, y));
            
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(1800));
            pathTransition.setPath(path);
            pathTransition.setNode(tstCrd);
            pathTransition.setOrientation(PathTransition.OrientationType.NONE);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(false);
            pathTransition.play();
        }
        x = -270;
        y = -50;
        
        for (int i = 0; i < 5; i++){
            Image testCard = new Image(currentDeck.get(randomIndex).getImgPath());
            ImageView tstCrd = new ImageView(testCard);
            tstCrd.setBlendMode(BlendMode.SRC_ATOP);
            tstCrd.setScaleX(1);
            tstCrd.setScaleY(1);
            tstCrd.setTranslateX(x);
            tstCrd.setTranslateY(y);
            tstCrd.setEffect(dropShadow);

            currentDeck.remove(randomIndex);
            randomIndex = rand.nextInt(currentDeck.size());
            
            x+=100;
            
            root.getChildren().add(tstCrd);
            
            Path path = new Path();
            path.getElements().add(new MoveTo(-800,-150));
            path.getElements().add(new CubicCurveTo(x-100, y, x-75, y, x, y));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(1800));
            pathTransition.setPath(path);
            pathTransition.setNode(tstCrd);
            pathTransition.setOrientation(PathTransition.OrientationType.NONE);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(false);
            pathTransition.play();
        }
        
        
        Scene scene = new Scene(root, 840, 580);
        
        primaryStage.setTitle("Brooklyn Bridge Poker");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
