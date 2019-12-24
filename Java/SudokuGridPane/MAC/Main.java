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

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                StackPane cell = new StackPane();
                cell.getStyleClass().add("cell");

                //Creates the 3 x 3 boxes
                cell.pseudoClassStateChanged(right, row == 2 || row == 5);
                cell.pseudoClassStateChanged(bottom, col == 2 || col == 5);

                cell.getChildren().add(createTextField(col, row, sudokuTable));

                board.add(cell, row, col);
            }
        }

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
        borderPane2.setCenter(solvePuzzle(sudokuTable));


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

        private TextField createTextField2( int row, int col, int[][] sudokuTable) {

        if (compute(0, 0, sudokuTable)) {

            TextField textField = new TextField(Integer.toString(sudokuTable[row][col]));
            return textField;

        }
        else{

            TextField textField = new TextField(Integer.toString(sudokuTable[row][col]));
            System.out.println("Row " + row + " Col " + col + " with " + Integer.toString(sudokuTable[row][col]));
            return textField;
        }

    }
    private GridPane solvePuzzle (int[][] sudokuTable) {



        GridPane board2 = new GridPane();

        PseudoClass right2 = PseudoClass.getPseudoClass("right");
        PseudoClass bottom2 = PseudoClass.getPseudoClass("bottom");

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                StackPane cell2 = new StackPane();
                cell2.getStyleClass().add("cell");

                //Creates the 3 x 3 boxes
                cell2.pseudoClassStateChanged(right2, row == 2 || row == 5);
                cell2.pseudoClassStateChanged(bottom2, col == 2 || col == 5);

                cell2.getChildren().add(createTextField2(col, row, sudokuTable));

                board2.add(cell2, row, col);
            }
        }
        createTable(sudokuTable);
        return board2;
    }

    private static boolean compute(int row, int col, int[][] sudoku){

        if (row == 9) {
            row = 0;
        if (++col == 9)
            return true;
        }

        //Skips any non-zero values
        if(sudoku[row][col] != 0)
            compute((row + 1), col, sudoku);

        for(int check = 1; check <= 9; check++){
            if(validNumber(row, col, check, sudoku)) {
                sudoku[row][col] = check;
            if (compute(row + 1, col, sudoku))
                return true;
        }

        }

        sudoku[row][col] = 0;
        return false;
        }

    private static boolean validNumber(int row, int col, int check, int[][] sudoku){

        //Check for same Values in each Column
        for(int i = 0; i < 9; i++)
            if(check == sudoku[i][col])
                return false;


        //Check for same Values in each row
        for(int j = 0; j < 9; j++){
            if(check == sudoku[row][j])
                return false;
        }

        //Check for same Values in the soduku Box

        int checkBoxRow = (row / 3)*3;
        int checkBoxCol = (col / 3)*3;

        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (check == sudoku[checkBoxRow + x][checkBoxCol + y])
                    return false;

        //If all conditions are valid then return true
        return true;
        }

    static void createTable(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; ++j) {

                System.out.print(Integer.toString(sudoku[i][j]));
            }
            System.out.println();
        }

    }

}