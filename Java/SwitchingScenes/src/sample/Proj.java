import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Proj extends Application {

    Stage window;
    Scene scene1, scene2;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

         ObservableList<Numbers> data = FXCollections.observableArrayList
                (new Numbers("0", "0", "0", "0", "3", "5", "0", "8", "0"),
                        new Numbers("4", "0", "0", "0", "0", "1", "9", "0", "5"),
                        new Numbers("0", "0", "0", "8", "0", "9", "0", "7", "0"),
                        new Numbers("0", "6", "0", "0", "1", "0", "0", "4", "9"),
                        new Numbers("3", "7", "9", "0", "8", "0", "6", "2", "1"),
                        new Numbers("1", "4", "0", "0", "6", "0", "0", "5", "0"),
                        new Numbers("0", "3", "0", "4", "0", "6", "0", "0", "0"),
                        new Numbers("7", "0", "2", "1", "0", "0", "0", "0", "6"),
                        new Numbers("0", "5", "0", "3", "9", "0", "0", "0", "0"));


         data.





        Label label1 = new Label("Welcome to the first scene!"); // Text
        Button button1 = new Button ("Go to scene 2"); //Creates a physical button
        button1.setOnAction(e -> window.setScene(scene2)); //Adds functionality

        //Layout 1 - childen are laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        //Button 2
        Button button2 = new Button("This is scene 2, to scene 1?");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle("The Title");
        window.show();

    }

    public static class Numbers {

        private final SimpleStringProperty firstNumbers, secondNumbers, thirdNumbers, fourthNumbers,
                fifthNumbers, sixthNumbers, seventhNumbers, eighthNumbers, ninthNumbers;

        public Numbers(String firstNumbers, String secondNumbers, String thirdNumbers, String fourthNumbers,
                       String fifthNumbers, String sixthNumbers, String seventhNumbers, String eighthNumbers,
                       String ninthNumbers) {

            this.firstNumbers = new SimpleStringProperty(firstNumbers);
            this.secondNumbers = new SimpleStringProperty(secondNumbers);
            this.thirdNumbers = new SimpleStringProperty(thirdNumbers);
            this.fourthNumbers = new SimpleStringProperty(fourthNumbers);
            this.fifthNumbers = new SimpleStringProperty(fifthNumbers);
            this.sixthNumbers = new SimpleStringProperty(sixthNumbers);
            this.seventhNumbers = new SimpleStringProperty(seventhNumbers);
            this.eighthNumbers = new SimpleStringProperty(eighthNumbers);
            this.ninthNumbers = new SimpleStringProperty(ninthNumbers);
        }

        public String getFirstNumbers() { return firstNumbers.get(); }
        public String getSecondNumbers() { return secondNumbers.get(); }
        public String getThirdNumbers() { return thirdNumbers.get(); }
        public String getFourthNumbers() { return fourthNumbers.get(); }
        public String getFifthNumbers() { return fifthNumbers.get();}
        public String getSixthNumbers() { return sixthNumbers.get();}
        public String getSeventhNumbers() { return seventhNumbers.get();}
        public String getEighthNumbers() { return eighthNumbers.get();}
        public String getNinthNumbers() { return ninthNumbers.get();}

        public StringProperty firstNumbersProperty() {
            return this.firstNumbers;
        }
        public StringProperty secondNumbersProperty() {
            return this.secondNumbers;
        }
        public StringProperty thirdNumbersProperty() {
            return this.thirdNumbers;
        }
        public StringProperty fourthNumbersProperty() {
            return this.fourthNumbers;
        }
        public StringProperty fifthNumbersProperty() {
            return this.fifthNumbers;
        }
        public StringProperty sixthNumbersProperty() {
            return this.sixthNumbers;
        }
        public StringProperty seventhNumbersProperty() {
            return this.seventhNumbers;
        }
        public StringProperty eighthNumbersProperty() {
            return this.eighthNumbers;
        }
        public StringProperty ninthNumbersProperty() {
            return this.ninthNumbers;
        }

        public void setFirstNumbers(String firstNumbers) {
            this.firstNumbers.set(firstNumbers);
        }
        public void setSecondNumbers(String secondNumbers) {
            this.secondNumbers.set(secondNumbers);
        }
        public void setThirdNumbers(String thirdNumbers) {
            this.thirdNumbers.set(thirdNumbers);
        }
        public void setFourthNumbers(String fourthNumbers) {
            this.fourthNumbers.set(fourthNumbers);
        }
        public void setFifthNumbers(String fifthNumbers) {
            this.fifthNumbers.set(fifthNumbers);
        }
        public void setSixthNumbers(String sixthNumbers) {
            this.sixthNumbers.set(sixthNumbers);
        }
        public void setSeventhNumbers(String seventhNumbers) {
            this.seventhNumbers.set(seventhNumbers);
        }
        public void setEighthNumbers(String eighthNumbers) {
            this.eighthNumbers.set(eighthNumbers);
        }
        public void setNinthNumbers(String ninthNumbers) {
            this.ninthNumbers.set(ninthNumbers);
        }
    }




}