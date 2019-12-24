--Filename:  dataArray.adb
--Author:    Nicholas Jones
--Date:	     02/24/2019
--Purpose:   Heapsort Algorithm
--Version:   2.0
--Reference: Dr. Burris Notes
with ada.text_io; use ada.text_io;

package body dataArray is

	package intIO is new Ada.Text_IO.Integer_IO(integer); use intIO;
	package tester is new Ada.Text_IO.enumeration_IO(subscript); use tester;

	procedure heapsort(arr: in out userDefinedArray;length: in integer;
										 start: in integer := 1; finish: in integer := 0) is
		n, rear: integer := length;
		K, m, j, i: integer;
		R: data;
		offset: integer := start - 1;
	begin
		if finish /= 0 then
			rear := finish;
		end if;
		m := (n / 2 + 1);
		MainLoop: loop
			if m > 1 then
				m := m - 1;
				R := arr(subscript'val(m + offset));
				K := arr(subscript'val(m + offset)).key;
			else
				R := arr(subscript'val(rear));
				K := arr(subscript'val(rear)).key;
				arr(subscript'val(rear)) := arr(subscript'val(start));
				rear := rear - 1;
				if rear = start then
					arr(subscript'val(start)) := R;
					exit MainLoop;
				end if;
			end if;
			j := m;
			AdvanceDownward: loop
				i := j;
				j := j * 2;
				if j < (rear - offset) then
					if arr(subscript'val(j+offset)).key < arr(subscript'val(j+1+offset)).key then
						j := j + 1;
					end if;
					if K >= arr(subscript'val(j+offset)).key then
						arr(subscript'val(i+offset)) := R;
						exit AdvanceDownward;
					else
						arr(subscript'val(i+offset)) := arr(subscript'val(j+offset));
					end if;
				elsif j = (rear-offset) then
					if K >= arr(subscript'val(j+offset)).key then
						arr(subscript'val(i+offset)) := R;
						exit AdvanceDownward;
					else
						arr(subscript'val(i+offset)) := arr(subscript'val(j + offset));
					end if;
				elsif j > (rear-offset) then
					arr(subscript'val(i+offset)) := R;
					exit AdvanceDownward;
				end if;
			end loop AdvanceDownward;
		end loop MainLoop;
	end heapsort;
end dataArray;