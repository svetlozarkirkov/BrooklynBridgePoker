
package brooklynbridgepoker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BrooklynBridgePoker extends Application {
    
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        
        Button newGame = new Button();
        newGame.setText("New Game");
        newGame.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Brooklyn Brigde Poker");
            }
        });
        
        Button options = new Button();
        options.setText("Options");
        options.setTranslateY(50);
        options.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        options.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Brooklyn Brigde Poker");
            }
        });
        
        Button exitGame = new Button();
        exitGame.setText("Exit");
        exitGame.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        exitGame.setTranslateY(100);
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        Image background = new Image("/brooklynbridgepoker/images/mainmenubackground.jpg");
        ImageView bckg = new ImageView(background);
        bckg.setScaleX(0.50);
        bckg.setScaleY(0.50);
        
        // below to be deleted
        
        Image testCard = new Image("/brooklynbridgepoker/images/cards/13.png");
        ImageView tstCrd = new ImageView(testCard);
        tstCrd.setBlendMode(BlendMode.SRC_ATOP);
        tstCrd.setScaleX(1);
        tstCrd.setScaleY(1);
        tstCrd.setTranslateX(-300);
        tstCrd.setTranslateY(-150);
        
        Image testCard2 = new Image("/brooklynbridgepoker/images/cards/15.png");
        ImageView tstCrd2 = new ImageView(testCard2);
        tstCrd2.setBlendMode(BlendMode.SRC_ATOP);
        tstCrd2.setScaleX(1);
        tstCrd2.setScaleY(1);
        tstCrd2.setTranslateX(-200);
        tstCrd2.setTranslateY(-150);
        
        // above to be deleted
        
        StackPane root = new StackPane();
        root.getChildren().add(bckg);
        root.getChildren().add(tstCrd);
        root.getChildren().add(tstCrd2);
        root.getChildren().add(newGame);
        root.getChildren().add(options);
        root.getChildren().add(exitGame);
        
        
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
