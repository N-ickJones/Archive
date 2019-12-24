--Author:    Nicholas Jones
--Date:	     02/06/2019
--Purpose:   To allocate and use stack memory with circular queue
--Version:   1.0
--Reference: Dr. Burris Notes

package body dataStack is

	subtype slotindex is integer range 0..(max_size -1);
	
	head, tail: slotindex :=0;
	items: integer range 0..max_size := 0;
	queue: array(slotindex) of object;
	temp: object;
		
	procedure push(input: in object) is
		begin
			if items < max_size then
				queue(head) := input;
				head := (head + 1) mod max_size;
				items := items + 1;
			end if;
	end push;
	
	procedure pop(output: out object) is
		begin
			if items > 0 then
				output := queue(tail); 
				tail := (tail + 1) mod max_size;
				items := items - 1;
			end if;
	end pop;
	
	procedure get(input: in integer;output: out object) is
		begin
			output := queue(input);
	end get;
	
	procedure heapSort(x: in integer) is
		n, m, k: integer := 0;
		begin
			n := tail + x - 1;
			m := tail;
			k := m - 1;
			
			for i in reverse m..n loop
				build_heap(n-k, i, k);
			end loop;
			
			for i in reverse (m+1)..n loop
				temp := queue(i);
				queue(i) := queue(m);
				queue(m) := temp;
				build_heap(i-k, m, k);
			end loop; 
	end heapSort;
	
	-- k is the offset for multiple datasets
	procedure build_heap(n: in integer; i: in integer; k: in integer) is
		t: integer := i - k;
		max: integer := t;
		left: integer := 2 * t;
		right: integer := 2 * t + 1;
		begin
			if left < n then
				if queue(max + k) < queue(left + k) then
					max := left;
				end if;
			end if;
			if right < n then
				if queue(max + k) < queue(right + k) then
					max := right;
				end if;
			end if;
			if max /= t then
				temp := queue(t + k);
				queue(t+ k) := queue(max + k);
				queue(max + k) := temp;
				build_heap(n, max + k, k);
			end if;
	end build_heap;
	
	function is_active return boolean is
		begin
			if head /= tail then
				return True;
			else
				return False;
			end if;
	end is_active;

	
	end dataStack;