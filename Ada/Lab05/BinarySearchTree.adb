-- Filename: BinarySearchTree.adb
-- Author: Nicholas Jones
-- Purpose: Fast Binary Search Tree w/ Threads
-- Date: 04/14/2019
-- Version: 1.0

-- Quick Note: Instead of Giving Access to the the Tree
-- 						 directly. Make all the Node Pointers
--						 Internal and Limited Private is not needed.

package body BinarySearchTree is

	procedure Insert(Input: Data) is
		Ip: NodePtr;
		Q: NodePtr := new Node;
	begin
		if Root = Null then
			Q.Info := Input;
			Wrap := new Node;
			Wrap.RLink := Wrap;
			Wrap.LTag := True;
			Wrap.RTag := True;
			Wrap.LLink := Q;
			Q.LLink := Wrap;
			Q.RLink := Wrap;
			Root := Q;
		else
			Ip := Root;
			loop
				if Input < Ip.Info then
					if Ip.LTag = True then
						Ip := Ip.LLink;
					else
						Q.Info := Input;
						Q.LLink := Ip.LLink;
						Q.LTag := Ip.LTag;
						Ip.LLink := Q;
						Ip.LTag := True;
						Q.RLink := Ip;
						Q.RTag := False;
						if Q.LTag = True then
							InOrderPredecessor(Q).RLink := Q;
						end if;
						exit;
					end if;
				elsif Input > Ip.Info then
					if Ip.RTag = True then
						Ip := Ip.RLink;
					else
						Q.Info := Input;
						Q.RLink := Ip.RLink;
						Q.RTag := Ip.RTag;
						Ip.RLink := Q;
						Ip.RTag := True;
						Q.LLink := Ip;
						Q.LTag := False;
						if Q.RTag = True then
							InOrderSuccessor(Q).LLink := Q;
						end if;
						exit;
					end if;
				else
					loop
						if Ip.RTag = True then
							Ip := Ip.RLink;
							if Input = Ip.Info then
								Null;
							else
								Ip := Ip.LLink;
								exit;
							end if;
						else
							exit;
						end if;
					end loop;
					Q.Info := Input;
					Q.RLink := Ip.RLink;
					Q.RTag := Ip.RTag;
					Ip.RLink := Q;
					Ip.RTag := True;
					Q.LLink := Ip;
					Q.LTag := False;
					if Q.RTag = True then
						InOrderSuccessor(Q).LLink := Q;
					end if;
					exit;
				end if;
			end loop;
		end if;
		Node_Count := Node_Count + 1;
	end;

	procedure FindIterative(Value: Data) is
		Pt: NodePtr := Root;
	begin
		Find_P := Null;
		loop
			if Value < Pt.info then
				if Pt.LTag = True then
					Find_P := Pt;
					Pt := Pt.LLink;
				else
					Find_Ptr := Null;
					Find_P := Null;
					exit;
				end if;
			elsif Value > Pt.info then
				if Pt.RTag = True then
					Find_P := Pt;
					Pt := Pt.RLink;
				else
					Find_Ptr := Null;
					Find_P := Null;
					exit;
				end if;
			elsif Pt.info = Value then
				Find_Ptr := Pt;
				exit;
			else
				Find_Ptr := Null;
				Find_P := Null;
				exit;
			end if;
		end loop;
	end;

	procedure FindRecursive(Value: Data) is
	begin
		FindRecursive(Root, Value);
	end FindRecursive;

	procedure FindRecursive(Pt: NodePtr;Value: Data) is
	begin
		if Value < Pt.info then
			if Pt.LTag = True then
				FindRecursive(Pt.LLink, Value);
			else
				Find_Ptr := Null;
			end if;
		elsif Value > Pt.info then
			if Pt.RTag = True then
				FindRecursive(Pt.RLink, Value);
			else
				Find_Ptr := Null;
			end if;
		elsif Pt.info = Value then
			Find_Ptr := Pt;
		else
			Find_Ptr := Null;
		end if;
	end;

	procedure Delete(Value: Data) is
		Q, P, Succ, Pred, T, S: NodePtr;
	begin
		FindIterative(Value);
		if Find_Ptr /= Null then
			P := Find_P;
			Q := Find_Ptr;
			if Q = Root then
				P := Wrap;
			end if;
			if Q = P.LLink then -- Left Child of Parent
				if Q.LTag = False and Q.RTag = False then -- Parent's Left Child [Leaf]
					if Q = Root then
						Free(Root);
						Free(Wrap);
					else
						P.LLink := Q.LLink;
						P.LTag := Q.LTag;
						Free(Q);
					end if;
				elsif Q.LTag = True and Q.RTag = False then -- Par Left Child [Has LC]
					Succ := InOrderSuccessor(Q);
					Pred := InOrderPredecessor(Q);
					Pred.RLink := Succ;
					P.LLink := Q.LLink;
					if Q = Root then
						Root := Root.LLink;
					end if;
					Free(Q);
				elsif Q.LTag = False and Q.RTag = True then -- Par Left Child [Has RC]
					Succ := InOrderSuccessor(Q);
					Pred := InOrderPredecessor(Q);
					Succ.LLink := Pred;
					P.LLink := Q.RLink;
					if Q = Root then
						Root := Root.RLink;
					end if;
					Free(Q);
				elsif Q.LTag = True and Q.RTag = True then -- Par Left Child [Has Both]
					T := Q;
					Q := T.RLink;
					if Q.LTag = False then
						Pred := InOrderPredecessor(T);
						Q.LLink := T.LLink;
						Q.LTag := T.LTag;
						P.LLink := Q;
						Pred.RLink := Q;
						if T = Root then
							Root := Root.RLink;
						end if;
						Free(T);
					else
						S := Q.LLink;
						While S.LTag = True loop
							Q := S;
							S := Q.LLink;
						end loop;
						T.info := S.info;
						if S.RTag = True then
							Succ := InOrderSuccessor(S);
							Succ.LLink := S.LLink;
							Q.LLink := S.RLink;
							Free(S);
						else
							Q.LLink := S.LLink;
							Q.LTag := S.LTag;
							Free(S);
						end if;
					end if;
				end if;
			elsif Q = P.RLink then -- Right Child of Parent
				if Q.LTag = False and Q.RTag = False then -- Parent's Right Child [Leaf]
					P.RLink := Q.RLink;
					P.RTag := Q.RTag;
					Free(Q);
				elsif Q.LTag = True and Q.RTag = False then -- Par Right Child [Has LC]
					Succ := InOrderSuccessor(Q);
					Pred := InOrderPredecessor(Q);
					Pred.RLink := Succ;
					P.RLink := Q.RLink;
					Free(Q);
				elsif Q.LTag = False and Q.RTag = True then -- Par Right Child [Has RC]
					Succ := InOrderSuccessor(Q);
					Pred := InOrderPredecessor(Q);
					Succ.LLink := Pred;
					P.RLink := Q.LLink;
					Free(Q);
				elsif Q.LTag = True and Q.RTag = True then -- Par Right Child [Has Both]
					T := Q;
					Q := T.RLink;
					if Q.LTag = False then
						Q.LLink := T.LLink;
						Q.LTag := T.LTag;
						P.RLink := Q;
						Free(T);
					else
						S := Q.LLink;
						While S.LTag = True loop
							Q := S;
							S := Q.LLink;
						end loop;
						T.info := S.info;
						if S.RTag = True then
							Succ := InOrderSuccessor(S);
							Succ.LLink := S.LLink;
							Q.LLink := S.RLink;
							Free(S);
						else
							Q.LLink := S.LLink;
							Q.LTag := S.LTag;
							Free(S);
						end if;
					end if;
				end if;
			end if;
			Node_Count := Node_Count - 1;
		else
			put(fOut, "Unable to Find & Delete: ");
			put(Value);
		end if;
	end;

	function InOrderSuccessor(Location: NodePtr) return NodePtr is
		Q: NodePtr;
	begin
		Q := Location.Rlink;
		if Location.RTag = True then
			while Q.LTag = True loop
				Q := Q.LLink;
			end loop;
		end if;
		return Q;
	end;

	function InOrderPredecessor(Location: NodePtr) return NodePtr is
		Q: NodePtr;
	begin
		Q := Location.LLink;
		if Location.LTag = True then
			while Q.RTag = True loop
				Q := Q.RLink;
			end loop;
		end if;
		return Q;
	end;

	procedure PreOrderTraversalIterative is
		Pt: NodePtr := Root;			-- A Stack is Overhead.
		Switch: Boolean := False; -- How about a single boolean check instead?
	begin												-- Takes Advantage of the Threads.
		loop
			if Switch = False then
				if Pt.Ltag = True then
					if Pt /= Wrap then
						put(Pt.info);
					else
						exit;
					end if;
					Pt := Pt.LLink;
				else
					if Pt /= Wrap then
						put(Pt.info);
					else
						exit;
					end if;
					Switch := True;
				end if;
			else
				if Pt.RTag = True then
					Pt := Pt.RLink;
					Switch := False;
				else
					Pt := Pt.RLink;
				end if;
			end if;
		end loop;
	end;

	procedure ReverseInOrderIterative(Value: Data) is
		t: NodePtr;
		Count: Integer := Node_Count;
	begin
		FindIterative(Value);
		if Has_Located then
			t := Find_Ptr;
			while Count > 0 loop
				if t.RTag = True then
					t := t.RLink;
				else
					if t /= Wrap then
						put(t.info);
						Count := Count - 1;
					end if;
					loop
						if t.Ltag = True then
							t := t.LLink;
							exit;
						else
							if Count > 0 then
								t := T.LLink;
								if t /= Wrap then
									put(t.info);
									Count := Count - 1;
								end if;
							else
								exit;
							end if;
						end if;
					end loop;
				end if;
			end loop;
		else
			put(Fout, "Unable to Find: ");put(Value);put_line(Fout, "");
		end if;
	end;

	procedure PostOrderIterative is
		Q, P: NodePtr := Root;
	begin
		for i in 1..Node_Count loop
			P := Q;
			if P.RTag = True then
				Q := P.RLink;
			else
				while Q.LTag = False loop
					Q := Q.LLink;
				end loop;
				Q := Q.LLink;
			end if;
			Push(Q.info);
		end loop;
		for i in 1..Node_Count loop
			pop(Q.Info);
			put(Q.Info, 1);
		end loop;
	end;

	procedure PostOrderRecursive is
	begin
		PostOrderRecursive(Root);
	end;

	procedure PostOrderRecursive(Q: in out NodePtr) is
		P: NodePtr;
	begin
		P := Q;
		if P.RTag = True then
			Q := P.RLink;
		else
			while Q.LTag = False loop
				Q := Q.LLink;
			end loop;
			Q := Q.LLink;
		end if;
		if Q /= Root then
			Push(Q.info);
			PostOrderRecursive(Q);
		else
			Push(Q.info);
			for i in 1..Node_Count loop
				pop(Q.info);
				put(Q.info, 1);
			end loop;
		end if;
	end;

	procedure Print is
		t: NodePtr := Root;
		Count: Integer := Node_Count;
	begin
		while Count > 0 loop
			if t.LTag = True then
				t := t.LLink;
			else
				put(t.info);
				Count := Count - 1;
				loop
					if t.Rtag = True then
						t := t.RLink;
						exit;
					else
						if Count > 0 then
							t := T.RLink;
							put(t.info);
							Count := Count - 1;
						else
							exit;
						end if;
					end if;
				end loop;
			end if;
		end loop;
	end Print;

	procedure Traverse_Inorder(Value: Data) is
		t: NodePtr;
		Count: Integer := Node_Count;
	begin
		FindIterative(Value);
		if Has_Located then
			t := Find_Ptr;
			while Count > 0 loop
				if t.LTag = True then
					t := t.LLink;
				else
					if t /= Wrap then
						put(t.info);
						Count := Count - 1;
					end if;
					loop
						if t.Rtag = True then
							t := t.RLink;
							exit;
						else
							if Count > 0 then
								t := T.RLink;
								if t /= Wrap then
									put(t.info);
									Count := Count - 1;
								end if;
							else
								exit;
							end if;
						end if;
					end loop;
				end if;
			end loop;
		else
			put(Fout, "Unable to Find: ");put(Value);put_line(Fout, "");
		end if;
	end Traverse_Inorder;

	procedure Traverse_Inorder_Successor is
		Pt: NodePtr := Root;
		Count: Integer := Node_Count;
	begin
		while Count > 0 loop
			Pt := InorderSuccessor(Pt);
			if Pt /= Wrap then
				put(Pt.info);
				Count := Count - 1;
			end if;
		end loop;
	end Traverse_Inorder_Successor;

	procedure Traverse_Inorder_Predeccesor is
		Pt: NodePtr := Root;
		Count: Integer := Node_Count;
	begin
		while Count > 0 loop
			Pt := InOrderPredecessor(Pt);
			if Pt /= Wrap then
				put(Pt.info);
				Count := Count - 1;
			end if;
		end loop;
	end Traverse_Inorder_Predeccesor;

	function Get_Node return Data is
		Temp_Node: Data;
	begin
		if Find_Ptr /= Null then
			Temp_Node := Find_Ptr.Info;
		end if;
		Return Temp_Node;
	end Get_Node;

	function Has_Located return Boolean is
	begin
		if Find_Ptr /= Null then
			return True;
		else
			return False;
		end if;
	end Has_Located;

	function Count return Integer is
	begin
		return Node_Count;
	end Count;

	procedure Push(Input: in Data) is
	begin
		if Top <= Max_Size then
			Top := Top + 1;
			Stack(Top) := Input;
		else
			put("Overflow Occured");new_line;
		end if;
	end;

	procedure Pop(Output: out Data) is
	begin
		if Top > 0 then
			Output := Stack(Top);
			Top := Top - 1;
		else
			put("Underflow Occured");new_line;
		end if;
	end;

end BinarySearchTree;
