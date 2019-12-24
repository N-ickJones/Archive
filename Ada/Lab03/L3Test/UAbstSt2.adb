--Using heterogeneous stack with cars and planes in same stack.

with Ada.Text_IO; use Ada.Text_io;
with AbstStck; use AbstStck; 
with MakeCar, MakePlane; use MakeCar, MakePlane;

procedure  UAbstSt2 is
  --type Stack_Ptr is access AbstractStack;
  --VehicleStack:  Stack_Ptr := new AbstractStack;
  
  --StackPoint: Stack_Ptr;
  --NewCar, CarPt, NewPlane, PlanePt, VehiclePt:
  --        AbstractStackElementPtr;

begin
 -- NewCar := new Car'(AbstractStackElement with 4, "Ford");  -- Heap allocation!
  --push(VehicleStack, NewCar); -- 1st car.

  NewPlane := new Plane'(AbstractStackElement with 2, 2, "Northrup");  -- in heap!
  push(VehicleStack, NewPlane);  --1st plane.

for I in 1..StackSize(VehicleStack.all) loop
    VehiclePt := pop(VehicleStack);
    if VehiclePt.all in Car then  -- ** Identify class of object at run time.
      IdentifyVehicle(Car'Class(VehiclePt.all));
    elsif VehiclePt.all in Plane then
      IdentifyVehicle(Plane'Class(VehiclePt.all));
    end if;
    new_line;
  end loop;

end UAbstSt2;
