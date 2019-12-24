;Nicholas Jones COSC 2329 01
;Task to perform precision arithmetic with rcl rcr on 32 bit number

    org 100h

section .text

start:

    mov     dx, name    ;Get Name
    mov     ah, 9       ;Display String Function
    int     21h         ;Display Message

	mov		dx, msg		;Get Message
	mov		ah, 9		;Display String Function
	int		21h			;Display Message
	
	mov		ah, 1		;Input char Function
	mov		dx, 0		;Clear dx
	mov		bx, 0		;Clear bx
;-----------------------------------------------------------------
	mov		cx, 16		;Counter set to 16
Input1:
	int		21h			;Input char
	sal		bx, 1		;Shift bx left
	and		al, 01h		;Convert to binary from ascii
	or		bl, al		;Flips lsb to to 1 or keeps 0. Rest unchanged
	loop 	Input1		;
;-----------------------------------------------------------------
	mov		cx, 16		;Counter set to 16
Input2:
	int		21h			;Input char
	sal		dx, 1		;Shift dx left
	and		al, 01h		;Convert to binary from ascii
	or		dl, al		;Flips lsb to 1 or keeps 0. Rest unchanged
	loop 	Input2		;
;-----------------------------------------------------------------
NewLine:	
	push	dx			;Hold dx value while printing newline
	mov		dx, newline	;
	mov 	ah, 9		;
	int		21h			;Print newline
	pop		dx			;Give dx its binary value back
;-----------------------------------------------------------------	
	push	dx			;Hold dx value while printing bx
Output:
	mov		cx, 16		;Counter set to 16
	
Print_Loop:	
	rol		bx, 1		;Rotates msb to lsb changing CF to this value
	jc		one			;If checkflag is 1 then jump
	mov		dl,'0'		;else mov 0 into dl
	jmp		print		;
	
one:	mov	dl,'1'		;mov 1 into dl if CF was 1

print:	
	mov		ah, 2		;Print char function
	int		21h			;Print char in dl
	loop	Print_Loop	;
	pop		dx			;Give dx its binary value back
;-----------------------------------------------------------------
			
	mov		bx, dx		;move dx into bx so, dx cant be used for printing
Output2:
	mov		cx, 16		;Counter set to 16
	
Print_Loop2:	
	rol		bx, 1		;Rotates msb to lsb changing CF to this value
	jc		one2		;If checkflag is 1 then jump
	mov		dl, '0'		;else mov 0 into dl
	jmp		print2		;
	
one2:	mov	dl, '1'		;mov 1 into dl if CF was 1

print2:	
	mov		ah, 2		;Print char function
	int		21h			;Print char in dl
	loop	Print_Loop2	;
;-----------------------------------------------------------------
NewLine2:	
	mov		dx, newline	;Make a newline
	mov 	ah, 9		;
	int		21h			;
	
Check:	
		
	mov 	ah, 9		;Print string function
	mov 	dx, msg2	;Move msg2 into dx
	int 	21h			;Print String
	
	mov		dx, 0		;clear dx
	mov 	ah, 1		;Input char Function
	int 	21h			;Take char input
	and		al, 01h		;Convert to binary from ascii
	or 		dl, al		;Change dl to 0[stop] or value greater than 0[continue]
	
 	cmp 	dl, 0		;Compare dl if == 0 stop, if > 0 continue
	je 		end			;Jump to end if dl is 0
	jmp 	start		;Else Jump to start
	 	
	;End of Output--------------------------------------------
end:	
  	mov     ah, 4ch     ; Close the Program
    mov     al, 0       ;
    int     21h         ;


section .data
name:    db  "Nicholas Jones", 0Dh, 0Ah, '$'
msg:	 db	 "Input a 32bit Binary Number", 0Dh, 0Ah, '$'
msg2:	 db	 "Do you want to input another number(Input 0 to stop)", 0dh, 0Ah, '$'
newline: db	0Dh, 0Ah, '$'