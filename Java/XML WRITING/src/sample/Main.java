package sample;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) {

        primaryStage = stage;

        try {
            URL url = getClass().getResource("sample.fxml");
            BorderPane root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("JavaFX Open-Save Example");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}