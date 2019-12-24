/**
 @Title: JonesNicholasTranslator
 @Purpose: To record Passenger Preferences
 @Author: (Jones, Nicholas)
 @Date: (11/30/2017)
 @Version 1.0
 */

public class Passenger {

    private String name;
    private int row;
    private int seatCode; // (Translate seat code A to seat number 0, B to 1, C to 2, and D to 3)



  public Passenger (String passengerName, int requestedRow, char requestedSeat){
      setName(passengerName);
      setRow(requestedRow);
      setSeatCode(requestedSeat);
  }

  public void setName(String passengerName) {
      name = passengerName;
  }

  public void setRow (int requestedRow){
      row = requestedRow;
  }

  public void setSeatCode(char requestedSeat) {
      if(requestedSeat == 'A' || requestedSeat == 'a')
          seatCode = 0;
      else if(requestedSeat == 'B' || requestedSeat == 'b')
          seatCode = 1;
      else if(requestedSeat == 'C' || requestedSeat == 'c')
          seatCode = 2;
      else if(requestedSeat == 'D' || requestedSeat == 'd')
          seatCode = 3;
  }

  public String getName(){
      return name;
  }

  public int getRow(){
      return row;
  }

  public char getSeatCode(){
      if(seatCode == 0)
          return 'A';
      else if(seatCode == 1)
          return 'B';
      else if(seatCode == 2)
          return 'C';
      else if(seatCode == 3)
          return 'D';
      else
          return '?';
  }
}
