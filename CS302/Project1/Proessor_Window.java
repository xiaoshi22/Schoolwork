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
 * In Processor class, we write an window to show the 
 * current state for registers, flags, etc. <p>
 * 
 * Author: Xiaoshi Wang
 */

public class Proessor_Window extends JFrame {
    private JMenuBar menuBar;
    private JLabel C;
    protected JTextField CText;
    private JLabel E;
    protected JTextField EText;
    private JLabel N;
    protected JTextField NText;
    private JLabel V;
    protected JTextField VText;
    protected JButton exit;
    protected JButton fast;
    private JLabel mm;
    protected JTextField mmText;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel5;
    private JPanel panel6;
    private JLabel rc;
    protected JTextField rcText;
    protected JTextArea registerArea;
    protected JTextArea registerArea2;
    protected JTextArea registerArea3;
    protected JButton reset;
    protected JButton slow;
    protected JButton step;
    private JLabel ws;
    protected JTextField wwText;

    //Constructor 
    public Proessor_Window(){

        this.setTitle("Proessor_Window");
        this.setSize(500,400);
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(192,192,192));

        C = new JLabel();
        C.setBounds(55,92,90,35);
        C.setBackground(new Color(214,217,223));
        C.setForeground(new Color(0,0,0));
        C.setEnabled(true);
        C.setFont(new Font("sansserif",0,12));
        C.setText("C");
        C.setVisible(true);

        CText = new JTextField();
        CText.setBounds(145,92,70,30);
        CText.setBackground(new Color(255,255,255));
        CText.setForeground(new Color(0,0,0));
        CText.setEnabled(true);
        CText.setFont(new Font("sansserif",0,12));
        CText.setText("0");
        CText.setVisible(true);

        E = new JLabel();
        E.setBounds(55,52,90,35);
        E.setBackground(new Color(214,217,223));
        E.setForeground(new Color(0,0,0));
        E.setEnabled(true);
        E.setFont(new Font("sansserif",0,12));
        E.setText("E");
        E.setVisible(true);

        EText = new JTextField();
        EText.setBounds(145,52,70,30);
        EText.setBackground(new Color(255,255,255));
        EText.setForeground(new Color(0,0,0));
        EText.setEnabled(true);
        EText.setFont(new Font("sansserif",0,12));
        EText.setText("0");
        EText.setVisible(true);

        N = new JLabel();
        N.setBounds(55,12,90,35);
        N.setBackground(new Color(214,217,223));
        N.setForeground(new Color(0,0,0));
        N.setEnabled(true);
        N.setFont(new Font("sansserif",0,12));
        N.setText("N");
        N.setVisible(true);

        NText = new JTextField();
        NText.setBounds(145,12,70,30);
        NText.setBackground(new Color(255,255,255));
        NText.setForeground(new Color(0,0,0));
        NText.setEnabled(true);
        NText.setFont(new Font("sansserif",0,12));
        NText.setText("0");
        NText.setVisible(true);

        V = new JLabel();
        V.setBounds(55,132,90,35);
        V.setBackground(new Color(214,217,223));
        V.setForeground(new Color(0,0,0));
        V.setEnabled(true);
        V.setFont(new Font("sansserif",0,12));
        V.setText("V");
        V.setVisible(true);

        VText = new JTextField();
        VText.setBounds(145,132,70,30);
        VText.setBackground(new Color(255,255,255));
        VText.setForeground(new Color(0,0,0));
        VText.setEnabled(true);
        VText.setFont(new Font("sansserif",0,12));
        VText.setText("0");
        VText.setVisible(true);

        exit = new JButton();
        exit.setBounds(383,3,90,35);
        exit.setBackground(new Color(214,217,223));
        exit.setForeground(new Color(0,0,0));
        exit.setEnabled(true);
        exit.setFont(new Font("sansserif",0,12));
        exit.setText("exit & save");
        exit.setVisible(true);

        fast = new JButton();
        fast.setBounds(192,3,90,35);
        fast.setBackground(new Color(214,217,223));
        fast.setForeground(new Color(0,0,0));
        fast.setEnabled(true);
        fast.setFont(new Font("sansserif",0,12));
        fast.setText("fast");
        fast.setVisible(true);

        mm = new JLabel();
        mm.setBounds(22,95,122,38);
        mm.setBackground(new Color(214,217,223));
        mm.setForeground(new Color(0,0,0));
        mm.setEnabled(true);
        mm.setFont(new Font("sansserif",0,12));
        mm.setText("maximum memory");
        mm.setVisible(true);

        mmText = new JTextField();
        mmText.setBounds(145,95,70,30);
        mmText.setBackground(new Color(255,255,255));
        mmText.setForeground(new Color(0,0,0));
        mmText.setEnabled(true);
        mmText.setFont(new Font("sansserif",0,12));
        mmText.setText("");
        mmText.setVisible(true);

        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(10,11,478,43);
        panel1.setBackground(new Color(214,217,223));
        panel1.setForeground(new Color(0,0,0));
        panel1.setEnabled(true);
        panel1.setFont(new Font("sansserif",0,12));
        panel1.setVisible(true);

