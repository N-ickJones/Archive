#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    int var1 = 1;
    int var2[10];
    int var3 = 3;
    int var4 = 56;
    int offSet;
    int dist;
    int *intPt;
    char charArray[2];
    charArray[0] = 'A';
    charArray[1] = '\0';
    
    for (offSet = 0; offSet < 10; offSet++)
    {
        var2[offSet] = 0;
    }

	printf("Address var1: 0x%x\n", &var1);
	printf("Address var3: 0x%x\n", &var3);
	printf("Address var4: ox%x\n", &var4);

	//Determine the "distance" for the search and destroy!
	if (argc > 1)
	{
		dist = atoi(argv[1]);
	}
	else
	{
		dist = 0;
	}

	printf("var1 = : 0x%x\n", var1);
	printf("var2[0]: 0x%x\n", var2[0]);
	printf("var2[1]: 0x%x\n", var2[1]);
	printf("var3 = : 0x%x\n", var3);
	printf("var4 = : 0x%x\n", var4);
	
	/*
	Index from high in stack (low address) to lower in stack (high address)
	While the effect is the same as buffer overflow using "strcpy()" 
	We are using simple memory indexing. This reflect the power of "C" when
	used properly, It is normal coding technique, not improper coding.
	Code auditing software, IDS, etcetera are not likely to detect the intent
	*/
	
	intPt = &var4;
	for (offSet = 0; offSet < dist; offSet += 1)
	{
		//printf("intPt + offSet = : 0x%x\n", intPt + offSet);
		// Store 16base10 (10 hex) at the specified address. Inject Attack!
		*(intPt + offSet) = 16;
	}

	printf("var1 = : 0x%x\n", var1);
	printf("var2[0]: 0x%x\n", var2[0]);
	printf("var2[1]: 0x%x\n", var2[1]);
	printf("var3 = : 0x%x\n", var3);
	printf("var4 = : 0x%x\n", var4);
					
	return 0;
}
