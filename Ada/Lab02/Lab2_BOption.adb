--Filename:  Lab2_BOption.adb
--Author:    Nicholas Jones
--Date:	     02/11/2019
--Purpose:   
--Version:   1.0
--Reference: Dr. Burris Notes

with ada.text_io; use ada.text_io;
with DynamicMultiStack;

procedure Lab2_BOption is
  package intIO is new ada.text_io.integer_io(integer); use intIO;
	
	procedure Process_BOption(Lo: integer := 0;m:integer := 37; n:integer := 3;
													 low:integer := -11; high:integer := 60) is	
		outputFile: file_type;												
		type MyString is record
			len: integer;
			rec: String(1..7);
		end record;
		subtype myIndex is integer range low..high;
		procedure putMyString(outputFile: in out file_type;myStr: MyString) is 
		begin 
			put(outputFile, myStr.rec(1..myStr.len)); 
		end;
		package stack is new DynamicMultiStack(MyString, myIndex, putMyString,
																							Lo, m, n, outputFile);
		inputFile: File_Type;
		char: character;
		num, count: integer := 1;
		str: String(1..7);
		myStr: MyString;
	begin
		open(inputFile, In_File, "input.txt");
		create(outputFile, OUT_File, "output.txt");
		stack.build;
		put(outputFile, "Report for Management [B-Option]");
		put_line(outputFile, "");put_line(outputFile, "");
    while not End_Of_File(inputFile) loop
      get(inputFile, char);
      if char = 'I' then
				get(inputFile, num);
				get(inputFile, char);
				build_string: loop
					get(inputFile, char);
					if char = ',' then
						count := count - 1;
						exit;
					elsif End_Of_File(inputFile) then
						str(count) := char;
						exit;
					else
						str(count) := char;
						count := count + 1;
					end if;
				end loop build_string;
				myStr.len := count;
				myStr.rec(1..myStr.len) := str(1..myStr.len);
				put(outputFile, "PUSH STACK");put(outputFile, num, 2);
				put(outputFile, " " & myStr.rec(1..myStr.len));put_line(outputFile, "");
				stack.push(myStr, num);
				count := 1;
			elsif char = 'D' then
				get(inputFile, num);
				stack.pop(myStr, num);
				put(outputFile, "POP  STACK");put(outputFile, num, 2);
				put(outputFile, " " & myStr.rec(1..myStr.len));put_line(outputFile, "");
			end if;
    end loop;
		close(outputFile);
		close(inputFile);
	end Process_BOption;
	
begin
	--put("What's the starting memory location?");new_line;get(Lo);Lo := Lo + 1;
	--put("What's the max length?");new_line;get(m);
	--put("What's the stack count?");new_line;get(n);
	--put("What's the lower bound?");new_line;get(low);
	--put("What's the upper bound?");new_line;get(high);
	--readIn(Lo, m, n, low, high);
	Process_BOption;
	
end Lab2_BOption;