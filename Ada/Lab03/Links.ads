--Filename:  Links.ads
--Author:    Nicholas Jones
--Date:	     03/24/2019
--Purpose:   AbstractStack
--Version:   1.0
--Reference: Dr. Burris Notes

with ada.text_io; use ada.text_io;
with Ada.Unchecked_Deallocation;

package Links is
	package intIO is new Ada.Text_IO.Integer_IO(integer); use intIO;
		
	type Node is tagged private;
	type NodePtr is access all Node'Class;
	
	procedure Insert_Left(Ip: in NodePtr);
	procedure Insert_Right(Ip: in NodePtr);
	procedure DeleteNode;
	procedure InitPt;
	function NextNode(Direction: in character := 'R') return NodePtr;
	function Size return Integer;
	procedure Clear;
	
private
	type Node is tagged record
		LLink: NodePtr;
		RLink: NodePtr;
	end record;

	type Head is new Node with record
     Count: Integer := 0;
  end record;
	
	HeadNode: NodePtr := new Head;
	Pt: NodePtr;
	
	procedure Free is new Ada.Unchecked_Deallocation(Node'Class, NodePtr);
	
end Links;
