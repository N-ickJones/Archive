--Filename:  Links.adb
--Author:    Nicholas Jones
--Date:	     03/24/2019
--Purpose:   AbstractStack
--Version:   1.0
--Reference: Dr. Burris Notes

package body Links is
	
	procedure Insert_Left(Ip: in NodePtr) is
	begin
		if Head'Class(HeadNode.All).Count = 0 then
			HeadNode.Rlink := Ip;
			HeadNode.LLink := Ip;
			Ip.LLink := HeadNode;
			Ip.RLink := HeadNode;
		else
			Ip.LLink := HeadNode.LLink;
			Ip.RLink := HeadNode;
			HeadNode.LLink.RLink := Ip;
			HeadNode.LLink := Ip;
		end if;
		Head'Class(HeadNode.All).Count := Head'Class(HeadNode.All).Count + 1;
	end Insert_Left;

	procedure Insert_Right(Ip: in NodePtr) is
	begin
		if Head'Class(HeadNode.All).Count = 0 then
			HeadNode.Rlink := Ip;
			HeadNode.LLink := Ip;
			Ip.LLink := HeadNode;
			Ip.RLink := HeadNode;
		else
			Ip.LLink := HeadNode;
			Ip.RLink := HeadNode.RLink;
			HeadNode.Rlink.LLink := Ip;
			HeadNode.Rlink := Ip;
		end if;
		Head'Class(HeadNode.All).Count := Head'Class(HeadNode.All).Count + 1;
	end Insert_Right;
	
	procedure DeleteNode is
		Dp: NodePtr := Pt;
	begin
		if Dp /= HeadNode then
			Dp.LLink.RLink := Dp.RLink;
			Dp.RLink.LLink := Dp.LLink;
			Pt := HeadNode;
			free(Dp);
			Head'Class(HeadNode.All).Count := Head'Class(HeadNode.All).Count - 1;
		end if;	
	end DeleteNode;
	
	procedure InitPt is
	begin
		Pt := HeadNode;
	end InitPt;
	
	function NextNode(Direction: in character := 'R') return NodePtr is
	begin
		if Direction = 'L' then
			Pt := Pt.LLink;
		else
			Pt := Pt.RLink;
		end if;
		return Pt;
	end NextNode;
	
	function Size return Integer is
	begin
		return Head'Class(HeadNode.All).Count;
	end Size;
	
	procedure Clear is
	begin
		Pt := HeadNode.RLink;
		while Pt /= HeadNode loop
			DeleteNode;
			Pt := Pt.RLink;
		end loop;
	end Clear;
	
end Links;

