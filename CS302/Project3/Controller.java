/***
 * Controller Class as the hardware Controller for driving data path.
 * 
 * Methods that have been added or modified:
 * load_cntl_code_1();
 * all methods in RTN inner class.
 */

public class Controller {

    // instance variables
    int memory_size; // the memory size
    RTN control_memory[]; // rtn code
    CPU data_path; // the cpu

    int current_entry; // counter for the index

    // constants
    private final int NEXT    = 0;
    private final int START   = 1;
    private final int CMDNAME = 2;
    private final int UNKNOWN = 3;

    /**
     * Constructor for objects controllers used in CPU class
     */
    public Controller(CPU cpu) {
        // initialise the instance variables
        memory_size = 512; // memorysize is 512
        data_path   = cpu; //set cpu

        control_memory = new RTN[memory_size];
        load_cntl_code_1();

        reset(); // reset all values
    }

    /**
     * increment_clock() method - set to different code to different return values in the advance() method
     */
    public void increment_clock() {
        System.err.println(control_memory[current_entry]);
        control_memory[current_entry].execute(); // excute the coressponding ntr

        switch (control_memory[current_entry].advance()) {
            // go to next code
            case NEXT: {
                current_entry++;
                break;
            }
            
            // start over to fetch
            case START: {
                current_entry = 0;
                break;
            }
            
            // proceed the operation wth corresponding opcode
            case CMDNAME: {
                try {
                    current_entry = (data_path.IR.decimal(15,12) + 1) * 5;
                    System.err.println("--------");
                    System.err.println(data_path.IR.binary());
                    System.err.println(current_entry);
                    System.err.println("--------");
                } catch (Exception e) {
                    System.err.println("In Controller:increment_clock");
                    System.err.println(e);
                }

                break;
            }
        }
    }

    /**
     * current_rtn() method - return the current rtn
     * 
     * @ return   return the name of current rtn for tracking
     */
    public String current_rtn() {
        return control_memory[current_entry].toString();
    }

    /**
     * reset() method - reset the current entry
     */
    public void reset() {
        current_entry = 0;
    }

    /**
     * load_cntl_code_1() method - load all cnt code we design
     */
    public void load_cntl_code_1() {
        // Fetch 
        control_memory[0] = new Fetch0();
        control_memory[1] = new Fetch1();
        control_memory[2] = new Fetch2();

        // NOP opcode: 0
        control_memory[5] = new NOP();

        // LOADI opcode: 1
        control_memory[10] = new LOADI0();

        // LDUR opcode: 2
        control_memory[15] = new LDUR0();
        control_memory[16] = new LDUR1();
        control_memory[17] = new LDUR2();

        //STUR opcode: 3
        control_memory[20] = new STUR0();
        control_memory[21] = new STUR1();
        control_memory[22] = new STUR2();

        // ADDI opcode: 4
        control_memory[25] = new ADDI0();
        control_memory[26] = new ADDI1();
        control_memory[27] = new ADDI2();

        // ADD opcode: 5
        control_memory[30] = new ADD0();
        control_memory[31] = new ADD1();
        control_memory[32] = new ADD2();

        // SUBI pcode: 6
        control_memory[35] = new SUBI0();
        control_memory[36] = new SUBI1();
        control_memory[37] = new SUBI2();

        // SUB opcode: 7
        control_memory[40] = new SUB0();
        control_memory[41] = new SUB1();
        control_memory[42] = new SUB2();

        // ANDI opcode: 8
        control_memory[45] = new ANDI0();
        control_memory[46] = new ANDI1();
        control_memory[47] = new ANDI2();

        // AND opcode: 9
        control_memory[50] = new AND0();
        control_memory[51] = new AND1();
        control_memory[52] = new AND2();

        // ORRI opcode: A
        control_memory[55] = new ORRI0();
        control_memory[56] = new ORRI1();
        control_memory[57] = new ORRI2();

        // ORR opcode: B
        control_memory[60] = new ORR0();
        control_memory[61] = new ORR1();
        control_memory[62] = new ORR2();

        // LSL opcode: C
        control_memory[65] = new LSL0();
        control_memory[66] = new LSL1();
        control_memory[67] = new LSL2();

        // LSR opcode: D
        control_memory[70] = new LSR0();
        control_memory[71] = new LSR1();
        control_memory[72] = new LSR2();

        //B opcode: E
        control_memory[75] = new B0();
    }

