#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdio.h>

int yourWish (int); 	//function prototype to satisfy your every wish
int hostileTakeOver();	//Code to hijack your computer by hostile takeover

void hope();

int main(int argc, char* argv[])
{
	static int (*fnc_ptr)(int choice); // Allocate in static memory (3rd alloc)
	static char buff[10]; //Buffer to overflow into function pointer (2nd)
	static float messyVariable; //1st

	//Seed random number generator using clock
	srand(time(NULL));

	//Assign "yourWish" to function pointer, used to execute yourWish indir
	fnc_ptr = yourWish;

	//Current memory assignments
	printf("Buffer at %p: value %s\n", buff, buff);
	printf("After string copy fnc_ptr at %p: w/value %p\n", &fnc_ptr, fnc_ptr);
	printf("atio(buff) = %d \n", atoi(buff));
	fnc_ptr(atoi(buff) + (rand() % 20)); //Should only invoke yourWish
	hope();	
}

int yourWish(int pick)
{
	printf("Your wish will be accomplished %d minutes\n", pick);
}

int hostileTakeOver()
{
	printf("This is a hostile takeover.\nYou have lost control of your environment\n");
}

void hope() 
{
	printf("The hostile takeover failed! :)- \n");
}

