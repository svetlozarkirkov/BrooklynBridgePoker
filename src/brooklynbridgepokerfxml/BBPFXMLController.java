
package brooklynbridgepokerfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


public class BBPFXMLController implements Initializable {
        
    @FXML
    private Button exitGameBtn;
    private Button startGameBtn;
    private ComboBox diff;
 
    @FXML
    private void handleExitBtnAction(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleStartBtnAction(ActionEvent event) {
        System.out.println("Start game");
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        
    }
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
    }    
    
}
