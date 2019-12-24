 org 100h


section .text
        
start:
   mov   dx,nme                     ; get name message
   mov   ah, 9                      ; display string function
   int   21h                        ; display message
   
   mov   dx,pmt                    ; ask for initials input
   mov   ah, 9                      ; display string function
   int   21h                        ; display message
   
   mov   ah, 1                      ; read char function
   int   21h                        ; input char into al
   mov  [charA], al                 ; store char in al to char1
   
   mov   ah, 1                      ; read char function
   int   21h                        ; input char into al
   mov  [charB], al                 ; store char in al to char2
   
   mov   ah, 1                      ; read char function
   int   21h                        ; input char into al
   mov   [charC], al                ; store char in al to char3
   
   mov   dx, newline                ; make a new line
   mov   ah, 9                      ; display string function
   int   21h                        ; display new line
   
   mov   dx, charA                  ; get char1 message
   mov   ah, 9                      ; display string function
   int   21h                        ; display char1 message
   
   mov   dx, newline                ; make a new line
   mov   ah, 9                      ; display string function
   int   21h                        ; display new line
   
   mov   dx, charB                  ; get char2 message
   mov   ah, 9                      ; display string function
   int   21h                        ; display char2 message
   
   mov   dx, newline                ; make a new line
   mov   ah, 9                      ; display string function
   int   21h                        ; display new line
   
   mov   dx, charC                  ; get char3 message
   mov   ah, 9                      ; display string function
   int   21h                        ; display char3 message


   ;exit DOS
   mov   ah, 4Ch                    ; Dos function: exit program
   mov   al, 0                      ; return exit code value
   int   21h                        ; Call Dos & Terminate program
   

section .data
	nme:    db   "Jose Chapela", 0Dh, 0Ah, '$'
	pmt: 	db   "Enter 3 characters from the keyboard : ", '$'
	newline:db   0Dh, 0ah, '$'
	charA:  db   ' ', '$'
	charB:  db   ' ', '$'
	charC:  db   ' ', '$'   

