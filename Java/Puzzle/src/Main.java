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
            (new Numbers("0", "0", "0", "0", "3", "5", "0", "8", "0",
                    "4", "0", "0", "0", "0", "1", "9", "0", "5",
                    "0", "0", "0", "8", "0", "9", "0", "7", "0",
                    "0", "6", "0", "0", "1", "0", "0", "4", "9",
                    "3", "7", "9", "0", "8", "0", "6", "2", "1",
                    "1", "4", "0", "0", "6", "0", "0", "5", "0",
                    "0", "3", "0", "4", "0", "6", "0", "0", "0",
                    "7", "0", "2", "1", "0", "0", "0", "0", "6",
                    "0", "5", "0", "3", "9", "0", "0", "0", "0"));


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
        firstNumbersCol.setCellValueFactory(cellData -> cellData.getValue(). firstNumbersProperty()
        );


        //firstNumbersCol.setCellFactory(cellFactory);
       // firstNumbersCol.setOnEditCommit(
       //         (TableColumn.CellEditEvent<Numbers, String> t) -> {
       //             ((Numbers) t.getTableView().getItems()
        //                    .get(t.getTablePosition().getRow()))
       //                     .setFirstNumbers(t.getNewValue());
       //         });


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
                fifthNumbers, sixthNumbers, seventhNumbers, eighthNumbers, ninthNumbers,
                firstNumbers2, secondNumbers2, thirdNumbers2, fourthNumbers2,
                fifthNumbers2, sixthNumbers2, seventhNumbers2, eighthNumbers2, ninthNumbers2,
                firstNumbers3, secondNumbers3, thirdNumbers3, fourthNumbers3,
                fifthNumbers3, sixthNumbers3, seventhNumbers3, eighthNumbers3, ninthNumbers3,
                firstNumbers4, secondNumbers4, thirdNumbers4, fourthNumbers4,
                fifthNumbers4, sixthNumbers4, seventhNumbers4, eighthNumbers4, ninthNumbers4,
                firstNumbers5, secondNumbers5, thirdNumbers5, fourthNumbers5,
                fifthNumbers5, sixthNumbers5, seventhNumbers5, eighthNumbers5, ninthNumbers5,
                firstNumbers6, secondNumbers6, thirdNumbers6, fourthNumbers6,
                fifthNumbers6, sixthNumbers6, seventhNumbers6, eighthNumbers6, ninthNumbers6,
                firstNumbers7, secondNumbers7, thirdNumbers7, fourthNumbers7,
                fifthNumbers7, sixthNumbers7, seventhNumbers7, eighthNumbers7, ninthNumbers7,
                firstNumbers8, secondNumbers8, thirdNumbers8, fourthNumbers8,
                fifthNumbers8, sixthNumbers8, seventhNumbers8, eighthNumbers8, ninthNumbers8,
                firstNumbers9, secondNumbers9, thirdNumbers9, fourthNumbers9,
                fifthNumbers9, sixthNumbers9, seventhNumbers9, eighthNumbers9, ninthNumbers9;

        public Numbers(String firstNumbers, String secondNumbers, String thirdNumbers, String fourthNumbers,
                       String fifthNumbers, String sixthNumbers, String seventhNumbers, String eighthNumbers,
                       String ninthNumbers,
                       String firstNumbers2, String secondNumbers2, String thirdNumbers2, String fourthNumbers2,
                       String fifthNumbers2, String sixthNumbers2, String seventhNumbers2, String eighthNumbers2,
                       String ninthNumbers2,
                       String firstNumbers3, String secondNumbers3, String thirdNumbers3, String fourthNumbers3,
                       String fifthNumbers3, String sixthNumbers3, String seventhNumbers3, String eighthNumbers3,
                       String ninthNumbers3,
                       String firstNumbers4, String secondNumbers4, String thirdNumbers4, String fourthNumbers4,
                       String fifthNumbers4, String sixthNumbers4, String seventhNumbers4, String eighthNumbers4,
                       String ninthNumbers4,
                       String firstNumbers5, String secondNumbers5, String thirdNumbers5, String fourthNumbers5,
                       String fifthNumbers5, String sixthNumbers5, String seventhNumbers5, String eighthNumbers5,
                       String ninthNumbers5,
                       String firstNumbers6, String secondNumbers6, String thirdNumbers6, String fourthNumbers6,
                       String fifthNumbers6, String sixthNumbers6, String seventhNumbers6, String eighthNumbers6,
                       String ninthNumbers6,
                       String firstNumbers7, String secondNumbers7, String thirdNumbers7, String fourthNumbers7,
                       String fifthNumbers7, String sixthNumbers7, String seventhNumbers7, String eighthNumbers7,
                       String ninthNumbers7,
                       String firstNumbers8, String secondNumbers8, String thirdNumbers8, String fourthNumbers8,
                       String fifthNumbers8, String sixthNumbers8, String seventhNumbers8, String eighthNumbers8,
                       String ninthNumbers8,
                       String firstNumbers9, String secondNumbers9, String thirdNumbers9, String fourthNumbers9,
                       String fifthNumbers9, String sixthNumbers9, String seventhNumbers9, String eighthNumbers9,
                       String ninthNumbers9) {
            this.firstNumbers = new SimpleStringProperty(firstNumbers);
            this.secondNumbers = new SimpleStringProperty(secondNumbers);
            this.thirdNumbers = new SimpleStringProperty(thirdNumbers);
            this.fourthNumbers = new SimpleStringProperty(fourthNumbers);
            this.fifthNumbers = new SimpleStringProperty(fifthNumbers);
            this.sixthNumbers = new SimpleStringProperty(sixthNumbers);
            this.seventhNumbers = new SimpleStringProperty(seventhNumbers);
            this.eighthNumbers = new SimpleStringProperty(eighthNumbers);
            this.ninthNumbers = new SimpleStringProperty(ninthNumbers);

            this.firstNumbers2 = new SimpleStringProperty(firstNumbers2);
            this.secondNumbers2 = new SimpleStringProperty(secondNumbers2);
            this.thirdNumbers2 = new SimpleStringProperty(thirdNumbers2);
            this.fourthNumbers2 = new SimpleStringProperty(fourthNumbers2);
            this.fifthNumbers2 = new SimpleStringProperty(fifthNumbers2);
            this.sixthNumbers2 = new SimpleStringProperty(sixthNumbers2);
            this.seventhNumbers2 = new SimpleStringProperty(seventhNumbers2);
            this.eighthNumbers2 = new SimpleStringProperty(eighthNumbers2);
            this.ninthNumbers2 = new SimpleStringProperty(ninthNumbers2);

            this.firstNumbers3 = new SimpleStringProperty(firstNumbers3);
            this.secondNumbers3 = new SimpleStringProperty(secondNumbers3);
            this.thirdNumbers3 = new SimpleStringProperty(thirdNumbers3);
            this.fourthNumbers3 = new SimpleStringProperty(fourthNumbers3);
            this.fifthNumbers3 = new SimpleStringProperty(fifthNumbers3);
            this.sixthNumbers3 = new SimpleStringProperty(sixthNumbers3);
            this.seventhNumbers3 = new SimpleStringProperty(seventhNumbers3);
            this.eighthNumbers3 = new SimpleStringProperty(eighthNumbers3);
            this.ninthNumbers3 = new SimpleStringProperty(ninthNumbers3);

            this.firstNumbers4 = new SimpleStringProperty(firstNumbers4);
            this.secondNumbers4 = new SimpleStringProperty(secondNumbers4);
            this.thirdNumbers4 = new SimpleStringProperty(thirdNumbers4);
            this.fourthNumbers4 = new SimpleStringProperty(fourthNumbers4);
            this.fifthNumbers4 = new SimpleStringProperty(fifthNumbers4);
            this.sixthNumbers4 = new SimpleStringProperty(sixthNumbers4);
            this.seventhNumbers4 = new SimpleStringProperty(seventhNumbers4);
            this.eighthNumbers4 = new SimpleStringProperty(eighthNumbers4);
            this.ninthNumbers4 = new SimpleStringProperty(ninthNumbers4);

            this.firstNumbers5 = new SimpleStringProperty(firstNumbers5);
            this.secondNumbers5 = new SimpleStringProperty(secondNumbers5);
            this.thirdNumbers5 = new SimpleStringProperty(thirdNumbers5);
            this.fourthNumbers5 = new SimpleStringProperty(fourthNumbers5);
            this.fifthNumbers5 = new SimpleStringProperty(fifthNumbers5);
            this.sixthNumbers5 = new SimpleStringProperty(sixthNumbers5);
            this.seventhNumbers5 = new SimpleStringProperty(seventhNumbers5);
            this.eighthNumbers5 = new SimpleStringProperty(eighthNumbers5);
            this.ninthNumbers5 = new SimpleStringProperty(ninthNumbers5);

            this.firstNumbers6 = new SimpleStringProperty(firstNumbers6);
            this.secondNumbers6 = new SimpleStringProperty(secondNumbers6);
            this.thirdNumbers6 = new SimpleStringProperty(thirdNumbers6);
            this.fourthNumbers6 = new SimpleStringProperty(fourthNumbers6);
            this.fifthNumbers6 = new SimpleStringProperty(fifthNumbers6);
            this.sixthNumbers6 = new SimpleStringProperty(sixthNumbers6);
            this.seventhNumbers6 = new SimpleStringProperty(seventhNumbers6);
            this.eighthNumbers6 = new SimpleStringProperty(eighthNumbers6);
            this.ninthNumbers6 = new SimpleStringProperty(ninthNumbers6);

            this.firstNumbers7 = new SimpleStringProperty(firstNumbers7);
            this.secondNumbers7 = new SimpleStringProperty(secondNumbers7);
            this.thirdNumbers7 = new SimpleStringProperty(thirdNumbers7);
            this.fourthNumbers7 = new SimpleStringProperty(fourthNumbers7);
            this.fifthNumbers7 = new SimpleStringProperty(fifthNumbers7);
            this.sixthNumbers7 = new SimpleStringProperty(sixthNumbers7);
            this.seventhNumbers7 = new SimpleStringProperty(seventhNumbers7);
            this.eighthNumbers7 = new SimpleStringProperty(eighthNumbers7);
            this.ninthNumbers7 = new SimpleStringProperty(ninthNumbers7);

            this.firstNumbers8 = new SimpleStringProperty(firstNumbers8);
            this.secondNumbers8 = new SimpleStringProperty(secondNumbers8);
            this.thirdNumbers8 = new SimpleStringProperty(thirdNumbers8);
            this.fourthNumbers8 = new SimpleStringProperty(fourthNumbers8);
            this.fifthNumbers8 = new SimpleStringProperty(fifthNumbers8);
            this.sixthNumbers8 = new SimpleStringProperty(sixthNumbers8);
            this.seventhNumbers8 = new SimpleStringProperty(seventhNumbers8);
            this.eighthNumbers8 = new SimpleStringProperty(eighthNumbers8);
            this.ninthNumbers8 = new SimpleStringProperty(ninthNumbers8);

            this.firstNumbers9 = new SimpleStringProperty(firstNumbers9);
            this.secondNumbers9 = new SimpleStringProperty(secondNumbers9);
            this.thirdNumbers9 = new SimpleStringProperty(thirdNumbers9);
            this.fourthNumbers9 = new SimpleStringProperty(fourthNumbers9);
            this.fifthNumbers9 = new SimpleStringProperty(fifthNumbers9);
            this.sixthNumbers9 = new SimpleStringProperty(sixthNumbers9);
            this.seventhNumbers9 = new SimpleStringProperty(seventhNumbers9);
            this.eighthNumbers9 = new SimpleStringProperty(eighthNumbers9);
            this.ninthNumbers9 = new SimpleStringProperty(ninthNumbers9);

        }
        public StringProperty firstNumbersProperty() { return this.firstNumbers; }
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

        public StringProperty firstNumbersProperty2() {
            return this.firstNumbers2;
        }
        public StringProperty secondNumbersProperty2() {
            return this.secondNumbers2;
        }
        public StringProperty thirdNumbersProperty2() {
            return this.thirdNumbers2;
        }
        public StringProperty fourthNumbersProperty2() {
            return this.fourthNumbers2;
        }
        public StringProperty fifthNumbersProperty2() { return this.fifthNumbers2; }
        public StringProperty sixthNumbersProperty2() {
            return this.sixthNumbers2;
        }
        public StringProperty seventhNumbersProperty2() {
            return this.seventhNumbers2;
        }
        public StringProperty eighthNumbersProperty2() {
            return this.eighthNumbers2;
        }
        public StringProperty ninthNumbersProperty2() {
            return this.ninthNumbers2;
        }

        public StringProperty firstNumbersProperty3() {
            return this.firstNumbers3;
        }
        public StringProperty secondNumbersProperty3() {
            return this.secondNumbers3;
        }
        public StringProperty thirdNumbersProperty3() {
            return this.thirdNumbers3;
        }
        public StringProperty fourthNumbersProperty3() {
            return this.fourthNumbers3;
        }
        public StringProperty fifthNumbersProperty3() { return this.fifthNumbers3; }
        public StringProperty sixthNumbersProperty3() {
            return this.sixthNumbers3;
        }
        public StringProperty seventhNumbersProperty3() {
            return this.seventhNumbers3;
        }
        public StringProperty eighthNumbersProperty3() {
            return this.eighthNumbers3;
        }
        public StringProperty ninthNumbersProperty3() {
            return this.ninthNumbers3;
        }

        public StringProperty firstNumbersProperty4() {
            return this.firstNumbers4;
        }
        public StringProperty secondNumbersProperty4() {
            return this.secondNumbers4;
        }
        public StringProperty thirdNumbersProperty4() {
            return this.thirdNumbers4;
        }
        public StringProperty fourthNumbersProperty4() {
            return this.fourthNumbers4;
        }
        public StringProperty fifthNumbersProperty4() { return this.fifthNumbers4; }
        public StringProperty sixthNumbersProperty4() {
            return this.sixthNumbers4;
        }
        public StringProperty seventhNumbersProperty4() {
            return this.seventhNumbers4;
        }
        public StringProperty eighthNumbersProperty4() {
            return this.eighthNumbers4;
        }
        public StringProperty ninthNumbersProperty4() {
            return this.ninthNumbers4;
        }

        public StringProperty firstNumbersProperty5() {
            return this.firstNumbers5;
        }
        public StringProperty secondNumbersProperty5() {
            return this.secondNumbers5;
        }
        public StringProperty thirdNumbersProperty5() {
            return this.thirdNumbers5;
        }
        public StringProperty fourthNumbersProperty5() {
            return this.fourthNumbers5;
        }
        public StringProperty fifthNumbersProperty5() { return this.fifthNumbers5; }
        public StringProperty sixthNumbersProperty5() {
            return this.sixthNumbers5;
        }
        public StringProperty seventhNumbersProperty5() {
            return this.seventhNumbers5;
        }
        public StringProperty eighthNumbersProperty5() {
            return this.eighthNumbers5;
        }
        public StringProperty ninthNumbersProperty5() {
            return this.ninthNumbers5;
        }

        public StringProperty firstNumbersProperty6() { return this.firstNumbers6; }
        public StringProperty secondNumbersProperty6() {
            return this.secondNumbers6;
        }
        public StringProperty thirdNumbersProperty6() { return this.thirdNumbers6; }
        public StringProperty fourthNumbersProperty6() {
            return this.fourthNumbers6;
        }
        public StringProperty fifthNumbersProperty6() { return this.fifthNumbers6; }
        public StringProperty sixthNumbersProperty6() {
            return this.sixthNumbers6;
        }
        public StringProperty seventhNumbersProperty6() {
            return this.seventhNumbers6;
        }
        public StringProperty eighthNumbersProperty6() {
            return this.eighthNumbers6;
        }
        public StringProperty ninthNumbersProperty6() {
            return this.ninthNumbers6;
        }

        public StringProperty firstNumbersProperty7() {
            return this.firstNumbers7;
        }
        public StringProperty secondNumbersProperty7() {
            return this.secondNumbers7;
        }
        public StringProperty thirdNumbersProperty7() {
            return this.thirdNumbers7;
        }
        public StringProperty fourthNumbersProperty7() {
            return this.fourthNumbers7;
        }
        public StringProperty fifthNumbersProperty7() { return this.fifthNumbers7; }
        public StringProperty sixthNumbersProperty7() {
            return this.sixthNumbers7;
        }
        public StringProperty seventhNumbersProperty7() {
            return this.seventhNumbers7;
        }
        public StringProperty eighthNumbersProperty7() {
            return this.eighthNumbers7;
        }
        public StringProperty ninthNumbersProperty7() {
            return this.ninthNumbers7;
        }

        public StringProperty firstNumbersProperty8() {
            return this.firstNumbers8;
        }
        public StringProperty secondNumbersProperty8() {
            return this.secondNumbers8;
        }
        public StringProperty thirdNumbersProperty8() {
            return this.thirdNumbers8;
        }
        public StringProperty fourthNumbersProperty8() {
            return this.fourthNumbers8;
        }
        public StringProperty fifthNumbersProperty8() { return this.fifthNumbers8; }
        public StringProperty sixthNumbersProperty8() {
            return this.sixthNumbers8;
        }
        public StringProperty seventhNumbersProperty8() {
            return this.seventhNumbers8;
        }
        public StringProperty eighthNumbersProperty8() {
            return this.eighthNumbers8;
        }
        public StringProperty ninthNumbersProperty8() {
            return this.ninthNumbers8;
        }

        public StringProperty firstNumbersProperty9() {
            return this.firstNumbers9;
        }
        public StringProperty secondNumbersProperty9() {
            return this.secondNumbers9;
        }
        public StringProperty thirdNumbersProperty9() {
            return this.thirdNumbers9;
        }
        public StringProperty fourthNumbersProperty9() {
            return this.fourthNumbers9;
        }
        public StringProperty fifthNumbersProperty9() { return this.fifthNumbers9; }
        public StringProperty sixthNumbersProperty9() {
            return this.sixthNumbers9;
        }
        public StringProperty seventhNumbersProperty9() {
            return this.seventhNumbers9;
        }
        public StringProperty eighthNumbersProperty9() {
            return this.eighthNumbers9;
        }
        public StringProperty ninthNumbersProperty9() {
            return this.ninthNumbers9;
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

        public void setFirstNumbers2(String firstNumbers2) {
            this.firstNumbers2.set(firstNumbers2);
        }
        public void setSecondNumbers2(String secondNumbers2) {
            this.secondNumbers2.set(secondNumbers2);
        }
        public void setThirdNumbers2(String thirdNumbers2) {
            this.thirdNumbers2.set(thirdNumbers2);
        }
        public void setFourthNumbers2(String fourthNumbers2) {
            this.fourthNumbers2.set(fourthNumbers2);
        }
        public void setFifthNumbers2(String fifthNumbers2) {
            this.fifthNumbers2.set(fifthNumbers2);
        }
        public void setSixthNumbers2(String sixthNumbers2) {
            this.sixthNumbers2.set(sixthNumbers2);
        }
        public void setSeventhNumbers2(String seventhNumbers2) {
            this.seventhNumbers2.set(seventhNumbers2);
        }
        public void setEighthNumbers2(String eighthNumbers2) {
            this.eighthNumbers2.set(eighthNumbers2);
        }
        public void setNinthNumbers2(String ninthNumbers2) {
            this.ninthNumbers2.set(ninthNumbers2);
        }

        public void setFirstNumbers3(String firstNumbers3) { this.firstNumbers3.set(firstNumbers3); }
        public void setSecondNumbers3(String secondNumbers3) {
            this.secondNumbers3.set(secondNumbers3);
        }
        public void setThirdNumbers3(String thirdNumbers3) {
            this.thirdNumbers3.set(thirdNumbers3);
        }
        public void setFourthNumbers3(String fourthNumbers3) {
            this.fourthNumbers3.set(fourthNumbers3);
        }
        public void setFifthNumbers3(String fifthNumbers3) {
            this.fifthNumbers3.set(fifthNumbers3);
        }
        public void setSixthNumbers3(String sixthNumbers3) {
            this.sixthNumbers3.set(sixthNumbers3);
        }
        public void setSeventhNumbers3(String seventhNumbers3) {
            this.seventhNumbers3.set(seventhNumbers3);
        }
        public void setEighthNumbers3(String eighthNumbers3) {
            this.eighthNumbers3.set(eighthNumbers3);
        }
        public void setNinthNumbers3(String ninthNumbers3) {
            this.ninthNumbers3.set(ninthNumbers3);
        }

        public void setFirstNumbers4(String firstNumbers4) {
            this.firstNumbers4.set(firstNumbers4);
        }
        public void setSecondNumbers4(String secondNumbers4) {
            this.secondNumbers4.set(secondNumbers4);
        }
        public void setThirdNumbers4(String thirdNumbers4) {
            this.thirdNumbers4.set(thirdNumbers4);
        }
        public void setFourthNumbers4(String fourthNumbers4) {
            this.fourthNumbers4.set(fourthNumbers4);
        }
        public void setFifthNumbers4(String fifthNumbers4) {
            this.fifthNumbers4.set(fifthNumbers4);
        }
        public void setSixthNumbers4(String sixthNumbers4) {
            this.sixthNumbers4.set(sixthNumbers4);
        }
        public void setSeventhNumbers4(String seventhNumbers4) {
            this.seventhNumbers4.set(seventhNumbers4);
        }
        public void setEighthNumbers4(String eighthNumbers4) {
            this.eighthNumbers4.set(eighthNumbers4);
        }
        public void setNinthNumbers4(String ninthNumbers4) {
            this.ninthNumbers4.set(ninthNumbers4);
        }

        public void setFirstNumbers5(String firstNumbers5) {
            this.firstNumbers5.set(firstNumbers5);
        }
        public void setSecondNumbers5(String secondNumbers5) {
            this.secondNumbers5.set(secondNumbers5);
        }
        public void setThirdNumbers5(String thirdNumbers5) {
            this.thirdNumbers5.set(thirdNumbers5);
        }
        public void setFourthNumbers5(String fourthNumbers5) {
            this.fourthNumbers5.set(fourthNumbers5);
        }
        public void setFifthNumbers5(String fifthNumbers5) {
            this.fifthNumbers5.set(fifthNumbers5);
        }
        public void setSixthNumbers5(String sixthNumbers5) {
            this.sixthNumbers5.set(sixthNumbers5);
        }
        public void setSeventhNumbers5(String seventhNumbers5) {
            this.seventhNumbers5.set(seventhNumbers5);
        }
        public void setEighthNumbers5(String eighthNumbers5) {
            this.eighthNumbers5.set(eighthNumbers5);
        }
        public void setNinthNumbers5(String ninthNumbers5) {
            this.ninthNumbers5.set(ninthNumbers5);
        }

        public void setFirstNumbers6(String firstNumbers6) { this.firstNumbers6.set(firstNumbers6); }
        public void setSecondNumbers6(String secondNumbers6) { this.secondNumbers6.set(secondNumbers6); }
        public void setThirdNumbers6(String thirdNumbers6) {
            this.thirdNumbers6.set(thirdNumbers6);
        }
        public void setFourthNumbers6(String fourthNumbers6) {
            this.fourthNumbers6.set(fourthNumbers6);
        }
        public void setFifthNumbers6(String fifthNumbers6) {
            this.fifthNumbers6.set(fifthNumbers6);
        }
        public void setSixthNumbers6(String sixthNumbers6) {
            this.sixthNumbers6.set(sixthNumbers6);
        }
        public void setSeventhNumbers6(String seventhNumbers6) {
            this.seventhNumbers6.set(seventhNumbers6);
        }
        public void setEighthNumbers6(String eighthNumbers6) {
            this.eighthNumbers6.set(eighthNumbers6);
        }
        public void setNinthNumbers6(String ninthNumbers6) {
            this.ninthNumbers6.set(ninthNumbers6);
        }

        public void setFirstNumbers7(String firstNumbers7) {
            this.firstNumbers7.set(firstNumbers7);
        }
        public void setSecondNumbers7(String secondNumbers7) {
            this.secondNumbers7.set(secondNumbers7);
        }
        public void setThirdNumbers7(String thirdNumbers7) {
            this.thirdNumbers7.set(thirdNumbers7);
        }
        public void setFourthNumbers7(String fourthNumbers7) {
            this.fourthNumbers7.set(fourthNumbers7);
        }
        public void setFifthNumbers7(String fifthNumbers7) {
            this.fifthNumbers7.set(fifthNumbers7);
        }
        public void setSixthNumbers7(String sixthNumbers7) {
            this.sixthNumbers7.set(sixthNumbers7);
        }
        public void setSeventhNumbers7(String seventhNumbers7) {
            this.seventhNumbers7.set(seventhNumbers7);
        }
        public void setEighthNumbers7(String eighthNumbers7) {
            this.eighthNumbers7.set(eighthNumbers7);
        }
        public void setNinthNumbers7(String ninthNumbers7) {
            this.ninthNumbers7.set(ninthNumbers7);
        }

        public void setFirstNumbers8(String firstNumbers8) {
            this.firstNumbers8.set(firstNumbers8);
        }
        public void setSecondNumbers8(String secondNumbers8) {
            this.secondNumbers8.set(secondNumbers8);
        }
        public void setThirdNumbers8(String thirdNumbers8) {
            this.thirdNumbers8.set(thirdNumbers8);
        }
        public void setFourthNumbers8(String fourthNumbers8) {
            this.fourthNumbers8.set(fourthNumbers8);
        }
        public void setFifthNumbers8(String fifthNumbers8) {
            this.fifthNumbers8.set(fifthNumbers8);
        }
        public void setSixthNumbers8(String sixthNumbers8) {
            this.sixthNumbers8.set(sixthNumbers8);
        }
        public void setSeventhNumbers8(String seventhNumbers8) {
            this.seventhNumbers8.set(seventhNumbers8);
        }
        public void setEighthNumbers8(String eighthNumbers8) {
            this.eighthNumbers8.set(eighthNumbers8);
        }
        public void setNinthNumbers8(String ninthNumbers8) {
            this.ninthNumbers8.set(ninthNumbers8);
        }

        public void setFirstNumbers9(String firstNumbers9) {
            this.firstNumbers9.set(firstNumbers9);
        }
        public void setSecondNumbers9(String secondNumbers9) {
            this.secondNumbers9.set(secondNumbers9);
        }
        public void setThirdNumbers9(String thirdNumbers9) {
            this.thirdNumbers9.set(thirdNumbers9);
        }
        public void setFourthNumbers9(String fourthNumbers9) {
            this.fourthNumbers9.set(fourthNumbers9);
        }
        public void setFifthNumbers9(String fifthNumbers9) {
            this.fifthNumbers9.set(fifthNumbers9);
        }
        public void setSixthNumbers9(String sixthNumbers9) {
            this.sixthNumbers9.set(sixthNumbers9);
        }
        public void setSeventhNumbers9(String seventhNumbers9) {
            this.seventhNumbers9.set(seventhNumbers9);
        }
        public void setEighthNumbers9(String eighthNumbers9) {
            this.eighthNumbers9.set(eighthNumbers9);
        }
        public void setNinthNumbers9(String ninthNumbers9) {
            this.ninthNumbers9.set(ninthNumbers9);
        }
    }

}