#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
	FILE *fd;
	char *auditText = malloc(20);
	char *auditFileName = malloc(20);

	if (argc < 2 )
	{
		printf("Usage: %s <audit text follow by auditfile\n", argv[0]);
		exit(0);	
	}

	//Copy data into heap memory
	strcpy(auditFileName, argv[2]);
	strcpy(auditText, argv[1]);

	//Print out some debug messages
	printf("--DEBUG--\n");
	printf("[*] audit text @ %p: %s\n", auditText, auditText);
	printf("[*] audit file @ %p: %s\n", auditFileName, auditFileName);
	printf("[*] distance between: %d\n", auditFileName - auditText);
	printf("--------\n\n");

	//Writing the data out to the file.
	printf("Writing to \"%s\" to the end of %s...\n", auditText, auditFileName);
	
	fd = fopen(auditFileName, "a");
	if (fd == NULL)
	{
		fprintf(stderr, "error opening %s\n", auditFileName);
		exit(1);
	}
	
	fprintf(fd, "%s\n", auditText);
	fclose(fd);

	return 0;
}

