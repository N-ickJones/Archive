generic
   type Element_Type is private;
   with function "<" (Left, right : element_type) return boolean;

package heapsort is
		type Collection is array(0..1000) of Element_Type;

	
	--type Education_type is (College, Associates, Bachelor, Master, PHD);
  --type yearEX_type is (oneToThree, threeToFive, fiveToTen, tenToTwenty, morethanTwenty);
  --type Rank is record
    --Education: Education_type;
    --yearEx: yearEX_type;
  --end record;
	
	procedure Generic_Heapsort;
   
--type my_array_type is array(Natural range<>) of Rank;
--package new_employee is new heapsort(Rank, Natural, my_array_type);
end heapsort;