    /** RTN to handle all rtn we have */
    public class RTN {
        /**
         * toString() method - load all cnt code we design
         * 
         * @ return  a string to show which rtn we are working on
         */
        public String toString() {
            return new String("RTN parent toString method.");
        }

        /**
         * excute() method - excute the current rtn
         */
        public void execute() {
            System.err.println("You are executing the RTN parent.");
        }

        /**
         * advance() method - show which rtn we should work on next
         * 
         * @ return  an integer of sign
         */
        public int advance() {
            return UNKNOWN;
        }
    };

    /**** FETCH: 0 ****/
    // The RTN for fetching the instruction.
    // Should always be in location zero of the
    // control memory.
    public class Fetch0 extends RTN {
        public String toString() {
            return new String("Fetch0");
        }

        public void execute() {
            data_path.PC.store(); // store value to bus
            data_path.MA.load(); // load value from bus
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class Fetch1 extends RTN {
        public String toString() {
            return new String("Fetch1");
        }

        public void execute() {
            data_path.PC.increment(2);
            data_path.main_memory.memory_load();
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class Fetch2 extends RTN {
        public String toString() {
            return new String("Fetch2");
        }

        public void execute() {
            data_path.MD.store(); // store value to bus
            data_path.IR.load();
        }

        public int advance() {
            return CMDNAME;
        }
    }

    /**** No Operation: 0 ****/
    // The RTN for doing nothing with the processor.
    // Should always be in location 1 of the
    // control memory.
    public class NOP extends RTN {

        public String toString() {
            return new String("NOP");
        }

        public void execute() {
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** Load Immediate: 1 ****/

    // The RTN for implementing the add immediate operation.
    // Should always be in location 2 of the control memory.
    // Will pull the destination register immediate from.
    // the IR and store in destation register
    public class LOADI0 extends RTN {
        public String toString() {
            return new String("LOADI0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7      0|
            // | op |dest| immed  |

            try {
                int source      = data_path.IR.decimal(7, 0);
                int destination = data_path.IR.decimal(11, 8);

                data_path.master_bus.store(source); // store sourse
                data_path.bank.load(destination);// load destination

            } catch (Exception e) {
                System.err.println("In Controller:LOADI0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** LDUR: 3 ****/
    // The RTN for implementing the LDUR operation.
    // put the data from main memory to some register
    public class LDUR0 extends RTN {

        public String toString() {
            return new String("LDUR0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7      0|
            // | op |reg |   addr |

            try {
                int addr      = data_path.IR.decimal(7, 0);

                data_path.master_bus.store(addr); // store addr to bus
                data_path.MA.load(); // load to MA
            } catch (Exception e) {
                System.err.println("In Controller:LDUR0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished      
        }
    }

    public class LDUR1 extends RTN {

        public String toString() {
            return new String("LDUR1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7      0|
            // | op |reg |   addr |

            try {
                data_path.main_memory.memory_load(); // load in main memory
            } catch (Exception e) {
                System.err.println("In Controller:LDUR1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class LDUR2 extends RTN {

        public String toString() {
            return new String("LDUR2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7      0|
            // | op |reg |   addr |

            try {
                data_path.MD.store(); // store value to bus
                int reg      = data_path.IR.decimal(11, 8);
                data_path.bank.load(reg); // load reg

            } catch (Exception e) {
                System.err.println("In Controller:LDUR2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** STUR: 4 ****/
    // The RTN for implementing the stur operation.
    // store some value in register to main memory at a given address
    public class STUR0 extends RTN {

        public String toString() {
            return new String("STUR0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7      0|
            // | op |reg |   addr |

            try {
                int reg      = data_path.IR.decimal(11, 8);
                data_path.bank.store(reg); // store reg
                data_path.MD.load(); // load value to bus
            } catch (Exception e) {
                System.err.println("In Controller:STUR0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished       
        }
    }

    public class STUR1 extends RTN {

        public String toString() {
            return new String("STUR1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7      0|
            // | op |reg |   addr |

            try {
                int addr      = data_path.IR.decimal(7, 0);
                data_path.MA.store(addr); // store addr
            } catch (Exception e) {
                System.err.println("In Controller:STUR1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class STUR2 extends RTN {

        public String toString() {
            return new String("STUR2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7      0|
            // | op |reg |   addr |

            try {
                data_path.main_memory.memory_store();  // store in main memory
            } catch (Exception e) {
                System.err.println("In Controller:STUR2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** ADDI: 5 ****/
    // The RTN for implementing the addi operation.
    // x1 = x2 +i 
    public class ADDI0 extends RTN {

        public String toString() {
            return new String("ADDI0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:ADDI0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished     
        }
    }

    public class ADDI1 extends RTN {

        public String toString() {
            return new String("ADDI1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int i = data_path.IR.decimal(3, 0);
                data_path.master_bus.store(i); // store i to bus
                data_path.alu.add();     // do add operation           
            } catch (Exception e) {
                System.err.println("In Controller:ADDI1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class ADDI2 extends RTN {

        public String toString() {
            return new String("ADDI2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                data_path.C.store(); // store c to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load x1
            } catch (Exception e) {
                System.err.println("In Controller:ADDI2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** ADD: 6 ****/
    // The RTN for implementing the add operation.
    // x1 = x2 +x3
    public class ADD0 extends RTN {

        public String toString() {
            return new String("ADD0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:ADD0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished     
        }
    }

    public class ADD1 extends RTN {

        public String toString() {
            return new String("ADD1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                int x3 = data_path.IR.decimal(3, 0);
                data_path.bank.store(x3); // store x3
                data_path.alu.add();  // do add operation             
            } catch (Exception e) {
                System.err.println("In Controller:ADD1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class ADD2 extends RTN {

        public String toString() {
            return new String("ADD2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load x1
            } catch (Exception e) {
                System.err.println("In Controller:ADD2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** SUBI: 7 ****/
    // The RTN for implementing the subi operation.
    // x1 = x2 - i 
    public class SUBI0 extends RTN {

        public String toString() {
            return new String("SUBI0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:SUBI0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished      
        }
    }

    public class SUBI1 extends RTN {

        public String toString() {
            return new String("SUBI1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int i = data_path.IR.decimal(3, 0);
                data_path.master_bus.store(i); // store i to bus
                data_path.alu.sub();                
            } catch (Exception e) {
                System.err.println("In Controller:SUBI1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class SUBI2 extends RTN {

        public String toString() {
            return new String("SUBI2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load x1
            } catch (Exception e) {
                System.err.println("In Controller:SUBI2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** SUB: 8 ****/
    // The RTN for implementing the sub operation.
    // x1 = x2 + x3
    public class SUB0 extends RTN {

        public String toString() {
            return new String("SUB0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:SUB0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class SUB1 extends RTN {

        public String toString() {
            return new String("SUB1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                int x3 = data_path.IR.decimal(3, 0);
                data_path.bank.store(x3); // store x3
                data_path.alu.sub();    // do substraction            
            } catch (Exception e) {
                System.err.println("In Controller:SUB1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class SUB2 extends RTN {

        public String toString() {
            return new String("SUB2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load x1
            } catch (Exception e) {
                System.err.println("In Controller:SUB2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** ANDI: 9 ****/
    // The RTN for implementing the andi operation.
    // x1 = x2 & i 
    public class ANDI0 extends RTN {

        public String toString() {
            return new String("ANDI0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:ANDI0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished     
        }
    }

    public class ANDI1 extends RTN {

        public String toString() {
            return new String("ANDI1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int i = data_path.IR.decimal(3, 0);
                data_path.master_bus.store(i); // store i to bus
                data_path.alu.and();   // do and operation             
            } catch (Exception e) {
                System.err.println("In Controller:ANDI1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class ANDI2 extends RTN {

        public String toString() {
            return new String("ANDI2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load x1
            } catch (Exception e) {
                System.err.println("In Controller:ANDI2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** AND: 10 ****/
    // The RTN for implementing the add operation.
    // x1 = x2 & x3 
    public class AND0 extends RTN {

        public String toString() {
            return new String("AND0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:AND0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished      
        }
    }

    public class AND1 extends RTN {

        public String toString() {
            return new String("AND1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                int x3 = data_path.IR.decimal(3, 0);
                data_path.bank.store(x3); // store x3
                data_path.alu.and();  // do and operation              
            } catch (Exception e) {
                System.err.println("In Controller:AND1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class AND2 extends RTN {

        public String toString() {
            return new String("AND2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load x1
            } catch (Exception e) {
                System.err.println("In Controller:AND2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** ORRI: 11 ****/
    // The RTN for implementing the orri operation.
    // x1 = x2 |i 
    public class ORRI0 extends RTN {

        public String toString() {
            return new String("ORRI0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store to x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:ORRI0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished      
        }
    }

    public class ORRI1 extends RTN {

        public String toString() {
            return new String("ORRI1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int i = data_path.IR.decimal(3, 0);
                data_path.master_bus.store(i); // store i to bus
                data_path.alu.orr();  // do orr operation              
            } catch (Exception e) {
                System.err.println("In Controller:ORRI1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class ORRI2 extends RTN {

        public String toString() {
            return new String("ORRI2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load to x1
            } catch (Exception e) {
                System.err.println("In Controller:ORRI2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** ORR: 12 ****/
    // The RTN for implementing the orr operation.
    // x1 = x2 | x3 
    public class ORR0 extends RTN {

        public String toString() {
            return new String("ORR0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store to x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:ORR0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished      
        }
    }

    public class ORR1 extends RTN {

        public String toString() {
            return new String("ORR1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                int x3 = data_path.IR.decimal(3, 0);
                data_path.bank.store(x3); // store to x3
                data_path.alu.orr();  // do orr operation              
            } catch (Exception e) {
                System.err.println("In Controller:ORR1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class ORR2 extends RTN {

        public String toString() {
            return new String("ORR2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| x3 |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load from x1
            } catch (Exception e) {
                System.err.println("In Controller:ORR2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** LSL: 13 ****/
    // The RTN for implementing the lsl operation.
    // x1 = x2 << i 
    public class LSL0 extends RTN {

        public String toString() {
            return new String("LSL0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store to x2
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:LSL0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished      
        }
    }

    public class LSL1 extends RTN {

        public String toString() {
            return new String("LSL1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int i = data_path.IR.decimal(3, 0);
                data_path.master_bus.store(i); // load i to bus
                data_path.alu.lsl();  // do lsl operation              
            } catch (Exception e) {
                System.err.println("In Controller:LSL1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class LSL2 extends RTN {

        public String toString() {
            return new String("LSL2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load x1
            } catch (Exception e) {
                System.err.println("In Controller:LSL2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is finished
        }
    }

    /**** LSR: 14 ****/
    // The RTN for implementing the lsr operation.
    // x1 = x2 >> i 
    public class LSR0 extends RTN {

        public String toString() {
            return new String("LSR0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int x2 = data_path.IR.decimal(7, 4);
                data_path.bank.store(x2); // store to x2 register
                data_path.B.load(); // load value to bus
                data_path.alu.initilizeFlags(); // inilizeflags before this operations
            } catch (Exception e) {
                System.err.println("In Controller:LSR0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT;  // this operation is not finished     
        }
    }

    public class LSR1 extends RTN {

        public String toString() {
            return new String("LSR1");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                int i = data_path.IR.decimal(3, 0);
                data_path.master_bus.store(i); // load i to bus
                data_path.alu.lsr();  // do lsr operation               
            } catch (Exception e) {
                System.err.println("In Controller:LSR1:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return NEXT; // this operation is not finished
        }
    }

    public class LSR2 extends RTN {

        public String toString() {
            return new String("LSR2");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op | x1 | x2| i  |

            try {
                data_path.C.store(); // store value to bus
                int x1 = data_path.IR.decimal(11, 8);
                data_path.bank.load(x1); // load to x1 register
            } catch (Exception e) {
                System.err.println("In Controller:LSR2:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() {
            return START; // this operation is not finished
        }
    }

    /**** B: 15 ****/
    // The RTN for implementing the Branch operation.
    // Go to the given address in main memory
    public class B0 extends RTN {

        public String toString() {
            return new String("B0");
        }

        public void execute() {
            // IR representation
            // |1  1|1  0|0      0|
            // |5  2|1  8|7 4|3  0|
            // | op |    addr     |

            try {
                int addr = data_path.IR.decimal(11, 0); 
                data_path.master_bus.store(addr);  // store addr to bus  
                data_path.PC.load(); // load value to bus
            }  catch (Exception e) {
                System.err.println("In Controller:B0:increment_clock");
                System.err.println(e);
            }
        }

        public int advance() { 
            return START; // this operation is finished
        }
    }
}