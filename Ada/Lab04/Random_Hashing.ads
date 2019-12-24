--Filename:  Hash.ads
--Author:    Nicholas Jones
--Date:	     04/01/2019
--Purpose:   Hashing Lab
--Version:   1.0
--Reference: Dr. Burris Notes

with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Ada.Direct_IO;

generic
  type Object is private;
	NumBits: Integer;
  Size: Natural;
	Input_File: in out file_type;
  with procedure SetProbe(X: in out Object; Y: Integer);
	with function "="(X: Object; Y: Object) return Boolean;

package Random_Hashing is
  type DataType is private;
  procedure Insert(Input: in out Object; Addr: Integer);
  procedure Delete(Address: Integer);
  function Get(Address: Integer) return Object;
  function Find(Input: Object; InitHash: Integer) return Integer;
  function Probes(Input: Object; InitHash: Integer) return Integer;
  procedure Clear;
	procedure Open;
  procedure Close;

private
  type DataType is record
    Status: Integer := 0;
    Data: Object;
  end record;
	package Dio is new Ada.Direct_IO(DataType);
  f1: Dio.File_Type;
	Count: Natural := 0;
	Empty: DataType;
	Random_Int: Integer := 1;
	function DataTable(Index: Integer) return DataType;
  procedure DataTable(Index: Integer;Y: Object;Z: Integer);
  procedure DataTable(Index: Integer;Z: Integer);
	procedure InitRandOffset;
	function UniqueRandOffset return Integer;

end Random_Hashing;
