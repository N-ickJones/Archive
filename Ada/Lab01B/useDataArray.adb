
--Filename:  useDataArray.adb
--Author:    Nicholas Jones
--Date:	     02/21/2019
--Purpose:   To Sort and Print Dates to File B-OPTION
--Version:   1.0
--Reference: Dr. Burris Notes

with ada.text_io; use ada.text_io;
with dataArray;

procedure useDataArray is

  type Month_Name is (January, February, March, April, May, June, July, August, September, October, November, December);
	type Date is record
		month: Month_Name;
		day: Integer range 1..31;
		year: Integer range 1400..2022;
	end record;
  type myIndex is range 1..100;

  package intIO is new Ada.Text_IO.Integer_IO(integer); use intIO;
  package Month_Name_IO is new Ada.Text_IO.enumeration_IO(Month_Name); use Month_Name_IO;
  package createArray is new dataArray(myIndex, date); use createArray;

  arr: userDefinedArray(myIndex);
  length, size: integer;
  temp: Date;
  filename: String(1..30);
  inputFile: file_type;
  outputFile: file_type;

  procedure readDatesFromFile(length: out integer;inputFile: file_type) is
    index: myIndex := 1;
	begin
      --get(inputFile, length);
			while not End_Of_File(inputFile) loop
        get(inputFile, arr(index).month);
        get(inputFile, arr(index).day);
        get(inputFile, arr(index).year);
        index := index + 1;
			end loop;
      length := integer(index) - 1;
	end readDatesFromFile;

  procedure outputToFile(length: in integer;outputFile: file_type) is
  begin
    for i in 1..length loop
      put_line(outputFile, "");
      put(outputFile, arr(myIndex(i)).month, 10);
      put(outputFile, arr(myIndex(i)).day, 4);
      put(outputFile, arr(myIndex(i)).year, 6);
    end loop;
    put_line(outputFile, "");
  end outputToFile;

  procedure build_heap(n: in integer; i: in integer) is
    max: integer := i;
    left: integer := 2 * i;
    right: integer := 2 * i + 1;
  begin

    if left < n then
      if arr(myIndex(max)).year < arr(myIndex(left)).year then
        max := left;
      elsif arr(myIndex(max)).year = arr(myIndex(left)).year then
        if arr(myIndex(max)).month < arr(myIndex(left)).month then
          max := left;
        elsif arr(myIndex(max)).month = arr(myIndex(left)).month then
          if arr(myIndex(max)).day < arr(myIndex(left)).day then
            max := left;
          end if;
        end if;
      end if;
    end if;

    if right < n then
      if arr(myIndex(max)).year < arr(myIndex(right)).year then
        max := right;
      elsif arr(myIndex(max)).year = arr(myIndex(right)).year then
        if arr(myIndex(max)).month < arr(myIndex(right)).month then
          max := right;
        elsif arr(myIndex(max)).month = arr(myIndex(right)).month then
          if arr(myIndex(max)).day < arr(myIndex(right)).day then
            max := right;
          end if;
        end if;
      end if;
    end if;

    if max /= i then
      temp := arr(myIndex(i));
      arr(myIndex(i)) := arr(myIndex(max));
      arr(myIndex(max)) := temp;
      build_heap(n, max);
    end if;

  end build_heap;

procedure heapSort(n: in integer) is
begin

  for i in reverse 1..n/2 + 1 loop
    build_heap(n, i);
  end loop;

  for i in reverse 2..n loop
    temp := arr(myIndex(i));
    arr(myIndex(i)) := arr(myIndex(1));
    arr(myIndex(1)) := temp;
    build_heap(i, 1);
  end loop;

end heapSort;

-- Begin Main
begin

  put("Enter the name of the file to be sorted: ");
  get_line(filename, size);
  open(inputFile, In_File, filename);
    readDatesFromFile(length, inputFile);
  close(inputFile);
  put("Enter the name of the file for output: ");
  get_line(filename, size);
  create(outputFile, Out_File, filename);
		put_line(outputFile, "Report For Management");
    outputToFile(length, outputFile);
    heapSort(length);
    outputToFile(length, outputFile);
  close(outputFile);
	
	
end useDataArray;
