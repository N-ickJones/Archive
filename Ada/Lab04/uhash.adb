--Filename:  uHash.adb
--Author:    Nicholas Jones
--Date:	     04/09/2019
--Purpose:   Hashing Lab
--Version:   1.0
--Reference: Dr. Burris Notes
with Ada.Unchecked_Conversion;
with Ada.Text_IO; use Ada.Text_IO;
with Linear_Hashing;
with Random_Hashing;
with Ada.Numerics.Generic_Elementary_Functions;

procedure uHash is
	package IntIO is new Integer_IO(Integer); use IntIO;
	package FloatIO is new Float_IO(Float); use FloatIO;
	package FloatFunc is new Ada.Numerics.Generic_Elementary_Functions(Float);
	use FloatFunc;

	-- Designed for 32 Bit Integers and 64 Bit Long_Integer on Machine
	function String_To_Integer_Conversion is new
		Ada.Unchecked_Conversion (Source => String, Target => Integer);
		function String_To_Linteger_Conversion is new
			Ada.Unchecked_Conversion (Source => String, Target => Long_Integer);

	type DataEntry is record
		Key: String(1..17) := (others => ' ');
		Hash: Integer := 0;
		Probes: Integer := 0;
	end record;

	procedure SetProbe(X: in out DataEntry; Y: Integer) is
	begin
		X.Probes := Y;
	end;

	function "="(X: DataEntry; Y: DataEntry) return Boolean is
	begin
		if X.Key = Y.Key then
			Return True;
		else
			Return False;
		end if;
	end;

	Table_Power: Integer := 7;
	Table_Size: Integer := 2**Table_Power;
	Input_File, Output_File: File_Type;
	Temp: array(1..4) of Integer;

	package LinearHashTable is new Linear_Hashing(DataEntry, Table_Size,
	 Input_File, SetProbe, "="); use LinearHashTable;
	package RandomHashTable is new Random_Hashing(DataEntry, Table_Power,
	 Table_Size, Input_File, SetProbe, "="); use RandomHashTable;

	function String_To_Integer(S: String) return Integer is
	begin
		return (String_To_Integer_Conversion(S) mod 2**16);
	end;

	function String_To_Integer(C: Character) return Integer is
	begin
		return (String_To_Integer_Conversion(C'Image) mod 2**16);
	end;

	function Calc_MyHash(Key: String) return Integer is
		HA: Integer;
	begin
		if Integer'Last = 2**31-1 then --32 Bit Signed Integer
			HA := String_To_Integer_Conversion(Key(Key'First+0..Key'First+4));
			Temp(1) := HA / 2**24 mod 2**8; --LeftMost 8 bits
			Temp(2) := HA / 2**16 mod 2**8; --MidLeft 8 bits
			Temp(3) := HA / 2**8  mod 2**8; --MidRight 8 bits
			Temp(4) := HA / 2**0  mod 2**8; --RightMost 8 bits
			HA := 5;
			for i in Temp'Range loop
				HA := HA + Temp(i) * i;
			end loop;
			HA := HA mod Table_Size-1;
			return HA;
		elsif Integer'Last = 2**15-1 then --16 Bit Signed Integer
		  put("16 Bit Signed Integers used by Compiler Please Implement");
			return 0;
		elsif Integer'Last = 2**63-1 then --64 Bit Signed Integer
			put("64 Bit Signed Integers used by Compiler Please Implement");
			return 1;
		else
			--put("Unknown Integer Value Being used");
			return 2;
		end if;
	end;

	function Calc_Hash(Key: String) return Integer is
		HA: Integer; --32 Bits
	begin --Need to cancel out leftmost 16 bits
		HA := abs(((((
			String_To_integer(Key(Key'First+3..Key'First+4)) -
			String_To_integer(Key(Key'First..Key'First+1))) / 256 +
			String_To_integer(Key(Key'First+2)'Image)) / 256 +
			String_To_integer(Key(Key'First)'Image)) mod 128)
		);
    -- put(Key);new_line;
		-- put(String_To_integer(Key(Key'First+3..Key'First+4)), 6);put("[4,5]");
		-- put(" - ");
		-- put(String_To_integer(Key(Key'First..Key'First+1)), 6);put("[1,2]");
		-- put(" / 256 + ");
		-- put(String_To_integer(Key(Key'First+2)), 6);put("[3]");
		-- put(" / 256 + ");
		-- put(String_To_integer(Key(Key'First)'Image), 6);put("[1]");
		-- put(" mod 128");
		-- new_line;
		--put(String_To_integer(Key(Key'First+3..Key'First+4)) - String_To_integer(Key(Key'First..Key'First+1)));put(" After - ");
		--put((String_To_integer(Key(Key'First+3..Key'First+4)) - String_To_integer(Key(Key'First..Key'First+1))) / 256);put(" After /256 ");
		--put(((String_To_integer(Key(Key'First+3..Key'First+4)) - String_To_integer(Key(Key'First..Key'First+1))) / 256) + (String_To_integer(Key(Key'First+2)) / 256));put(" After Next + and /256 ");
		--put(String_To_integer(Key(Key'First+3..Key'First+4)) - String_To_integer(Key(Key'First..Key'First+1)) / 256 + String_To_integer(Key(Key'First+2)'Image) / 256);put(" After Next + and /256 + [1] ");
    --put((String_To_integer(Key(Key'First+3..Key'First+4)) - String_To_integer(Key(Key'First..Key'First+1)) / 256 + String_To_integer(Key(Key'First+2)'Image) / 256) mod 128);put(" After Next + and /256 + [1] ");

		--new_line;

		return HA;
	end;

	procedure ReadIn(Input_File: in out File_Type;Percent: float;
	 									Probe_Type: Character := 'L'; My_Hash: Integer := 0) is
		Input: DataEntry;
		Count: Natural := 1;
	begin
		Open(Input_File, In_File, "2057357.sql");
			while not End_Of_File(Input_File) loop
				get(Input_File, Input.Key);
				if My_Hash = 0 then
					Input.Hash := Calc_Hash(Input.Key);
				else
					Input.Hash := Calc_MyHash(Input.Key);
				end if;
				if Probe_Type = 'L' then
					LinearHashTable.Insert(Input, Input.Hash);
				elsif Probe_Type = 'R' then
					RandomHashTable.Insert(Input, Input.Hash);
				end if;
				Count := Count + 1;
				if Count > integer(float(Table_Size) * Percent) then
					exit;
				end if;
			end loop;
		Close(Input_File);
	end;

	procedure Print(Output_File: File_Type;Amount: Integer;
									Probe_Type: Character := 'L') is
		Output: DataEntry;
		Index: Integer := 0;
		Count: Integer := Amount;
		KeyTest: String(1..17) := (others => ' ');
		Check: Integer := Table_Size;
	begin
		while Count > 0 and Check > 0 loop
			if Probe_Type = 'L' then
				Index := Index + 1;
				Output := LinearHashTable.Get(Index);
			elsif Probe_Type = 'R' then
				Index := Index + 1;
				Output := RandomHashTable.Get(Index);
			end if;
			if Output.Key /= KeyTest then
				put(Output_File, "      Index[");
				put(Output_File, Index, 3);
				put(Output_File, "]");
				put(Output_File, " Hash[");
				put(Output_File, Output.Hash, 3);
				put(Output_File, "] Probes[");
				put(Output_File, Output.Probes, 3);
				put(Output_File, "] Key[");
				put(Output_File, Output.Key & "]");
				put_line(Output_File, "");
			else
				put(Output_File, "      Index[");
				put(Output_File, Index, 3);
				put(Output_File, "]");
				put(Output_File, " Empty");
				put_line(Output_File, "");
			end if;
			Count := Count - 1;
		end loop;
	end;

	procedure Get_Probes(Output_File: File_Type;Amount: Integer;
											Direction: Integer := 0; Probe_Type: Character := 'L') is
		Output: DataEntry;
		Max: Integer := 0;
		Min: Integer := Table_Size + 1;
		Avg: Float := 0.0;
		Index: Integer;
		Count: Integer := Amount;
		KeyTest: String(1..17) := (others => ' ');
		Check: Integer := Table_Size;
	begin
		if Direction = 0 then
			Index := 0;
		else
			Index := Table_Size;
		end if;
		while Count > 0 and Check > 0 loop
			if Probe_Type = 'L' then
				if Direction = 0 then
					Index := Index + 1;
					Output := LinearHashTable.Get(Index);
				else
					Output := LinearHashTable.Get(Index);
					Index := Index - 1;
				end if;
			elsif Probe_Type = 'R' then
				if Direction = 0 then
					Index := Index + 1;
					Output := RandomHashTable.Get(Index);
				else
					Output := RandomHashTable.Get(Index);
					Index := Index - 1;
				end if;
			end if;
			if Output.Key /= KeyTest then
				if Output.Probes > Max then
					Max := Output.Probes;
				elsif Output.Probes < Min and Output.Probes /= 0 then
					Min := Output.Probes;
				end if;
				Avg := Avg + float(Output.Probes);
				Count := Count - 1;
			end if;
			Check := Check - 1;
		end loop;
		Avg := Avg / float(Amount);
		put(Output_File, "          Minimum Probes = ");
		put(Output_File, Min, 3);put_Line(Output_File, "");
		put(Output_File, "          Maximum Probes = ");
		put(Output_File, Max, 3);put_Line(Output_File, "");
		put(Output_File, "          Average Probes = ");
		put(Output_File, Avg, Fore => 3, Aft => 3, Exp => 0);
		put_Line(Output_File, "");
	end Get_Probes;

	procedure Calc_Theo(Output_File: File_Type; Perc: Float; Probe: Character) is
		a: float := (float(Table_Size) * Perc) / float(Table_Size);
		E: float;
	begin
		if Probe = 'L' then
			E := (1.0 - a / 2.0) / (1.0 - a);
			put(Output_File, "      Theoretically the result should be");
			put(Output_File, E, Fore => 3, Aft => 3, Exp => 0);
			put_line(Output_File, " probes to locate key.");
		else
			E := -(1.0/a) * Log(Base => 2.71828, X => (1.0-a));
			put(Output_File, "      Theoretically the result should be");
			put(Output_File, E, Fore => 3, Aft => 3, Exp => 0);
			put_line(Output_File, " probes to locate a key.");
		end if;
	end Calc_Theo;

begin
	LinearHashTable.Open;
	Create(Output_File, Out_File, "Report.txt");
	  --Start Burris Algorithm
		--Start Linear
		ReadIn(Input_File, 0.50, 'L');
			put_line(Output_File, "--Burris Hash Algorithm--");
			put_line(Output_File, "  Linear Probe Results");
			put_line(Output_File, "    Start of 50% Full Table");
			put_line(Output_File, "      First 30 Key Entries");
			Get_Probes(Output_File, 30, 0, 'L');
			put_line(Output_File, "      Last 30 Key Entries");
			Get_Probes(Output_File, 30, 1, 'L');
			Print(Output_File, Table_Size, 'L');
			Calc_Theo(Output_File, 0.50, 'L');
		LinearHashTable.Clear;
			ReadIn(Input_File, 0.90, 'L');
			put_line(Output_File, "    Start of 90% Full Table");
			put_line(Output_File, "      First 30 Key Entries");
			Get_Probes(Output_File, 30, 0, 'L');
			put_line(Output_File, "      Last 30 Key Entries");
			Get_Probes(Output_File, 30, 1, 'L');
			Print(Output_File, Table_Size, 'L');
			Calc_Theo(Output_File, 0.90, 'L');
		LinearHashTable.Close;
		--Start Random
		RandomHashTable.Open;
			ReadIn(Input_File, 0.50, 'R');
			put_line(Output_File, "  Random Probe Results");
			put_line(Output_File, "    Start of 50% Full Table");
			put_line(Output_File, "      First 30 Key Entries");
			Get_Probes(Output_File, 30, 0, 'R');
			put_line(Output_File, "      Last 30 Key Entries");
			Get_Probes(Output_File, 30, 1, 'R');
			Print(Output_File, Table_Size, 'R');
			Calc_Theo(Output_File, 0.50, 'R');
		RandomHashTable.Clear;
			ReadIn(Input_File, 0.90, 'R');
			put_line(Output_File, "    Start of 90% Full Table");
			put_line(Output_File, "      First 30 Key Entries");
			Get_Probes(Output_File, 30, 0, 'R');
			put_line(Output_File, "      Last 30 Key Entries");
			Get_Probes(Output_File, 30, 1, 'R');
			Print(Output_File, Table_Size, 'R');
			Calc_Theo(Output_File, 0.90, 'R');
		 RandomHashTable.Close;

		--Start My Hash
		--Start Linear
		LinearHashTable.Open;
		LinearHashTable.Clear;
		ReadIn(Input_File, 0.50, 'L', 1);
			put_line(Output_File, "--My Hash Algorithm--");
			put_line(Output_File, "  Linear Probe Results");
			put_line(Output_File, "    Start of 50% Full Table");
			put_line(Output_File, "      First 30 Key Entries");
			Get_Probes(Output_File, 30, 0, 'L');
			put_line(Output_File, "      Last 30 Key Entries");
			Get_Probes(Output_File, 30, 1, 'L');
			Print(Output_File, Table_Size, 'L');
			Calc_Theo(Output_File, 0.50, 'L');
		LinearHashTable.Clear;
			ReadIn(Input_File, 0.90, 'L', 1);
			put_line(Output_File, "    Start of 90% Full Table");
			put_line(Output_File, "      First 30 Key Entries");
			Get_Probes(Output_File, 30, 0, 'L');
			put_line(Output_File, "      Last 30 Key Entries");
			Get_Probes(Output_File, 30, 1, 'L');
			Print(Output_File, Table_Size, 'L');
			Calc_Theo(Output_File, 0.90, 'L');
		LinearHashTable.Close;
		--Start Random
		RandomHashTable.Open;
		RandomHashTable.Clear;
			ReadIn(Input_File, 0.50, 'R', 1);
			put_line(Output_File, "  Random Probe Results");
			put_line(Output_File, "    Start of 50% Full Table");
			put_line(Output_File, "      First 30 Key Entries");
			Get_Probes(Output_File, 30, 0, 'R');
			put_line(Output_File, "      Last 30 Key Entries");
			Get_Probes(Output_File, 30, 1, 'R');
			Print(Output_File, Table_Size, 'R');
			Calc_Theo(Output_File, 0.50, 'R');
		RandomHashTable.Clear;
			ReadIn(Input_File, 0.90, 'R', 1);
			put_line(Output_File, "    Start of 90% Full Table");
			put_line(Output_File, "      First 30 Key Entries");
			Get_Probes(Output_File, 30, 0, 'R');
			put_line(Output_File, "      Last 30 Key Entries");
			Get_Probes(Output_File, 30, 1, 'R');
			Print(Output_File, Table_Size, 'R');
			Calc_Theo(Output_File, 0.90, 'R');
		 RandomHashTable.Close;


	Close(Output_File);
end uHash;
