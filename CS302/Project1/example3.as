.wordsize 16              
.regcnt    5             
.maxmem   0x10000000      

.pos 0x0
main:  ADDI x4, x4, #15     
       LDUR x1, [x4, #0]  
       ADD  x2, x1, x4
	  SUB x3, x2, x1
	EOR x1, x2, x3
	HALT           
.pos 0x100                
.align 16                

value:  .single 0xAB     
        .half   0xBC     
        .byte   0xCD  

.pos 0x200                
stack:  .single 0xABC     