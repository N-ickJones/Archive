-- Filename: uBST.adb
-- Author: Nicholas Jones
-- Purpose: Fast Binary Search Tree w/ Threads
-- Date: 04/14/2019
-- Version: 1.0

with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with BinarySearchTree;

procedure uBST is

	Fout: File_Type;

	type Customer is record
		Name: String(1..15);
		Phone: String(1..10);
	end record;

	function "<"(aKey: Customer; aRec: Customer) return Boolean is
	begin
		if aKey.Name < aRec.Name then
			return True;
		else
			return False;
		end if;
	end;

	function ">"(aKey: Customer; aRec: Customer) return Boolean is
	begin
		if aKey.Name > aRec.Name then
			return True;
		else
			return False;
		end if;
	end;

	function "="(aKey: Customer; aRec: Customer) return Boolean is
	begin
		if aKey.Name = aRec.Name then
			return True;
		else
			return False;
		end if;
	end;

	procedure put(aRec: Customer; X: Integer := 0) is
	begin
		if X = 0 then
			put(Fout, aRec.Name);put(Fout, aRec.Phone);
			put_line(Fout, "");
		else
			put(Fout, aRec.Name);
			put_line(Fout, "");
		end if;
	end put;



	package Tree is new BinarySearchTree(Customer, "<", ">", "=", Fout, put, 100);

	procedure Find_Customer(Search_Type: String; aCustomer: Customer) is
	begin
		if Search_Type = "Recursive" then
			Tree.FindRecursive(aCustomer);
		else
			Tree.FindIterative(aCustomer);
		end if;
		if Tree.Has_Located then
			put(Fout, Tree.Get_Node.Name & " " &Tree.Get_Node.Phone);put_line(Fout, "");
		else
			put(Fout, aCustomer.Name & " Not Found");put_line(Fout, "");
		end if;
	end;

begin
	create(Fout, Out_File, "report.txt");
	put_line(Fout, "Report for Management");put_line(Fout, "");

	--Start C Option
	put(Fout, "Inserting 9 Customers into the Tree");put_line(Fout, "");
	Tree.Insert(Customer'("McManus        ", "295-1492  "));
	Tree.Insert(Customer'("Figueroa       ", "291-1864  "));
	Tree.Insert(Customer'("Wimalasekara   ", "295-1601  "));
	Tree.Insert(Customer'("Parker         ", "293-6122  "));
	Tree.Insert(Customer'("Abo-Shear      ", "295-1882  "));
	Tree.Insert(Customer'("Syed           ", "291-7890  "));
	Tree.Insert(Customer'("Khadka         ", "294-8075  "));
	Tree.Insert(Customer'("Musco          ", "584-3622  "));
	put_line(Fout, "");
	put(Fout, "Starting FindIterative/FindRecursive Searches");put_line(Fout, "");
	Find_Customer("Iterative", Customer'("Musco          ", "          "));
	Find_Customer("Recursive", Customer'("Musco          ", "          "));
	Find_Customer("Iterative", Customer'("Penton         ", "          "));
	Find_Customer("Recursive", Customer'("Penton         ", "          "));
	put_line(Fout, "");
	put(Fout, "Traversing the Tree starting with Figueroa");put_line(Fout, "");
	Tree.Traverse_Inorder(Customer'("Figueroa       ", "          "));
	put_line(Fout, "");
	put(Fout, "Inserting 3 Customers into the Tree");put_line(Fout, "");
	Tree.Insert(Customer'("Bolen          ", "294-1568  "));
	Tree.Insert(Customer'("Skinner        ", "294-1882  "));
	Tree.Insert(Customer'("Krischke       ", "295-6622  "));
	put_line(Fout, "");
	put(Fout, "Traversing The Tree using Inorder Successor [Root]");put_line(Fout, "");
	Tree.Traverse_Inorder_Successor;
	put_line(Fout, "");
	put(Fout, "Traversing The Tree using Inorder Predeccesor [Root]");put_line(Fout, "");
	Tree.Traverse_Inorder_Predeccesor;
	put_line(Fout, "");
	put(Fout, "Traversing the Tree using PreOrder Iterative");put_line(Fout, "");
	Tree.PreOrderTraversalIterative;
	--End C Option

	--Start B Option
	Tree.Delete(Customer'("Parker         ", "          "));
	Tree.Delete(Customer'("McManus        ", "          "));
	Tree.Delete(Customer'("Figueroa       ", "          "));
	Tree.Insert(Customer'("Sumdin         ", "294-1666  "));
	Tree.Insert(Customer'("Bolen          ", "295-1882  "));
	put_line(Fout, "");
	put(Fout, "Traversing The Tree using Inorder Successor");put_line(Fout, "");
	Tree.Traverse_Inorder_Successor;
	put_line(Fout, "");
	put(Fout, "Traversing The Tree using Reverse Inorder");put_line(Fout, "");
	Tree.ReverseInOrderIterative(Customer'("Musco          ", "          "));
	put_line(Fout, "");
	put(Fout, "Traversing the Tree using PreOrder Iterative");put_line(Fout, "");
	Tree.PreOrderTraversalIterative;
	--End B Option

	--Start A Option
	put_line(Fout, "");
	put(Fout, "Traversing the Tree using PostOrder Iterative");put_line(Fout, "");
	Tree.PostOrderIterative;
	put_line(Fout, "");
	put(Fout, "Traversing the Tree using PostOrder Recursive");put_line(Fout, "");
	Tree.PostOrderRecursive;
	--End A Option
	close(Fout);
end uBST;
