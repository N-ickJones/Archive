package Application;


import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Controller5NewSaveCheck {
    @FXML
    private javafx.scene.control.Button button;
    @FXML
    private Controller1Main controller1Main;
    @FXML
    private void confirmNew () {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        controller1Main.newAction(true);
    }
    @FXML
    private void confirmBack () {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void passReferenceToController(Controller1Main controller1Main){
        this.controller1Main = controller1Main;
    }
}
