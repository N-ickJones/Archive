

package body AbLab03 is
	
	procedure Push(Stack: access AbstractStack; Y: in AbstractStackElementPtr) is
		Pt: AbstractStackElementPtr;
	begin
		Y.Next := Stack.Top;
		Stack.Top := Y;
		Stack.Count := Stack.Count + 1;
	end Push;
	
	procedure Pop(Stack: access AbtractStack) return AbstractStackElementPtr is 
		Pt: AbstractStackElementPtr;
	begin
		if Stack.Top = Null then
			return null;
		end if;
		Stack.Count := Stack.Count - 1;
		Pt := Stack.Top;
		Stack.Top := Stack.Top.Next;
		return Pt;
	end Pop;
	
	function StackSize(Stack: AbstractStack) return integer is
	begin
		return Stack.Count
	end StackSize;
	
end AbLab03;