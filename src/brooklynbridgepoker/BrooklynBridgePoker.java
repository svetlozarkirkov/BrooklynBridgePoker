
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
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
        
        Image background = new Image("/brooklynbridgepoker/resources/images/mainmenubackground.jpg");
        
        ImageView back = new ImageView(background);
        back.setScaleX(0.8);
        back.setScaleY(1.3);
        back.setTranslateX(-300);
        back.setFitHeight(720);
        //back.setFitWidth(1280);
        
        MenuButton newGame = new MenuButton("NEW GAME",1);
        newGame.setTranslateX(400);
        newGame.setTranslateY(248);
        
        MenuButton options = new MenuButton("OPTIONS",2);
        options.setTranslateX(400);
        options.setTranslateY(288);
        
        MenuButton exitGame = new MenuButton("EXIT GAME",3);
        exitGame.setTranslateX(400);
        exitGame.setTranslateY(328);
        
        Board table = new Board();
        table.defaultDeck();
        table.addHumanPlayer("Sve");
        table.addRound();
        
        for (int x = 0; x < 50; x++){
             table.defaultDeck();
        table.addHumanPlayer("Sve");
        table.addRound();
            for(int z = 0; z < 10; z++){
                
            Random rnd = new Random();
        int randomIndex = rnd.nextInt(table.deck.size());
        ArrayList<PlayCard> humanCards = new ArrayList();
            for (int i = 0; i < 5; i++){
                humanCards.add(table.deck.get(randomIndex));
                table.deck.remove(randomIndex);
                randomIndex = rnd.nextInt(table.deck.size());
            }
        table.human.setHumanPlayerCards(humanCards);
        Collections.sort(table.human.getHumanPlayerCurrentCards(), new Comparator<PlayCard>() {    // sort by rank (has to be tested)
                    @Override public int compare(PlayCard cardOne, PlayCard cardTwo) {
                        return cardOne.getRank() - cardTwo.getRank(); // Ascending
                    }
                });
            for (PlayCard card: table.human.getHumanPlayerCurrentCards()){
                System.out.print(card.getFace()+""+card.getSuit()+"  ");
            }
            int result = HandCheck.checkHand(humanCards);
            System.out.println("  unique rank: "+result);
            }
           
        }
        
            
        
        
        
        
        
        
        
        
        root.getChildren().addAll(back);
        root.getChildren().addAll(newGame);
        root.getChildren().addAll(options);
        root.getChildren().addAll(exitGame);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        
    }

    private static class MenuButton extends StackPane{
        private Text text;
        
        public MenuButton(String name, int action){
            
            text = new Text(name);
            text.setFont(text.getFont().font(22));
            text.setFill(Color.WHITE);
            
            Rectangle btnBack = new Rectangle(250,40);
            btnBack.setOpacity(0.6);
            btnBack.setFill(Color.BLACK);
            btnBack.setEffect(new GaussianBlur(3.5));
            
            setAlignment(Pos.CENTER);
            //setRotate(-0.5);
            getChildren().addAll(btnBack,text);
            
            setOnMouseEntered(event -> {
                btnBack.setTranslateX(10);
                //text.setTranslateX(10);
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
            
            if (action == 1){
                //setOnMousePressed(event -> setEffect(dropSh));
                //setOnMouseReleased(event -> setEffect(null));
            }
            else if (action == 3){
                setOnMousePressed(event -> System.exit(0));
            }
        }
    }
}


