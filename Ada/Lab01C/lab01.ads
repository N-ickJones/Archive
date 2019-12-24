--Filename: 
--Author:    Nicholas Jones
--Date:	     02/06/2019
--Purpose:   To create a generic for using stack memory
--Version:   1.0
--Reference: Dr. Burris Notes

generic
	max_size: integer;
	type object is private;
	with function "<"(X,Y: object) return boolean;
	with procedure Put(X: object);
	
package dataStack is	
	procedure push(input: in object);
	procedure pop(output: out object);
	procedure get(input: in integer;output: out object);
	procedure heapSort(x: in integer);
	procedure build_heap(n: in integer; i: in integer; k: in integer);
	function is_active return boolean;

end dataStack;