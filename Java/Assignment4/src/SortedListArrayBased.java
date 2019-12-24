/*
Name    : Nicholas Jones
Purpose : Array-based implementation of the ADT Sorted List.
Date    : 2/27/2018
Version : 1.0
*/

 /**
 * class SortredListArrayBased
 *
 *    A class gets inherited from ListArrayBased
 *
 */

public class SortedListArrayBased extends ListArrayBased {

  	public SortedListArrayBased() {
		//Already created when extended from superclass default constructor
  	}

  	public void add(Object item) throws ListException {
		try {
			super.add(locateIndexToAdd(item), item);
		}
		catch(Exception e) {
			throw new ListException("Add to List failed:  " + e.toString());
		}

	}

  	public void remove(Object item) throws ListException {
		try {
		    if(!(locateIndexToRemove(item) == -1))
			    super.remove(locateIndexToRemove(item));
		}
		catch(Exception e) {
			throw new ListException("Remove " + item.toString() + " from List failed:  " + e.toString());
		}
	}

    public int locateIndexToAdd(Object item) {

  	    int index = 0;

  	    for(int i = 0; i < super.size(); i++)
  	        if(super.get(i).toString().compareTo(item.toString()) < 0)
  	            index++;

        return index;
	}

  	public int locateIndexToRemove(Object item) {

        for(int i = 0; i < super.size(); i++)
            if(super.get(i).toString().compareTo(item.toString()) == 0)
                return i;

		return -1;
	}
}