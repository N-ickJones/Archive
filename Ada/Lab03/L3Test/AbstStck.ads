with Ada.Unchecked_Deallocation;

package AbstStck is
  type AbstractStack is limited private;
  type AbstractStackElement is tagged private;
  type AbstractStackElementPtr is access all AbstractStackElement'Class;  
  procedure Push(Stack: access AbstractStack; Ip: in AbstractStackElementPtr);
  function   Pop(Stack: access AbstractStack) return AbstractStackElementPtr;
  function  StackSize(Stack: AbstractStack) return integer;
	--function "=" (aNode: AbstractStackElementPtr, 
		--valueToCompare: generic-parameter-for-comparison) return Boolean;
private
  type AbstractStackElement is tagged
    record
			LLink: AbstractStackElementPtr;
      RLink: AbstractStackElementPtr;
    end record;

  type AbstractStack is limited 
    record
			LLink: AbstractStackElementPtr;
      Count: integer := 0;
      RLink: AbstractStackElementPtr;
    end record;
		
	procedure Free is new Ada.Unchecked_Deallocation(AbstractStackElement'Class,AbstractStackElementPtr);
end AbstStck;
