
--Filename:  dataArray.ads
--Author:    Nicholas Jones
--Date:	     02/21/2019
--Purpose:   To create a Generic Array
--Version:   1.0
--Reference: Dr. Burris Notes

generic
  type subscript is (<>);
  type myType is private;

package dataArray is
  type userDefinedArray is array(subscript range <>) of myType;
end dataArray;
