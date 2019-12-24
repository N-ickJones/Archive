package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller {

    // First we deal with the FXML
    // dependency injection

    @FXML
    private MenuItem openMI;

    @FXML
    private MenuItem saveMI;

    @FXML
    private MenuItem saveAsMI;

    @FXML
    private MenuItem quitMI;

    @FXML
    private TextArea myTextArea;

    @FXML
    void openAction(ActionEvent event) {
        handleOpenClick();
    }

    @FXML
    void saveAction(ActionEvent event) {

        // The saveMI is disabled until
        // saveAsAction is invoked at least
        // one time.

        handleSaveClick();
    }

    @FXML
    void saveAsAction(ActionEvent event) {
        handleSaveAsClick();
    }

    @FXML
    void quitAction(ActionEvent event) {

        // If you wanted to be fancy,
        // you could do a check of the
        // current application's data
        // to see if the file has been
        // saved before exiting and
        // losing any unsaved data.

        System.exit(0);
    }

    @FXML
    private void initialize() {}



    ////////////////////////////////////////
    // The following methods and variables
    // might be separated into a utility
    // class in a more flushed out
    // JavaFX application.

    private File dataFile = null;

    private void handleOpenClick() {

        // A fuller JavaFX app would
        // want to perform a check
        // to make sure the current
        // data has been saved.

        //creating JavaFX file chooser
        FileChooser fc = new FileChooser();
        fc.setTitle("Get Text");
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));

        //showing the file chooser
        File phil = fc.showOpenDialog(Main.primaryStage);

        // checking that a file was
        // chosen by the user
        if (phil != null) {

            // opening a scanner
            try (Scanner scan = new Scanner(phil)) {

                // grabbing the file data
                String content = scan.useDelimiter("\\Z").next();
                myTextArea.setText(content);

                // saving the file for
                // use by the saveMI
                dataFile = phil;

                // enabling saveMI
                saveMI.setDisable(false);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleSaveAsClick() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Text");

        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));

        File phil = fc.showSaveDialog(Main.primaryStage);

        if (phil != null) {
            try (PrintStream ps = new PrintStream(phil)) {

                ps.print(myTextArea.getText());

                // saving the file for
                // use by the saveMI
                dataFile = phil;

                // enabling saveMI
                saveMI.setDisable(false);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleSaveClick() {
        try (PrintStream ps = new PrintStream(dataFile)) {

            ps.print(myTextArea.getText());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}