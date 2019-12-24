package Tester;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class Controller {
    @FXML
    private TableView<Numbers> table = new TableView<>();
    private final ObservableList<Numbers> data = FXCollections.observableArrayList
            (new Numbers("0", "0", "0", "0", "3", "5", "0", "8", "0"),
                    new Numbers("4", "0", "0", "0", "0", "1", "9", "0", "5"),
                    new Numbers("0", "0", "0", "8", "0", "9", "0", "7", "0"),
                    new Numbers("0", "6", "0", "0", "1", "0", "0", "4", "9"),
                    new Numbers("3", "7", "9", "0", "8", "0", "6", "2", "1"),
                    new Numbers("1", "4", "0", "0", "6", "0", "0", "5", "0"),
                    new Numbers("0", "3", "0", "4", "0", "6", "0", "0", "0"),
                    new Numbers("7", "0", "2", "1", "0", "0", "0", "0", "6"),
                    new Numbers("0", "5", "0", "3", "9", "0", "0", "0", "0"));


    @FXML TableColumn<Numbers, String> firstNumbersCol = new TableColumn("");
    @FXML TableColumn<Numbers, String> secondNumbersCol = new TableColumn("");
    @FXML TableColumn<Numbers, String> thirdNumbersCol = new TableColumn("");
    @FXML TableColumn<Numbers, String> fourthNumbersCol = new TableColumn("");
    @FXML TableColumn<Numbers, String> fifthNumbersCol = new TableColumn("");
    @FXML TableColumn<Numbers, String> sixthNumbersCol = new TableColumn("");
    @FXML TableColumn<Numbers, String> seventhNumbersCol = new TableColumn("");
    @FXML TableColumn<Numbers, String> eighthNumbersCol = new TableColumn("");
    @FXML TableColumn<Numbers, String> ninthNumbersCol = new TableColumn("");



    public void processNumbers (ActionEvent event) {

        firstNumbersCol.setCellValueFactory(cellData -> cellData.getValue().firstNumbersProperty());
        secondNumbersCol.setCellValueFactory(cellData -> cellData.getValue().secondNumbersProperty());
        thirdNumbersCol.setCellValueFactory(cellData -> cellData.getValue().thirdNumbersProperty());
        fourthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().fourthNumbersProperty());
        fifthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().fifthNumbersProperty());
        sixthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().sixthNumbersProperty());
        seventhNumbersCol.setCellValueFactory(cellData -> cellData.getValue().seventhNumbersProperty());
        eighthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().eighthNumbersProperty());
        ninthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().ninthNumbersProperty());

        table.setItems(data);

        //To hide the Column Header
        Pane header = (Pane) table.lookup("TableHeaderRow");
        header.setMaxHeight(0);
        header.setMinHeight(0);
        header.setPrefHeight(0);

        //Gives Css Values to each Row
        int i = 1;
        for (Node n: table.lookupAll("TableRow")) {
            if (n instanceof TableRow) {
                TableRow row = (TableRow) n;
                if (i == 1)
                    row.getStyleClass().add("row1");
                if (i == 2)
                    row.getStyleClass().add("row2");
                if (i == 3)
                    row.getStyleClass().add("row3");
                if (i == 4)
                    row.getStyleClass().add("row4");
                if (i == 5)
                    row.getStyleClass().add("row5");
                if (i == 6)
                    row.getStyleClass().add("row6");
                if (i == 7)
                    row.getStyleClass().add("row7");
                if (i == 8)
                    row.getStyleClass().add("row8");
                if (i == 9)
                    row.getStyleClass().add("row9");
            }
            i++;

        }
        System.out.println(table.lookupAll("TableRow"));
    }





    class EditingCell extends TableCell<Numbers, String> {

        private TextField textField;

        private EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());    //Sets table Cell to original number
            setGraphic(null);               //Gets rid of textfield graphic
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item);              //sets the cell back to original value if cleared
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
//                        setGraphic(null);
                    }
                 setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnAction((e) -> commitEdit(textField.getText()));
            textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    System.out.println("Commiting " + textField.getText());
                    commitEdit(textField.getText());
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
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
    public StringProperty fifthNumbersProperty() { return this.fifthNumbers; }
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

