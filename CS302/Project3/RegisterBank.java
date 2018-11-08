/***
 * RegisterBank Class implementing a collection of 
 * registers. Source and destination selection is 
 * implemented here to control how the bus is manipulated 
 * by this device.
 */

public class RegisterBank {
    // instance variables
    private Register registers[];

    private int wordsize;
    private int register_cnt;

    /**
     * Constructor for objects register bank used in CPU class
     */
    public RegisterBank(int wordSize, int registerCnt) {
        // initialise instance variables
        wordsize     = wordSize;
        register_cnt = registerCnt;
        // cpnstruct all empty registers
        registers = new Register[register_cnt];
        for(int index = 0; index < register_cnt; index++) {
            registers[index] = new Register(wordsize);
        }
    }

    /**
     * set_source_bus(Bus bus) method - set the source bus of our bank
     *
     * @param  bus      the source bus we want to set
     */
    public void set_source_bus(Bus bus) {
        // set all registers
        for(int index = 0; index < register_cnt; index++) {
            registers[index].set_source_bus(bus);
        }
    }

    /**
     * set_destination_bus(Bus bus) method - set the destination bus of our bank
     *
     * @param  bus      the destination bus we want to set
     */
    public void set_destination_bus(Bus bus) {
        // set all registers
        for(int index = 0; index < register_cnt; index++) {
            registers[index].set_destination_bus(bus);
        }
    }

    /**
     *load(int register_id) method - load value in a given register to bus
     *
     * @param  register_id      the register we want to store
     */
    public void load(int register_id) throws Exception {
        if(register_id < 0 || register_cnt <= register_id) 
            throw new Exception("RegisterBank binary register_id out of range.");

        registers[register_id].load();
    }

    /**
     *store(int register_id) method - store value to a given register from bus
     *
     * @param  register_id      the register we want to load
     */
    public void store(int register_id) throws Exception {
        if(register_id < 0 || register_cnt <= register_id) 
            throw new Exception("RegisterBank binary register_id out of range.");

        registers[register_id].store();
    }

    /**
     *increment(int register_id) method - increase the value of a given register by 1
     *
     * @param  register_id      the register we want to incremment
     */
    public void increment(int register_id) throws Exception {
        if(register_id < 0 || register_cnt <= register_id) 
            throw new Exception("RegisterBank binary register_id out of range.");

        registers[register_id].increment();
    }

    /**
     *negate(int register_id) method - negate the value of a given register
     *
     * @param  register_id      the register we want to negate
     */
    public void negate(int register_id) throws Exception {
        if(register_id < 0 || register_cnt <= register_id) 
            throw new Exception("RegisterBank binary register_id out of range.");

        registers[register_id].negate();
    }

    /**
     *binary(int register_id) method - load the value in a given register in binary
     *
     * @param  register_id      the register we want to load
     * @return   the binary word in the given register
     */
    public String binary(int register_id) throws Exception {
        if(register_id < 0 || register_cnt <= register_id) 
            throw new Exception("RegisterBank binary register_id out of range.");

        return registers[register_id].binary();
    }

    /**
     *binary(int register_id) method - load the value in a given register in hex
     *
     * @param  register_id      the register we want to load
     * @return   the hex word in the given register
     */
    public String hex(int register_id) throws Exception {

        if(register_id < 0 || register_cnt <= register_id) 
            throw new Exception("RegisterBank binary register_id out of range.");

        return registers[register_id].hex();
    }

    /**
     *store(int value, int register_id) method - store an integer to a given register
     *
     * @param  value            the integer we want to store
     * @param  register_id      the register we want to store
     */
    public void store(int value, int register_id) throws Exception {
        if(register_id < 0 || register_cnt <= register_id) 
            throw new Exception("RegisterBank store(int) register_id out of range.");

        registers[register_id].store(value);
    }

    /**
     *store(String value, int register_id) method - store an hex word to a given register
     *
     * @param  value            the hex we want to store
     * @param  register_id      the register we want to store
     */
    public void store(String value, int register_id) throws Exception {
        if(register_id < 0 || register_cnt <= register_id) 
            throw new Exception("RegisterBank store(str) register_id out of range.");

        registers[register_id].store(value);
    }

    /**
     * main method - test or illustrate methods in this class
     */
    public static void main(String args[]) {
        /*** Examples of usage. ***/
        RegisterBank a = new RegisterBank(32, 8); // construct a bank with 8 registers and with wordsize 32

        try {
            // print all value of the 8 registers
            for(int cnt = 0; cnt < 8; cnt++) {
                System.err.println(String.format("Reg 0x%02X: %s", cnt, a.binary(cnt)));
            }
            // store several values in our bank, and then print the whole bank
            System.err.println("------------------------------------------");
            a.store(0x4A5, 0);
            a.store(0xFFFFFF, 1);
            a.store(0x38D, 2);
            a.store(0x3AB3, 3);

            for(int cnt = 0; cnt < 8; cnt++) {
                System.err.println(String.format("Reg 0x%02X: %s", cnt, a.binary(cnt)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}