	cpu 286
	org	100h
	
section .data
msg:	db	"Input an Integer", 0Dh, 0Ah, '$'
	
section .text

	call DEC_IN
	
	mov ah, 4ch     ; Close the Program
    mov al, 0       ;
    int 21h         ;
	
	
	
	

DEC_IN: 
	
	push bp
	push ax
	push dx
	
	
	mov ah, 9	;msg
	mov dx, msg
	int 21h
	
	mov ah, 1	;integer
	int 21h
	mov bx, ax
	
	;mov ah
	
	mov dl, bl	;print
	mov ah, 2
	int 21h
	
	pop ax
	pop dx
	
	ret
	
	
	
	
