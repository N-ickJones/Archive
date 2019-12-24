--Filename:  uLab03.adb
--Author:    Nicholas Jones
--Date:	     03/24/2019
--Purpose:   Use of AbstractStack
--Version:   1.0
--Reference: Dr. Burris Notes

with Ada.Text_Io; use Ada.Text_Io;
with Links; use Links;
with MyItem; use MyItem;
with MakeCar; use MakeCar;
with MakePlane; use MakePlane;

procedure uLab03 is

	package intIO is new integer_io(integer);use intIO;
	Object: NodePtr;
	fOut: File_Type;
	
	procedure PrintList(Direction: in Character) is
	begin
		InitPt;
		for i in 1..Size loop
			Object := NextNode(Direction);
			if Object.all in Item then
				Print(fOut, Item'Class(Object.all));
			elsif Object.all in Car then
				Print(fOut, Car'Class(Object.all));
			elsif Object.all in Plane then
				Print(fOut, Plane'Class(Object.all));
			else
				put(fOut, "HeadNode Print Attempted");put_line(fOut,"");
			end if;
		end loop;
	end PrintList;
	
	procedure COptionData is
	begin
		Object := new Item'(Node with 33);Insert_Right(Object);
		Object := new Item'(Node with 57);Insert_Right(Object);
		Object := new Item'(Node with 85);Insert_Left(Object);
		Object := new Item'(Node with 62);Insert_Left(Object);
		Object := new Item'(Node with 95);Insert_Right(Object);
		put_line(fOut,"");put(fOut, "[Front To Rear]");
		put_line(fOut,"");PrintList('R');
		put_line(fOut,"");put(fOut, "[Rear To Front]");
		put_line(fOut,"");PrintList('L');
		Find('R', Item'(Node with 57));DeleteNode;
		Find('R', Item'(Node with 33));DeleteNode;
		Find('R', Item'(Node with 33));DeleteNode;
		Find('R', Item'(Node with 62));DeleteNode;
		Object := new Item'(Node with 22);Insert_Right(Object);
		Find('R', Item'(Node with 95));DeleteNode;
		put_line(fOut,"");put(fOut, "[Front To Rear]");
		put_line(fOut,"");PrintList('R');
	end COptionData;
	
	procedure AOptionData is
	begin
		Object := new Car'(Node with 4, "Ford ");Insert_Left(Object);
		Object := new Car'(Node with 2, "Ford ");Insert_Right(Object);
		Object := new Car'(Node with 2, "GMC  ");Insert_Left(Object);
		Object := new Car'(Node with 2, "RAM  ");Insert_Left(Object);
		Object := new Car'(Node with 3, "Chevy");Insert_Right(Object);
		put_line(fOut,"");put(fOut, "Number of Items =");
		put(fOut, Size, 2);put_line(fOut,"");
		put_line(fOut,"");put(fOut, "[Front To Rear]");
		put_line(fOut,"");PrintList('R');
		Find('R', Car'(Node with 0, "Ford "), 1);DeleteNode;
		put_line(fOut,"");put(fOut, "Number of Items =");
		put(fOut, Size, 2);put_line(fOut,"");
		put_line(fOut,"");put(fOut, "[Front To Rear]");
		put_line(fOut,"");PrintList('R');
		Object := new Plane'(Node with 3, 6, "Boeing");Insert_Right(Object);
		Object := new Plane'(Node with 2, 1, "Piper ");Insert_Right(Object);
		Object := new Plane'(Node with 4, 4, "Cessna");Insert_Right(Object);
		put_line(fOut,"");put(fOut, "[Front To Rear]");
		put_line(fOut,"");PrintList('R');
	end AOptionData;
	
begin
	Create(fOut, Out_File, "report.txt");
	put(fOut, "Report for Management [C - Data]");put_line(fOut, "");
	COptionData;
	put_line(fOut, "");
	Clear;
	put(fOut, "Report for Management [B/A - Data]");put_line(fOut, "");
	AOptionData;
	Close(fOut);
end uLab03;