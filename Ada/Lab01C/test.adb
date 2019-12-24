--Author:    Nicholas Jones
--Date:	     02/06/2019
--Purpose:   To sort integer data
--Version:   1.0
--Reference: 

with ada.text_io, ada.integer_text_io;
use ada.text_io, ada.Integer_Text_IO;

procedure main is

   n: integer := 16;
   type int_array is array(1..n) of integer;
   data: int_array  := (3, 4, 7, 5, 1, 8, 6, 2, 6, 4 , 5, 10, 15, 20, 11, 66);

   procedure build_heap(data: in out int_array; n: in integer; i: in integer) is

      max: integer := i;
      left: integer := 2 * i;
      right: integer := 2 * i + 1;
      temp: integer := 1;

   begin

      if left < n then
         if data(max) < data(left) then
            max := left;
         end if;
      end if;

      if right < n then
         if data(max) < data(right) then
            max := right;
         end if;
      end if;

      if max /= i then
         temp := data(i);
         data(i) := data(max);
         data(max) := temp;
         build_heap(data, n, max);
      end if;

   end build_heap;

   procedure heap_sort(data: in out int_array; n: in out integer) is

      temp: integer := 0;

   begin

      for i in reverse 1..n loop --Check if n+1 is neccessary
         build_heap(data, n, i);
      end loop;

      for i in reverse 2..n loop
         temp := data(i);
         data(i) := data(1);
         data(1) := temp;
         build_heap(data, i, 1);
      end loop;

   end heap_sort;

-- Start Main
begin
   heap_sort(data, n);
   for i in 1..n loop
      put(data(i));new_line;
   end loop;
   new_line;

end main;


-- Stack 
	--Circular
	--front, rear, items: integer range 0..(max + 1) := 0;
	--procedure push(input: in object) is
		--begin
			--top := top + 1;
			--stack(top) := input;
		--end push;
	
	--procedure pop(output: out object) is
		--begin
			--output := stack(top);
			--top := top - 1;
		--end pop;
		
	-- Queue
	
	--procedure push(input: in object) is
		--begin
			--if rear > max then
				--put("circle");new_line;
				--rear := 1;
			--end if;
			--if rear = (front + 1) then
				--put("overflow");new_line;
			--end if;
			--stack(rear) := input;
			--rear := rear + 1;
		--end push;
	
	--procedure pop(output: out object) is
		--begin
			--front := front + 1;
			--if front > max then
				-front := 1;
			--end if;
			--if front = (rear + 1) then
				--put("underflow");new_line;
			--end if;
			--output := stack(front);
		--end pop;