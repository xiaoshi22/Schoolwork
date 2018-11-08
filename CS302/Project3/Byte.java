/***
 * Low-leve Byte class for storing a single byte of information,
 * 8-bits.
 */

public class Byte {
    // instance variable
    private int bits[]; // value

    /**
     * Constructor for objects bytes used in RAM class
     */
    public Byte() {
        // initialise the instance variable bits array
        bits = new int[8];

        for(int index = 0; index < bits.length; index++) {
            bits[index] = 0;
        }
    }
    
    /**
     * hex() method - return this byte in hex
     *
     * @return    a string of this byte in hex form
     */
    public String hex() {

        int pow_value = 1;
        int value     = 0;

        for(int index = 0; index < 8; index++) {

            if (bits[index] == 1) {
                value += pow_value;
            }
            pow_value *= 2;
        }

        return String.format("%02X", value);
    }
    
    /**
     * binary() method _ return this byte in binary
     *
     * @return    a string of this byte in binary form
     */
    public String binary() {

        String result = "";

        for(int index = 7; index >= 0; index--) {

            if (bits[index] == 1) {
                result += "1";
            } else {
                result += "0";
            }
        }

        return result;
    }

    /**
     * binary(int high, int low) method - return part of this byte of given positions
     *
     * @param  high  the highest digit of the substring we need 
     * @param  low   the lowested digit of the substring we need
     * @return       a string of the substring we need
     */
    public String binary(int high, int low) {

        String result = "";

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
        if (value < 0 || 255 < value) 
            throw new Exception("Passed value is too large for Byte");

        for(int index = 0; index < 8; index++) {
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

        // System.err.println("--" + value + "--");
        if (value.length() != 2) 
            throw new Exception("Passed value is not the right length for Byte");

        int int_value = Integer.parseInt(value, 16);

        for(int index = 0; index < 8; index++) {
            bits[index] = int_value % 2;
            int_value   = int_value / 2;
        }
    }

    /**
     * main method - test or illustrate methods in this class
     */
    public static void main(String args[]) {

        /*** Examples of usage. ***/
        Byte a = new Byte();

        try {
            a.store(0xAA); // suppose we store number 00100010
            System.out.println(a.hex()); // print it in hex
            System.out.println(a.binary()); // print it in binary
            System.out.println(a.binary(3,0)); // print the last four bits
            
            a.store(5); // try to store 5, which is 00000101
            System.out.println(a.binary(2,0)); // which should be 101

            // three other examples
            a.store("AB"); 
            System.out.println(a.binary());

            a.store("D9");
            System.out.println(a.binary());

            a.store("00");
            System.out.println(a.hex());
            System.out.println(a.binary());
        } catch (Exception e) {
            System.out.println(e);
        }
    }  
}