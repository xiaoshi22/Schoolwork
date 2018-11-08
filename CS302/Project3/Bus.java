/***
 *  Bus class used for temporarly storing 
 *  values between registers.
 */

public class Bus {
    // instance variables
    public  int bits[];
    private int wordsize;

    /**
     * Constructor for objects bus used in CPU class
     */
    public Bus(int _wordsize) {
        // initialise instance variables
        wordsize = _wordsize;

        bits = new int[wordsize]; // construct a all-zero bits array
        for(int index = 0; index < bits.length; index++) {
            bits[index] = 0;
        }
    }

    /**
     * decimal() method - return this value in decimal
     *
     * @return    a string of this value in decimal form
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
     * hex() method - return this value in hex
     *
     * @return    a string of this value in hex
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
     * binary() method - return this value in binary
     *
     * @return    a string of this value in binary
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
     * store(int value) method - store an integer to this byte
     *
     * @param  value  the number we want to store
     */
    public void store(int value) throws Exception {
        // System.err.println("--" + value + "--");
        if (value < 0 && 255 < value) 
            throw new Exception("Passed value is too large for Bus");

        for(int index = 0; index < bits.length; index++) {
            bits[index] = value % 2;
            value       = value / 2;
        }
    }

    /**
     * store(String value) method - store a string in hex to this byte
     *
     * @param  value  the number we want to store
     */
    public void store(String value) throws Exception {
        System.err.println("--" + value + "--");
        if (value.length() != 2) 
            throw new Exception("Passed value is not the right length for Bus");

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
        // construct a bus of wordsize 32
        Bus a = new Bus(32);  

        try {
            a.store(0xAAFF); // store 0xAAFF

            System.out.println(a.hex()); // print "AAFF"
            System.out.println(a.binary()); // print "0010001011111111"
            System.out.println(a.binary(3,0)); // print "1111"
            System.out.println(a.binary(2,0)); // print "111"
            System.out.println(a.binary(18,13)); // print "001"
            System.out.println(a.binary(13,13)); // print "1"
            System.out.println(a.binary(14,14)); // print "0"
            System.out.println(a.binary(15,15)); // print ""
            System.out.println(a.binary(16,16)); // print ""
            System.out.println(a.binary(17,17)); // print ""
            System.out.println(a.binary(18,18)); // print ""
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}