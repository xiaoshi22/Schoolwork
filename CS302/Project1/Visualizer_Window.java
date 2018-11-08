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
import java.util.Hashtable;
/**
 * In Visualizer_Window class, we construct a window
 * as a debugger. <p>
 * 
 * Author: Xiaoshi Wang
 */

public class Visualizer_Window extends JFrame {
    protected static JButton rangeButton;
    protected static JButton codeButton;
    protected  static JTextField codeText;
    protected static JButton hexButton;
    protected static JTextField hexText;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    protected JTextField rangeText;
    private static Hashtable<String, Integer> tags;
    private static Hashtable<Integer, String> tagsRevise;
    //Constructor 
    public Visualizer_Window(){
        initializeTags();
        initializeTagsRevise();
        this.setTitle("Visualizer_Window");
        this.setSize(500,400);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(192,192,192));

        rangeButton= new JButton();
        rangeButton.setBounds(360,50,110,35);
        rangeButton.setBackground(new Color(214,217,223));
        rangeButton.setForeground(new Color(0,0,0));
        rangeButton.setEnabled(true);
        rangeButton.setFont(new Font("sansserif",0,12));
        rangeButton.setText("get code");
        rangeButton.setVisible(true);

        codeButton = new JButton();
        codeButton.setBounds(360,50,110,35);
        codeButton.setBackground(new Color(214,217,223));
        codeButton.setForeground(new Color(0,0,0));
        codeButton.setEnabled(true);
        codeButton.setFont(new Font("sansserif",0,12));
        codeButton.setText("covert to hex");
        codeButton.setVisible(true);
        codeButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    codeButton(evt);
                }
            });

        codeText = new JTextField();
        codeText.setBounds(5,50,340,40);
        codeText.setBackground(new Color(255,255,255));
        codeText.setForeground(new Color(0,0,0));
        codeText.setEnabled(true);
        codeText.setFont(new Font("sansserif",0,12));
        codeText.setText("");
        codeText.setVisible(true);

        hexButton = new JButton();
        hexButton.setBounds(361,50,110,35);
        hexButton.setBackground(new Color(214,217,223));
        hexButton.setForeground(new Color(0,0,0));
        hexButton.setEnabled(true);
        hexButton.setFont(new Font("sansserif",0,12));
        hexButton.setText("convert to code");
        hexButton.setVisible(true);
        hexButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    hexButton(evt);
                }
            });

        hexText = new JTextField();
        hexText.setBounds(5,50,340,40);
        hexText.setBackground(new Color(255,255,255));
        hexText.setForeground(new Color(0,0,0));
        hexText.setEnabled(true);
        hexText.setFont(new Font("sansserif",0,12));
        hexText.setText("");
        hexText.setVisible(true);

        label1 = new JLabel();
		label1.setBounds(11,13,447,29);
		label1.setBackground(new Color(214,217,223));
		label1.setForeground(new Color(0,0,0));
		label1.setEnabled(true);
		label1.setFont(new Font("sansserif",0,12));
		label1.setText("please type some assembly language. For example, type'SUB x2, x3, 20'");
		label1.setVisible(true);

        label2 = new JLabel();
		label2.setBounds(10,10,30000,35);
		label2.setBackground(new Color(214,217,223));
		label2.setForeground(new Color(0,0,0));
		label2.setEnabled(true);
		label2.setFont(new Font("sansserif",0,12));
		label2.setText("please type the address you are looking for. For example, type '16'");
		label2.setVisible(true);
		
        label3 = new JLabel();
		label3.setBounds(9,12,452,34);
		label3.setBackground(new Color(214,217,223));
		label3.setForeground(new Color(0,0,0));
		label3.setEnabled(true);
		label3.setFont(new Font("sansserif",0,12));
		label3.setText("please type some hex numbers with length 16. e.g., type '0202030000000014'");
		label3.setVisible(true);

        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(10,10,480,120);
        panel1.setBackground(new Color(214,217,223));
        panel1.setForeground(new Color(0,0,0));
        panel1.setEnabled(true);
        panel1.setFont(new Font("sansserif",0,12));
        panel1.setVisible(true);

        panel2 = new JPanel(null);
        panel2.setBorder(BorderFactory.createEtchedBorder(1));
        panel2.setBounds(10,140,480,120);
        panel2.setBackground(new Color(214,217,223));
        panel2.setForeground(new Color(0,0,0));
        panel2.setEnabled(true);
        panel2.setFont(new Font("sansserif",0,12));
        panel2.setVisible(true);

        panel3 = new JPanel(null);
        panel3.setBorder(BorderFactory.createEtchedBorder(1));
        panel3.setBounds(10,270,480,120);
        panel3.setBackground(new Color(214,217,223));
        panel3.setForeground(new Color(0,0,0));
        panel3.setEnabled(true);
        panel3.setFont(new Font("sansserif",0,12));
        panel3.setVisible(true);

        rangeText = new JTextField();
        rangeText.setBounds(5,49,340,40);
        rangeText.setBackground(new Color(255,255,255));
        rangeText.setForeground(new Color(0,0,0));
        rangeText.setEnabled(true);
        rangeText.setFont(new Font("sansserif",0,12));
        rangeText.setText("");
        rangeText.setVisible(true);

        //adding components to contentPane panel
        panel1.add(rangeButton);
        panel2.add(codeButton);
        panel2.add(codeText);
        panel3.add(hexButton);
        panel3.add(hexText);
        panel2.add(label1);
        panel1.add(label2);
        panel3.add(label3);
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);
        panel1.add(rangeText);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private static void codeButton(MouseEvent evt){
        String build_image = codeText.getText();

        String strArray[] = build_image.trim().split("\\W+");
        if(strArray.length == 4){
            build_image = intToHex(tags.get(strArray[0]), 2);
            build_image += intToHex(toIntStr(strArray[1]), 2);
            build_image += intToHex(toIntStr(strArray[2]), 2);
            build_image += intToHex(toIntStr(strArray[3]), 10);
        } else if(strArray.length == 3){
            build_image = intToHex(tags.get(strArray[0]), 2);
            build_image += intToHex(toIntStr(strArray[1]), 2);
            build_image += intToHex(toIntStr(strArray[2]), 12);
        } else if(strArray.length == 2){
            build_image = intToHex(tags.get(strArray[0]), 2);
            if (tags.containsKey(strArray[1])){
                build_image += intToHex(tags.get(strArray[1]), 14);
            } else {
                build_image += intToHex(toIntStr(strArray[1]), 14);
            }
        } else if(strArray.length == 1){
            if (strArray[0].equals("HALT")){ 
                build_image = "FFFFFFFFFFFFFFFF";
            } else if (strArray[0].equals("NOP")){ 
                build_image = "";
            } else if (strArray[0].equals("PUSH")){ 
                build_image = "EFFFFFFFFFFFFFFF";
            } else if (strArray[0].equals("POP")){ 
                build_image = "DFFFFFFFFFFFFFFF";
            }
        }

        codeText.setText(build_image);
    }

    private static void hexButton(MouseEvent evt){
        String line = hexText.getText();

        if (line.length() < 16) {
            hexText.setText("the length of the hex word should be at least 16");
            return;
        }
        Integer i= Integer.valueOf(line.substring(0, 2));
        String result = tagsRevise.get(i);
        result+=" ";

        switch (line.charAt(0)){
            case '0':
            case '1':
            case '2':
            i= Integer.parseInt(line.substring(2, 4),16);
            result += i;
            result += ", ";
            i= Integer.parseInt(line.substring(4, 6),16);
            result += i;
            result += ", ";
            i= Integer.parseInt(line.substring(6, 16),16);
            result += i;
        }
        hexText.setText(result);

    }

    private static void initializeTags() {
        tags = new Hashtable<String, Integer>();

        tags.put("ADD", Integer.valueOf(1));
        tags.put("SUB", Integer.valueOf(2));
        tags.put("ADDI", Integer.valueOf(3));
        tags.put("SUBI", Integer.valueOf(4));
        tags.put("ADDS", Integer.valueOf(5));
        tags.put("SUBS", Integer.valueOf(6));
        tags.put("ADDIS", Integer.valueOf(7));
        tags.put("SUBIS", Integer.valueOf(8));

        tags.put("AND", Integer.valueOf(17));
        tags.put("ORR", Integer.valueOf(18));
        tags.put("EOR", Integer.valueOf(19));
        tags.put("ANDI", Integer.valueOf(20));
        tags.put("ORRI", Integer.valueOf(21));
        tags.put("EORI", Integer.valueOf(22));
        tags.put("LSL", Integer.valueOf(23));
        tags.put("LSR", Integer.valueOf(24));

        tags.put("LDUR", Integer.valueOf(33));
        tags.put("STUR", Integer.valueOf(34));
        tags.put("LDURSW", Integer.valueOf(35));
        tags.put("STURW", Integer.valueOf(36));
        tags.put("LDURH", Integer.valueOf(37));
        tags.put("STURH", Integer.valueOf(38));
        tags.put("LDURB", Integer.valueOf(39));
        tags.put("STURB", Integer.valueOf(40));

        tags.put("CBZ", Integer.valueOf(49));
        tags.put("CBNZ", Integer.valueOf(50));
        tags.put("B.cond", Integer.valueOf(51));
        tags.put("B", Integer.valueOf(52));
        tags.put("BR", Integer.valueOf(53));
        tags.put("BL", Integer.valueOf(54));

        tags.put("HALT", Integer.valueOf(65535));
    } 

    private static void initializeTagsRevise() {
        tagsRevise = new Hashtable<Integer, String>();

        tagsRevise.put(Integer.valueOf(1), "ADD");
        tagsRevise.put(Integer.valueOf(2),"SUB");
        tagsRevise.put(Integer.valueOf(3),"ADDI");
        tagsRevise.put(Integer.valueOf(4),"SUBI");
        tagsRevise.put(Integer.valueOf(5),"ADDS");
        tagsRevise.put(Integer.valueOf(6),"SUBS");
        tagsRevise.put(Integer.valueOf(7),"ADDIS");
        tagsRevise.put(Integer.valueOf(8),"SUBIS");

        tagsRevise.put(Integer.valueOf(17), "AND");
        tagsRevise.put(Integer.valueOf(18),"ORR");
        tagsRevise.put(Integer.valueOf(19),"EOR");
        tagsRevise.put(Integer.valueOf(20),"ANDI");
        tagsRevise.put(Integer.valueOf(21),"ORRI");
        tagsRevise.put(Integer.valueOf(22),"EORI");
        tagsRevise.put(Integer.valueOf(23),"LSL");
        tagsRevise.put(Integer.valueOf(24),"LSR");

        tagsRevise.put(Integer.valueOf(33),"LDUR");
        tagsRevise.put(Integer.valueOf(34),"STUR");
        tagsRevise.put(Integer.valueOf(35),"LDURSW");
        tagsRevise.put(Integer.valueOf(36),"STURW");
        tagsRevise.put(Integer.valueOf(37),"LDURH");
        tagsRevise.put(Integer.valueOf(38),"STURH");
        tagsRevise.put(Integer.valueOf(39),"LDURB");
        tagsRevise.put(Integer.valueOf(40),"STURB");

        tagsRevise.put(Integer.valueOf(49),"CBZ");
        tagsRevise.put(Integer.valueOf(50),"CBNZ");
        tagsRevise.put(Integer.valueOf(51),"B.cond");
        tagsRevise.put(Integer.valueOf(52),"B");
        tagsRevise.put(Integer.valueOf(53),"BR");
        tagsRevise.put(Integer.valueOf(54),"BL");

        tagsRevise.put(Integer.valueOf(65535),"HALT");
    } 

    private static String intToHex(int i, int length) {
        if (length == 0) return "";

        String str = Integer.toHexString(i);
        int l = str.length();
        for (int k = l; k<length; k++){
            str = "0"+str;
        }
        return str;
    }

    private static Integer toIntStr(String str) {
        if (str.length()>2 && str.charAt(0) == '0' && str.charAt(1) == 'x'){
            str = str.substring(2, str.length());
            return Integer.parseInt(str, 16);
        } else if (str.charAt(0) == 'x'|str.charAt(0) == 'X'){
            str = str.substring(1, str.length());
            return Integer.parseInt(str);
        } else {
            return Integer.parseInt(str);
        }
    }
}