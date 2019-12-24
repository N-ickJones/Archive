package body MyItem is

	procedure AssignValue(aItem: in out Item; N: in integer) is
  begin 
		aItem.Value := N; 
	end AssignValue;

	procedure Print(aItem: in Item) is
	begin
		put("Item with ");put(aItem.Value, 2);new_line;
	end Print;
	
	procedure Print(fOut: File_Type;aItem: in Item) is
	begin
		put(fOut, "Item with ");put(fOut, aItem.Value, 2);put_line(fOut,"");
	end Print;
	
	procedure Find(Direction: in Character; FindItem: in Item) is
		Count: Integer := Size;
		Audit: NodePtr;
	begin
		InitPt;
		while Count > 0 loop
			Audit := NextNode(Direction);
			if Audit.all in Item then
				if Item'Class(Audit.all).Value = FindItem.Value then
					Count := 0;
				end if;
			end if;
			Count := Count - 1;
		end loop;
		if Count /= -1 then
			InitPt;
		end if;
	end Find;

end MyItem;