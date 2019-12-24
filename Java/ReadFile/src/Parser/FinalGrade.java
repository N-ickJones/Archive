package Parser;

import java.util.*;

public class FinalGrade {
    public static void main(String[] args) {
        double attendance, midterm1, midterm2, finalExam;
        double finalGrade = 0.0;
        ArrayList<Double> assignment = new ArrayList<Double>();


        Scanner kb = new Scanner(System.in);

        //calculate for attendance
        System.out.println("Enter the estimated attendance rate, ranging from 0 (not at all), to 100 (attended every class): ");
        attendance = kb.nextDouble();
        finalGrade += attendance / 100.0 * 5.0;


        //calculate for assignment
        System.out.println("Enter grade for all the 6 assignments. For future assignments, enter your expected grade.\n" +
                "The program will drop the lowest for you");
        double sumAssignment = 0.0;
        for (int i = 0; i < 6; i++) {
            System.out.println("Now enter grade for Assignment " + (i + 1));
            double grade = kb.nextDouble();
            assignment.add(grade);
            sumAssignment += grade;
        }

        sumAssignment -= Collections.min(assignment);
        finalGrade += sumAssignment / 100.0 * (50.0 / 5.0);


        //calculate for midterm 1
        System.out.println("Enter midterm 1 grade: ");
        midterm1 = kb.nextDouble();
        finalGrade += midterm1 / 100.0 * 12.5;


        //calculate for midterm 2
        System.out.println("Enter midterm 2 grade: ");
        midterm2 = kb.nextDouble();
        finalGrade += midterm2 / 100.0 * 12.5;


        //calculate for final
        System.out.println("Enter final exam grade: ");
        finalExam = kb.nextDouble();
        finalGrade += finalExam / 100.0 * 20.0;


        System.out.println("Your final grade is: " + finalGrade);


    }
}