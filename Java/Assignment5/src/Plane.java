/**
 @Title: JonesNicholasTranslator
 @Purpose: To hold the Plane data
 @Author: (Jones, Nicholas)
 @Date: (11/30/2017)
 @Version 1.0
 */

public class Plane {

    private int[][] seats;



    public Plane (int rows, int cols){

        seats = new int[rows][cols];

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                seats[i][j] = -1;


    }

    public boolean makeReservation(int passengerID, int passengerRow, char passengerSeat) {

        int passengerCol = 0;

        if(passengerSeat == 'a')
            passengerCol = 0;
        if(passengerSeat == 'A')
            passengerCol = 0;
        if(passengerSeat == 'b')
            passengerCol = 1;
        if(passengerSeat == 'B')
            passengerCol = 1;
        if(passengerSeat == 'c')
            passengerCol = 2;
        if(passengerSeat == 'C')
            passengerCol = 2;
        if(passengerSeat == 'd')
            passengerCol = 3;
        if(passengerSeat == 'D')
            passengerCol = 3;

        if(seats[passengerRow][passengerCol] == -1){
            seats[passengerRow][passengerCol] = passengerID;
            return true;
        }

        else
            return false;

    }

    public int getID(int setRow, char seatCode) {

        int passengerCol = 0;

        if(seatCode == 'a')
            passengerCol = 0;
        if(seatCode == 'A')
            passengerCol = 0;
        if(seatCode == 'b')
            passengerCol = 1;
        if(seatCode == 'B')
            passengerCol = 1;
        if(seatCode == 'c')
            passengerCol = 2;
        if(seatCode == 'C')
            passengerCol = 2;
        if(seatCode == 'd')
            passengerCol = 3;
        if(seatCode == 'D')
            passengerCol = 3;

        return seats[setRow][passengerCol];

    }

    public String getSeatAssignment(int passengerID) {

        for(int i = 0; i < seats.length; i++){

            for(int j = 0; j < seats[0].length; j++) {
                if(passengerID == seats[i][j]){

                    if(j == 0)
                        return (Integer.toString(i) + "A");
                    if(j == 1)
                        return (Integer.toString(i) + "B");
                    if(j == 2)
                        return (Integer.toString(i) + "C");
                    if(j == 3)
                        return (Integer.toString(i) + "D");


                }

            }
        }
        return "";

    }


}
