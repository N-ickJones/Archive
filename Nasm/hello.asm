; HelloWorld in NASM assembly
;    *  to assemble, use the command "nasm hello.asm -o hello.com"
;    *  to execute in DOSBox, type "hello"

	org 100h

section .text

start:

    mov dx, msg
	mov ah, 9
	int 21h


	mov ah, 4Ch		; call for sys_exit
	mov al, 0		; normal termination code
	int 21h			; return to OS

section .data
msg:    db "Hello Dude", 0Dh, 0Ah, '$'

	

