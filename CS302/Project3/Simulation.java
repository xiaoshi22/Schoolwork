import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/***
 * Simulation class that initiates all other objects.
 */

public class Simulation {
    // instance variable
    public static RAM    mem;

    /**
     * main method - Schedule a job for the event dispatch thread: creating and showing this application's GUI.
     */
    public static void main(String[] args) {
        // contruct a gui for RAM of size 32 and byte width 0x2
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    mem = RAM.createAndShowGUI(32, 0x2);
                }
            });

        // contruct a gui for CPU of word size 16, 8 registers, and RAM of mem we just created
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    CPU.createAndShowGUI(16, 8, mem);
                }
            });
    }
}