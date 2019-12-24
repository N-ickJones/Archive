	cpu 286
	org	100h
	
	
	;option2
	
section .data


section .text

	xor		ah,ah		; clear ah
	mov		al, 1		; 40x25 color display text mode
	int 	10h			;
	
	


section .bss
