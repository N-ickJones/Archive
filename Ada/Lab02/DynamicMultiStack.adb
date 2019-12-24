--Filename:  DynamicMultiStack.adb
--Author:    Nicholas Jones
--Date:	     03/03/2019
--Purpose:   Dynamic MultiStack PUSH/POP, REALLOCATION, REPACKING
--Version:   1.0
--Reference: Dr. Burris Notes

package body DynamicMultiStack is
		
  procedure build is
  begin
    for j in 1..n loop
      Base(j) := floor(float(j - 1) / float(n) * float(m)) + Lo;
      Top(j) := Base(j);
      Audit(j) := Top(j);
    end loop;
    Base(n+1) := m + Lo;
  end build;
	
  procedure push(input: in object; k: in integer) is
  begin
    Top(k) := Top(k) + 1;
    if Top(k) > Base(k + 1) then
      put_line(fOut, "");
			put(fOut, "OVERFLOW OCCURED WITH ");putObject(fOut, input);
			put(fOut, " INTO STACK ");put(fOut, k, 0);put_line(fOut, "");
			put(fOut, "CONTENT BEFORE REALLOCATION");put_line(fOut, "");
			top(k) := top(k) - 1;
			print_content;
			top(k) := top(k) + 1;
      find_space;
      top(k) := top(k) - 1;
      move_stack;
      top(k) := top(k) + 1;
      Stack(subscript'val(top(k))) := input;
      for i in 1..n loop
        Audit(i) := Top(i);
      end loop;
			put(fOut, "CONTENT AFTER REPACKING");put_line(fOut, "");
			print_content;
    else
      Stack(subscript'val(top(k))) := input;
    end if;
  end push;

  procedure pop(output: out object; k: in integer) is
  begin
    if Top(k) = Base(k) then
      put(fOut, "UNDERFLOW OCCURED");put_line(fOut, "");
    else
      output := Stack(subscript'val(Top(k)));
      top(k) := top(k) - 1;
    end if;
  end pop;

  procedure find_space is
    AvailSpace: integer := m - Lo - 1; -- Lo < L <= M, -1 is to exclude Lo From L
    t: integer := n;
    EqualAllocate: float := 0.23;
    GrowthAllocate: float := 1.0 - EqualAllocate;
    Alpha, Beta, Tau, Sigma: float := 0.0;
    TotalInc: integer := 0;
    MinSpace: float := float(m) * min;
  begin
    while t > 0 loop
      AvailSpace := AvailSpace - (Top(t) - Base(t));
      if Top(t) > Audit(t) then
        Audit(t+1) := Top(t) - Audit(t);
        TotalInc := TotalInc + Audit(t+1);
      else
        Audit(t+1) := 0;
      end if;
      t := t - 1;
    end loop;
    if float(AvailSpace) < (MinSpace - 1.0) then
			put(fOut, "--Insufficient Memory for Repacking--");
      raise PROGRAM_ERROR with "Insufficient Memory for Repacking";
    end if;
    Alpha := EqualAllocate * float(AvailSpace) / float(n);
		Beta := GrowthAllocate * float(AvailSpace) / float(TotalInc);
    Audit(1) := Base(1);
    for i in 2..n loop
			Tau := Sigma + Alpha + (float(Audit(i)) * Beta);
			Audit(i) := Audit(i-1) + (Top(i-1) - Base(i-1)) + floor(tau) - floor(Sigma);
      Sigma := Tau;
    end loop;
  end find_space;
  procedure move_stack is
    T: integer;
  begin
    for i in 2..n loop
      if Audit(i) < Base(i) then
        T := Base(i) - Audit(i);
        for L in (Base(i)+1)..top(i) loop
          Stack(subscript'val(L-T)) := Stack(subscript'val(L));
        end loop;
        Base(i) := Audit(i);
        Top(i) := top(i) - T;
      end if;
    end loop;
    for i in reverse 2..n loop
      if Audit(i) > Base(i) then
        T := Audit(i) - Base(i);
        for L in reverse (Base(i)+1)..Top(i) loop
          Stack(subscript'val(L + T)) := Stack(subscript'val(L));
        end loop;
        Base(i) := Audit(i);
        Top(i) := Top(i) + T;
      end if;
    end loop;
  end move_stack;
	
	procedure print_content is
	begin
		for j in 1..n loop
			put(fOut, "Base(");put(fOut, j, 0);put(fOut, ")=");put(fOut, Base(j), 2);
			put(fOut, " Top(");put(fOut, j, 0);put(fOut, ")=");put(fOut, Top(j), 2);
			put(fOut, " OldTop(");put(fOut, j, 0);put(fOut, ")=");put(fOut, Audit(j), 2);
			put_line(fOut, "");
			for k in base(j) + 1..top(j) loop
				put(fOut,"(");put(fOut, k, 2);put(fOut, ")");
				putObject(fOut, Stack(subscript'val(k)));
				put_line(fOut, "");
			end loop;
		end loop;
		put_line(fOut, "");
	end print_content;
	
	function floor(x: float) return integer is 
		t: integer;
	begin
		t := integer(x);
		if float(t) <= x then
			return t;
		else
			return t - 1;
		end if;
	end;

end DynamicMultiStack;