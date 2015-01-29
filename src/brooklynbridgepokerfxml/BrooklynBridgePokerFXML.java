
package brooklynbridgepokerfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class BrooklynBridgePokerFXML extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BBPFXML.fxml"));
        
        Image background = new Image("/brooklynbridgepokerfxml/images/table.jpg");     // main menu background
        ImageView bckg = new ImageView(background);
        bckg.setScaleX(0.7);   // smaller horizontally
        bckg.setScaleY(0.7);   // smaller vertically
        bckg.setOpacity(90);    // 
        
        
        
        Screen screen = Screen.getPrimary();
        stage.setMaximized(true);
        stage.setFullScreen(true);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.setScene(scene);
        stage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
