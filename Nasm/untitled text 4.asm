	;-----------------------------------------------------------------
	
		;	procedure to print the value in bx as a base 2 number	
		
bin_out:
	push	ax			; save register being used
	push	cx
	push	dx
	mov	cx, 16			; loop counter
topPrint1:	rol	bx,1		; rotate msb into CF
	jc	one				; CF = 1?
	mov	dl,'0'			; no, set up to print a 0
	jmp	print			; now print
one:	mov	dl,'1'		; printing a 1
print:	mov	ah,2		; print char fcn
	int	21h				; print it
	loop	topPrint1	; loop until done
	pop	dx				; restore regs
	pop	cx
	pop	ax
	
	;-----------------------------------------------------------------
			;	procedure to print the value in bx as a base 2 number	
		
bin_out2:
	push	ax			; save register being used
	push	cx
	push	bx
	mov	cx, 16			; loop counter
topPrint2:	rol	bx,1		; rotate msb into CF
	jc	two				; CF = 1?
	mov	dl,'0'			; no, set up to print a 0
	jmp	print2			; now print
two:	mov	dl,'1'		; printing a 1
print2:	mov	ah,2		; print char fcn
	int	21h				; print it
	loop	topPrint2	; loop until done
	pop	bx				; restore regs
	pop	cx
	pop	ax


