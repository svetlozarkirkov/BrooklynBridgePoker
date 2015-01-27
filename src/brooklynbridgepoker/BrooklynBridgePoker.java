
package brooklynbridgepoker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BrooklynBridgePoker extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
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
        
        Image background = new Image("/brooklynbridgepoker/background.jpg");
        ImageView bckg = new ImageView(background);
                       
        StackPane root = new StackPane();
        root.getChildren().add(bckg);
        root.getChildren().add(newGame);
        root.getChildren().add(options);
        root.getChildren().add(exitGame);
        
        
        Scene scene = new Scene(root, 840, 580);
        
        primaryStage.setTitle("Brooklyn Bridge Poker");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNIFIED);
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
