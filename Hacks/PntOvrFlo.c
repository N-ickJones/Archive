#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdio.h>

int yourWish (int); 	//function prototype to satisfy your every wish
int hostileTakeOver();	//Code to hijack your computer by hostile takeover

int main(int argc, char* argv[])
{
	static int (*fnc_ptr)(int choice); // Allocate in static memory (2nd alloc)
	static char buff[10]; //Buffer to overflow into function pointer (1st)

	//Seed random number generator using clock
	srand(time(NULL));

	//Assign "yourWish" to function pointer, used to execute yourWish indir
	fnc_ptr = yourWish;

	//Current memory assignments
	printf("Before string copy fnc_ptr at %p: w/value %p\n", &fnc_ptr, fnc_ptr);
	strcpy(buff, argv[1]); //We do the function pointer overflow here!
	printf("Buffer at %p: value %s\n", buff, buff);
	printf("After string copy fnc_ptr at %p: w/value %p\n", &fnc_ptr, fnc_ptr);
	
	fnc_ptr(atoi(buff) + (rand() % 20)); //Should only invoke yourWish
}

int yourWish(int pick)
{
	printf("Your wish will be accomplished %d minutes\n", pick);
}

int hostileTakeOver()
{
	printf("This is a hostile takeover.\nYou have lost control of your environment\n");
}





