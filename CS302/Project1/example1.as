.wordsize 16              
.regcnt    4              
.maxmem   0x10000000      

.pos 0x0
main:  ADDI x2, x2, #20     
       LDUR x1, [x2, #0]  
       ADD  x2, x2, x1
	B main 
	HALT           
.pos 0x100                
.align 8                

data:  .double 0x0AB    
        .single 0x0AB     
        .half   0x0AB     
        .byte   0x0AB  

.pos 0x200                
stack:  .double 0xDEF     