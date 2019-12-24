with ada.text_Io; use ada.Text_IO;

package body AbstStck is

	package IntIO is new Ada.Text_IO.Integer_IO(Integer);  use IntIO;
	
	--Head : AbstractStackElementPtr;
	
	procedure Push(Stack: access AbstractStack; Ip: in AbstractStackElementPtr) is
    Pt: AbstractStackElementPtr;
  begin
		--if Stack.Count < 1 then
			--Right Insert
			Ip.LLink := Stack.RLink;			
			Stack.RLink := Ip;
			Stack.LLink := Ip;
			
			--Left Insert
			Ip.RLink := Stack.LLink;			
			Stack.LLink := Ip;
		
			--Head Build
			--Ip.LLink := Ip;
			--Ip.RLink := Ip;
			--Stack.LLink := Ip;
			--Stack.RLink := Ip;
			--2nd
			--Ip.LLink := Stack.RLink;
			--Ip.RLink := Stack.LLink;
			--if Stack.RLink = Stack.LLink and Ip.LLink = Ip.RLink then
			--	put("Equal");new_line;
			--end if;
			--Pt := Stack.RLink.RLink.RLink.RLink.RLink.RLink;
			--Pt := Stack.LLink.LLink.LLink.LLink.LLink.LLink;
		--elsif Stack.Count < 2 then
			--Ip.LLink := Head;
			--Ip.RLink := Pt.RLink;
			--Pt.LLink := Ip;
			--Pt.RLink := Ip;
			
			
			
			--Pt := Stack.RLink.RLink.RLink.RLink.RLink.RLink;
			--Pt := Stack.LLink.LLink.LLink.LLink.LLink.LLink;
		--else
			--Pt := Stack.RLink;
			--Ip.LLink := Pt;
			--Ip.RLink := Pt.RLink;
			--Pt.LLink := Ip;
			--Pt.RLink := Ip;
			--Pt := Stack.RLink.RLink.RLink.RLink.RLink.RLink;
			--Pt := Stack.LLink.LLink.LLink.LLink.LLink.LLink;
		--end if;
		Stack.Count := Stack.Count + 1;
  end Push;

  function Pop(Stack: access AbstractStack) return AbstractStackElementPtr is
   Pt: AbstractStackElementPtr;
  begin
   if Stack.RLink = null then 			-- Check for underflow.
     return null; 
   end if;
   Stack.Count := Stack.Count - 1;
   Pt := Stack.RLink;  
	 Stack.RLink := Stack.RLink.RLink;  	-- Pop stack, note hemorrhaging.
   return Pt;    									-- Storage should be returned to an available storage list for applications
  end Pop;     										-- with high activity or executing for extended periods of time.

  function StackSize(Stack: AbstractStack) return integer is
  begin  return Stack.Count;  end StackSize;
end AbstStck;
