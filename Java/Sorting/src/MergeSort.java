/*
    Recursive (Calls itself)
    Divide and Conquer Algorithm
    Very efficient for large data sets
    O(n log n)
*/

import java.util.Random;

public class MergeSort {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args){
/*
        int[] list = new int[8];

        Random random = new Random();
        for(int i = 0; i < list.length; i++)
            list[i] = random.nextInt(50);
*/
        int[] list = new int[8];

        list[0] = 42;
        list[1] = 23;
        list[2] = 10;
        list[3] = 64;
        list[4] = 55;
        list[5] = 37;
        list[6] = 96;
        list[7] = 7;

        for(int i = 0; i < list.length; i++) {
            sb.append(list[i]);
            sb.append(" ");
        }

        System.out.println(sb.toString() + " Original");

        mergeSort(list);

        sb = new StringBuilder();

        for(int i = 0; i < list.length; i++){
            sb.append(list[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString() + " Final");
    }

    private static void mergeSort(int[] array){
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort (int[] list, int lowIndex, int highIndex) {

        if (lowIndex >= highIndex)
            return;
        else {

            int midIndex = (lowIndex + highIndex) / 2;
            mergeSort(list, lowIndex, midIndex);                //low Half
            mergeSort(list, midIndex + 1, highIndex);  //high Half
            merge(list, lowIndex, midIndex, highIndex);
            System.out.println("-------------------------------");
        }
    }

    private static void merge(int[] list, int lowIndex, int midIndex, int highIndex) {

        int[] L = new int[midIndex - lowIndex + 2];

        for (int i = lowIndex; i <= midIndex; i++) {
            L[i - lowIndex] = list[i];
        }
        L[midIndex - lowIndex + 1] = Integer.MAX_VALUE;

        ////////////////////////////////////////////////////////////////////

        int[] R = new int[highIndex - midIndex + 1];

        for (int i = midIndex + 1; i <= highIndex; i++) {
            R[i - midIndex - 1] = list[i];
        }
        R[highIndex - midIndex] = Integer.MAX_VALUE;


        //////////////////////////////////////////////////////////////////

        int i = 0, j = 0;

        for (int k = lowIndex; k <= highIndex; k++) {
            if (L[i] <= R[j]) {
                list[k] = L[i];
                i++;
            }
            else {
                list[k] = R[j];
                j++;
            }
            System.out.print(list[k] + " ");
        }
        System.out.println();



        ////////////////////////////////////////////////////////////////
    }
}