package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.Main.primaryStage;

public class Controller implements javafx.fxml.Initializable {

    private Controller control;
    private Controller2 control2;

    @FXML
    Label label;
    @FXML
    TextArea textArea;

    @FXML
    private void save() {
        handleSaveClick();
    }

    @FXML
    private void open() {
        handleOpenClick();
    }


    public void handleSaveClick() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Grade Data");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File file = fc.showSaveDialog(primaryStage);

        //Checks if File Location has been selected
        if (file != null) {

            try (PrintStream ps = new PrintStream(file)) {

                ps.println(textArea.getText());
                System.out.println("handleOpenClick() --> \"" + textArea.getText() + "\" was saved in txt file.");

                /*
                Example of more content

                ps.println(textArea2.getText());
                ps.println(textArea3.getText());

                */


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void handleOpenClick() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Select a File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        //Pops up File Chooser
        File file = fc.showOpenDialog(primaryStage);

        //Checks if file has been Open
        if (file != null) {

            //Starts Scanner
            try (Scanner scan = new Scanner(file)) {

                String value = scan.useDelimiter("\\Z").nextLine();

                textArea.setText(value);
                System.out.println("handleOpenClick() --> \"" + value + "\" was opened from txt file.");

                /*
                Example of more content

                textArea2.setText(scan.useDelimiter("\\Z").nextLine());
                textArea3.setText(scan.useDelimiter("\\Z").nextLine());

                */

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void exitScreen() {

        try {

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("sample2.fxml"));
            loader2.setController(control2);
            Parent root2 = loader2.load();
            Stage stage = new Stage();
            Scene scene2 = new Scene(root2, 150, 100);
            stage.setScene(scene2);
            stage.setTitle("Exit Window");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void passReferenceToController(Controller control, Controller2 control2){
        this.control = control;
        this.control2 = control2;
        control2.passReferenceToController(control, control2);
    }
}

