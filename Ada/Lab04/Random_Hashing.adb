--Filename:  Hash.adb
--Author:    Nicholas Jones
--Date:	     04/01/2019
--Purpose:   Hashing Lab
--Version:   1.0
--Reference: Dr. Burris Notes

package body Random_Hashing is

  procedure Insert(Input: in out Object; Addr: Integer) is
    Address: Integer := Addr;
    ProbeCount: Integer := 1;
  begin
		InitRandOffset;
    if Count /= Size then
      loop
        if DataTable(Address).Status = 2 then -- In Use
					Address := Address + UniqueRandOffset;
					If Address > Size then
						Address := Address - Size;
					end if;
          ProbeCount := ProbeCount + 1;
        else                                  -- Available
          SetProbe(Input, ProbeCount);
          DataTable(Address, Input, 2);
          Count := Count + 1;
          exit;
        end if;
      end loop;
    else
      put_line("Table is Full");
    end if;
  end;

  function Get(Address: Integer) return Object is
  begin
    return DataTable(Address).Data;
  end;

  procedure Delete(Address: Integer) is
	begin
		InitRandOffset;
		DataTable(Address, Empty.Data, Empty.Status);

	end Delete;

  function Find(Input: Object; InitHash: Integer) return Integer is
	begin
		return 0;
	end;

  function Probes(Input: Object; InitHash: Integer) return Integer is
	begin
		return 0;
	end;

  procedure Clear is
  begin
    for Address in 1..Size loop
      DataTable(Address, Empty.Data, Empty.Status);
    end loop;
    Count := 0;
  end;

	procedure InitRandOffset is
	begin
		Random_Int := 1;
	end InitRandOffset;

	function UniqueRandOffset return Integer is
	begin
		Random_Int := (5 * 6) Mod (2**(NumBits + 2));
		return Random_Int / 4;
	end UniqueRandOffset;

	function DataTable(Index: Integer) return DataType is
    Output: DataType;
  begin
    Dio.Read(f1, Output, Dio.Positive_Count(Index));
    Return Output;
  end;

  procedure DataTable(Index: Integer;Y: Object;Z: Integer) is
    Input: DataType;
  begin
    Input.Data := Y; Input.Status := Z;
    Dio.Write(f1, Input, Dio.Positive_Count(Index));
  end;

  procedure DataTable(Index: Integer;Z: Integer) is
    Value: DataType;
  begin
    Dio.Read(f1, Value, Dio.Positive_Count(Index));
    Value.Status := Z;
    Dio.Write(f1, Value, Dio.Positive_Count(Index));
  end;

  procedure Open is

  begin
    Dio.Create(f1, Dio.InOut_File, "DataStorage2");
    for pt in Dio.Positive_Count range 1..Dio.Positive_Count(Size) loop
      Dio.write(f1, Empty, pt);
    end loop;
  end Open;

  procedure Close is
  begin
    Dio.Close(f1);
  end Close;

end Random_Hashing;
