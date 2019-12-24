--Filename:  Lab02_AOption.adb
--Author:    Nicholas Jones
--Date:	     03/03/2019
--Purpose:   To Read in Dates Using DynamicMultiStack
--Version:   1.0
--Reference: Dr. Burris Notes

with ada.text_io; use ada.text_io;
with DynamicMultiStack;

procedure Lab02_AOption is
	package intIO is new ada.text_io.integer_io(integer); use intIO;
	package floatIO is new ada.text_io.float_io(float); use floatIO;

	procedure Process_AOption(Lo: integer := 4; m:integer := 15; n:integer := 3; 
														low:integer := 0; high:integer := 50;min: float := 0.05) is
		outputFile: file_type;		
		type Month_Name is (January, February, March, April, May, June,
												July, August, September, October, November, December);
		package monthIO is new ada.text_io.enumeration_io(Month_Name); use monthIO;
		type Date is record
			month: Month_Name;
			day: Integer range 1..31;
			year: Integer range 1400..2020;
		end record;
		subtype myIndex is integer range low..high;
		procedure putMyDate(outputFile: in out file_type;myDate: Date) is 
		begin 
			put(outputFile, myDate.month);put(outputFile, myDate.day, 3);
			put(outputFile, myDate.year, 5);
		end;
		procedure putMyDate(myDate: Date) is 
		begin 
			put(myDate.month);put(myDate.day, 3);put(myDate.year, 5);new_line;
		end;
		package stack is new DynamicMultiStack(Date, myIndex, putMyDate, 
																							Lo, m, n, 0.05, outputFile); use stack;																				
		inputFile: File_Type;
		char: character;
		num, count: integer := 1;
		myDate: Date;
	begin
		open(inputFile, In_File, "input2.txt");
		create(outputFile, OUT_File, "output2.txt");
		stack.build;
		put(outputFile, "Report for Management [A-Option]");
		put_line(outputFile, "");put_line(outputFile, "");
    while not End_Of_File(inputFile) loop
			get(inputFile, char);
      if char = 'I' then
				get(inputFile, num);
				get(inputFile, char);
				get(inputFile, myDate.month);
				get(inputFile, myDate.day);
				get(inputFile, myDate.year);
				push(myDate, num);
				put(outputFile, "PUSH STACK");put(outputFile, num, 2);
				put(outputFile, " ");putMyDate(outputFile, myDate);put_line(outputFile, "");
			elsif char = 'D' then
				get(inputFile, num);
				stack.pop(myDate, num);
				put(outputFile, "POP  STACK");put(outputFile, num, 2);
				putMyDate(outputFile, myDate);put_line(outputFile, ""); --TODO ADD IT
			end if;
		end loop;
		close(outputFile);
		close(inputFile);
	end Process_AOption;
	
	Lo, m, n, low, high: integer := 0;
	Loc, mc, c: character := 'z';
	min: float;
begin
	while c /= 'c' and c/= 'i' loop
		put("Input c for Character or i for Integer Subscript: ");get(c);
	end loop;
	if c = 'c' then
		put("What's the start location?");new_line;get(Loc);
		put("What's the memory size?");new_line;get(mc);
		Lo := character'pos(Loc) - character'pos('a');
		m  := character'pos(mc) - character'pos('a');
	else
		put("What's the start location?");new_line;get(Lo);
		put("What's the memory size?");new_line;get(m);
	end if;
	put("What's the stack count?");new_line;get(n);
	put("What's the lower bound?");new_line;get(low);
	put("What's the upper bound?");new_line;get(high);
		put("What's the min memory allowed?");new_line;get(min);
	Process_AOption(Lo, m, n, low, high, min);
		
end Lab02_AOption;
	
	