	cpu	286
 	org	100h

section .data

section .text	
start:
	mov 	bx,0	; bx holds input value
	mov	ah,1		; input char function
	int	21h		    ; read char into al

top1:	cmp	al,0Dh	; is char = CR?
    je out1;
	sal	bx,3    	; bx *= 16
	and	al,0Fh		; converts ASCII to binary value
	jmp	addit		; ready to add to bx

addit:	or	bl,al	; "adds" the input bit
	int	21h		    ; read next character
	jmp	top1		; loop until done

out1:
	;BINARY
	mov	cx, 16		; loop counter
top2:	rol	bx,1	; rotate msb into CF
	jc	one		    ; CF = 1?
	mov	dl,'0'		; no, set up to print a 0
	jmp	print		; now print

one:	mov	dl,'1'		; printing a 1
print:	mov	ah,2		; print char fcn
	int	21h		; print it
	loop	top2		; loop until done
	
Exit:
	mov     ah,04Ch           ;DOS function: Exit program
	mov     al,0              ;Return exit code value
	int     21h               ;Call DOS.  Terminate program