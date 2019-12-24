package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

    //First Call Application
public class Main extends Application {

    //Second call Forces the FX toolkit to start, etc.
    public static void main (String[]args){
        launch(args);
    }

    //Third call init
    public void init() {

    }

    public static Stage primaryStage;

    //Fourth call start
    @Override
    public void start(Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Controller control = new Controller();
        Controller2 control2 = new Controller2();
        control.passReferenceToController(control, control2);

        try {
            loader.setController(control);
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.setTitle("Save and Exit Test");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Platform.setImplicitExit(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                control.exitScreen();
            }
        });

    }

    //Last call stop
    @Override
    public void stop () {
        System.out.println("stop() --> Called From Main");
    }

}
























/*
                try {

                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("sample2.fxml"));
                    Controller2 controller2 = new Controller2();
                    loader2.setController(controller2);
                    loader2.setClassLoader(getClass().getClassLoader());
                    Parent root2 = loader2.load();

                    //loader2.setRoot(this);
                    //loader2.setController(controller2);
                    // The following line is supposed to help Scene Builder, although it doesn't seem to be needed for me.
                    //loader2.load();
                    //loader2.getController();

                    Stage stage = new Stage();
                    Scene scene2 = new Scene(root2, 150, 100);
                    stage.setScene(scene2);
                    stage.setTitle("Exit Window");
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
              */





