
package brooklynbridgepoker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BrooklynBridgePoker extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button newGame = new Button();
        newGame.setText("New Game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Brooklyn Brigde Poker");
            }
        });
        
        Button options = new Button();
        options.setText("Options");
        options.setTranslateY(40);
        options.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Brooklyn Brigde Poker");
            }
        });
        
        Button exitGame = new Button();
        exitGame.setText("Exit");
        exitGame.setTranslateY(80);
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(newGame);
        root.getChildren().add(options);
        root.getChildren().add(exitGame);
        
        Scene scene = new Scene(root, 840, 480);
        
        primaryStage.setTitle("Brooklyn Bridge Poker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
