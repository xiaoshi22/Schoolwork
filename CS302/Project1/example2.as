.wordsize 16              
.regcnt    3              
.maxmem   0x10000000      

.pos 0x0
main:  ADDI x0, x0, #3     
       ORR x1, [x0, #0]  
       ADD  x2, x0, x1
	 STUR x0, [x2, #0] 
	HALT           
.pos 0x150                
.align 8                

data:  .single 0x01    
       .double 0x01   