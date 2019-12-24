import javafx.application.Application;
import javafx.css.PseudoClass;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        //---------------------------
        SudokuGen gen = new SudokuGen();
        Sudoku sud = new Sudoku();
        sud.main();
        int[][] test = gen.getPuzzle();

        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                sud.setValue(i, j, test[i][j]);

            }

        }
        sud.printTable();
        //-----------------------------

        //Initial Sudoku Puzzle
        int[][] sudokuTable = new int[][] {

                {0, 0, 0, 0, 3, 5, 0, 8, 0},
                {4, 0, 0, 0, 0, 1, 9, 0, 5},
                {0, 0, 0, 8, 0, 9, 0, 7, 0},
                {0, 6, 0, 0, 1, 0, 0, 4, 9},
                {3, 7, 9, 0, 8, 0, 6, 2, 1},
                {1, 4, 0, 0, 6, 0, 0, 5, 0},
                {0, 3, 0, 4, 0, 6, 0, 0, 0},
                {7, 0, 2, 1, 0, 0, 0, 0, 6},
                {0, 5, 0, 3, 9, 0, 0, 0, 0}

/*
                {0, 0, 6, 0, 0, 8, 0, 1, 0},
                {8, 0, 0, 2, 4, 0, 0, 3, 0},
                {0, 2, 0, 0, 1, 0, 8, 0, 0},
                {6, 0, 0, 0, 0, 3, 9, 0, 0},
                {7, 0, 2, 0, 0, 0, 1, 0, 3},
                {0, 0, 5, 1, 0, 0, 0, 0, 7},
                {0, 0, 8, 0, 2, 0, 0, 9, 0},
                {0, 7, 0, 0, 8, 1, 0, 0, 6},
                {0, 9, 0, 3, 0, 0, 7, 0, 0}
*/
                };//End of Table

        //Layout1-------------------------------------------------------------------------------------------------------

        GridPane board = new GridPane();

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        Sudoku sudoku = new Sudoku();
        int value;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                StackPane cell = new StackPane();
                cell.getStyleClass().add("cell");

                //Creates the 3 x 3 boxes
                cell.pseudoClassStateChanged(right, row == 2 || row == 5);
                cell.pseudoClassStateChanged(bottom, col == 2 || col == 5);

                cell.getChildren().add(createTextField(col, row, sudokuTable));
                value = sudokuTable[row][col];
                sudoku.setValue(row, col, value);

                board.add(cell, row, col);
            }
        }
        sudoku.printTable();

        Button solve = new Button("Solve Puzzle");
        //solve.setOnAction(e -> solvePuzzle(sudokuTable));
        VBox box = new VBox();
        box.getChildren().addAll(solve);
        BorderPane borderPane = new BorderPane();
        borderPane.setRight(box);
        borderPane.setCenter(board);

        //Layout2-------------------------------------------------------------------------------------------------------

        Button back = new Button("Start Over");
        VBox box2 = new VBox();
        box2.getChildren().addAll(back);
        BorderPane borderPane2 = new BorderPane();
        borderPane2.setRight(box2);
        borderPane2.setCenter(solvePuzzle(sudokuTable, sudoku));


        //Set Scene and Stage-------------------------------------------------------------------------------------------

        Scene scene1 = new Scene(borderPane);
        Scene scene2 = new Scene(borderPane2);
        scene1.getStylesheets().add("sudoku.css");
        scene2.getStylesheets().add("sudoku.css");
        primaryStage.setScene(scene1);
        back.setOnAction(e -> primaryStage.setScene(scene1));
        solve.setOnAction(e -> primaryStage.setScene(scene2));
        primaryStage.setTitle("Sudoku Puzzle Solver");
        primaryStage.show();
        }

    private TextField createTextField(int row, int col, int[][] sudokuTable) {

        TextField textField = new TextField (Integer.toString(sudokuTable[row][col]));

        // restrict input to integers:
        textField.setTextFormatter(new TextFormatter<Integer>(c -> {
            if (c.getControlNewText().matches("\\d?")) {
                return c ;
            } else {
                return null ;
            }
        }));

        return textField ;
        }


    private TextField createTextField2(int row, int col, Sudoku sudoku, int[][] sudokuTable) {

        TextField textField = new TextField(Integer.toString(sudokuTable[row][col]));
        return textField;
    }


    private GridPane solvePuzzle (int[][] sudokuTable, Sudoku sudoku) {

        GridPane board2 = new GridPane();

        PseudoClass right2 = PseudoClass.getPseudoClass("right");
        PseudoClass bottom2 = PseudoClass.getPseudoClass("bottom");



       //Calls the Compute Method And Solves Puzzle
        sudoku.compute(0, 0, sudokuTable);



        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                StackPane cell2 = new StackPane();
                cell2.getStyleClass().add("cell");

                //Creates the 3 x 3 boxes
                cell2.pseudoClassStateChanged(right2, row == 2 || row == 5);
                cell2.pseudoClassStateChanged(bottom2, col == 2 || col == 5);

                cell2.getChildren().add(createTextField2(col, row, sudoku, sudokuTable));


                board2.add(cell2, row, col);
            }

        }
        sudoku.printTable();
        return board2;
    }
}