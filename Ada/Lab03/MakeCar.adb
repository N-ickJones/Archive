

package body MakeCar is

  package IntIO is new Ada.Text_IO.Integer_IO(Integer);  use IntIO;

  procedure AssignNumDoors(aCar: in out Car; N: in integer) is
  begin 
		aCar.NumDoors := N; 
	end AssignNumDoors;

  procedure AssignManufacturer(aCar: in out Car; Manu: in String5) is
  begin 
		aCar.Manufacturer := Manu; 
	end AssignManufacturer;
 
  procedure PrintNumDoors(aCar: in Car) is
  begin 
		put("Num doors = "); put(aCar.NumDoors); new_line; 
	end PrintNumDoors;

  procedure PrintString5(PrtStr: String5) is
  begin for I in 1..5 loop 
     put(PrtStr(I)); 
  end loop; end PrintString5;
	
	procedure PrintString5(fOut: File_Type;PrtStr: String5) is
  begin for I in 1..5 loop 
     put(fOut, PrtStr(I)); 
  end loop; end PrintString5;

  procedure PrintManufacturer(aCar: in Car) is
  begin 
		put("Manufacturer is "); PrintString5(aCar.Manufacturer); new_line; 
	end;

  procedure Print(aCar: in Car) is
  begin 
    put("Car with "); put(aCar.NumDoors, 1); put(" doors"); 
    put(" and made by "); PrintString5(aCar.Manufacturer); new_line;
  end Print;
	
	procedure Print(fOut: File_Type;aCar: in Car) is
  begin 
    put(fOut, "Car with "); put(fOut, aCar.NumDoors, 1); put(fOut, " doors"); 
    put(fOut, " and made by "); PrintString5(fOut, aCar.Manufacturer); put_line(fOut,"");
  end Print; 
	
	procedure Find(Direction: in Character;FindCar: in Car; level: in integer := 2) is
		Count: integer := Size;
		Audit: NodePtr;
	begin
		InitPt;
		while Count > 0 loop
			Audit := NextNode(Direction);
			if Audit.all in Car then
				if Car'Class(Audit.all).Manufacturer = FindCar.Manufacturer then
					if level > 1 and Car'Class(Audit.all).NumDoors = FindCar.NumDoors	then
						Count := 0;
					elsif level = 1 then
						Count := 0;
					end if;
				end if;
			end if;
			Count := Count - 1;
		end loop;
		if Count /= -1 then
			InitPt;
		end if;
	end Find;
	
end MakeCar;
