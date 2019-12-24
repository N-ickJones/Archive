/**
@Title: JonesNicholasTranslator
@Purpose: To make a reservation array list
@Author: (Jones, Nicholas)
@Date: (11/30/2017)
@Version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JonesNicholasReservations {

    public static void main(String[] args) {

        Boolean check = true, initilize = true;
        String cont, name;
        int row, counter = 0;
        char seat;

        List<Passenger> passengerList = new ArrayList<Passenger>();

        Scanner sc = new Scanner(System.in);

        Plane plane = new Plane(21, 4);

        do {
            //Takes Name Row and Seat Input
            System.out.print("Passenger Name: ");
            name = sc.nextLine();

            System.out.print("Passenger Row: ");
            row = sc.nextInt();

            System.out.print("Passenger Seat: ");
            seat = sc.next().charAt(0);

            //Updates Entries Based on Passenger Name
            for (int i = 0; i < passengerList.size(); i++) {

                if (passengerList.get(i).getName().equals(name)) {
                    passengerList.get(i).setRow(row);
                    passengerList.get(i).setSeatCode(seat);
                    check = true;
                    counter--;
                    if (plane.makeReservation(counter, passengerList.get(counter).getRow(), passengerList.get(counter).getSeatCode()))
                        System.out.println("Reservation Successful");
                    else
                        System.out.println("Reservation Unsucessful");
                }
            }
            //Adds Entry if it doesn't already exist
            if (!check) {
                passengerList.add(new Passenger(name, row, seat));
                if (plane.makeReservation(counter, passengerList.get(counter).getRow(), passengerList.get(counter).getSeatCode()))
                    System.out.println("Reservation Successful");
                else
                    System.out.println("Reservation Unsucessful");
            } else
                check = false;

            //Adds First Entry Only
            if (initilize) {
                passengerList.add(new Passenger(name, row, seat));
                initilize = false;
                check = false;
                if (plane.makeReservation(counter, passengerList.get(counter).getRow(), passengerList.get(counter).getSeatCode()))
                    System.out.println("Reservation Successful");
                else
                    System.out.println("Reservation Unsucessful");
            }

            counter++;
            //To clear Input
            sc.nextLine();

            //Ask User if they Want to Continue
            System.out.print("Type 0 to Continue: ");
            cont = sc.nextLine();
        } while (cont.equals("0"));

        //Prints seats array of the Plane Object
        System.out.println("Reserved Seats on the Plane");
        System.out.println("---------------------------");
        for (int i = 0; i < passengerList.size(); i++)
            System.out.println(passengerList.get(i).getName()+ " " + plane.getSeatAssignment(i));
        System.out.println("---------------------------");
    }
}
