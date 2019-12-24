--type Car is new AbstStck.AbstractStackElement with record
--     NumDoors:  integer;
--     Manufacturer: String4 := "GMC ";  -- Sample default value.
--  end record;

with Ada.Text_IO; use Ada.Text_io;
with AbstStck; use AbstStck; 
with MakeCar; use MakeCar;

procedure UAbstStc is
  type Stack_Ptr is access AbstractStack;
  CarStack:  Stack_Ptr := new AbstractStack;
  StackPoint: Stack_Ptr;
  NewCar, CarPt: AbstractStackElementPtr;
  
begin  --Create 1st car.

  NewCar := new Car'(AbstractStackElement with 1, "Ford");
  push(CarStack, NewCar);
	
	NewCar := new Car'(AbstractStackElement with 2, "Ford");
  push(CarStack, NewCar);
	
	NewCar := new Car'(AbstractStackElement with 3, "Ford");
  push(CarStack, NewCar);  
	
	NewCar := new Car'(AbstractStackElement with 4, "Ford");
  push(CarStack, NewCar);

  --NewCar := new Car;  -- Create 2nd car.
  --AssignNumDoors(Car'Class(NewCar.All), 2);
  --AssignManufacturer(Car'Class(NewCar.all), "Chev");
  --push(CarStack, NewCar);  

  --NewCar := new Car;  -- Create 3rd car.
  --AssignNumDoors(Car'Class(NewCar.All), 2);
   --Default manufacturer to "GMC ".
  --push(CarStack, NewCar); 
  
  --for I in 1..StackSize(CarStack.all) loop 
  --  CarPt := pop(CarStack);
  --  PrintManufacturer(Car'Class(CarPt.All));
  --  PrintNumDoors(Car'Class(CarPt.All));
  --  new_line;
	--end loop;

end UAbstStc;