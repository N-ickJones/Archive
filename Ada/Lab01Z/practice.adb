
with ada.text_io; use ada.text_io;
with dataArray;

procedure practice is

	n: integer := 8;
	
	type data is record
		key: integer;
		rec: integer;
	end record;
	
	K: integer;
	R: data;

	package intIO is new Ada.Text_IO.Integer_IO(integer); use intIO;
	
	arr: array(1..n) of data;
	m: integer;
	rear: integer := n;
	j, i: integer;
	
	procedure fillArray is
		inputFile: file_type;
		count, tmp: integer := 1;
	begin
		open(inputFile, In_File, "inputFile.txt");
			while not End_Of_File(inputFile) loop
				--arr(count).key := count;
				get(inputFile, tmp);
				arr(count).rec := 0;
				arr(count).key := tmp;
				count := count + 1;
			end loop;
		
		close(inputFile);
	end fillArray;
	
	procedure outputArray is
	begin
		for q in 1..n loop
			put(arr(q).key);new_line;
			--put(arr(q).rec);new_line;
		end loop;
		
	end outputArray;
	
	procedure heapsort is
	begin
		m := n / 2 + 1;
		MainLoop: loop
			if m > 1 then
				m := m - 1;
				R := arr(m);
				K := arr(m).key;
			else
				R := arr(rear);
				K := arr(rear).key;
				arr(rear) := arr(1);
				rear := rear - 1;
				if rear = 1 then
					arr(1) := R;
					exit MainLoop;
				end if;
			end if;
			j := m;
			AdvanceDownward: loop
				i := j;
				j := 2 * j;
				if j < rear then
					if arr(j).key < arr(j+1).key then
						j := j + 1;
					end if;
					if K >= arr(j).key then
						arr(i) := R;
						exit AdvanceDownward;
					else
						arr(i) := arr(j);
					end if;
				elsif j = rear then
					if K >= arr(j).key then
						arr(i) := R;
						exit AdvanceDownward;
					else
						arr(i) := arr(j);
					end if;
				elsif j > rear then
					arr(i) := R;
					exit AdvanceDownward;
				end if;
			end loop AdvanceDownward;
		end loop MainLoop;
	end heapsort;
	
begin

	fillArray;
	
	heapsort;
	
	outputArray;
end practice;
