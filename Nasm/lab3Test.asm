    org 100h

section .text

start:

	mov 	ah, 1
	mov		cx, 16
	
InputLoop:

	int		21h			; read next character
	sal		dx, 1		; dx *= 2
	and		al, 01h		; converts ASCII to binary value
	or		dl, al		; "adds" the input bit
	
	loop 	InputLoop	;
	
	push	dx
	mov		dx, newline
	mov 	ah, 9
	int		21h
	pop		dx
	
			;	procedure to print the value in bx as a base 2 number	
	mov 	bx, dx
bin_out:
	mov		cx, 16			; loop counter
	
topPrint_1:	
	rol		bx, 1			; rotate msb into CF
	jc		one				; CF = 1?
	mov		dl, '0'			; no, set up to print a 0
	jmp		print_1			; now print
	
one:	
	mov		dl, '1'	
	
print_1:	
	mov		ah, 2		; print char fcn
	int		21h				; print it
	loop	topPrint_1	; loop until done
	
	;End of Output--------------------------------------------
end:	
  	mov     ah, 4ch     ; Close the Program
    mov     al, 0       ;
    int     21h         ;


	section .data
name:    db  "Nicholas Jones", 0Dh, 0Ah, '$'
msg:	 db	 "Input a 32bit Binary Number", 0Dh, 0Ah, '$'
msg2:	 db	 "Do you want to input another number(Input 1 to continue)", 0dh, 0Ah, '$'
newline: db	0Dh, 0Ah, '$'