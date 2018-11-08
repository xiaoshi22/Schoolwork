import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

/***
 * RAM class as a memory simulation, including visual representation.
 * 
 * Methods that have been added or modified:
 * setMemoryWord(int address, String value);
 */

public class RAM extends JPanel implements ActionListener {
    // instance variables
    protected JButton button;

    protected JTextArea textArea;
    private final static String newline = "\n";

    private Byte memory[]; // main memory
    private int  size; // the size
    private int  byte_width; // byte width
    private int  last_access; // the counter

    private Register address_register; // address register
    private Register data_register; // data register

    /**
     * Constructor for objects main memory used in CPU class
     */
    public RAM(int sizeValue, int byteWidth) {
        // create a gui to show RAM and which line we are proceeding 
        super(new GridBagLayout());

        button = new JButton("Refresh");
        button.addActionListener(this);

        textArea = new JTextArea(30, 70);
        textArea.setFont(new Font("Courier", Font.PLAIN, 16));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(button, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

        // initialise the instance variables
        size        = sizeValue;      // store memory organization
        byte_width  = byteWidth;      // the number of bytes in one line
        last_access = 0;              // the line number we are proceeding

        memory     = new Byte[size];  // construct memory

        for(int cnt = 0; cnt < size; cnt++) {
            memory[cnt] = new Byte();
        }

        load_memory("test_file.as");  // load memory file

        refresh_display();            // redraw the display
    }

    /**
     * actionPerformed(ActionEvent evt) method - preform to refresh when buttons are pressed
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Refresh")) {
            refresh_display();
        } else {
            System.err.println ("Unknown action (RAM.java): " + 
                evt.getActionCommand());
        }
    }

    /**
     * createAndShowGUI(int sizeValue, int byteWidth)  - Create the GUI and show it
     *
     * @param  sizeValue   the size of our RAM
     * @param  byteWidth   the byte width of our RAM
     * @return    the RAM we are working on
     */
    public static RAM createAndShowGUI(int sizeValue, int byteWidth) {
        // For thread safety,this  should be invoked from the event dispatch thread.
        // Create and set up the window.
        JFrame frame = new JFrame("RAM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        RAM mem = new RAM(sizeValue, byteWidth);
        frame.add(mem);

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        return mem;
    }

    /**
     * load_memory(String file_name) method - load memory from a given file
     *
     * @param  file_name   the file we want to load in
     */
    public void load_memory(String file_name) {

        Scanner sc = null;       // initialize and create scanner mechanism
        String   curr_line;      // current line

        try {
            sc = new Scanner(new File(file_name));
        } catch (FileNotFoundException e) {
            System.err.println("Error in load_memory");
            System.err.println(e);
        }

        // load file into memory
        try {                    

            int line_cnt = 0;

            while (sc.hasNextLine()) {
                curr_line = sc.nextLine();               // get next line
                // System.out.println(">>>>" + curr_line + "<<<<");

                if(curr_line.length() == 0) continue;    // skip blank lines

                for(int cnt = 0; cnt <= byte_width; cnt += 2 ) {
                    memory[(line_cnt * byte_width) + (cnt / 2)].store(
                        String.format("%c%c", 
                            curr_line.charAt(cnt),
                            curr_line.charAt(cnt+1)
                        )
                    );
                }

                line_cnt++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        last_access = 0; // reset the line number
    }

    /**
     * refresh_display() method - refresh the window
     */
    public void refresh_display() {

        // textArea.setText(""); // clears the text
        textArea.setText(build_display()); // stores memory as a string to widget
    }

    /**
     * build_display() method - write the current values in the window
     */
    public String build_display(){

        int    offset = 0;
        String result = "    Address  Hex Display   Binary Display" + newline;

        result +=       "    -------  ------------  --------------" + newline;

        for (int cnt = 0; cnt < size; cnt += byte_width) {

            if(cnt == last_access) {                    // add pointer code here
                result += "==> ";
            } else {
                result += "    ";
            }

            result += String.format("0x%05X  ", cnt);   // display address column

            offset = 12 - (byte_width * 2);  // compute offset between hex and bin

            for (int cnt2 = 0; cnt2 < byte_width; cnt2++) { // display hex value
                result += memory[cnt + cnt2].hex();
            }

            for (int cnt2 = 0; cnt2 < offset; cnt2++) {     // add offset
                result += " ";
            }

            result += "  ";

            for (int cnt2 = 0; cnt2 < byte_width; cnt2++) { // display binary value
                result += memory[cnt + cnt2].binary() + " ";
            }

            result += newline;
        }

        return result;
    }

    /**
     * getMemoryWord_binary(int address) method - return memory at a given address
     *
     * @param  address  the location of the wanted memory
     * @return       a string of the memory word we want
     */
    public String getMemoryWord_binary(int address) {
        String result = ""; 
        last_access = address; // update our pointer

        // load the memory word to result
        for(int cnt = 0; cnt < byte_width; cnt++) {
            result += memory[address + cnt].binary();
        }

        return result;
    }

    /**
     * getMemoryWord_hex(int address) method - return memory at a given address in hex
     *
     * @param  address  the location of the wanted memory
     * @return       a string of the memory word in hex we want
     */
    public String getMemoryWord_hex(int address) {
        String result = "";
        last_access = address;

        for(int cnt = 0; cnt < byte_width; cnt++) {
            result += memory[address + cnt].hex();
        }

        return result;
    }

    /**
     * setMemoryWord(int address, String value) method - set the memory of a given address to be a given binary string
     *
     * @param  address  the location of the target memory
     * @param  value    the value we want to change to 
     */
    public void setMemoryWord(int address, String value) {
        last_access = address; // update our last access location

        try {
            // find the coressponding values byte by byte, and store it in momery
            for(int cnt = 0; cnt < byte_width; cnt++) {
                int temp = Integer.parseInt(value.substring((cnt*8), (cnt*8)+8), 2); 
                memory[address + cnt].store(temp);
            }
        } catch (Exception e) {
            System.err.println("In RAM:setMemoryWord.");
            System.err.println(e);
        }

        refresh_display(); // refresh the window
    }

    /**
     * set_address_register(Register reg) method - set the address register of our RAM
     *
     * @param  reg      the address register we want to set
     */
    public void set_address_register(Register reg) {
        address_register = reg;
    }

    /**
     * set_data_register(Register reg) method - set the data register of our RAM
     *
     * @param  reg      the data register we want to set
     */
    public void set_data_register(Register reg) {
        data_register = reg;
    }

    /**
     * memory_load() method - load the memory at address in address register to data register
     */
    public void memory_load() {
        int address = address_register.decimal(); // get the target address
        data_register.store(getMemoryWord_hex(address)); // store value to the data register
    }

    /**
     * memory_store() method - store the memory in data register to the main memory at the address of address register
     */
    public void memory_store() {
        int address = address_register.decimal(); // get the address
        setMemoryWord(address, data_register.binary()); // load value to main memory
        refresh_display(); // refresh window to show current memory map after storing
    }  
}