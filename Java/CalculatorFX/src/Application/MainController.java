package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Label result;
    private long number1 = 0;
    private String operator = "";
    private boolean start = true;
    private Model model = new Model();

    public void processNumbers(ActionEvent event){

        if(start) {
            result.setText(""); //Sets label to blank on original startup
            start = false;      //Line 48
        }
                                                                //.getText() is what holds the value
        String value = ((Button)event.getSource()).getText(); //gets actual Text from button
        result.setText(result.getText() + value);       //Inputs that value to the textbox

    }

    public void processOperators(ActionEvent event){
        String value = ((Button)event.getSource()).getText();

        if(!value.equals("=")) {            //Check for = sign
            if (!operator.isEmpty())        //Check if operator is empty
                return;                     //Return Blank

            //If it is equal sign
            operator = value; //sets this to operator sign
            number1 = Long.parseLong(result.getText()); // this is the same as line 25
            result.setText(""); //clears the label

        } else { //Equal Sign operator
            if(operator.isEmpty())               //check if operator is empty
                return;                         //resets it if no operator
            long number2 = Long.parseLong(result.getText()); //sets last numeric value to number 2
            float output = model.calculate(number1, number2, operator); //Calls Model.java to perform calculations
            result.setText(String.valueOf(output)); //sets the value from model.java
            operator = "";  // clears the operator
            start = true; //On next start will be cleared
        }

    }


}


