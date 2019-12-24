
with Ada.Text_IO; use Ada.Text_io; 
with Links; use Links;

package MakePlane is
	package IntIO is new Ada.Text_IO.Integer_IO(Integer);  use IntIO;
	
  type String6 is new String(1..6);

  type Plane is new Node with record
     NumDoors:  integer;
     NumEngines:  integer;
     Manufacturer: String6 := "Boeing";
  end record;
 
  procedure AssignNumDoors(aPlane: in out Plane; N: in integer); 
  procedure AssignManufacturer(aPlane: in out Plane; Manu: in String6); 
  procedure AssignNumEngines(aPlane: in out Plane; NE: in integer); 
  procedure Print(aPlane: in Plane);
	procedure Print(fOut: File_Type; aPlane: in Plane);
	procedure Find(Direction: in Character; FindPlane: in Plane);
	
end MakePlane;
