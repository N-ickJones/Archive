
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//package editabletableview;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SudokuProject extends Application {

    Stage window;
    Scene scene, scene1, scene2;
    TableView<SudokuList> table;
    TextField column1, column2, column3, column4, column5, column6, column7, column8, column9;



    private final ObservableList<SudokuList> sudoku2 = FXCollections.observableArrayList(
        new SudokuList("0", "0", "0", "0", "3", "5", "0", "8", "0"),
        new SudokuList("4", "0", "0", "0", "0", "1", "9", "0", "5"),
        new SudokuList("0", "0", "0", "8", "0", "9", "0", "7", "0"),
        new SudokuList("0", "6", "0", "0", "1", "0", "0", "4", "9"),
        new SudokuList("3", "7", "9", "0", "8", "0", "6", "2", "1"),
        new SudokuList("1", "4", "0", "0", "6", "0", "0", "5", "0"),
        new SudokuList("0", "3", "0", "4", "0", "6", "0", "0", "0"),
        new SudokuList("7", "0", "2", "1", "0", "0", "0", "0", "6"),
        new SudokuList("0", "5", "0", "3", "9", "0", "0", "0", "0"));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setWidth(1366);
        primaryStage.setHeight(768);
        window.setTitle("Sudoku Game");

        table.setEditable(true);
        Callback<TableColumn<SudokuList, String>, TableCell<SudokuList, String>> cellFactory //Allows Editiable List w/Line 73
                = (TableColumn<SudokuList, String> param) -> new EditingCell();


                                                                                    //Starts Building the Table Columns
//----------------------------------------------------------------------------------------------------------------------

        TableColumn<SudokuList, String> column1I = new TableColumn<>("");                       //Column 1 Setup
        column1I.setMinWidth(30);
        column1I.setMaxWidth(30);
        column1I.getStyleClass().add("column1");
        column1I.setSortable(false);
       // column1I.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row1Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                        .get(t.getTablePosition().getRow()))
                        .setRow1(t.getNewValue());

                });


        TableColumn<Sudoku, String> column2I = new TableColumn<>("");                       //Column 2 Setup
        column2I.getStyleClass().add("column2");
        column2I.setSortable(false);
        column2I.setMinWidth(30);
        column2I.setMaxWidth(30);
        column2I.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row2Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setRow2(t.getNewValue());

                });

        TableColumn<Sudoku, String> column3I = new TableColumn<>("");                       //Column 3 Setup
        column3I.getStyleClass().add("column3");
        column3I.setSortable(false);
        column3I.setMinWidth(30);
        column3I.setMaxWidth(30);
        column3I.setCellValueFactory(new PropertyValueFactory<>("column3"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row3Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setRow3(t.getNewValue());

                });

        TableColumn<Sudoku, String> column4I = new TableColumn<>("");                       //Column 4 Setup
        column4I.getStyleClass().add("column4");
        column4I.setSortable(false);
        column4I.setMinWidth(30);
        column4I.setMaxWidth(30);
        column4I.setCellValueFactory(new PropertyValueFactory<>("column4"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row4Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setRow4(t.getNewValue());

                });

        TableColumn<Sudoku, String> column5I = new TableColumn<>("");                       //Column 5 Setup
        column5I.getStyleClass().add("column5");
        column5I.setSortable(false);
        column5I.setMinWidth(30);
        column5I.setMaxWidth(30);
        column5I.setCellValueFactory(new PropertyValueFactory<>("column5"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row5Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setRow5(t.getNewValue());

                });

        TableColumn<Sudoku, String> column6I = new TableColumn<>("");                       //Column 6 Setup
        column6I.getStyleClass().add("column6");
        column6I.setSortable(false);
        column6I.setMinWidth(30);
        column6I.setMaxWidth(30);
        column6I.setCellValueFactory(new PropertyValueFactory<>("column6"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row6Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setRow6(t.getNewValue());

                });

        TableColumn<Sudoku, String> column7I = new TableColumn<>("");                       //Column 7 Setup
        column7I.getStyleClass().add("column7");
        column7I.setSortable(false);
        column7I.setMinWidth(30);
        column7I.setMaxWidth(30);
        column7I.setCellValueFactory(new PropertyValueFactory<>("column7"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row7Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setRow7(t.getNewValue());

                });

        TableColumn<Sudoku, String> column8I = new TableColumn<>("");                       //Column 8 Setup
        column8I.getStyleClass().add("column8");
        column8I.setSortable(false);
        column8I.setMinWidth(30);
        column8I.setMaxWidth(30);
        column8I.setCellValueFactory(new PropertyValueFactory<>("column8"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row8Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setRow8(t.getNewValue());

                });

        TableColumn<Sudoku, String> column9I = new TableColumn<>("");                       //Column 9 Setup
        column9I.getStyleClass().add("column9");
        column9I.setSortable(false);
        column9I.setMinWidth(30);
        column9I.setMaxWidth(30);
        column9I.setCellValueFactory(new PropertyValueFactory<>("column9"));
        column1I.setCellValueFactory(cellData -> cellData.getValue().row9Property());
        column1I.setCellFactory(cellFactory);
        column1I.setOnEditCommit(
                (TableColumn.CellEditEvent<SudokuList, String> t) -> {
                    ((SudokuList) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setRow9(t.getNewValue());

                });


        //                          Starts Building the Input Row @ the bottom of the page & Add, Delete, Solve Buttons
        //--------------------------------------------------------------------------------------------------------------

        column1 = new TextField();
        column1.setPromptText("");
        column1.setMinWidth(30);
        column1.setMaxWidth(30);

        column2 = new TextField();
        column2.setPromptText("");
        column2.setMinWidth(30);
        column2.setMaxWidth(30);

        column3 = new TextField();
        column3.setPromptText("");
        column3.setMinWidth(30);
        column3.setMaxWidth(30);

        column4 = new TextField();
        column4.setPromptText("");
        column4.setMinWidth(30);
        column4.setMaxWidth(30);

        column5 = new TextField();
        column5.setPromptText("");
        column5.setMinWidth(30);
        column5.setMaxWidth(30);

        column6 = new TextField();
        column6.setPromptText("");
        column6.setMinWidth(30);
        column6.setMaxWidth(30);

        column7 = new TextField();
        column7.setPromptText("");
        column7.setMinWidth(30);
        column7.setMaxWidth(30);

        column8 = new TextField();
        column8.setPromptText("");
        column8.setMinWidth(30);
        column8.setMaxWidth(30);

        column9 = new TextField();
        column9.setPromptText("");
        column9.setMinWidth(30);
        column9.setMaxWidth(30);

        //Button
        Button addButton = new Button("Add");                           //Add Button
        //addButton.setOnAction(e -> addButtonClicked());

        Button deleteButton = new Button("Delete");                     //Delete Button
        //deleteButton.setOnAction(e -> deleteButtonClicked());

        //Button solveButton = new Button("Solve");                       //Solve Button
        //solveButton.setOnAction(e -> window.setScene(scene2));

        //Creates the input Bar
        HBox bottomMenu = new HBox();
        bottomMenu.getStyleClass().add("hbox");
        bottomMenu.setPadding(new Insets(10, 0, 10, 0));
        bottomMenu.setSpacing(0);
        bottomMenu.getChildren().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, addButton, deleteButton);
               /* addButton, deleteButton, solveButton);*/

