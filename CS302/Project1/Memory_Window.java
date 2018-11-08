/**
 *Text genereted by Simple GUI Extension for BlueJ
 */
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;

/**
 * In Memory_Window class, we write a window to show 
 * which part of the main memory we are working on. <p>
 * 
 * Author: Xiaoshi Wang
 */

public class Memory_Window extends JFrame {
    protected JTextArea MemoryText;
    private JPanel panel1;

    //Constructor 
    public Memory_Window(){

        this.setTitle("Memory_Window");
        this.setSize(500,400);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(192,192,192));

        MemoryText = new JTextArea();
        MemoryText.setBounds(5,9,462,349);
        MemoryText.setBackground(new Color(255,255,255));
        MemoryText.setForeground(new Color(0,0,0));
        MemoryText.setEnabled(true);
        MemoryText.setFont(new Font("sansserif",0,12));
        MemoryText.setText("");
        MemoryText.setBorder(BorderFactory.createBevelBorder(1));
        MemoryText.setVisible(true);

        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(11,18,474,365);
        panel1.setBackground(new Color(214,217,223));
        panel1.setForeground(new Color(0,0,0));
        panel1.setEnabled(true);
        panel1.setFont(new Font("sansserif",0,12));
        panel1.setVisible(true);

        //adding components to contentPane panel
        panel1.add(MemoryText);
        contentPane.add(panel1);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}