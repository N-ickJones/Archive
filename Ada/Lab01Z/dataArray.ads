--Filename:  dataArray.ads
--Author:    Nicholas Jones
--Date:	     02/24/2019
--Purpose:   A Generic Array that can use Heap Sorting
--Version:   2.0
--Reference: Dr. Burris Notes

generic
  type subscript is (<>);
  type myType is private;

package dataArray is
	type data is record
		key: integer;
		rec: myType;
	end record;
  type userDefinedArray is array(subscript range <>) of data;
	procedure heapsort(arr: in out userDefinedArray;length: in integer;
										 start: in integer := 1; finish: in integer := 0);
end dataArray;
