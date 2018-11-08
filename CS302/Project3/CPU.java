import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/***
 * CPU simulation class, including visual representation.
 * 
 * Methods that have been added or modified:
 * public  ALU           alu;
 * CPU Constructor;
 * reset();
 *  build_display();
 */

public class CPU extends JPanel implements ActionListener {
    // instance variables
    protected JButton button_refresh;
    protected JButton button_clock_tick;
    protected JButton button_reset;

    protected JTextArea textArea;
    private final static String newline = "\n";

    public  Register      PC, IR, MA, MD, B, C; // the registers in cpu
    public  ALU           alu;// the alu
    public  RegisterBank  bank; // the bank
    public  Bus           master_bus; // the bus

    private int           clock_ticks;
    private int           general_purpose_reg_cnt; // number of registers
    private int           wordsize; // word size

    public  RAM           main_memory; // our ram
    private Controller     controller; // the controller

    /**
     * Constructor for objects CPU used in simulation class
     */
    public CPU(int wordSize, int register_cnt, RAM mem) {
        // create a gui
        super(new GridBagLayout());

        button_refresh = new JButton("Refresh");
        button_refresh.addActionListener(this);
        button_clock_tick = new JButton("Increment Clock");
        button_clock_tick.addActionListener(this);
        button_reset = new JButton("Reset");
        button_reset.addActionListener(this);

        textArea = new JTextArea(30, 30);
        textArea.setFont(new Font("Courier", Font.PLAIN, 16));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(button_refresh, c);
        add(button_clock_tick, c);
        add(button_reset, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

        /*** Build the machine here. ***/
        // initialise instance variables
        general_purpose_reg_cnt = register_cnt;
        wordsize                = wordSize;
        main_memory             = mem;
        controller               = new Controller(this);
        // initialise all registers and bus
        master_bus = new Bus(wordsize);

        PC         = new Register(wordsize);
        IR         = new Register(wordsize);
        MA         = new Register(wordsize);
        MD         = new Register(wordsize);

        B          = new Register(wordsize); 
        C          = new Register(wordsize); 
        alu        = new ALU(wordsize); 
        bank       = new RegisterBank(wordsize, general_purpose_reg_cnt);

        // set source and destinations bus, or source and destination to them all
        bank.set_source_bus(master_bus);
        bank.set_destination_bus(master_bus);

        PC.set_source_bus(master_bus);
        PC.set_destination_bus(master_bus);
        IR.set_source_bus(master_bus);
        IR.set_destination_bus(master_bus);
        MA.set_source_bus(master_bus);
        MA.set_destination_bus(master_bus);
        MD.set_source_bus(master_bus);
        MD.set_destination_bus(master_bus);
        B.set_source_bus(master_bus);
        B.set_destination_bus(master_bus);
        C.set_source_bus(master_bus);
        C.set_destination_bus(master_bus);

        alu.set_source_A(master_bus);
        alu.set_source_B(B);
        alu.set_dest_C(C);

        main_memory.set_address_register(MA);
        main_memory.set_data_register(MD);

        reset();           // clear everything to zero
        refresh_display(); // redraw the display
    }

    /**
     * actionPerformed(ActionEvent evt) method - achieve preformance of refresh, reset and Increment clock
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Increment Clock")) {
            increment_clock();
        } else
        if (evt.getActionCommand().equals("Reset")) {
            reset();
        } else
        if (evt.getActionCommand().equals("Refresh")) {
            refresh_display();
        } else {
            System.err.println ("Unknown action (CPU.java): " + 
                evt.getActionCommand());
        }
    }

    /**
     * createAndShowGUI(int sizeValue, int byteWidth) method - Create the GUI and show it
     *
     * @param  sizeValue   the size of our RAM
     * @param  byteWidth   the byte width of our RAM
     * @return    the RAM we are working on
     */
    public static void createAndShowGUI(int wordSize, int register_cnt, 
    RAM mem) {
        // For thread safety,this method should be invoked from the event dispatch thread.
        //Create and set up the window.
        JFrame frame = new JFrame("CPU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new CPU(wordSize, register_cnt, mem));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /***
     *  increment_clock() method - increase the clock by 1 and refresh the window
     */
    public void increment_clock() {
        clock_ticks++;
        controller.increment_clock();
        refresh_display(); // dispaly window
        main_memory.refresh_display(); // refresh the ram window
    }

    /***
     *  reset() method - Clears all the registers
     */
    public void reset() {
        try { 
            // set all value to be default values
            clock_ticks = 0;

            PC.store(0x0);
            IR.store(0x0);
            MA.store(0x0);
            MD.store(0x0);
            B.store(0x0);
            C.store(0x0);
            for(int cnt = 0; cnt < general_purpose_reg_cnt; cnt++) {
                bank.store(0x0, cnt);
            }

            controller.reset();

        } catch (Exception e) {
            System.err.println("In CPU:reset");
            System.err.println(e);
        }
    }

    /**
     * refresh_display() method - refresh the window
     */
    public void refresh_display() {
        textArea.setText(build_display()); // stores memory as a string to widget
    }

    /**
     * build_display() method - write the current values in the window
     */
    public String build_display(){
        int    offset = 0;
        String result = "";

        result += "Wordsize   : " + wordsize;
        result += "Reg Count  : " + general_purpose_reg_cnt + newline;
        result += "Clock Ticks: " + clock_ticks + newline;
        result += "Curr RTN   : " + controller.current_rtn() + newline;

        result += newline;
        result += "Bus: " + master_bus.binary() + newline;

        result += newline;
        result += "IR : " + IR.binary() + newline;
        result += "PC : " + PC.binary() + newline;
        result += newline;
        result += "MA : " + MA.binary() + newline;
        result += "MD : " + MD.binary() + newline;
        result += newline;
        result += "B : " + B.binary() + newline;
        result += "C : " + C.binary() + newline;
        result += newline;
        result += alu.returnFlags() + newline;
        result += newline;

        result += newline + "----- Register Bank -----" + newline;
        try {
            for(int cnt = 0; cnt < 8; cnt++) {
                result += String.format("%02d: %s", cnt, bank.binary(cnt)) + newline;
            }
        } catch (Exception e) {
            System.err.println("In CPU:build_display");
            System.err.println(e);
        }

        return result;
    }
}