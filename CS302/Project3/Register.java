/***
 * Register class for implementing memory components
 * that hang together thru the bus.
 */ 

public class Register {
    // instance variables
    private int bits[]; // the value
    private int wordsize; // the word size

    private Bus source; // source
    private Bus destination; // destination

    /**
     * Constructor for objects registers used in CPU class and other classes
     */
    public Register(int _wordsize) {
        // initialise instance variables
        wordsize = _wordsize;

        bits = new int[wordsize]; // set all bits to be 0
        for(int index = 0; index < bits.length; index++) {
            bits[index] = 0;
        }
    }

    /**
     * set_source_bus(Bus bus) method - set the source bus of our register
     *
     * @param  bus      the source bus we want to set
     */
    public void set_source_bus(Bus bus) {
        source = bus;
    }

    /**
     * set_destination_bus(Bus bus) method - set the destination bus of our register
     *
     * @param  bus      the destination bus we want to set
     */
    public void set_destination_bus(Bus bus) {
        destination = bus;
    }

    /**
     * load() method - load the value from bus
     */
    public void load() {
        for(int cnt = 0; cnt < wordsize; cnt++) {
            bits[cnt] = source.bits[cnt];
        }
    }

    /**
     * store() method - store the value to bus
     */
    public void store() {
        for(int cnt = 0; cnt < wordsize; cnt++) {
            destination.bits[cnt] = bits[cnt];
        }
    }

    /**
     * increment() method - increase the value by 1
     */
    public void increment() {
        increment(1);
    }

    /**
     * increment(int times) method - increate the value by some times
     * 
     * @ param times      the number of times we want to increment
     */
    public void increment(int times) {
        for(int cnt = 0; cnt < times; cnt++) {

            int carry = 1;

            for(int inner_cnt = 0; inner_cnt < wordsize; inner_cnt++) {

                bits[inner_cnt] += carry;

                if (bits[inner_cnt] > 1) {
                    bits[inner_cnt] = 0;
                    carry = 1;
                } else {
                    carry = 0;
                }
            }
        }
    }

    /**
     * negate() method - negate the value 
     */
    public void negate() {
        for(int cnt = 0; cnt < wordsize; cnt++) {

            if (bits[cnt] == 1) {
                bits[cnt] = 0;
            } else {
                bits[cnt] = 1;
            }
        }

        increment();
    }

    /**
     * decimal() method _ return this value in decimal
     *
     * @return    a int in decimal
     */
    public int decimal() {
        int pow_value = 1;
        int value     = 0;

        for(int index = 0; index < bits.length; index++) {

            if (bits[index] == 1) {
                value += pow_value;
            }
            pow_value *= 2;
        }

        return value;
    }

    /**
     * decimal(int high, int low) method - return part of this value of given positions
     *
     * @param  high  the highest digit of the substring we need 
     * @param  low   the lowested digit of the substring we need
     * @return       an integer we want
     */
    public int decimal(int high, int low) throws Exception {
        String result = "";

        if (low > high) 
            throw new Exception("Binary range values should have a low " +
                "value less than or equal to the high");
        int pow_value = 1;
        int value     = 0;

        for(int index = low; index <= high; index++) {

            value     += bits[index] * pow_value;
            pow_value *= 2;
        }

        return value;
    }

    /**
     * hex() method _ return this value in hex
     *
     * @return    a hex word
     */
    public String hex() {
        int pow_value = 1;
        int value     = 0;

        for(int index = 0; index < bits.length; index++) {

            if (bits[index] == 1) {
                value += pow_value;
            }
            pow_value *= 2;
        }

        return String.format("%02X", value);
    }

    /**
     * binary() method _ return this value in binary
     *
     * @return    a binary word
     */
    public String binary() {
        String result = "";

        for(int index = (bits.length - 1); index >= 0; index--) {

            if (bits[index] == 1) {
                result += "1";
            } else {
                result += "0";
            }
        }

        return result;
    }

    /**
     * binary(int high, int low) method - return part of this value of given positions
     *
     * @param  high  the highest digit of the substring we need 
     * @param  low   the lowested digit of the substring we need
     * @return       a string of the substring we need
     */
    public String binary(int high, int low) throws Exception {
        String result = "";

        if (low > high) 
            throw new Exception("Binary range values should have a low " +
                "value less than or equal to the high");

        for(int index = high; index >= low; index--) {

            if (bits[index] == 1) {
                result += "1";
            } else {
                result += "0";
            }
        }

        return result;
    }

    /**
     * store(int value) method - store an integer to our register
     *
     * @param  value  the number we want to store
     */
    public void store(int value) {    
        for(int index = 0; index < bits.length; index++) {
            bits[index] = value % 2;
            value       = value / 2;
        }
    }

    /**
     * store(int value) method - store a hex number to our register
     *
     * @param  value  the number we want to store
     */
    public void store(String value) {
        int int_value = Integer.parseInt(value, 16);

        for(int index = 0; index < bits.length; index++) {
            bits[index] = int_value % 2;
            int_value   = int_value / 2;
        }
    }

    /**
     * main method - test or illustrate methods in this class
     */
    public static void main(String args[]) {

        /*** Examples of usage. ***/
        Register a = new Register(64); // construct a register of wordsize 64

        try {
            a.store(0xAAFF); // set the value to be 0xAAFF

            // test all return methods  
            System.out.println(a.hex());
            System.out.println(a.binary());
            System.out.println(a.binary(3,0));
            System.out.println(a.binary(2,0));
            System.out.println(a.binary(18,13));
            System.out.println(a.binary(13,13));
            System.out.println(a.binary(14,14));
            System.out.println(a.binary(15,15));
            System.out.println(a.binary(16,16));
            System.out.println(a.binary(17,17));
            System.out.println(a.binary(18,18));

            // and alse the increment and negate method
            a.increment();
            System.out.println(a.binary());
            a.increment();
            System.out.println(a.binary());
            a.increment();
            System.out.println(a.binary());
            a.increment();
            System.out.println(a.binary());
            a.increment();
            System.out.println(a.binary());
            a.increment();
            System.out.println(a.binary());
            a.negate();
            System.out.println(a.binary());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}