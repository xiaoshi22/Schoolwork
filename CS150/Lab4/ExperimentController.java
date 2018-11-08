import java.util.*;
import java.io.PrintWriter; 
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class operates methods in MyListIntegerContainer and record the run time for 
 * different ways to search an number in a sequence. PrintWriter are used to write an 
 * excel file.
 * 
 * @author Xiaoshi Wang
 * @version 02/20/2017
 */
public class ExperimentController
{
    /** the main method */
    public static void main(String[] args) {
        ExperimentController c = new ExperimentController();

        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileWriter("myConclusion.csv")); // save the output in "myConclusion.csv"

            // print the first line in the file
            pw.write(", timeForSearch, timeForSearchByIteratorst" + System.getProperty("line.separator"));

            // for each line, we record two times with size = 10000*i
            for (int i = 1; i <= 5; i++){
                int inputSize = 1000*i; // it would be better if inputSize = 10000*i
                double timeForSearch = c.timeForSearch(inputSize, 100);
                double timeForSearchByIterator = c.timeForSearchByIterator(inputSize, 100);

                pw.write(inputSize+","+timeForSearch+","+timeForSearchByIterator + System.getProperty("line.separator"));
            }
            System.out.println("Done!"); // print Done! to remind myself I've done my experiment
        } catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(pw != null) // close the printWriter
                pw.close(); 
        } 
    }

    /** run time for linear search */
    private double timeForSearch(int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        
         MyListIntegerContainer c = new MyListIntegerContainer();
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt());// add elements to back
            }
            
            long startTime = System.currentTimeMillis();
            c.search(random.nextInt());

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5; // return the average after five times
    }

   /** run time for search an element by iterator */
    private double timeForSearchByIterator(int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        
         MyListIntegerContainer c = new MyListIntegerContainer();
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt());// add elements to back
            }

            long startTime = System.currentTimeMillis();
            c.searchByIterator(random.nextInt());

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5; // return the average after five times
    }
}