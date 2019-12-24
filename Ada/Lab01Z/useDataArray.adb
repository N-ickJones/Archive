--Filename:  useDataArray.adb
--Author:    Nicholas Jones
--Date:	     02/21/2019
--Purpose:   Processes C & B Option Data with generic dataArray
--Version:   2.0
--Reference: Dr. Burris Notes
with ada.text_io; use ada.text_io;
with dataArray;

procedure useDataArray is
	type Month_Name is (January, February, March, April, May, June,
											July, August, September, October, November, December);
	type Date is record
		month: Month_Name;
		day: Integer range 1..31;
		year: Integer range 1400..2022;
	end record;
	type subscript is range 1..100;
	
	package Integer_IO is new Ada.Text_IO.Integer_IO(integer); use Integer_IO;
  package Month_Name_IO is new Ada.Text_IO.enumeration_IO(Month_Name); use Month_Name_IO;
	package Subscript_IO is new Ada.Text_IO.enumeration_IO(subscript); use Subscript_IO;
  package createArray is new dataArray(subscript, Date); use createArray;
	
	arr: userDefinedArray(subscript);
	length, size: integer;
	inFile, outFile: String(1..30);
	
	procedure getUserInput(S: out string) is
	begin
		get_line(S, size);
		S(size+1 .. S'Last) := (others => ' ');
	end getUserInput;
	
	procedure fill_Dates(filename: in String) is
		inputFile: file_type;
		count: integer := 1;
	begin
		open(inputFile, In_File, filename);
			while not End_Of_File(inputFile) loop
				get(inputFile, arr(subscript'val(count)).rec.month);
				get(inputFile, arr(subscript'val(count)).rec.day);
				get(inputFile, arr(subscript'val(count)).rec.year);
				-- Convert Date to Integer
				arr(subscript'val(count)).key := (
				integer(arr(subscript'val(count)).rec.year) * 372 + --372 is 12 * 31
				(Month_Name'Pos(arr(subscript'val(count)).rec.month) + 1) * 31  + 
				integer(arr(subscript'val(count)).rec.day));
				-- End Convert
				count := count + 1;
			end loop;
		close(inputFile);
		length := count - 1;
	end fill_Dates;
	
	procedure print_Dates(filename: in String;selection: in integer) is
		outputFile: file_type;
	begin
		if selection = 1 then
			create(outputFile, Out_File, filename);
			put_line(outputFile, "Report For Management");
			for i in 1..length loop
				put_line(outputFile, "");
				put(outputFile, arr(subscript'val(i)).rec.month, 10);
				put(outputFile, arr(subscript'val(i)).rec.day, 4);
				put(outputFile, arr(subscript'val(i)).rec.year, 6);
			end loop;
		elsif selection = 2 then
			open(outputFile, Append_File, filename);
			for i in 1..length loop
				put_line(outputFile, "");
				put(outputFile, arr(subscript'val(i)).rec.month, 10);
				put(outputFile, arr(subscript'val(i)).rec.day, 4);
				put(outputFile, arr(subscript'val(i)).rec.year, 6);
			end loop;
			
		end if;
		close(outputFile);
	end print_Dates;
	
	procedure fill_Int(filename: in String) is
		inputFile: file_type;
		count: integer := 1;
	begin
		open(inputFile, In_File, filename);
			while not End_Of_File(inputFile) loop
				get(inputFile, arr(subscript'val(count)).key);
				count := count + 1;
			end loop;
		close(inputFile);
		length := count - 1;
	end fill_Int;
	
	procedure print_Int(filename: in String;selection: in integer;padding: in integer) is
		outputFile: file_type;
		count: integer := 0;
		offset: integer := 1;
	begin
		if selection = 1 then
			create(outputFile, Out_File, filename);
			put_line(outputFile, "Report For Management");
			put_line(outputFile, "");
			put_line(outputFile, "Unsorted Data");
			for i in 1..length loop
				put(outputFile, arr(subscript'val(i)).key, padding);
				put_line(outputFile, "");
			end loop;
		elsif selection = 2 then
			open(outputFile, Append_File, filename);
			put_line(outputFile, "");
			put_line(outputFile, "Sorted Data");
			for i in 1..length loop
				if count = 0 then
					count := arr(subscript'val(offset)).key;
					offset := offset + count + 1;
					put_line(outputFile, "");
				else
					put(outputFile, arr(subscript'val(i)).key, padding);
					put_line(outputFile, "");
					count := count - 1;
				end if;
			end loop;
		end if;
		close(outputFile);
	end print_Int;
	
begin
	B_Option: declare
	begin
		put("Enter the name of the file to be sorted: ");
		getUserInput(inFile);
		put("Enter the name of the file for output: ");
		getUserInput(outFile);
		fill_Dates(inFile);
		print_Dates(outFile, 1);
		heapsort(arr, length);
		print_Dates(outFile, 2);
	end B_Option;
	C_option: declare
		n: integer := 0; offset: integer := 1;
	begin
		put("Enter the name of the file to be sorted: ");
		getUserInput(inFile);
		put("Enter the name of the file for output: ");
		getUserInput(outFile);
		fill_int(inFile);
		print_Int(outFile, 1, 5);
		Offset_Heapsort: loop
			if offset > length then
				exit;
			else
				n := arr(subscript'val(offset)).key;
				heapsort(arr, n,(offset+1),(offset+n));
				offset := offset + n + 1;
			end if; 
		end loop Offset_Heapsort;
		print_Int(outFile, 2, 5);
	end C_option;
end useDataArray;