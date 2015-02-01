
package brooklynbridgepoker;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class BrooklynBridgePoker extends Application {
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException, IOException {
        Menus.printMainMenu();
        Menus.mainMenuSelection();
    }
}


