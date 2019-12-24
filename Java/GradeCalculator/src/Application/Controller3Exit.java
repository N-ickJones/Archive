package Application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Controller3Exit {
    @FXML
    private javafx.scene.control.Button button;
    @FXML
    private Controller1Main controller1Main;

    @FXML
    private void confirmSave () {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        if(controller1Main.getSaveStatus())
            controller1Main.saveAction();
        else
            controller1Main.saveAsAction();
        Platform.exit();
    }
    @FXML
    private void confirmCancel () {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void confirmExit () {
        Platform.exit();
    }
    @FXML
    public void passReferenceToController(Controller1Main controller1Main){
        this.controller1Main = controller1Main;
    }
}
