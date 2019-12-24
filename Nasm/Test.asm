	cpu 286
	org	100h
	
section .data
msg:	db	"Input an Integer", 0Dh, 0Ah, '$'

section .text	
	
	mov ah, 9	;msg
	mov dx, msg
	int 21h
	
	mov ah, 1	;integer
	mov cx, 3
	
	
	
	; -32768 to 32767 signed 16bits
	
Loopy:	

	int 21h
	
	cmp al, 0Dh	; jump if enter
	je end
	
	cmp al, '-'  ;45 is ascii for negative sign
	je negativeSymbol
	
	
	sal		dx, 1		; dx *= 2
	and		al, 01h		; converts ASCII to binary value
	or		dl, al		; "adds" the input bit
	
	
	
	dec cx
	jmp Loopy
	
negativeSymbol:



	dec cx
	jmp Loopy
	
	
;set properly
	
end:
	
	;mov ah
	
	;mov dl, bl	;print
	;mov ah, 2
	;int 21h
	
	mov ah, 4ch     ; Close the Program
    mov al, 0       ;
    int 21h         ;
		