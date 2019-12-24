public class Sudoku {

    public static void main(String[] args) {

        //Makes an easy sudoku puzzle for Testing the Program

        int[][] sudokuTable = new int[][]{	{0, 0, 0, 0, 3, 5, 0, 8, 0},
                {4, 0, 0, 0, 0, 1, 9, 0, 5},
                {0, 0, 0, 8, 0, 9, 0, 7, 0},
                {0, 6, 0, 0, 1, 0, 0, 4, 9},
                {3, 7, 9, 0, 8, 0, 6, 2, 1},
                {1, 4, 0, 0, 6, 0, 0, 5, 0},
                {0, 3, 0, 4, 0, 6, 0, 0, 0},
                {7, 0, 2, 1, 0, 0, 0, 0, 6},
                {0, 5, 0, 3, 9, 0, 0, 0, 0} };
        createTable(sudokuTable);

        if(compute(0, 0, sudokuTable))
            createTable(sudokuTable);
        else
            System.out.println("Unable to solve");

    }


    static boolean compute(int row, int col, int[][] sudoku){

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
                if (compute(row + 1, col, sudoku)) //
                    return true;// returns true to main method
            }

        }

        sudoku[row][col] = 0;
        return false;
    }


    static boolean validNumber(int row, int col, int check, int[][] sudoku){

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
            if (i % 3 == 0)
                System.out.println(" -----------------------");
            for (int j = 0; j < 9; ++j) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(sudoku[i][j] == 0
                        ? " "
                        : Integer.toString(sudoku[i][j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }





}