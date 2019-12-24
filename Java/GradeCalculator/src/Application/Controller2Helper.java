package Application;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class Controller2Helper implements Initializable {
    @FXML
    private Label label;
    @FXML
    private ImageView imageView = new ImageView();
    @FXML
    private Button backButton, nextButton;
    // 350 width x 400 height
    private Image step1 = new Image("Step1.png");
    // 293 Width x 374 Height --Extra-- 57 width x 26 height  -- 28.5 Width x 13 Height
    private Image step2 = new Image("Step2.png");
    // 328 Width x 153 Height --Extra-- 22 width x 247 height -- 11 Width x 123.5 Height
    private Image step3 = new Image("Step3.png");
    // 343 Width x 104 Height --Extra--  7 width x 296 height --  3.5 Width x 148 Height
    private int tracker = 1;
    @FXML
    private void hideHelper(){
        label.getScene().getWindow().hide();
    }
    @FXML
    private void setBackButton() {
        if(tracker == 1) {
            tracker = 1;
            imageView.setImage(step1);
            imageView.setX(17);
            imageView.setY(0);
            label.setText("Login with Sam ID and Password");

        }
        if(tracker == 2) {
            tracker = 1;
            imageView.setImage(step1);
            imageView.setX(17);
            imageView.setY(0);
            label.setText("Login with Sam ID and Password");

        }
        if(tracker == 3) {
            tracker = 2;
            imageView.setImage(step2);
            imageView.setX(0);
            imageView.setY(123.5);
            label.setText("Choose Default's and Click Submit");
        }
    }
    @FXML
    private void setNextButton() {
        if(tracker == 3) {
            tracker = 3;
            imageView.setImage(step3);
            imageView.setX(0);
            imageView.setY(148);
            label.setText("Input These Into the Grade Calculator");
        }
        if(tracker == 2) {
            tracker = 3;
            imageView.setImage(step3);
            imageView.setX(0);
            imageView.setY(148);
            label.setText("Scroll To Bottom of Page and Find Transcript Total's");
        }
        if(tracker == 1) {
            tracker = 2;
            imageView.setImage(step2);
            imageView.setX(0);
            imageView.setY(123.5);
            label.setText("Choose Default's and Click Submit");
        }

    }
    @FXML
    private void setHyperLink() {

        if(Desktop.isDesktopSupported())
        {
            try {
                Desktop.getDesktop().browse(new URI("https://bansso.shsu.edu/ssomanager/c/SSB?pkg=bwskotrn.P_ViewTermTran"));
            } catch (IOException  | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        backButton.setDisable(false);
        nextButton.setDisable(false);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setDisable(true);
        nextButton.setDisable(true);

        imageView.setImage(step1);
        imageView.setX(17);
        imageView.setY(0);
        label.setText("Login with Sam ID and Password");
    }
}














