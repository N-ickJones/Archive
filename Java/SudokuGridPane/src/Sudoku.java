public class Sudoku {

    int[][] sudokuTable = new int[9][9];


    public void setValue(int row, int col, int value){

        sudokuTable[row][col] = value;
    }


     public  boolean compute(int row, int col, int[][] sudoku){

        if (row == 9) {
            row = 0;
            if (++col == 9)
                return true;
        }

        //Skips any non-zero values
        if(!(sudoku[row][col] == 0))
            compute((row + 1), col, sudoku);

        for(int check = 1; check <= 9; check++){
            if(validNumber(row, col, check, sudoku)) {
                sudoku[row][col] = check;
                sudokuTable[row][col] = check;
                if (compute(row + 1, col, sudoku))
                    return true;
            }

        }

        sudoku[row][col] = 0;
        return false;
    }

    private static boolean validNumber(int row, int col, int check, int[][] sudoku){

        //to offset the for the Boxes
        int checkBoxRow = (row / 3)*3;
        int checkBoxCol = (col / 3)*3;

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
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (check == sudoku[checkBoxRow + x][checkBoxCol + y])
                    return false;

        //If all conditions are valid then return true
        return true;
    }

    public void printTable() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(sudokuTable[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

}