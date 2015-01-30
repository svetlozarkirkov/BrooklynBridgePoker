
package brooklynbridgepoker;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BrooklynBridgePoker extends Application {
    

    public static void main(String[] args) {
        launch(args);
    }

    
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        Pane root = new Pane();
        root.setPrefSize(1280, 720);
        
        Image background = new Image("/brooklynbridgepoker/resources/images/table.jpg");
        
        ImageView back = new ImageView(background);
        back.setScaleX(0.8);
        back.setScaleY(0.8);
        back.setFitHeight(720);
        back.setFitWidth(1280);
        
        root.getChildren().addAll(back);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        
    }
    
    private static class MenuButton extends StackPane{
        private Text text;
        
        public MenuButton(String name){
            
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);
            
            Rectangle btnBack = new Rectangle(250,30);
            btnBack.setOpacity(0.6);
            btnBack.setFill(Color.BLACK);
            btnBack.setEffect(new GaussianBlur(3.5));
            
            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(btnBack,text);
            
            setOnMouseEntered(event -> {
                btnBack.setTranslateX(10);
                text.setTranslateX(10);
                btnBack.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });
            
            setOnMouseExited(event -> {
                btnBack.setTranslateX(0);
                text.setTranslateX(0);
                btnBack.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
            
            DropShadow dropSh = new DropShadow(50,Color.WHITE);
            dropSh.setInput(new Glow());
            
            setOnMousePressed(event -> setEffect(dropSh));
            setOnMouseReleased(event -> setEffect(null));
            
        }
    }
}


