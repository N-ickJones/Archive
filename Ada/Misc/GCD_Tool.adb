
with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
--with Ada.Direct_IO;

procedure Quick is


  procedure GCD(X: Integer; Y: Integer) is
    t1: integer := x;
    t2: integer := y;
    t3: integer := 1;
    s1: integer := 1;
    s2: integer := 0;
    d1: integer := 0;
    d2: integer := 1;
    temp: integer;
    q: Integer;
    space: integer := 15;
  begin
    put("            ---");put(X, space);put(s1, space);put(d1, space);new_line;
    put(x/y, space);put(Y, space);put(s2, space);put(d2, space);new_line;
    while t3 > 0 loop
      q := t1 / t2;
      t3 := t1 mod t2;
      t1 := t2;
      t2 := t3;
      if t2 /= 0 then
        put(t1/t2, space);
        put(t3, space);
        temp := s2;s2 := s1 - (q*s2);s1 := temp;put(s2, space);
        temp := d2;d2:=d1 - (q*d2);d1:=temp;put(d2, space);new_line;
      end if;
    end loop;
    put("            ---");put(0, space);
    temp := s2;s2 := s1 - (q*s2);s1 := temp;--put(s2, space);
    --temp := d2;d2:=d1 - (q*d2);d1:=temp;put(d2, space);new_line;
    new_line;
  end GCD;

  type rec is record
    i: integer;
    s: string(1..5);
  end record;
  rec1: rec;
  --package dio is new Ada.Direct_IO(rec);
  --f1: dio.file_type;
  j: integer := 0;
  left, right: integer;

begin
  --dio.create(f1, dio.inout_file, "joedir");
  --rec1.s := "test1";
  --rec1.i := 5;
  --dio.write(f1, rec1, 5);
  --rec1.s := "     ";
  --rec1.i := 0;
  --dio.read(f1, rec1, 5);
  --ada.text_io.put(rec1.s);put(rec1.i, 5);
  loop
    put("GCD(X, Y) Enter X: ");get(Left);
    put("GCD(X, Y) Enter Y: ");get(Right);
    GCD(Left, Right);
  end loop;
  --dio.close(f1);

  --GCD(54321, 50);
  --GCD(29341, 1739);
  --GCD(641, 521);

end Quick;
