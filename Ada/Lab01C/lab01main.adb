--Filename:  lab01main.adb
--Author:    Nicholas Jones
--Date:	     02/11/2019
--Purpose:   To sort an array with stack allocated memory
--Version:   1.0
--Reference: Dr. Burris Notes

with ada.text_io; use ada.text_io;
with dataQueue;

procedure lab01main is
	
	package intIO is new Ada.Text_IO.Integer_IO(integer); use intIO;
	
	procedure putInt(x: Integer) is
		begin
			intIO.Put(x);
		end;
	
	package intQueue is new dataQueue(100, integer, "<", putInt);
	
	procedure readFromFile is
		inputFile: file_type;
		filename: String(1..50);
		input, length: integer := 0;
		begin
		
			put("Enter the name of the file to be sorted: ");
			get_line(filename, length);
			open(inputFile, In_File, filename);
			
			while not End_Of_File(inputFile) loop
				get(inputFile, input);
				intQueue.add(input);
			end loop;
			
			close(inputFile);
		end readFromFile;
		
	value, temp, count, count2, length: integer := 0;
	setCount : integer := 1;
	outputFile : file_type;
	filename: String(1..50);
	
	
	begin
			readFromFile;
			put("Enter the name of the file for output: ");
			get_line(filename, length);
			create(outputFile, Out_File, filename);
			put_line(outputFile, "Report For Management");
			while intQueue.is_active loop
				put_line(outputFile, "");
				put(outputFile, "Data Set");put(outputFile, setCount, width => 2); put_line(outputFile, "");
				
				-- Print Unsorted
				intQueue.remove(count);
				for i in 1..count loop
					intQueue.get(i + count2, value);
					put(outputFile, value, width => 5);
				end loop;
				put_line(outputFile, "");
				
				-- Print Sorted
				intQueue.heapSort(count);
				for i in 1..count loop
					intQueue.remove(value);
					put(outputFile, value, width => 5);
				end loop;
				
				put_line(outputFile, "");
				count2 := count2 + count + 1;
				setCount := setCount + 1;
				
			end loop;
		close(outputFile);
	end Lab01main;