//----------------------------------------------------------------------------------------------------------------------
        /*
        HBox topMenu = new HBox();
        topMenu.getStyleClass().add("hbox");
        bottomMenu.setPadding(new Insets(10,0,10,0)); //Around Input box     //To Create Hbox at Top of Page
        bottomMenu.setSpacing(0);
        bottomMenu.getChildren().addAll();

        VBox rightMenu = new VBox();                                        //To Create VBox on Right of Page
        rightMenu.setPrefWidth(400);
        rightMenu.setPadding(new Insets(10,10,10,10)); //Around Input box
        rightMenu.getChildren().addAll();

        */


        //Creates The Table with Settings
        table = new TableView<>();
        table.setEditable(true);
        table.setMaxSize(290, 290);
        table.setMinSize(290, 290);
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //table.setFixedCellSize(30); //Cell Height
        //table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(15)));
        //table.minHeightProperty().bind(table.prefHeightProperty());
        //table.maxHeightProperty().bind(table.prefHeightProperty());
        table.getStyleClass().add("table");
        //table.setItems(getSudoku());
         //table.getColumns().addAll(column1I, column2I, column3I, column4I, column5I,
                //column6I, column7I, column8I, column9I);


        BorderPane borderPane = new BorderPane();                           //Initializes the Border Pane
        borderPane.setPrefWidth(400);
        //borderPane.setTop(topMenu);
        //borderPane.setRight(rightMenu);
        borderPane.setBottom(bottomMenu);
        borderPane.setCenter(table);
