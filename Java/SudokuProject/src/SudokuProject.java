import javafx.application.Application;
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

public class SudokuProject extends Application {

    Stage window;
    Scene scene1, scene2;
    TableView<Sudoku> table;
    TextField column1, column2, column3, column4, column5, column6, column7, column8, column9;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setWidth(1366);
        primaryStage.setHeight(768);
        window.setTitle("Sudoku Game");


                                                                    //Starts Building the Table Columns
//----------------------------------------------------------------------------------------------------------------------
        TableColumn<Sudoku, String> column1I = new TableColumn<>(""); //Column 1 Setup
        column1I.getStyleClass().add("column1");
        column1I.setSortable(false);
        column1I.setMinWidth(30);
        column1I.setMaxWidth(30);
        column1I.setCellValueFactory(new PropertyValueFactory<>("column1"));

        TableColumn<Sudoku, String> column2I = new TableColumn<>(""); //Column 2 Setup
        column2I.getStyleClass().add("column2");
        column2I.setSortable(false);
        column2I.setMinWidth(30);
        column2I.setMaxWidth(30);
        column2I.setCellValueFactory(new PropertyValueFactory<>("column2"));

        TableColumn<Sudoku, String> column3I = new TableColumn<>(""); //Column 3 Setup
        column3I.getStyleClass().add("column3");
        column3I.setSortable(false);
        column3I.setMinWidth(30);
        column3I.setMaxWidth(30);
        column3I.setCellValueFactory(new PropertyValueFactory<>("column3"));

        TableColumn<Sudoku, String> column4I = new TableColumn<>(""); //Column 4 Setup
        column4I.getStyleClass().add("column4");
        column4I.setSortable(false);
        column4I.setMinWidth(30);
        column4I.setMaxWidth(30);
        column4I.setCellValueFactory(new PropertyValueFactory<>("column4"));

        TableColumn<Sudoku, String> column5I = new TableColumn<>(""); //Column 5 Setup
        column5I.getStyleClass().add("column5");
        column5I.setSortable(false);
        column5I.setMinWidth(30);
        column5I.setMaxWidth(30);
        column5I.setCellValueFactory(new PropertyValueFactory<>("column5"));

        TableColumn<Sudoku, String> column6I = new TableColumn<>(""); //Column 6 Setup
        column6I.getStyleClass().add("column6");
        column6I.setSortable(false);
        column6I.setMinWidth(30);
        column6I.setMaxWidth(30);
        column6I.setCellValueFactory(new PropertyValueFactory<>("column6"));

        TableColumn<Sudoku, String> column7I = new TableColumn<>(""); //Column 7 Setup
        column7I.getStyleClass().add("column7");
        column7I.setSortable(false);
        column7I.setMinWidth(30);
        column7I.setMaxWidth(30);
        column7I.setCellValueFactory(new PropertyValueFactory<>("column7"));

        TableColumn<Sudoku, String> column8I = new TableColumn<>(""); //Column 8 Setup
        column8I.getStyleClass().add("column8");
        column8I.setSortable(false);
        column8I.setMinWidth(30);
        column8I.setMaxWidth(30);
        column8I.setCellValueFactory(new PropertyValueFactory<>("column8"));

        TableColumn<Sudoku, String> column9I = new TableColumn<>(""); //Column 9 Setup
        column9I.getStyleClass().add("column9");
        column9I.setSortable(false);
        column9I.setMinWidth(30);
        column9I.setMaxWidth(30);
        column9I.setCellValueFactory(new PropertyValueFactory<>("column9"));


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

        column8= new TextField();
        column8.setPromptText("");
        column8.setMinWidth(30);
        column8.setMaxWidth(30);

        column9  = new TextField();
        column9.setPromptText("");
        column9.setMinWidth(30);
        column9.setMaxWidth(30);

        //Button
        Button addButton = new Button("Add");                           //Add Button
        addButton.setOnAction(e -> addButtonClicked());

        Button deleteButton = new Button("Delete");                     //Delete Button
        deleteButton.setOnAction(e -> deleteButtonClicked());

        Button solveButton = new Button("Solve");                       //Solve Button
        solveButton.setOnAction(e -> window.setScene(scene2));

                                                                            //Creates the input Bar
        HBox bottomMenu = new HBox();
        bottomMenu.getStyleClass().add("hbox");
        bottomMenu.setPadding(new Insets(10,0,10,0));
        bottomMenu.setSpacing(0);
        bottomMenu.getChildren().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9,
                                    addButton, deleteButton, solveButton);

//----------------------------------------------------------------------------------------------------------------------
                                                                            //Creates The Table with Settings
        table = new TableView<>();
        table.setEditable(true);
        table.setMaxSize(290,290);
        table.setMinSize(290, 290);
        table.getStyleClass().add("table");
        //table.setItems(getSudoku());
        table.getColumns().addAll(column1I, column2I, column3I, column4I, column5I,
                                    column6I, column7I, column8I, column9I);


        BorderPane borderPane = new BorderPane();                           //Initializes the Border Pane
        borderPane.setPrefWidth(400);
        borderPane.setBottom(bottomMenu);
        borderPane.setCenter(table);

        Button button2 = new Button("");                            //Creates Scene 2 When Solve is Pressed
        button2.setOnAction(e -> window.setScene(scene1));
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);



        scene1 = new Scene(borderPane, 1028,720);           //Setup for Scene 1
        scene1.getStylesheets().add("style.css");
        window.setScene(scene1);
        window.show();

    }

//--------------------------------------------------------------------------Add Button Method---------------------------
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

        table.getItems().add(sudoku);
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
    public void deleteButtonClicked(){
        ObservableList<Sudoku> sudokuSelected, allSudokus;
        allSudokus = table.getItems();
        sudokuSelected = table.getSelectionModel().getSelectedItems();

        sudokuSelected.forEach(allSudokus::remove);
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
*/
}
