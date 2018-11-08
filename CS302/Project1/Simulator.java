import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
/**
 * In Simulator class, we write an CPU to execute a given
 * mechinal code. <p>
 * 
 * Author: Xiaoshi Wang
 */

public class Simulator{
    // instance variables
    static int    wordsize;
    static int    register_cnt;
    static int    max_memory;
    static Scanner fileIn = null;
    static PrintWriter fileOut = null;
    static PrintWriter fileOut2 = null;
    static String fileName;
    static String build_image;
    static ArrayList<String> registers;
    static ArrayList<String> memory;
    static int[] flags  = {0, 0, 0, 0}; // N, E, C, V
    static ArrayList<String> noiseMode;
    static int lineNum;
    static Proessor_Window pw;
    static Memory_Window mw;
    static Visualizer_Window vw;

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater
        (new Runnable() 
            {
                public void run() {
                    pw = new Proessor_Window();
                    mw = new Memory_Window();
                    vw = new Visualizer_Window();

                    activeMouse();
                    fileName = args[0];
                    initializeMemory();
                    registers = new ArrayList<String>();
                    noiseMode = new ArrayList<String>();
                    resetReg();
                }
            });
    }

    /** create mouse listener for all button */
    private static void activeMouse(){
        pw.step.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    stepButton(evt);
                }
            });

        pw.slow.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    slowButton(evt);
                }
            });

        pw.fast.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    fastButton(evt);
                }
            });

        pw.reset.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    resetButton(evt);
                }
            });
        pw.exit.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    exitButton(evt);
                }
            });

        vw.rangeButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    rangeButton(evt);
                }
            });
    }

    /** method to call when step Button is pressed */
    private static void stepButton (MouseEvent evt) {
        if(lineNum == -1){
            pw.NText.setText("0");
            pw.EText.setText("0");
            pw.CText.setText("0");
            pw.VText.setText("0");
            pw.wwText.setText(Integer.toString(wordsize));
            pw.rcText.setText(Integer.toString(register_cnt));
            pw.mmText.setText(Integer.toString(max_memory));
            for(int i = 0; i<register_cnt; i++){
                pw.registerArea2.append("     "+"x"+i+"\n");
            }
            mw.MemoryText.setText("");
        }
        pw.registerArea.setText(printText(registers));
        pw.registerArea3.setText(printText(noiseMode));
        if (lineNum>= memory.size()-1) return;
        lineNum++;
        processLine(lineNum); 
        mw.MemoryText.append("next code is ...\n");
        mw.MemoryText.append(memory.get(lineNum)+"\n");
    }

    /** method to call when slow Button is pressed */
    private static void slowButton (MouseEvent evt) {
        if(lineNum == -1){
            updateFlags();
            pw.wwText.setText(Integer.toString(wordsize));
            pw.rcText.setText(Integer.toString(register_cnt));
            pw.mmText.setText(Integer.toString(max_memory));
            for(int i = 0; i<register_cnt; i++){
                pw.registerArea2.append("     "+"x"+i+"\n");
            }
        }
        pw.registerArea.setText(printText(registers));
        if (lineNum>= memory.size()) return;
        lineNum++;
        processLine(lineNum); 
        mw.MemoryText.append("next code is ...\n");
        mw.MemoryText.append(memory.get(lineNum)+"\n");
    }

    /** method to call when fast Button is pressed */
    private static void fastButton (MouseEvent evt) {
        if(lineNum == -1){
            updateFlags();
            pw.wwText.setText(Integer.toString(wordsize));
            pw.rcText.setText(Integer.toString(register_cnt));
            pw.mmText.setText(Integer.toString(max_memory));
            for(int i = 0; i<register_cnt; i++){
                pw.registerArea2.append("     "+"x"+i+"\n");
            }
        }
        pw.registerArea.setText(printText(registers));
        if (lineNum>= memory.size()) return;
        lineNum++;
        processLine(lineNum); 
        mw.MemoryText.append("next code is ...\n");
        mw.MemoryText.append(memory.get(lineNum)+"\n");
    }

    /** method to call when reset Button is pressed */
    private static void resetButton (MouseEvent evt) {
        resetReg();
        updateFlags();
        pw.registerArea.setText(printText(registers));
    }

    /** method to call when exit Button is pressed */
    private static void exitButton (MouseEvent evt) {
        writeFinalFile();
        resetReg();
        pw.NText.setText("");
        pw.EText.setText("");
        pw.CText.setText("");
        pw.VText.setText("");
        pw.registerArea.setText("");
        pw.registerArea2.setText("");
        pw.registerArea3.setText("");
        pw.wwText.setText("");
        pw.rcText.setText("");
        pw.mmText.setText("");
    }

    /** method to call when range Button is pressed */
    private static void rangeButton(MouseEvent evt){
        int address =  Integer.parseInt(vw.rangeText.getText());
        address = address/8;
        vw.rangeText.setText(memory.get(address));
    }

    /** initialze Memory arrayList */
    private static void initializeMemory(){
        try{
            fileIn = new Scanner(new File(fileName+".o"));//new FileReader(args[0])); 
            build_image = fileIn.next();
            String array[] =build_image.split(":");

            wordsize = Integer.parseInt(array[1].substring(3, array[1].length()));
            register_cnt = Integer.parseInt(array[2].substring(3, array[2].length()));
            max_memory = Integer.parseInt(array[3].substring(5, array[3].length()));

            memory = new ArrayList<String>();
            build_image = fileIn.next();

            int pos = 0; 
            while(pos < max_memory/8){
                if(build_image.charAt(pos)=='F')break;
                memory.add(build_image.substring(pos, pos+16)); 
                pos+=16;
            }
        }catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(fileIn!= null) // close the printWriter
                fileIn.close(); 
        }
    }

    /** initialze registers arrayList */
    private static void resetReg(){
        registers.clear();
        for(int k = 0; k< register_cnt; k++){
            String reg = "";
            for(int h = 0; h< wordsize; h++){
                reg += "0";
            }
            registers.add(reg);
        }

        lineNum = -1;  
    }

    /** initialze noise Mode arrayList */
    private static void resetNoise(){
        noiseMode.clear();
        for(int k = 0; k< register_cnt; k++){
            noiseMode.add("F");
        }
    }

    /** process ith line */
    private static void processLine(int i){
        resetNoise();
        flags[0] = 0;
        flags[1] = 0;
        flags[2] = 0;
        flags[3] = 0;
        if (i>= memory.size()) return;
        String line = memory.get(i);
        switch(line.charAt(0)){
            case '0':
            arthmeticOp(line);
            break;

            case '1':
            logicalOp(line);
            break;

            case '2':
            transferOp(line);
            break;

            case '3':
            conditionalOp(line);
            break;
        }
    }

    /** a helper method for arthmetic Operation */
    private static void arthmeticOp(String str){
        int r1 = Integer.parseInt(str.substring(2, 4),16);
        int r2 = Integer.parseInt(str.substring(4, 6),16);
        int r3 = Integer.parseInt(str.substring(6, 16),16);
        int temp;
        switch(str.charAt(1)){
            case '1': // ADD
            temp = returnReg(r2)+returnReg(r3);
            setReg(r1, temp);
            break;

            case '2': // SUB
            temp = returnReg(r2)-returnReg(r3);
            setReg(r1, temp);
            break;

            case '3': // ADDI
            temp = returnReg(r2)+r3;
            setReg(r1, temp);
            break;

            case '4': // SUBI
            temp = returnReg(r2)-r3;
            setReg(r1, temp);
            break;

            case '5': // ADDS
            temp = returnReg(r2)+returnReg(r3);
            setReg(r1, temp);
            break;

            case '6': // SUBS
            temp = returnReg(r2)-returnReg(r3);
            setReg(r1, temp);
            break;

            case '7': // ADDIS
            temp = returnReg(r2)+r3;
            setReg(r1, temp);
            flags[2]=1;
            break;

            case '8': // SUBIS
            temp = returnReg(r2)-r3;
            setReg(r1, temp);
            break;
        }     
    }

    /** a helper method for logical Operation */
    private static void logicalOp(String str){
        int r1 = Integer.parseInt(str.substring(2, 4),16);
        int r2 = Integer.parseInt(str.substring(4, 6),16);
        int r3 = Integer.parseInt(str.substring(6, 16),16);
        int temp;
        switch(str.charAt(1)){
            case '1': // AND
            temp = returnReg(r2)&returnReg(r3);
            setReg(r1, temp);
            break;

            case '2': // ORR
            temp = returnReg(r2)|returnReg(r3);
            setReg(r1, temp);
            break;

            case '3': // EOR
            temp = returnReg(r2)^returnReg(r3);
            setReg(r1, temp);
            break;

            case '4': //ANDI
            temp = returnReg(r2)&r3;
            setReg(r1, temp);
            break; 

            case '5': // ORRI
            temp = returnReg(r2)|r3;
            setReg(r1, temp);
            break;

            case '6': // EORI
            temp = returnReg(r2)^r3;
            setReg(r1, temp);
            break;

            case '7': // LSL
            temp = returnReg(r2)<<r3;
            setReg(r1, temp);
            break;

            case '8': // LSR
            temp = returnReg(r2)>>r3;
            setReg(r1, temp);
            break;
        }     
    }

    /** a helper method for transfer Operation */
    private static void transferOp(String str){
        int r1 = Integer.parseInt(str.substring(2, 4),16);
        int r2 = Integer.parseInt(str.substring(4, 6),16);
        int r3 = Integer.parseInt(str.substring(6, 16),16);
        int temp;
        switch(str.charAt(1)){
            case '1': // LDUR
            case '3': // LDURSW
            case '5': // LDURH
            case '7': // LDURB
            temp = returnReg(r2+r3);
            setReg(r1, temp);
            break;

            case '2': // STUR
            case '4': // STURW
            case '6': // STURH
            case '8': //STURB
            temp = returnReg(r1);
            setReg(r2+r3, temp);
            break;
        }     
    }

    /** a helper method for conditional Operation */
    private static void conditionalOp(String str){
        int r1;
        int r2 = Integer.parseInt(str.substring(4, 16),16);
        int temp;
        switch(str.charAt(1)){
            case '1': // CBZ
            r1 = Integer.parseInt(str.substring(2, 4),16);
            if (returnReg(r1) == 0) lineNum = r2/16;
            break;

            case '2': // CBNZ
            r1 = Integer.parseInt(str.substring(2, 4),16);
            if (returnReg(r1) != 0) lineNum = r2/16;
            break;

            case '3': // B.cond
            case '4': // B
            case '6': // BL
            lineNum = r2/16;
            break;

            case '5': // BR
            lineNum = returnReg(r2)/16;
            break;
        }     
    }

    /** return ith register */
    private static int returnReg(int i){
        String reg = registers.get(i);
        return Integer.parseInt(reg,2);
    }

    /** set ith register */
    private static void setReg(int i, int value){
        if (i <0) flags[0]=1;
        if(i> Math.pow(2, wordsize)) {
            flags[2] = 1;
            return;
        }
        String str = Integer.toBinaryString(value);
        int l = str.length();
        for (int k = l; k<wordsize; k++){
            str = "0"+str;
        }
        if (!registers.get(i).equals(str)) noiseMode.set(i, "T");
        registers.set(i, str);
    }

    /** print a given arrayList */
    private static String printText(ArrayList list){
        String str = "";
        for(int k = 0; k< list.size(); k++){
            str +=list.get(k);
            str +="\n";
        }
        return str;
    }

    /** update flags */
    private static void updateFlags(){
        pw.NText.setText(Integer.toString(flags[0]));
        pw.EText.setText(Integer.toString(flags[1]));
        pw.CText.setText(Integer.toString(flags[2]));
        pw.VText.setText(Integer.toString(flags[3]));
    }

    /** write two final files */
    private static void writeFinalFile(){
        try{
            fileOut = new PrintWriter(new FileWriter(fileName+"ProcessorState.o")); 
            fileOut2 = new PrintWriter(new FileWriter(fileName+"MainMemory.o")); 
            fileOut.write("Registers: \n");
            fileOut.write(printText(registers));
            fileOut.write("\n");
            fileOut.write("N: "+flags[0]+"\n");
            fileOut.write("Z: "+flags[1]+"\n");
            fileOut.write("C: "+flags[2]+"\n");
            fileOut.write("V: "+flags[3]+"\n");
            fileOut2.write("Main Memory: \n");
            fileOut2.write(printText(memory));
        }catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(fileOut != null || fileOut2 != null){ // close the scanner
                fileOut.close(); 
                fileOut2.close(); 
            }
        }
        System.out.println("You have pressed 'exit' button, and a file named '"+fileName+"ProcessorState.o' and '"+fileName+"MainMemory.o'are just created. ");
    }
}