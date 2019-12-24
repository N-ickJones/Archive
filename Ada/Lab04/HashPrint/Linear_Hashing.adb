--Filename:  Hash.adb
--Author:    Nicholas Jones
--Date:	     04/09/2019
--Purpose:   Hashing Lab
--Version:   1.0
--Reference: Dr. Burris Notes

package body Linear_Hashing is

  procedure Insert(Input: in out Object; Addr: Integer) is
    Address: Integer := Addr;
    ProbeCount: Integer := 1;
  begin
    if Count /= Size then
      loop
        if DataTable(Address).Status = 2 then -- In Use
          Address := Address + 1;
          if Address > Size then
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
      Ada.Text_IO.Put_Line("Table is Full");
    end if;
  end;

  function Get(Address: Integer) return Object is
  begin
    return DataTable(Address).Data;
  end;

  procedure Delete(Address: Integer) is
    Count: Integer := Address - 1;
  begin
    DataTable(Address, Empty.Data, Empty.Status);
    if DataTable(Address+1).Status = 0 then
      while DataTable(Count).Status = 1 loop
        DataTable(Count, 0);
        Count := Count - 1;
      end loop;
    else
      DataTable(Address, 1);
    end if;
  end Delete;

  function Find(Input: Object; InitHash: Integer) return Integer is
    Address: Integer := InitHash;
  begin
    while DataTable(Address).Data /= Input loop
      if DataTable(Address).Status = 0 then
        return -1; -- Not Found
      end if;
      Address := Address + 1;
      if Address > Size then
        Address := Address - Size;
      end if;
    end loop;
    return Address;
  end Find;

  function Probes(Input: Object; InitHash: Integer) return Integer is
    Address: Integer := InitHash;
    Probes: Integer := 1;
  begin
    while DataTable(Address).Data /= Input loop
      if DataTable(Address).Status = 0 then
        return Probes; -- Not Found
      end if;
      Address := Address + 1;
      Probes := Probes + 1;
      if Address > Size then
        Address := Address - Size;
      end if;
    end loop;
    return Probes;
  end Probes;

  procedure Clear is
  begin
    for Address in 1..Size loop
      DataTable(Address, Empty.Data, Empty.Status);
    end loop;
    Count := 0;
  end;

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
    Dio.Create(f1, Dio.InOut_File, "DataStorage");
    for pt in Dio.Positive_Count range 1..Dio.Positive_Count(Size) loop
      Dio.write(f1, Empty, pt);
    end loop;
  end Open;

  procedure Close is
  begin
    Dio.Close(f1);
  end Close;

end Linear_Hashing;
