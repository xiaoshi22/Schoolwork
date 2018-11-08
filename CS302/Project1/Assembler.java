import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader; 
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Hashtable;
/**
 * In Assembler class, we write an assmebler to translate assmbly
 * language to hexadecimal code. <p>
 * 
 * Author: Xiaoshi Wang
 */

public class Assembler{

    static boolean with_line_breaks = false;
    static boolean debug            = false;

    static int    wordsize;
    static int    register_cnt;
    static int    max_memory;
    static String build_image;
    static int    position;
    static PrintWriter fileOut = null;

    static Hashtable<String, Integer> tags;

    public static void main(String args[]) {
        initializeTags();

        Scanner fileIn=null; 
        try{
            fileIn = new Scanner(new File(args[0]+".as"));//new FileReader(args[0])); 
            fileOut = new PrintWriter(new FileWriter(args[0]+".o")); 
            position = 0;
            while (fileIn.hasNextLine()){ 
                build_image = fileIn.nextLine();
                if(build_image.trim().equals("")) continue;

                if (build_image.contains(":")){
                    String array[] = build_image.split(":");
                    tags.put(array[0].trim(), Integer.valueOf(position));
                    if (array.length<2 |array[1].trim() == "") continue;
                    build_image = array[1].trim();
                }

                if (build_image.contains(".")){
                    lineWithDots(build_image);
                    continue;
                }

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

                fileOut.write(build_image);
                position +=16;
            }  
        }catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(fileOut != null) // close the scanner
                fileOut.close(); 
            if(fileIn != null) // close the printWriter
                fileIn.close(); 
        }

        try{
            fileIn = new Scanner(new File(args[0]+".o"));//new FileReader(args[0])); 
            fileOut = new PrintWriter(new FileWriter(args[0]+"Version2.o"));

            fileOut.write("      "+fileIn.next()+"\n");
            build_image = fileIn.next();
            int l = build_image.length()-16;
            int k = 0; 
            while(k*16 < l){ 
                fileOut.write("0x"+intToHex(k*8, 3)+" ");
                fileOut.write(build_image.substring(k*16, k*16+16)+"\n");
                k++;
            }  

            fileOut.write("0x"+intToHex(k*8, 3)+" ");
            fileOut.write(build_image.substring(k*16, build_image.length()));
            if (build_image.length()%16!= 0)  printZero(16- build_image.length()%16);
            fileOut.write("\n");
            fileOut.write("      "+"."+"\n");
            fileOut.write("      "+"."+"\n");
            fileOut.write("      "+"."+"\n");
        }catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(fileOut != null) // close the scanner
                fileOut.close(); 
            if(fileIn != null) // close the printWriter
                fileIn.close(); 
        }
        System.out.println("Finish Assembler program for '"+args[0]+".o'.");
    }

    /** method to handle those line with dots */
    private static void lineWithDots(String str) {
        String strArray[] = str.split("\\W+");

        switch(strArray[1]){
            case "wordsize": 
            wordsize = Integer.parseInt(strArray[2]);
            fileOut.write("#hex:WS-"+wordsize+":");
            break;

            case "regcnt": 
            register_cnt = Integer.parseInt(strArray[2]);
            fileOut.write("RC-"+register_cnt+":");
            break;

            case "maxmem": 
            max_memory = (strArray[2].length()-2)*4;
            fileOut.write("MM-"+strArray[2]+"\n");
            break;

            case "pos":
            int nextPos = toIntStr((strArray[2]))*2;
            printZero(nextPos-position);
            position = nextPos;
            break;

            case "aligen":
            int reminder = position%Integer.parseInt(strArray[2]);
            fileOut.write(intToHex(0, reminder));
            position +=reminder;
            break;

            case "double":
            fileOut.write(hexToHex(strArray[2], 16));
            position +=16;
            break;

            case "single":
            fileOut.write(hexToHex(strArray[2], 8));
            position +=8;
            break;

            case "half":
            fileOut.write(hexToHex(strArray[2], 4));
            position +=4;
            break;

            case "byte":
            fileOut.write(hexToHex(strArray[2], 2));
            position +=2;
            break;
        }
    }

     /** method to converge string to integer */
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

     /** method to converge integer to a hex string */
    private static String intToHex(int i, int length) {
        if (length == 0) return "";

        String str = Integer.toHexString(i);
        int l = str.length();
        for (int k = l; k<length; k++){
            str = "0"+str;
        }
        return str;
    }

     /** method to print k zeros */
    private static void printZero(int k){
        while (k>0){
            fileOut.write("0");
            k--;
        }
    }
    
     /** method to enlarge a hex string */
    private static String hexToHex(String str, int length) {
        if (length == 0) return "";
        str= str.substring(2, str.length());
        if(length < str.length()) return str.substring(str.length()-length, str.length());
        int l = str.length();
        for (int k = l; k<length; k++){
            str = "0"+str;
        }
        return str;
    }

     /** initialize hashTable Tags */
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
}