/*
        Button button2 = new Button("");                            //Creates Scene 2 When Solve is Pressed
       // button2.setOnAction(e -> window.setScene(scene1));
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);
*/

        scene = new Scene(borderPane, 1028, 720);           //Setup for Scene 1
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.show();


    }

    //Solve Button Clicked
    /*public void solveButtonClicked(){                                 //Solve Button Method


    }
    */


    //--------------------------------------------------------------------------Add Button Method---------------------------
    /*
    public void addButtonClicked(){
        Sudoku sudoku = new Sudoku();
        sudoku.setColumn1(Integer.parseInt(column1.getText()));
        sudoku.setColumn2(Integer.parseInt(column2.getText()));
        sudoku.setColumn3(Integer.parseInt(column3.getText()));
        sudoku.setColumn4(Integer.parseInt(column4.getText()));
        sudoku.setColumn5(Integer.parseInt(column5.getText()));
        sudoku.setColumn6(Integer.parseInt(column6.getText()));
        sudoku.setColumn7(Integer.parseInt(column7.getText()));
        sudoku.setColumn8(Integer.parseInt(column8.getText()));
        sudoku.setColumn9(Integer.parseInt(column9.getText()));

       // table.getItems().add(sudoku);
        column1.clear();
        column2.clear();
        column3.clear();
        column4.clear();
        column5.clear();
        column6.clear();
        column7.clear();
        column8.clear();
        column9.clear();
    }
//--------------------------------------------------------------------------Delete Button Method------------------------
    public void deleteButtonClicked() {
        ObservableList<SudokuList> sudokuSelected, allSudokus;
        allSudokus = table.getItems();
        sudokuSelected = table.getSelectionModel().getSelectedItems();

        sudokuSelected.forEach(allSudokus::remove);

    }
*/
//----------------------------------------------------------------------------------------------------------------------


//--------------------------------------------------------------------------Editing Cell Method-------------------------

    class EditingCell extends TableCell<SudokuList, String> {

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

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item);
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





                                                                                    //SudokuList Class
//----------------------------------------------------------------------------------------------------------------------

    public static class SudokuList {

        private final SimpleStringProperty row1, row2, row3, row4, row5, row6, row7, row8, row9 ;

        public SudokuList(String row1, String row2, String row3, String row4, String row5,
                      String row6, String row7, String row8, String row9) {

            this.row1 = new SimpleStringProperty(row1);
            this.row2 = new SimpleStringProperty(row2);
            this.row3 = new SimpleStringProperty(row3);
            this.row4 = new SimpleStringProperty(row4);
            this.row5 = new SimpleStringProperty(row5);
            this.row6 = new SimpleStringProperty(row6);
            this.row7 = new SimpleStringProperty(row7);
            this.row8 = new SimpleStringProperty(row8);
            this.row9 = new SimpleStringProperty(row9);
        }

        public void setRow1(String row1) {
            this.row1.set(row1);
        }
        public void setRow2(String row2) {
            this.row2.set(row2);
        }
        public void setRow3(String row3) {
            this.row3.set(row3);
        }
        public void setRow4(String row4) {
            this.row4.set(row4);
        }
        public void setRow5(String row5) {
            this.row5.set(row5);
        }
        public void setRow6(String row6) {
            this.row6.set(row6);
        }
        public void setRow7(String row7) {
            this.row7.set(row7);
        }
        public void setRow8(String row8) {
            this.row8.set(row8);
        }
        public void setRow9(String row9) {
            this.row9.set(row9);
        }

        public String getRow1() {
            return row1.get();
        }
        public String getRow2() { return row2.get(); }
        public String getRow3() {
            return row3.get();
        }
        public String getRow4() { return row4.get(); }
        public String getRow5() {
            return row5.get();
        }
        public String getRow6() { return row6.get(); }
        public String getRow7() {
            return row7.get();
        }
        public String getRow8() { return row8.get(); }
        public String getRow9() { return row9.get(); }

        public StringProperty row1Property() { return this.row1; }
        public StringProperty row2Property() { return this.row2; }
        public StringProperty row3Property() { return this.row3; }
        public StringProperty row4Property() { return this.row4; }
        public StringProperty row5Property() { return this.row5; }
        public StringProperty row6Property() { return this.row6; }
        public StringProperty row7Property() { return this.row7; }
        public StringProperty row8Property() { return this.row8; }
        public StringProperty row9Property() { return this.row9; }

    }


























}


//--------------------------------------------------------------------------Creates an Observable List------------------
/*
    public ObservableList<Sudoku> getSudoku(){
        ObservableList<Sudoku> sudokus = FXCollections.observableArrayList();
        sudokus.add(new Sudoku(0, 0, 0, 0, 3, 5, 0, 8, 0));
        sudokus.add(new Sudoku(4, 0, 0, 0, 0, 1, 9, 0, 5));
        sudokus.add(new Sudoku(0, 0, 0, 8, 0, 9, 0, 7, 0));
        sudokus.add(new Sudoku(0, 6, 0, 0, 1, 0, 0, 4, 9));
        sudokus.add(new Sudoku(3, 7, 9, 0, 8, 0, 6, 2, 1));
        sudokus.add(new Sudoku(1, 4, 0, 0, 6, 0, 0, 5, 0));
        sudokus.add(new Sudoku(0, 3, 0, 4, 0, 6, 0, 0, 0));
        sudokus.add(new Sudoku(7, 0, 2, 1, 0, 0, 0, 0, 6));
        sudokus.add(new Sudoku(0, 5, 0, 3, 9, 0, 0, 0, 0));
        return sudokus;
    }


}


/*

To remove all data:

eventsTableView.getItems().clear();

To remove all columns:

eventsTableView.getColumns().clear();
 */