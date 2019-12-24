package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements javafx.fxml.Initializable {

    //Will auto call a no arg constructor

    private Controller control;
    private Controller2 control2;
/*
    //First Call
    public Controller2() {}
*/
    //Calls any @FXML

    @FXML
    private javafx.scene.control.Button button;

    @FXML
    private void handleSave() {
        System.out.println("handleSave() --> Called from Controller 2");

        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {

            //TODO - Save File and Exit - CURRENT: RUNTIME ERROR!

            control.handleSaveClick();

            //controller.test();

            //control = getControl();
            //control.handleSaveClick();


        } catch (NullPointerException e) {
            System.out.println("handleSave() --> Unable to save! NullPointerException");
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*Disabled for Testing

        Platform.exit();

         */
    }

    @FXML
    private void handleCancel() {
        System.out.println("handleCancel() --> Called from Controller 2");
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleExit() {
        System.out.println("handleExit() --> Called from Controller 2");
        Platform.exit();
    }

    //Will register any event handlers

    //Last Call
    public void initialize(URL location, ResourceBundle resources) {}

    public void passReferenceToController(Controller control, Controller2 control2){
        this.control = control;
        this.control2 = control2;
    }

    private void info() {
/*
When the load() method is called on the FXMLLoader, it:

Loads the FXML file

Creates an instance of the controller class specified by the fx:controller attribute, by calling its no-argument constructor

Sets the value of any @FXML-annotated fields in the controller to the elements defined with matching fx:id attributes

Registers any event handlers mapping to methods in the controller

Calls the initialize() method on the controller, if there is one.

Notice the order of those events: the constructor is called before the @FXML-annotated fields are injected, but the initialize() method is called after.
This means you can access (and configure) and @FXML-annotated fields in the initialize() method,
but not in the constructor. It is quite common (at least in simple applications)
not to define any constructor in the controller classes and just to use the default.

You can have as many FXML/controller pairs in your application as you need/want. Each FXML file should have its own controller class.

You can load an FXML file as many times as you need if you want multiple instances of the UI
it defines: each time the FXMLLoader will create a new controller instance for you that is associated with the UI element you loaded.

When you start a FX application (which I'll describe below), the FX toolkit is started. Then an instance of your Application subclass is created, and its init() method is called (if you don't define one, the default implementation does nothing).
The FX Application Thread is then started and the Application subclass instance's start() method is called on that thread.



 */
}

}