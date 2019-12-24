with Links; use Links;
with Ada.Text_IO; use Ada.Text_io;

package MyItem is
	package IntIO is new Ada.Text_IO.Integer_IO(Integer);  use IntIO;

	type Item is new Node with record
		Value: Integer;
	end record;
	
	procedure AssignValue(aItem: in out Item; N: in integer);
	procedure Print(aItem: in Item);
	procedure Print(fOut: File_Type;aItem: in Item);
	procedure Find(Direction: in Character; FindItem: in Item);

end MyItem;