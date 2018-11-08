import java.util.Scanner;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.PrintWriter; 
import java.io.IOException;

/**
 * ReadFile.java <p>
 * 
 * This program will read a shopping list from the current working directory 
 * and create a list of the cheapest product from each series of products in 
 * a new file. <p>
 * 
 * @author Xiaoshi Wang
 */

public class ReadFile{
    /** the main method */
    public static void main(String[] args) {
        for(String fileName : args)          
            readFile( fileName ); 
    }

    /** the run method */
    public static void readFile(String fileName) {
        Scanner fileIn=null; // 
        PrintWriter fileOut = null;
        try{
            fileIn = new Scanner(new FileReader(fileName)); // read input from "file name"
            fileOut = new PrintWriter(new FileWriter("myConclusion.txt")); // save the output in "myConclusion.txt"
            while (fileIn.hasNextLine()){ // while there are some lines left
                String category = fileIn.next(); // the frist word is the category
                String product = fileIn.next();  // the name of the frist product following the category           
                double price = fileIn.nextDouble(); // its price following the name
        
                Scanner restOfLine = new Scanner(fileIn.nextLine()); // then read rest of the line
                while (restOfLine.hasNextLine()){ 
                    restOfLine = new Scanner(restOfLine.nextLine());
                    String newProduct = restOfLine.next(); // new variable for comparision
                    double newPrice = restOfLine.nextDouble();
                    if (newPrice < price){ // if the price is cheaper, we save this product as our cheapest product so far
                        price = newPrice;
                        product = newProduct;
                    }
                }
                // if there is nothing left in this line, print our conclusion and read next line
                fileOut.write("The cheapest " + category + " is " +product + System.getProperty("line.separator"));       
            }
        } catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(fileOut != null) // close the scanner
                fileOut.close(); 
            if(fileIn != null) // close the printWriter
                fileIn.close(); 
        }
    }
}

