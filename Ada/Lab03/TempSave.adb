	procedure Insert_Right(Input: in Object) is
		temp: NodePtr;
	begin
		if Head = Null then
			Head := new HeadNode'(Node with 0);
			Head.LLink := Head;
			Head.RLink := Head;
			Ip := new DataNode'(Node'(Head, Head) with Input);
			Head.LLink := Ip;
			Head.RLink := Ip;
		else
			Pt := Head.RLink;
			Ip := new DataNode'(Node'(Pt.LLink, Pt) with input);
			Pt.LLink.RLink := Ip;
			Pt.LLink := Ip;
		end if;
		Print(1, Count, 1);
		Count := Count + 1;
	end Insert_Right;
	
	
	--fOut: in out file_type; --outputFile: in out file_type;X: Object);


	procedure Insert_Left(Input: in Object) is
	begin
		if Pt = Null then
			Pt := New Node'(NodeElement'(Pt, Pt) with input);
			Pt.LLink := Pt;
			Pt.RLink := Pt;
		elsif Head.Count = 1 then
			Ip := new Node'(NodeElement'(Pt, Pt) with input);
			Pt.LLink := Ip;
			Pt.RLink := Ip;
			Pt := Pt.LLink;
		elsif Head.Count > 1 then
			Ip := new Node'(NodeElement'(Pt.LLink, Pt) with input);
			Pt.LLink.RLink := Ip;
			Pt.LLink := Ip;
			Pt := Pt.LLink;
		end if;
		Head.Count := Head.Count + 1;
	end Insert_Left;
	
	procedure Insert_Right(Input: in Object) is
	begin
		if Pt = Null then
			Pt := New Node'(NodeElement'(Pt, Pt) with input);
			Pt.LLink := Pt;
			Pt.RLink := Pt;
		elsif Head.Count = 1 then
			Ip := new Node'(NodeElement'(Pt, Pt) with input);
			Pt.LLink := Ip;
			Pt.RLink := Ip;
			Pt := Pt.LLink;
		elsif Head.Count > 1 then
			Ip := new Node'(NodeElement'(Pt, Pt.RLink) with input);
			Pt.RLink.LLink := Ip;
			Pt.RLink := Ip;
			Pt := Pt.RLink;
		end if;
		Head.Count := Head.Count + 1;
	end Insert_Right;
	
	procedure Delete(Output: out Object) is
	begin
		if Pt = Null then
			put("Underflow Occurred");new_line;
		elsif Head.Count = 1 then
			Output := Node'Class(Pt.All).MyData;
			Free(Pt);
			Pt := Null;
			Head.Count := Head.Count - 1;
		elsif Head.Count > 1 then
			Dp := Pt;
			Pt := Pt.LLink;
			Dp.RLink.LLink := Dp.LLink;
			Dp.LLink.RLink := Dp.RLink;
			Output := Node'Class(Dp.All).MyData;
			Free(Dp);
			Head.Count := Head.Count - 1;
		end if;
	end Delete;
	
	procedure Length(Output: Out Integer) is
	begin
		Output := Head.Count;
	end Length;
	
	procedure Find(Input: In Object) is 
		temp: integer := Head.Count;
		check: integer := 0;
		Tp: NodeElementPtr := Pt;
	begin
		if Pt /= Null then
			while temp > 0 loop
				if Node'Class(Tp.All).MyData = Input then
					check := 1;
					temp := 0;
				else
					temp := temp -1;
				end if;
			end loop;
			if check = 1 then
				put("Match Found");new_line;
			else
				put("Match Not Found");new_line;
			end if;
		else
			put("Empty List");new_line;
		end if;
	end Find;
	
	procedure Print(start: integer; finish: integer; direction: integer := 1) is
		temp: NodeElementPtr;
	begin
		
		--if Pt /= Null then
			if direction = 1 then
				temp := Head.RLink;
				for i in start..finish loop
					temp := temp.RLink;
					putObject(Node'Class(temp.All).MyData);
				end loop;
			else
				temp := Head.LLink;
				for i in start..finish loop
					temp := temp.LLink;
					putObject(Node'Class(temp.All).MyData);
				end loop;
			end if;
		--else
			--put("Empty List");new_line;
		--end if;
	end Print;

	
	

	procedure Test2(Input: in Object) is
		temp: NodeElementPtr;
	begin
		--Insert Right
		
		if Head2 = Null then
			Head2 := new NodeHead2;
			Head2.LLink := Head2;
			Head2.RLink := Head2;
			--Ip := new Node'(NodeElement'(Head2.RLink, Head2.LLink) with Input);
			--Head2.LLink := Ip;
			--Head2.RLink := Ip;
		else
			--Pt := Head2.RLink;
			--Ip := new Node'(NodeElement'(Pt.LLink, Pt) with input);
			--Pt.LLink.RLink := Ip;
			--Pt.LLink := Ip;
			put("");
		end if;
		temp := Head2.RLink;
		for i in 1..1 loop --Items in List
			--putObject(Node'Class(temp.All).MyData);
			temp := temp.RLink;
		end loop;
		new_line;
		Count := Count + 1;	
	end Test2;
	
	procedure TestLeft is
		x: integer;	
	begin
		test.Insert_Left(6);
		test.Insert_Left(7);
		test.Insert_Left(8);
		test.Insert_Left(9);
		test.Insert_Left(10);
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);
		test.Length(x);
		put("Count is = ");put(x, 2);new_line;
		test.find(2);
	end TestLeft;
	
	procedure TestLR is --Note:(1)[6,8,10,9,7]--(2)[7,9,10,8,6]
		x: integer;	
	begin
		test.Insert_Left(6);
		test.Insert_Right(7);
		test.Insert_Left(8);
		test.Insert_Right(9);
		test.Insert_Left(10);
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 2);
		test.Length(x);
		put("Count is = ");put(x, 2);new_line;
		test.find(2);
	end TestLR;
	
	procedure TestRL is --Note:(1)[7,9,10,8,6]--(2)[6,8,10,9,7]
		x: integer;	
	begin
		test.Insert_Right(6);
		test.Insert_Left(7);
		test.Insert_Right(8);
		test.Insert_Left(9);
		test.Insert_Right(10);
		test.Print(1, 10, 1);
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 1);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 1);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 1);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 1);new_line;
		test.Delete(x);
		put("Deleting ");put(x, 2);new_line;
		test.Print(1, 10, 1);
		test.Length(x);
		put("Count is = ");put(x, 2);new_line;
		test.find(2);
	end TestRL;
	
	procedure TestCOption is
		x: integer;
	begin
		test.Insert_Right(33);
		test.Insert_Right(57);
		test.Insert_Left(85);
		test.Insert_Left(62);
		test.Insert_Right(95);
		test.Length(x);
		test.Print(1, x, 1);new_line;
		--test.Print(1, x, 0);new_line;
		
		--test.Find(57);
		--test.Delete(57);
		
		--test.Find(33);
		--test.Delete(33);
		
		--test.Find(33);
		--test.Delete(33);
		
		--test.Find(62);
		--test.Delete(62);
		
		--test.Insert_Right(22);
		
		--test.Delete(95);
		
		--test.Length(x);
		--print(1, x, 1);new_line;
		
		
	end TestCOption;
	
	procedure TestBOption is
	begin
		put("");
		--Insert  Ford, 4 doors, Left
		--Insert  Ford, 2 doors, Right
		--Insert   GMC, 2 doors, Left
		--Insert   RAM, 2 doors, Rear
		--Insert Chevy, 3 doors, Front
		-- Print Length
		-- Print Content Left -> Right
		-- Find and Delete First Ford in the list (Search Right to Left)
		-- Print Length
		-- Print Contents (Front to Rear)
		--Insert Plane, 3 doors, 6 engines, Boeing, Left
		--Insert Plane, 2 doors, 1 engines,  Piper, Left
		--Insert Plane, 4 doors, 4 engines, Cessna, Left
		-- Print List
		--print(1, 2, 1);new_line;
	end TestBOption;
	
		--procedure putObject(output: integer) is 
	--	begin 
	--		put(output, 3);--new_line;
	--	end putObject;
	
	procedure Print(start: integer; finish: integer; direction: integer := 1) is
		temp: NodePtr;
	begin
		if direction = 1 then
			temp := Head.RLink;
			for i in 1..Count loop --Items in List
				--if temp /= Head then
					--putObject(DataNode'Class(temp.All).MyData);
				--end if;
				temp := temp.RLink;
			end loop;
		--new_line;
		else
			temp := Head.LLink;
			for i in 1..Count loop --Items in List
				--if temp /= Head then
					--putObject(DataNode'Class(temp.All).MyData);
				--end if;
				temp := temp.LLink;
			end loop;
		--new_line;
		end if;
	end Print;	
	
	