        panel2 = new JPanel(null);
        panel2.setBorder(BorderFactory.createEtchedBorder(1));
        panel2.setBounds(11,63,232,324);
        panel2.setBackground(new Color(214,217,223));
        panel2.setForeground(new Color(0,0,0));
        panel2.setEnabled(true);
        panel2.setFont(new Font("sansserif",0,12));
        panel2.setVisible(true);

        panel5 = new JPanel(null);
        panel5.setBorder(BorderFactory.createEtchedBorder(1));
        panel5.setBounds(247,64,236,166);
        panel5.setBackground(new Color(214,217,223));
        panel5.setForeground(new Color(0,0,0));
        panel5.setEnabled(true);
        panel5.setFont(new Font("sansserif",0,12));
        panel5.setVisible(true);

        panel6 = new JPanel(null);
        panel6.setBorder(BorderFactory.createEtchedBorder(1));
        panel6.setBounds(247,235,236,150);
        panel6.setBackground(new Color(214,217,223));
        panel6.setForeground(new Color(0,0,0));
        panel6.setEnabled(true);
        panel6.setFont(new Font("sansserif",0,12));
        panel6.setVisible(true);

        rc = new JLabel();
        rc.setBounds(23,55,90,35);
        rc.setBackground(new Color(214,217,223));
        rc.setForeground(new Color(0,0,0));
        rc.setEnabled(true);
        rc.setFont(new Font("sansserif",0,12));
        rc.setText("register count");
        rc.setVisible(true);

        rcText = new JTextField();
        rcText.setBounds(145,55,70,30);
        rcText.setBackground(new Color(255,255,255));
        rcText.setForeground(new Color(0,0,0));
        rcText.setEnabled(true);
        rcText.setFont(new Font("sansserif",0,12));
        rcText.setText("");
        rcText.setVisible(true);
        
        registerArea3 = new JTextArea();
        registerArea3.setBounds(5,5, 30,310);
        registerArea3.setBackground(new Color(255,255,255));
        registerArea3.setForeground(new Color(0,0,0));
        registerArea3.setEnabled(true);
        registerArea3.setFont(new Font("sansserif",0,12));
        registerArea3.setText("");
        registerArea3.setBorder(BorderFactory.createBevelBorder(1));
        registerArea3.setVisible(true);

        registerArea2 = new JTextArea();
        registerArea2.setBounds(40,5, 35,310);
        registerArea2.setBackground(new Color(255,255,255));
        registerArea2.setForeground(new Color(0,0,0));
        registerArea2.setEnabled(true);
        registerArea2.setFont(new Font("sansserif",0,12));
        registerArea2.setText("");
        registerArea2.setBorder(BorderFactory.createBevelBorder(1));
        registerArea2.setVisible(true);

        registerArea = new JTextArea();
        registerArea.setBounds(80,5, 221,310);
        registerArea.setBackground(new Color(255,255,255));
        registerArea.setForeground(new Color(0,0,0));
        registerArea.setEnabled(true);
        registerArea.setFont(new Font("sansserif",0,12));
        registerArea.setText("");
        registerArea.setBorder(BorderFactory.createBevelBorder(1));
        registerArea.setVisible(true);

        reset = new JButton();
        reset.setBounds(289,3,90,35);
        reset.setBackground(new Color(214,217,223));
        reset.setForeground(new Color(0,0,0));
        reset.setEnabled(true);
        reset.setFont(new Font("sansserif",0,12));
        reset.setText("reset");
        reset.setVisible(true);

        slow = new JButton();
        slow.setBounds(100,3,90,35);
        slow.setBackground(new Color(214,217,223));
        slow.setForeground(new Color(0,0,0));
        slow.setEnabled(true);
        slow.setFont(new Font("sansserif",0,12));
        slow.setText("slow");
        slow.setVisible(true);

        step = new JButton();
        step.setBounds(5,3,90,35);
        step.setBackground(new Color(214,217,223));
        step.setForeground(new Color(0,0,0));
        step.setEnabled(true);
        step.setFont(new Font("sansserif",0,12));
        step.setText("step");
        step.setVisible(true);
        ws = new JLabel();
        ws.setBounds(23,15,90,35);
        ws.setBackground(new Color(214,217,223));
        ws.setForeground(new Color(0,0,0));
        ws.setEnabled(true);
        ws.setFont(new Font("sansserif",0,12));
        ws.setText("word size");
        ws.setVisible(true);

        wwText = new JTextField();
        wwText.setBounds(145,15,70,30);
        wwText.setBackground(new Color(255,255,255));
        wwText.setForeground(new Color(0,0,0));
        wwText.setEnabled(true);
        wwText.setFont(new Font("sansserif",0,12));
        wwText.setText("");
        wwText.setVisible(true);

        //adding components to contentPane panel
        panel5.add(C);
        panel5.add(CText);
        panel5.add(E);
        panel5.add(EText);
        panel5.add(N);
        panel5.add(NText);
        panel5.add(V);
        panel5.add(VText);
        panel1.add(exit);
        panel1.add(fast);
        panel6.add(mm);
        panel6.add(mmText);
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel5);
        contentPane.add(panel6);
        panel6.add(rc);
        panel6.add(rcText);
        panel2.add(registerArea3);
        panel2.add(registerArea2);
        panel2.add(registerArea);
        panel1.add(reset);
        panel1.add(slow);
        panel1.add(step);
        panel6.add(ws);
        panel6.add(wwText);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}