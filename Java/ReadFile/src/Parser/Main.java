package Parser;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("Screen1.fxml").openStream());
        Controller controller = loader.getController();
        //controller.passReferenceToController(controller);
        primaryStage.setTitle("Sam Houston State University - Log Parser");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
        Platform.setImplicitExit(true); // determines whether stop() method is called
       // primaryStage.setOnCloseRequest(event -> {
           // event.consume();
            //controller.exitScreen();});
    }
    @Override
    public void stop(){
    }
    public static void main(String[] args) {
        launch(args);
    }
}
