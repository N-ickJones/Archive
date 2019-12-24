--Filename:  DynamicMultiStack.ads
--Author:    Nicholas Jones
--Date:	     03/03/2019
--Purpose:   Generic Dynamic MultiStack
--Version:   1.0
--Reference: Dr. Burris Notes

with ada.text_io; use ada.text_io;
generic
  type object is private;
	type subscript is (<>);
  with procedure putObject(outputFile: in out file_type;X: object);
  Lo, m, n: integer;
	min: float;
	fOut: in out file_type;

package DynamicMultiStack is
	package intIO is new Ada.Text_IO.Integer_IO(integer); use intIO;
  package floatIO is new Ada.Text_IO.Float_IO(float); use floatIO;
	package lfloatIO is new Ada.Text_IO.Float_IO(long_float); use lfloatIO;
	type userDefinedArray is array(subscript range <>) of object;
		Stack: userDefinedArray(subscript);
	Top: array(0..n) of integer;
  Base: array(0..n+1) of integer;
  Audit: array(1..n+1) of integer; --OldTop, Growth & NewBase
  procedure build;
	procedure push(input: in object; k: in integer);
	procedure pop(output: out object; k: in integer);
  procedure find_space;
  procedure move_stack;
	procedure print_content;
	function floor(x: float) return integer;
	
end DynamicMultiStack;
