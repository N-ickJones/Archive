 
 
package body MakePlane is

  procedure AssignNumDoors(aPlane: in out Plane; N: in integer) is
  begin 
		aPlane.NumDoors := N;
	end AssignNumDoors;

  procedure AssignManufacturer(aPlane: in out Plane; Manu: in String6) is
  begin 
		aPlane.Manufacturer := Manu; 
	end AssignManufacturer;
 
  procedure AssignNumEngines(aPlane: in out Plane; NE: in integer) is
  begin 
		aPlane.NumEngines := NE; 
	end AssignNumEngines;

  procedure PrintString6(PrtStr: String6) is
  begin 
		for I in 1..6 loop  
			put(PrtStr(I));   
		end loop;
	end PrintString6;
	
	procedure PrintString6(fOut: File_Type; PrtStr: String6) is
  begin 
		for I in 1..6 loop  
			put(fOut, PrtStr(I));   
		end loop;
	end PrintString6;

  procedure Print(aPlane: in Plane) is
  begin 
    put("Plane with "); put(aPlane.NumDoors, 1); put(" doors, ");
    put(aPlane.NumEngines, 1); put(" engines, and made by ");
    PrintString6(aPlane.Manufacturer); new_line;
  end Print;
	
	procedure Print(fOut: File_Type;aPlane: in Plane) is
  begin 
    put(fOut, "Plane with "); put(fOut, aPlane.NumDoors, 1); put(fOut, " doors, ");
    put(fOut, aPlane.NumEngines, 1); put(fOut, " engines, and made by ");
    PrintString6(fOut, aPlane.Manufacturer); put_line(fOut,"");
  end Print;
	
	procedure Find(Direction: in Character; FindPlane: in Plane) is
		Count: Integer := Size;
		Audit: NodePtr;
	begin
		InitPt;
		while Count > 0 loop
			Audit := NextNode(Direction);
			if Audit.all in Plane then
				if Plane'Class(Audit.all).Manufacturer = FindPlane.Manufacturer and
							Plane'Class(Audit.all).NumEngines = FindPlane.NumEngines and
								Plane'Class(Audit.all).NumDoors = FindPlane.NumDoors then
					Count := 0;
				end if;
			end if;
			Count := Count - 1;
		end loop;
		if Count /= -1 then
			InitPt;
		end if;
	end Find;
	
end MakePlane;

