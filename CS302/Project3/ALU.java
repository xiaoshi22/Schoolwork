import java.util.*;

/***
  * ALU Class implementing the ALU in our simulator.
  * Basic claculations and logic operations can be handled
  * in this class.
  * 
  * Methods that have been added or modified:
  * all methods and instance variables!
  */
 
public class ALU
{
    // instance variables 
    private int bits[]; // the value
    private int wordsize; // the word size
    private Bus A; // A
    private Register B; // B
    private Register C; // S
    private int flags[]; //flags
    /**
     * Constructor for object  alu used in CPU class
     */
    public ALU(int _wordsize)
    {
        // initialise instance variables
        wordsize = _wordsize;

        bits = new int[wordsize]; // the values

        for(int index = 0; index < bits.length; index++) {
            bits[index] = 0;
        }
        flags = new int[4];
    }

    /**
     * set_source_A(Bus bus) method - set the A of our ALU
     *
     * @param  bus      the source A we want to set
     */
    public void set_source_A(Bus bus){
        A = bus;
    }

    /**
     * set_source_A(Register reg) method - set the B of our ALU
     *
     * @param  reg      the source B we want to set
     */
    public void set_source_B(Register reg){
        B = reg;
    }

    /**
     * set_dest_C(Register reg) method - set the C of our ALU
     *
     * @param  reg      the destination C we want to set
     */
    public void set_dest_C(Register reg){
        C = reg;
    }

    /**
     * add() method - addtion operation
     */
    public void add(){
        int a = A.decimal();
        int b = B.decimal();

        int result = a + b;
        updateFlags(0,result); // update flags
        // if result is overflow, keep it in bound
        if (flags[3] == 1) result = result - (int)(Math.pow(2, wordsize/2))-1;
        C.store(result);
    }

    /**
     * sub() method - subtraction operation
     */
    public void sub(){
        int a = A.decimal();
        int b = B.decimal();

        int result = b - a;

        updateFlags(1,result); // update flags
        
        // if result is overflow, keep it in bound
        if (flags[3] == 1) result = result + (int)(Math.pow(2, wordsize/2))-1;
        C.store(result);
    }

    /**
     * and() method - and operation
     */
    public void and(){
        int a = A.decimal();
        int b = B.decimal();

        int result = a & b;

        updateFlags(2,result); // update flags
        C.store(result);
    }

    /**
     * or() method - or operation
     */
    public void orr(){
        int a = A.decimal();
        int b = B.decimal();

        int result = a | b;

        updateFlags(3,result); // update flags
        C.store(result);
    }

    /**
     * lsl() method - left shift operation
     */
    public void lsl(){
        int a = A.decimal();
        int b = B.decimal();

        int result = b<<a;
        updateFlags(4,result); // update flags
        // if result is overflow, keep it in bound
        if (flags[3] == 1){
            String str = Integer.toHexString(result);
            String temp = str.substring(str.length()-2, str.length());
            C.store(temp);
        } else 
            C.store(result);
    }

    /**
     * lsr() method - right shift operation
     */
    public void lsr(){
        int a = A.decimal();
        int b = B.decimal();

        int result = b>>a;
        updateFlags(5,result); // update flags
        C.store(result);
    }
    
    /**
     * initilizeFlags() method - set all flags be 0
     */
    public void initilizeFlags(){
        Arrays.fill(flags, 0);
    }

    /**
     * returnFlags() method - return a string including all flags
     */
    public String returnFlags(){
        String str = "N: "+ flags[0]+"   "+"Z: "+ flags[1]+"   "+"C: "+ flags[2]+"   "+"V: "+ flags[3];
        return str;
    }

    /**
     * updateFlags(int num, int result) method - update flags for a given operation ans a given result
     */
    private void updateFlags(int num, int result){
        int bound = (int)(Math.pow(2, wordsize/2))-1;
        // N: the result of the operation was Negative
        if (result < 0) flags[0] = 1;
        
        // Z: the result of the operation was Zero
        if (result == 0) flags[1] = 1;
        
        // C: if bit shift or overflow in addition or positive in substraction
        if ((num == 0 && result > bound) || (num == 1 && result >= 0) 
        || num == 4 || num == 5) flags[2] = 1;
        
        // V: if the number is not from 0 to 2^((wordsize/2)-1)
        if (result > bound || result < 0) flags[3] = 1;
    }
}