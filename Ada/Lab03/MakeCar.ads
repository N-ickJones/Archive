

with Links; use Links;
with Ada.Text_IO; use Ada.Text_io;

package MakeCar is

  type String5 is new String(1..5);

  type Car is new Node with record
     NumDoors:  integer;
     Manufacturer: String5 := "GMC  ";  -- Sample default value.
  end record;
 
  procedure AssignNumDoors(aCar: in out Car; N: in integer); 
  procedure AssignManufacturer(aCar: in out Car; Manu: in String5); 
  procedure PrintNumDoors(aCar: in Car); 
  procedure PrintManufacturer(aCar: in Car); 
  procedure Print(aCar: in Car);
	procedure Print(fOut: File_Type;aCar: in Car);
	procedure Find(Direction: in Character;FindCar: in Car; level: in integer := 2);
	
end MakeCar;
