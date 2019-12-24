import java.util.Random;

public class SudokuDemo {

    public static void main(String[] args) {

        int[][] puzzle = new int[][]{
                {9, 2, 7, 3, 6, 5, 1, 4, 8},
                {8, 4, 6, 2, 7, 1, 9, 5, 3},
                {1, 5, 3, 4, 8, 9, 6, 2, 7},
                {6, 8, 5, 1, 2, 3, 4, 7, 9},
                {7, 3, 9, 8, 5, 4, 2, 6, 1},
                {2, 1, 4, 6, 9, 7, 8, 3, 5},
                {3, 9, 1, 7, 4, 6, 5, 8, 2},
                {4, 7, 2, 5, 1, 8, 3, 9, 6},
                {5, 6, 8, 9, 3, 2, 7, 1, 4}};

        int[] holdA = new int[9];
        int[] holdB = new int[9];
        int[] holdC = new int[9];


        Random rand = new Random();
        SudokuGenerator sudokuGen = new SudokuGenerator();

        sudokuGen.generate();        //Generate Table

        shiftRows(puzzle, holdA, holdB, holdC, rand);
        //shiftCols(puzzle, holdA, rand);
        //shiftTrans(puzzle, holdA, rand);

        printPuzzle(a);     //Print Puzzle



    }
