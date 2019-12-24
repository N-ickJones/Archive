import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.text.html.ImageView;
import java.awt.*;


public class Main extends Application {

    private TableView<Numbers> table = new TableView<>();

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


    public static void main(String[] args) { launch(args); }

//-----------Column Initialization--------------------------------------------------------------------------------------

    @Override
    public void start(Stage stage) {

        //An empty label
        Label label = new Label("Sudoku Puzzle Game");

        Label label2 = new Label("Search");

       // Image image = new Image(getClass().getResourceAsStream("label.jpg"));
       // Label label3 = new Label("Search", new ImageView(image));

        Callback<TableColumn<Numbers, String>, TableCell<Numbers, String>> cellFactory
                = (TableColumn<Numbers, String> param) -> new EditingCell();

        TableColumn<Numbers, String> firstNumbersCol = new TableColumn("");
        firstNumbersCol.setSortable(false);
        firstNumbersCol.getStyleClass().add("column1");
        firstNumbersCol.setMinWidth(50);
        firstNumbersCol.setMaxWidth(50);
        firstNumbersCol.setCellValueFactory(cellData -> cellData.getValue().firstNumbersProperty());
        firstNumbersCol.setCellFactory(cellFactory);
        firstNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setFirstNumbers(t.getNewValue());
                });

        TableColumn<Numbers, String> secondNumbersCol = new TableColumn("");
        secondNumbersCol.setSortable(false);
        secondNumbersCol.getStyleClass().add("column2");
        secondNumbersCol.setMinWidth(50);
        secondNumbersCol.setMaxWidth(50);
        secondNumbersCol.setCellValueFactory(cellData -> cellData.getValue().secondNumbersProperty());
        secondNumbersCol.setCellFactory(cellFactory);
        secondNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setSecondNumbers(t.getNewValue());
                });

        TableColumn<Numbers, String> thirdNumbersCol = new TableColumn("");
        thirdNumbersCol.setSortable(false);
        thirdNumbersCol.getStyleClass().add("column3");
        thirdNumbersCol.setMinWidth(50);
        thirdNumbersCol.setMaxWidth(50);
        thirdNumbersCol.setCellValueFactory(cellData -> cellData.getValue().thirdNumbersProperty());
        thirdNumbersCol.setCellFactory(cellFactory);
        thirdNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setThirdNumbers(t.getNewValue());
                });

        TableColumn<Numbers, String> fourthNumbersCol = new TableColumn("");
        fourthNumbersCol.setSortable(false);
        fourthNumbersCol.getStyleClass().add("column4");
        fourthNumbersCol.setMinWidth(50);
        fourthNumbersCol.setMaxWidth(50);
        fourthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().fourthNumbersProperty());
        fourthNumbersCol.setCellFactory(cellFactory);
        fourthNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setFourthNumbers(t.getNewValue());
                });

        TableColumn<Numbers, String> fifthNumbersCol = new TableColumn("");
        fifthNumbersCol.setSortable(false);
        fifthNumbersCol.getStyleClass().add("column5");
        fifthNumbersCol.setMinWidth(50);
        fifthNumbersCol.setMaxWidth(50);
        fifthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().fifthNumbersProperty());
        fifthNumbersCol.setCellFactory(cellFactory);
        fifthNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setFifthNumbers(t.getNewValue());
                });

        TableColumn<Numbers, String> sixthNumbersCol = new TableColumn("");
        sixthNumbersCol.setSortable(false);
        sixthNumbersCol.getStyleClass().add("column6");
        sixthNumbersCol.setMinWidth(50);
        sixthNumbersCol.setMaxWidth(50);
        sixthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().sixthNumbersProperty());
        sixthNumbersCol.setCellFactory(cellFactory);
        sixthNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setSixthNumbers(t.getNewValue());
                });

        TableColumn<Numbers, String> seventhNumbersCol = new TableColumn("");
        seventhNumbersCol.setSortable(false);
        seventhNumbersCol.getStyleClass().add("column7");
        seventhNumbersCol.setMinWidth(50);
        seventhNumbersCol.setMaxWidth(50);
        seventhNumbersCol.setCellValueFactory(cellData -> cellData.getValue().seventhNumbersProperty());
        seventhNumbersCol.setCellFactory(cellFactory);
        seventhNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setSeventhNumbers(t.getNewValue());
                });

        TableColumn<Numbers, String> eighthNumbersCol = new TableColumn("");
        eighthNumbersCol.setSortable(false);
        eighthNumbersCol.getStyleClass().add("column8");
        eighthNumbersCol.setMinWidth(50);
        eighthNumbersCol.setMaxWidth(50);
        eighthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().eighthNumbersProperty());
        eighthNumbersCol.setCellFactory(cellFactory);
        eighthNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setEighthNumbers(t.getNewValue());
                });

        TableColumn<Numbers, String> ninthNumbersCol = new TableColumn("");
        ninthNumbersCol.setSortable(false);
        ninthNumbersCol.getStyleClass().add("column9");
        ninthNumbersCol.setMinWidth(50);
        ninthNumbersCol.setMaxWidth(50);
        ninthNumbersCol.setCellValueFactory(cellData -> cellData.getValue().ninthNumbersProperty());
        ninthNumbersCol.setCellFactory(cellFactory);
        ninthNumbersCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Numbers, String> t) -> {
                    ((Numbers) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setNinthNumbers(t.getNewValue());
                });
//---------------------------------------------------------------------Stage, Scene, Vbox-------------------------------

        Button solveButton = new Button("Solve Puzzle");
        //solveButton.setOnAction(e -> solvePuzzle());


        final VBox vbox = new VBox();
        vbox.getStyleClass().add("vbox");
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(100));
        vbox.getChildren().addAll(label, solveButton);
        table.setEditable(true);
        table.setItems(data);
        table.getColumns().addAll(firstNumbersCol, secondNumbersCol, thirdNumbersCol, fourthNumbersCol,
                fifthNumbersCol, sixthNumbersCol, seventhNumbersCol, eighthNumbersCol, ninthNumbersCol);

        table.setMaxSize(462, 463);
        table.setMinSize(462, 463);
        table.getStyleClass().add("table");


        BorderPane borderPane = new BorderPane();
       // borderPane.setMaxSize(1366, 768);
        //borderPane.setTop();
        borderPane.setRight(vbox);
        //borderPane.setBottom();
        //borderPane.setLeft();
        borderPane.setCenter(table);


        Scene scene = new Scene(new Group());
        scene.getStylesheets().add("style.css");
        ((Group) scene.getRoot()).getChildren().addAll(borderPane);
        stage.setWidth(1366);
        stage.setHeight(768);
        stage.setTitle("Game Central Paradise");
        stage.setScene(scene);
        stage.show();


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

//----------------------------------------------------------------------------------------------------------------------
    public class Numbers {

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