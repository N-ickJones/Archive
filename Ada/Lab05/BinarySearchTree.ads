-- Filename: BinarySearchTree.ads
-- Author: Nicholas Jones
-- Purpose: Fast Binary Search Tree w/ Threads
-- Date: 04/14/2019
-- Version: 1.0

with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Ada.Unchecked_Deallocation;

Generic
	type Data is private;
	with function "<"(aKey: in Data; aRec: in Data) return Boolean;
	with function ">"(aKey: in Data; aRec: in Data) return Boolean;
	with function "="(aKey: in Data; aRec: in Data) return Boolean;
	Fout: in out File_Type;
	with procedure put(aRec: in Data; X: Integer := 0);
	Max_Size: Integer;

Package BinarySearchTree is
	type NodePtr is private;
	procedure Insert(Input: Data);
	procedure FindIterative(Value: Data);
	procedure FindRecursive(Value: Data);
	procedure Delete(Value: Data);
	procedure PreOrderTraversalIterative;
	procedure ReverseInOrderIterative(Value: Data);
	procedure PostOrderIterative;
	procedure PostOrderRecursive;
	procedure Print;
	procedure Traverse_Inorder(Value: Data);
	procedure Traverse_Inorder_Successor;
	procedure Traverse_Inorder_Predeccesor;
	function Count return Integer;
	function Get_Node return Data;
	function Has_Located return Boolean;
private
	type Node;
	type NodePtr is access Node;
	type Node is record
		Llink: NodePtr;
		Ltag: Boolean := False;
		Info: Data;
		Rtag: Boolean := False;
		Rlink: NodePtr;
	end record;
	Root, Wrap, Find_Ptr, Find_P: NodePtr;
	Node_Count: Integer := 0;
	Top: Integer := 0;
	Stack: array(1..Max_Size) of Data;
	procedure FindRecursive(Pt: NodePtr;Value: Data);
	function InOrderSuccessor(Location: NodePtr) return NodePtr;
	function InOrderPredecessor(Location: NodePtr) return NodePtr;
	procedure Free is new Ada.Unchecked_Deallocation(Node, NodePtr);
	procedure Push(Input: in Data);
	procedure Pop(Output: out Data);
	procedure PostOrderRecursive(Q: in out NodePtr);
end BinarySearchTree;
